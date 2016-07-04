package com.freedom.coder.androidsupportlib;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weilongzhang on 16/7/2.
 */
public class MyApplication extends Application {

    private static RequestQueue requestQueue;

    public static String url = "http://baike.baidu.com/api/openapi/BaikeLemmaCardApi";


    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.start();
    }

    public static RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public static JSONObject getJSonRequest() {
        Map<String,String> params = new HashMap<String,String>();
        params.put("scope","103");
        params.put("format","json");
        params.put("appid","379020");
        params.put("bk_key","习近平");
        params.put("bk_length","600");
        try {
            JSONObject jsonObject = new JSONObject(new Gson().toJson(params));
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Map<String,String> getParams() {
        Map<String,String> params = new HashMap<String,String>();
        params.put("scope","103");
        params.put("format","json");
        params.put("appid","379020");
        params.put("bk_key","习近平");
        params.put("bk_length","600");
        return params;
    }

}
