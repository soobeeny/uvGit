package com.example.beeny.uvchecker2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.renderscript.ScriptGroup;
import android.util.Log;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by beeny on 2019-10-21.
 */

public class apiConnect extends Thread{
    URL detectUrl = null;
    HttpsURLConnection conn = null;
    public void run() {
        try {
            String photoUrl = new  String("https://image.shutterstock.com/image-photo/face-beautiful-young-girl-clean-260nw-328676489.jpg");
                    //new String("http://pngimg.com/uploads/face/face_PNG11757.png");
            //Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.face1);
            //String boundary =  "^-----^";

            detectUrl = new URL("https://api-us.faceplusplus.com/facepp/v3/detect");
            conn = (HttpsURLConnection)detectUrl.openConnection();

            //conn.setRequestProperty("Content-Type","multipart/form-data;charset=utf8;boundary="+boundary);
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");

            //DataOutputStream request = new DataOutputStream(conn.getOutputStream());
            //OutputStream outputStream = conn.getOutputStream();
            conn.setDoInput(true);  // InputStream으로 서버로 부터 응답을 받겠다는 옵션.
            conn.setDoOutput(true); //indicates POST method .OutputStream으로 POST 데이터를 넘겨주겠다는 옵션.

            JSONObject params = new JSONObject();
            params.put("api_key","sNNGFcQWX_yI_4T_xNjwzB3-TFeVfxfv");
            params.put("api_secret","m1DP7UblPXs7yDDuHMw-dFC6Ob6BwbgK");
            params.put("image_url",photoUrl);
            //conn.connect();

            OutputStream os = conn.getOutputStream();
            os.write(params.toString().getBytes("utf-8"));
            os.flush();
            /*String data = conn.getInputStream().toString();
            Log.d("Log_T aaaaaa ",data);*/
            InputStream is = conn.getInputStream();
            String result;
            if (is != null)
                result = is.toString();
            else
                result = "dosen't work";
            Log.d("Log_T Result : ",result);

            conn.connect();
            Log.d("Log_T connect?",conn.getResponseMessage());

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                Log.d("Log_T Response", conn.getResponseMessage());
            } else {
                Log.d("Log_T Response Fail", conn.getResponseMessage());
            }
        } catch (Exception e) {
            Log.d("Log_T",e.getMessage());

        } finally {
            conn.disconnect();
        }
    }
}
