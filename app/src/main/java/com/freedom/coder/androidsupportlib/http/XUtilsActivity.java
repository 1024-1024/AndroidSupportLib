package com.freedom.coder.androidsupportlib.http;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.freedom.coder.androidsupportlib.R;

/**
 * Created by weilongzhang on 16/7/2.
 */
public class XUtilsActivity extends Activity {

    private TextView mTvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xutils);

        mTvData = (TextView) findViewById(R.id.tv_data);

        findViewById(R.id.btn_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xUtilsGet();
            }
        });

        findViewById(R.id.btn_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xUtilsPost();
            }
        });
    }

    private void xUtilsGet() {
    }

    private void xUtilsPost() {

    }
}
