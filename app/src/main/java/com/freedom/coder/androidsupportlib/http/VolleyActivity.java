package com.freedom.coder.androidsupportlib.http;

import android.app.Activity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.freedom.coder.androidsupportlib.MyApplication;

import toast.ToastUtils;

/**
 * Created by weilongzhang on 16/7/2.
 */
public class VolleyActivity extends Activity {

    private final String TAG = this.getClass().getSimpleName();

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = MyApplication.getRequestQueue();
        StringRequest request = new StringRequest(Request.Method.GET, "http://baike.baidu.com/api/openapi/BaikeLemmaCardApi?scope=103&format=json&appid=379020&bk_key=%E9%93%B6%E9%AD%82&bk_length=600", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ToastUtils.showLongToast(VolleyActivity.this,response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);

    }
}
