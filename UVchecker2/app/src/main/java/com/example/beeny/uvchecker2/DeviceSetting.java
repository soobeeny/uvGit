package com.example.beeny.uvchecker2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DeviceSetting extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listView;
    ArrayAdapter<String> adapter;
    //ArrayList<String> datas ;
    View view;
    String category;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_setting);
        String[] datas = {"기기설정","이용약관"};
        listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datas);
        listView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        intent = new Intent(this,BluetoothSetup.class);
        startActivity(intent);
    }
}
