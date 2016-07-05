package com.freedom.coder.androidsupportlib;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

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
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("scope","103");
            jsonObject.put("format","json");
            jsonObject.put("appid","379020");
            jsonObject.put("bk_key","习近平");
            jsonObject.put("bk_length","600");
            return jsonObject;
        }catch (Exception e) {
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
