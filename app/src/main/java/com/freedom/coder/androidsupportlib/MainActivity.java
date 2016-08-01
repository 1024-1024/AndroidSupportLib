package com.freedom.coder.androidsupportlib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.freedom.coder.androidsupportlib.designpattern.DesignPatternActivity;
import com.freedom.coder.androidsupportlib.http.HttpActivity;
import com.freedom.coder.androidsupportlib.imageloader.ImageLoaderActivity;
import com.freedom.coder.androidsupportlib.rxjava.RxJavaActivity;

/**
 * Created by weilongzhang on 16/7/15.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void http(View view) {
        Intent intent = new Intent(this, HttpActivity.class);
        startActivity(intent);
    }

    public void rxJava(View view) {
        Intent intent = new Intent(this, RxJavaActivity.class);
        startActivity(intent);
    }

    public void imageLoader(View view) {
        Intent intent = new Intent(this, ImageLoaderActivity.class);
        startActivity(intent);
    }

    public void designPattern(View view) {
        Intent intent = new Intent(this, DesignPatternActivity.class);
        startActivity(intent);
    }

}
