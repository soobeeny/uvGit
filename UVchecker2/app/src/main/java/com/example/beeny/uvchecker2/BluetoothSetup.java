package com.example.beeny.uvchecker2;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class BluetoothSetup extends AppCompatActivity {
    TextView btText;
    ImageView btImage;
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_setup);

        // Register for broadcasts when a device is discovered.
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(receiver, filter);


        BluetoothAdapter ap = BluetoothAdapter.getDefaultAdapter();
        if(ap == null)
            toast = Toast.makeText(this,"bluetooth를 지원하지 않습니다.",Toast.LENGTH_SHORT);

        if(!ap.isEnabled()){
            Intent bIntent = new Intent (BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(bIntent,5 );
            ap.enable(); //블루투스 강제 활성화
        }
        /*
        기기가 검색 가능한 경우 기기 이름, 클래스 및 고유 MAC 주소와 같은 정보를 공유하여 검색 요청에 응답합니다. 이때 이 정보를 사용하여 검색 프로세스를 실행하는 기기가 검색된 기기에 연결을 시작하도록 선택할 수 있습니다.

원격 기기와 처음으로 연결되면 페어링 요청이 자동으로 사용자에게 제공됩니다. 기기가 페어링되면 해당 기기에 대한 기본 정보(예: 기기 이름, 클래스 및 MAC 주소)는 저장되고 Bluetooth API를 사용하여 읽을 수 있습니다. 원격 기기에 대해 알려진 MAC 주소를 사용하면 (기기가 범위 내에 있다고 가정했을 때) 검색을 수행하지 않고 언제든지 연결을 시작할 수 있습니다.

페어링된 것과 연결된 것에는 차이점이 있습니다.

페어링은 두 기기가 서로의 존재를 알고, 인증에 사용할 수 있는 공유 링크 키를 가지고 있으며, 서로 암호화된 연결을 설정할 수 있다는 것을 의미합니다.
연결은 기기가 현재 RFCOMM 채널을 공유하고 있고 데이터를 서로 전송할 수 있다는 것을 의미합니다. 현재 Android Bluetooth API는 RFCOMM 연결을 설정할 수 있기 전에 기기를 페어링하도록 요청합니다. Bluetooth API와 암호화된 연결을 시작하면 페어링이 자동으로 실행됩니다.
        * */

        //페어링된 기기 목록 가져오기 - 원하는 기기가 이미 있는지
        Set<BluetoothDevice> devices = ap.getBondedDevices(); // 페어링 된 기기를 나타내는 BluetoothDevide객체 반환. 이름과 MAC주소
        if(devices.size() > 0){
            /*Iterator<BluetoothDevice> iter = devices.iterator();
            while(iter.hasNext()){ //for (BluetoothDevice device : devices
                BluetoothDevice device = iter.next();
                deviceArray[iter] = d.getName();
            }*/
            for (BluetoothDevice device : devices){
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); //get the MAC address
                Log.d("Log_T deviceName: ",deviceName);
                Log.d("Log_T device Address : ",deviceHardwareAddress);
            }
        }
    }
    //기기 검색
    // Create a BroadcastReceiver for ACTION_FOUND.
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Discovery has found a device. Get the BluetoothDevice
                // object and its info from the Intent.
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
                Log.d("Log_T device ",deviceName);
            }
        }
    };

/*
    private class AcceptThread extends Thread {
        private final BluetoothServerSocket mmServerSocket;

        public AcceptThread() {
            // Use a temporary object that is later assigned to mmServerSocket
            // because mmServerSocket is final.
            BluetoothServerSocket tmp = null;
            try {
                // MY_UUID is the app's UUID string, also used by the client code.
                tmp = bluetoothAdapter.listenUsingRfcommWithServiceRecord(NAME, MY_UUID);
            } catch (IOException e) {
                Log.e(TAG, "Socket's listen() method failed", e);
            }
            mmServerSocket = tmp;
        }

        public void run() {
            BluetoothSocket socket = null;
            // Keep listening until exception occurs or a socket is returned.
            while (true) {
                try {
                    socket = mmServerSocket.accept();
                } catch (IOException e) {
                    Log.e(TAG, "Socket's accept() method failed", e);
                    break;
                }

                if (socket != null) {
                    // A connection was accepted. Perform work associated with
                    // the connection in a separate thread.
                    manageMyConnectedSocket(socket);
                    try {
                        mmServerSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }

        // Closes the connect socket and causes the thread to finish.
        public void cancel() {
            try {
                mmServerSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "Could not close the connect socket", e);
            }
        }
    }
*/
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Don't forget to unregister the ACTION_FOUND receiver.
        unregisterReceiver(receiver);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch(requestCode){
            case 5:
                if( resultCode == RESULT_OK)
                    toast =Toast.makeText(this,"bluetooth가 활성화 되었습니다.",Toast.LENGTH_SHORT);
                else if (resultCode == RESULT_CANCELED)
                    toast =Toast.makeText(this,"bluetooth가 활성화 되지 못하였습니다.",Toast.LENGTH_SHORT);

        }
    }

}
