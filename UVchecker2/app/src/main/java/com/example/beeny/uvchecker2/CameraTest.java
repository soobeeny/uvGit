package com.example.beeny.uvchecker2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class CameraTest extends AppCompatActivity implements View.OnClickListener{

    ImageView imageView;
    Bitmap bitmap;
    String timeStamp;
    Button buttonOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_test);
        imageView = (ImageView) findViewById(R.id.cameraTestView);
        connectToCamera();
        timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        buttonOk = (Button)findViewById(R.id.cameraOk);
        buttonOk.setOnClickListener(this);
    }

    public void connectToCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 30);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //bitmap = (Bitmap) data.getExtras().get("data");

        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
    }
    private void saveBitmapToJpeg(Bitmap bitmap,String name){
        //String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        //비트맵을 캐시에 저장하는 함수
        File storage = getCacheDir(); //내부 저장소 캐시 경로를 받아옵니다.
        String fileName =  name + ".jpg"; //저장할 파일 이름
        File tempFile = new File(storage,fileName);

        try{
            tempFile.createNewFile(); //자동으로 빈 파일 생성
            FileOutputStream out = new FileOutputStream(tempFile);//파일을 쓸 수 있는 스트림 준비
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,out);//compress 함수를 사용해 스트림에 비트맵 저장
            out.close(); //스트림 사용 후 닫아줌.
        }
        catch (FileNotFoundException e){
            Log.e("MyTag","FileNotFoundException : " + e.getMessage());
        }catch (IOException e){
            Log.e("MyTag","IOException : " + e.getMessage());
        }
    }
    private void getBitmapFromCacheDir() {

        //blackJin 이 들어간 파일들을 저장할 배열 입니다.
        ArrayList<String> UVDatas = new ArrayList<>();
        File file = new File(getCacheDir().toString());
        File[] files = file.listFiles();

        for(File tempFile : files) {

            Log.d("MyTag",tempFile.getName());

            //blackJin 이 들어가 있는 파일명을 찾습니다.
            if(tempFile.getName().contains("2019")) {
                UVDatas.add(tempFile.getName());
            }
        }
        Log.e("MyTag","UVDatas size : " + UVDatas.size());
        if(UVDatas.size() > 0) {
            int randomPosition = new Random().nextInt(UVDatas.size());

            //UVDatas 배열에 있는 파일 경로 중 하나를 랜덤으로 불러옵니다.
            String path = getCacheDir() + "/" + UVDatas.get(randomPosition);

            //파일경로로부터 비트맵을 생성합니다.
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onClick(View view) {
        if(view == buttonOk){
            //detect하는거 불러서 사진이랑 정보 저장해야 할듯
            saveBitmapToJpeg(bitmap,timeStamp); //Ok하면 저장
            Log.d("Log_T timeStamp: ",timeStamp);

            getBitmapFromCacheDir();
            new apiConnect().start();
        }
    }
}
