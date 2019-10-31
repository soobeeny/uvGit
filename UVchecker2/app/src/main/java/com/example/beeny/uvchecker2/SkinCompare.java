package com.example.beeny.uvchecker2;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class SkinCompare extends Fragment {
    TextView textView ;
    View view;

    public SkinCompare() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_skin_compare, container, false);
        //connectToDetect();

        return view;
    }

    public void onResume(){
        super.onResume();

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        if(isVisibleToUser){
            //사용자한테 보일 때
            new apiConnect().start();
        }
        else{
            //사용자한테 안 보일 때
        }
       super.setUserVisibleHint(isVisibleToUser);
    }
    @Override
    public void onDestroyView() {
        Log.d("Log_T destroy","DestroyView(skinCompare)");
        super.onDestroyView();
    }
    public void connectToDetect(){
        URL detectUrl = null;
        HttpURLConnection conn = null;
        try {
            String photoUrl = new String("http://pngimg.com/uploads/face/face_PNG11757.png");
            Bitmap bitmap = BitmapFactory.decodeResource(getActivity().getApplicationContext().getResources(),R.drawable.face1);
            String boundary =  "^-----^";
            detectUrl = new URL("https://api-us.faceplusplus.com/facepp/v3/detect");
            conn = (HttpURLConnection)detectUrl.openConnection();

            //conn.setRequestProperty("Content-Type","multipart/form-data;charset=utf8;boundary="+boundary);
            conn.setRequestProperty("Contet-Type","application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            Log.d("Log_T 실행하나?","ffaaaaaaa");

            DataOutputStream request = new DataOutputStream(conn.getOutputStream());
            OutputStream outputStream = conn.getOutputStream();

            conn.setRequestProperty("api_key","sNNGFcQWX_yI_4T_xNjwzB3-TFeVfxfv");
            conn.setRequestProperty("api_secret","m1DP7UblPXs7yDDuHMw-dFC6Ob6BwbgK");
            conn.setRequestProperty("image_url",photoUrl);
            //conn.setRequestProperty("image_file",bitmap);
            conn.setDoOutput(true); //indicates POST method

            //OutputStream outputPost = new BufferedOutputStream(conn.getOutputStream());

            if(conn.getResponseCode() == 200){
                Log.d("Log_T Respose Success",conn.getResponseMessage());
            }else {
                Log.d("Log_T Response Fail",conn.getResponseMessage());
            }
        }catch (Exception e){

        }finally {
            if(conn != null)
                conn.disconnect();
        }
    }
    public void connectToAnalyze (){
        URL analyzeUrl = null;
        HttpURLConnection conn = null;
        try {
            analyzeUrl = new URL("https://api-us.faceplusplus.com/facepp/v3/face/analyze");
            conn =(HttpURLConnection) analyzeUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("api_key","sNNGFcQWX_yI_4T_xNjwzB3-TFeVfxfv");
            conn.setRequestProperty("api_secret","m1DP7UblPXs7yDDuHMw-dFC6Ob6BwbgK");
            conn.setRequestProperty("face_tokens","1");//?
            conn.setRequestProperty("return_attributes","gender,age,skinstatus");
            conn.setDoOutput(true);

            OutputStream outputPost = new BufferedOutputStream(conn.getOutputStream());
            //writeStream(outputPost);

        } catch (Exception e) {
            e.printStackTrace();
            //MalformedURLException AND IOException,, catch문 따로 써주면 됨.
        }finally{
            if(conn!= null)
                conn.disconnect();
        }

    }
}
