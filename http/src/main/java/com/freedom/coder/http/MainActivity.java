package com.freedom.coder.http;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.freedom.coder.http.custom.HttpCallbackListener;
import com.freedom.coder.http.custom.HttpResponse;
import com.freedom.coder.http.custom.HttpSender;
import com.freedom.coder.http.custom.bean.UrlCacheBean;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UrlCacheBean urlCacheBean = new UrlCacheBean();
        urlCacheBean.setUrl("http://api.map.baidu" +
                ".com/telematics/v3/weather?location=%E5%8C%97%E4%BA%AC&output=json&ak" +
                "=5slgyqGDENN7Sy7pw29IUvrZ");

        HttpSender.sendGet(this, urlCacheBean, null, true, new HttpCallbackListener() {


            @Override
            public void onSuccess(int code, HttpResponse successData) {
                Log.d("zwl", code + successData.getResponseBean().getData());
                Toast.makeText(getApplication(), successData.getResponseBean().getData(), Toast
                        .LENGTH_SHORT).show();
            }

            @Override
            public void onError(int errorCode, HttpResponse successData) {
                Log.d("zwl", errorCode + successData.getResponseBean().getData());
                Toast.makeText(getApplication(), successData.getResponseBean().getData(), Toast
                        .LENGTH_SHORT).show();
            }

            @Override
            protected HttpResponse onCache(String cacheData) {
                return null;
            }
        });
    }
}
