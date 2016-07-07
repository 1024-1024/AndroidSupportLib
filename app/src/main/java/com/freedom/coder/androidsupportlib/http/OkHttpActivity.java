package com.freedom.coder.androidsupportlib.http;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.freedom.coder.androidsupportlib.MyApplication;
import com.freedom.coder.androidsupportlib.R;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import unicode.UnicodeUtils;
import url.UrlUtils;

/**
 * Created by weilongzhang on 16/7/2.
 */
public class OkHttpActivity extends Activity {

    private TextView mData;
    private static final int RESPONSE_DATA = 0x1;
    private ProgressBar mPb;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        mData = (TextView) findViewById(R.id.tv_data);
        mPb = (ProgressBar) findViewById(R.id.pb1);

        findViewById(R.id.btn_download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                okHttpDownload();
            }
        });
        
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

        findViewById(R.id.btn_upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });

    }

    private void okHttpDownload() {



    }

    private void uploadFile() {
        String IMGUR_CLIENT_ID = "9199fdef135c122";
        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

        MultipartBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title", "Square Logo")
                .addFormDataPart("image", "logo-square.png", RequestBody.create(MEDIA_TYPE_PNG, new File("website/static/logo-square.png")))
                .build();

        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
                .url("https://api.imgur.com/3/image")
                .post(requestBody)
                .build();

        Response response = null;
        try {
            response = MyApplication.getOkHttpClient().newCall(request)
                    .execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code" + request);
            }
            final String resp = response.body().string();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mData.setText(resp);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.arg1) {
                case RESPONSE_DATA:
                    mData.setText(msg.obj.toString());
                    break;
                default:
            }
        }
    };


    private void okHttpGet() {
        final Request request = new Request.Builder().url(UrlUtils.concatUrl(MyApplication.mGetUrl,
                MyApplication.getParams())).build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response resp = MyApplication.getOkHttpClient().newCall(request).execute();
                    String responseString;
                    if (resp.isSuccessful()) {
                        responseString = resp.body().string();
                        responseString = UnicodeUtils.decode1(responseString);
                    } else {
                        responseString = "失败";
                    }
                    Message message = Message.obtain();
                    message.obj = responseString;
                    message.arg1 = RESPONSE_DATA;
                    mHandler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void okHttpPost() {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                HashMap<String, String> params = new HashMap<>();
//                params.put( "Param1", "A" );
//                params.put( "Param2", "B" );
//                // Initialize Builder (not RequestBody)
//                FormBody.Builder builder = new FormBody.Builder();
//                // Add Params to Builder
//                for ( Map.Entry<String, String> entry : params.entrySet() ) {
//                    builder.add( entry.getKey(), entry.getValue() );
//                }
//                // Create RequestBody
//                RequestBody formBody = builder.build();
//                // Create Request (same)
//                Request request = new Request.Builder()
//                        .mGetUrl( "" )
//                        .post( formBody )
//                        .build();

                /**
                 * 普通form表单提交(成功)
                 */
//                RequestBody requestBody = new FormBody.Builder().add("scope", "103").add
//                        ("format", "json").add("appid", "379020").add("bk_key", "习近平").add
//                        ("bk_length", "600").build();

                MediaType jsonType = MediaType.parse("application/json;charset=utf-8");
                RequestBody requestBody = RequestBody.create(jsonType,MyApplication.getPostJsonRequest().toString());
                Request request = new Request.Builder().url(MyApplication.mPostUrl).post(requestBody).build();
                try {
                    final Response response = MyApplication.getOkHttpClient().newCall(request).execute();
                    if (response.isSuccessful()) {
                        final String responseString = UnicodeUtils.decode1(response.body().string());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mData.setText("okhttp post:" + responseString);
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
