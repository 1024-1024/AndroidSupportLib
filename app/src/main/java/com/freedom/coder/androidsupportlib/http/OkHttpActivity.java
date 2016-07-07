package com.freedom.coder.androidsupportlib.http;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.freedom.coder.androidsupportlib.MyApplication;
import com.freedom.coder.androidsupportlib.R;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;
import url.UrlUtils;

/**
 * Created by weilongzhang on 16/7/2.
 */
public class OkHttpActivity extends Activity {
    private TextView mData;
    private static final int RESPONSE_DATA = 0x1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        mData = (TextView) findViewById(R.id.tv_data);

        findViewById(R.id.btn_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                okHttpGet();
            }
        });

        findViewById(R.id.btn_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                okHttpPost();
            }
        });

    }

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.arg1) {
                case RESPONSE_DATA:
                    try {
                        mData.setText(((Response)msg.obj).body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
            }
        }
    };


    private void okHttpGet() {
        final Request request = new Request.Builder().url(UrlUtils.concatUrl(MyApplication.url,
                MyApplication.getParams())).build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response resp = MyApplication.getOkHttpClient().newCall(request).execute();
                    Message message = Message.obtain();
                    message.obj = resp;
                    message.arg1 = RESPONSE_DATA;
                    mHandler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    private void okHttpPost() {

    }
}
