package com.freedom.coder.androidsupportlib.http;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.freedom.coder.androidsupportlib.MyApplication;
import com.freedom.coder.androidsupportlib.R;

import org.json.JSONObject;

import url.UrlUtils;

/**
 * Created by weilongzhang on 16/7/2.
 */
public class VolleyActivity extends Activity {

    private final String TAG = this.getClass().getSimpleName();

    private RequestQueue requestQueue;
    private TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        data = (TextView) findViewById(R.id.tv_data);
        requestQueue = MyApplication.getRequestQueue();
    }


    public void get(View view) {

        StringRequest request = new StringRequest(Request.Method.GET, UrlUtils.concatUrl
                (MyApplication.url, MyApplication.getParams()), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                data.setText("get:"+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }

    public void post(View view) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, MyApplication.url, MyApplication.getJSonRequest(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                data.setText("post:"+response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }

}
