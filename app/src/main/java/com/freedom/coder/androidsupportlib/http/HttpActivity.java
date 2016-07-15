package com.freedom.coder.androidsupportlib.http;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.freedom.coder.androidsupportlib.R;

public class HttpActivity extends Activity implements View.OnClickListener{

    private Button mVolley,mOkHttp,mXutils,mRetrofit;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        mVolley = (Button) findViewById(R.id.btn_volley);
        mOkHttp = (Button) findViewById(R.id.btn_okHttp3);
        mXutils = (Button) findViewById(R.id.btn_xUtils);
        mRetrofit = (Button) findViewById(R.id.btn_retrofit2);
        mVolley.setOnClickListener(this);
        mRetrofit.setOnClickListener(this);
        mXutils.setOnClickListener(this);
        mOkHttp.setOnClickListener(this);
        intent = new Intent();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_okHttp3:
                intent.setClass(this, OkHttpActivity.class);
                break;
            case R.id.btn_retrofit2:
                intent.setClass(this, RetrofitActivity.class);
                break;
            case R.id.btn_volley:
                intent.setClass(this, VolleyActivity.class);
                break;
            case R.id.btn_xUtils:
                intent.setClass(this, XUtilsActivity.class);
                break;
        }
        startActivity(intent);
    }
}
