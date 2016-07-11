package com.freedom.coder.androidsupportlib;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit.Retrofit;


/**
 * Created by weilongzhang on 16/7/2.
 */
public class MyApplication extends Application {

    private static OkHttpClient mOkHttpClient;
    private static RequestQueue mRequestQueue;
    private static Retrofit mRetrofit;
    public static String mGetUrl = "http://baike.baidu.com/api/openapi/BaikeLemmaCardApi/";
    public static String mPostUrl = "http://staging.qraved.com:8033/app/home/timeline/";

    @Override
    public void onCreate() {
        super.onCreate();
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient();
        }
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(this);
            mRequestQueue.start();
        }
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder().baseUrl(mGetUrl).build();

        }

    }

    public static RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public static OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public static Retrofit getRetrofit() {
        return mRetrofit;
    }

    public static JSONObject getPostJsonRequest() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cityId", 1);
            jsonObject.put("max", 10);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static String getPostParams() {
        StringBuilder params = new StringBuilder();
        try {
            params.append(URLEncoder.encode("cityId", "utf-8")).append("=").append(URLEncoder
                    .encode("1", "utf-8")).append(URLEncoder.encode("max", "utf-8")).append("=")
                    .append(URLEncoder.encode("10", "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return params.toString();
    }


    public static JSONObject getJSonRequest() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("scope", "103");
            jsonObject.put("format", "json");
            jsonObject.put("appid", "379020");
            jsonObject.put("bk_key", "习近平");
            jsonObject.put("bk_length", "600");
            return jsonObject;
        } catch (Exception e) {
            return null;
        }
    }

    public static Map<String, String> getParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("scope", "103");
        params.put("format", "json");
        params.put("appid", "379020");
        params.put("bk_key", "习近平");
        params.put("bk_length", "600");
        return params;
    }

    public static String getGetParams() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(URLEncoder.encode("scope", "utf-8")).append("=").append
                    (URLEncoder.encode("103", "utf-8")).append(URLEncoder.encode("format",
                    "utf-8")).append("=").append(URLEncoder.encode("json", "utf-8")).append
                    (URLEncoder.encode("appid", "utf-8")).append("=").append(URLEncoder.encode
                    ("379020", "utf-8")).append(URLEncoder.encode("bk_key", "utf-8")).append("=")
                    .append(URLEncoder.encode("习近平", "utf-8")).append(URLEncoder.encode
                    ("bk_length", "utf-8")).append("=").append(URLEncoder.encode("600", "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

}
