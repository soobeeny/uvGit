package com.example.beeny.uvchecker2;


import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class photoRegist extends Fragment {

    ImageView resultImageView;
    View view;
    public photoRegist() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_photo_regist, container, false);
        super.onCreate(savedInstanceState);
        resultImageView = (ImageView)view.findViewById(R.id.registrationResult);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    public void onResume() {
        super.onResume();

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        if(isVisibleToUser){
            //사용자한테 보일 때
            sendTakePhotoIntent();
        }
        else{
            //사용자한테 안 보일 때
        }
        super.setUserVisibleHint(isVisibleToUser);
    }
    @Override
    public void onDestroyView() {
        Log.d("Log_T destroy","DestroyView(photoRegist)");
        super.onDestroyView();
    }
    private void sendTakePhotoIntent(){
        Intent takePicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePicIntent.resolveActivity(getActivity().getPackageManager())!= null){
            startActivityForResult(takePicIntent,30);
        }

/*
        if(takePicIntent.resolveActivity(getActivity().getPackageManager())!= null){
            File photoFile = null;
            try{
                photoFile = createImageFile();
            }catch (IOException e){
                Log.d("Log_tag", e.getMessage());
            }

            if(photoFile != null){
                photoUri = FileProvider.getUriForFile(getActivity(),getActivity().getPackageName(),photoFile);
                takePicIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
                //이미지가 저장될 Uri를 함께 넘김.
                startActivityForResult(takePicIntent,30);
            }
        }*/
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,Intent data ){
        super.onActivityResult(requestCode,resultCode,data); ///???
        if(requestCode == 30 && resultCode == RESULT_OK) { //-rusult ok1인데?
            /*Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            saveToInternalStorage(imageBitmap);
            */
            //resultImageView.setImageBitmap(imageBitmap);
            //Log.d("Log_T PhotoURl",""+photoUri);

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;

            //resultImageView.setImageURI(photoUri);

            Bitmap bitmap = (Bitmap) data.getExtras().get("data");

            if (bitmap != null) {
                resultImageView.setImageBitmap(bitmap);
                //bitmap = BitmapFactory.decodeFile(file)
            }

        }
    }

    private String imageFilePath;
    private Uri photoUri;

    private File createImageFile() throws IOException{ //이미지 파일 생성 메소드
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "UV_"+timeStamp+"_";
        File storgaeDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,".jpg",storgaeDir
        );
        imageFilePath = image.getAbsolutePath();
        Log.d("Log_tag",imageFilePath);
        return image;
    }

    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getActivity().getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }
}
