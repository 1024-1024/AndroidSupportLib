package com.freedom.coder.androidsupportlib.imageloader;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.freedom.coder.androidsupportlib.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Created by weilongzhang on 16/7/16.
 */
public class ImageLoaderActivity extends Activity {

    private ImageView mImageView;
    private DisplayImageOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageloader);

        options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .showImageOnLoading(R.mipmap.loading)
                .showImageOnFail(R.mipmap.loading_fail)
                .displayer(new RoundedBitmapDisplayer(10,10)).build();

        mImageView = (ImageView) findViewById(R.id.iv);
        String url = "http://www.fwjia" +
                ".com/thumb/d/file/2013-08-23/91c4c30a965c925c9393b426ee931612__450.jpg";
        ImageLoader.getInstance().displayImage(url, mImageView, options);
    }
}
