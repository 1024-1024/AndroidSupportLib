package com.freedom.coder.androidsupportlib.http;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.freedom.coder.androidsupportlib.R;

import java.util.Hashtable;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;

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

        new Thread(new Runnable() {
            @Override
            public void run() {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.github.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(new com.squareup.okhttp.OkHttpClient())
                        .build();
                GitHub gitHubService = retrofit.create(GitHub.class);
                Call<List<Contributor>> call = gitHubService.contributors("square", "retrofit");
//                try{
//                    Response<List<Contributor>> response = call.execute(); // 同步
//                    Log.d("zwl", "response:" + response.body().toString());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                call.enqueue(new Callback<List<Contributor>>() {
                    @Override
                    public void onResponse(retrofit.Response<List<Contributor>> response, Retrofit retrofit) {
                        if (response.isSuccess()) {
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
                    public void onFailure(Throwable t) {
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
        }).start();


    }

    private void retrofitPost() {

    }

    public static class Contributor {
        public final String login;
        public final int contributions;
        public Contributor(String login, int contributions) {
            this.login = login;
            this.contributions = contributions;
        }
        @Override
        public String toString() {
            return "Contributor{" +
                    "login='" + login + '\'' +
                    ", contributions=" + contributions +
                    '}';
        }
    }
    public interface GitHub {
        @GET("/repos/{owner}/{repo}/contributors")
        Call<List<Contributor>> contributors(
                @Path("owner") String owner,
                @Path("repo") String repo);
    }
}
