package com.freedom.coder.androidsupportlib.http;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.freedom.coder.androidsupportlib.MyApplication;
import com.freedom.coder.androidsupportlib.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by weilongzhang on 16/7/2.
 */
public class RetrofitActivity extends Activity {

    private TextView mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        mData = (TextView) findViewById(R.id.tv_data);

        findViewById(R.id.btn_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofitGet();
            }
        });

        findViewById(R.id.btn_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofitPost();
            }
        });

    }

    private void retrofitGet() {

        Retrofit retrofit = MyApplication.getRetrofit();
        GetData getData = retrofit.create(GetData.class);
        Call<String> call = getData.getDatas(MyApplication.getGetParams());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful()) {
                    final String string = response.body().toString();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mData.setText(string);
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                final String string = t.getLocalizedMessage();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mData.setText(string);
                    }
                });
            }
        });

    }

    private void retrofitPost() {

    }


    interface GetData {
        @GET("/{param}")
        Call<String> getDatas(@Path("param") String param);
    }
}
