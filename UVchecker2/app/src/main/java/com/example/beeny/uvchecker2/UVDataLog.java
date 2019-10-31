package com.example.beeny.uvchecker2;


import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * A simple {@link Fragment} subclass.
 */
public class UVDataLog extends Fragment {
    View view;
    TextView textView;
   /* URL url = new URL("https://api-us.faceplusplus.com/facepp/v3/detect");
    HttpsURLConnection myConn = (HttpsURLConnection) url.openConnection();
*/

    /*String url = "https://api-us.faceplusplus.com/facepp/v3/detect";

    public UVDataLog() throws IOException {
        // Required empty public constructor
    }

    RequestQueue queue = Volley.newRequestQueue(getActivity());


    StringRequest detectRequest = new StringRequest(
            Request.Method.POST, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.i("onResponse:", "in response Write");
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.i("error response", error.getMessage());
        }
    }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String> params = new HashMap<>();
            params.put("api_key","sNNGFcQWX_yI_4T_xNjwzB3-TFeVfxfv");
            params.put("api.secret","m1DP7UblPXs7yDDuHMw-dFC6Ob6BwbgK");
            params.put("face_token","1");

            return params;
        }
    };
    queue.add(detectRequest);
    */
   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_uvdata_log, container, false);
        textView = (TextView) view.findViewById(R.id.connTest);

        return view;
    }

}
