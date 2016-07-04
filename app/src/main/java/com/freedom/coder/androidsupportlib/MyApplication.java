package com.freedom.coder.androidsupportlib;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by weilongzhang on 16/7/2.
 */
public class MyApplication extends Application {

    private static RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.start();
    }

    public static RequestQueue getRequestQueue() {
        return requestQueue;
    }

}
