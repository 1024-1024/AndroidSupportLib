package com.freedom.coder.androidsupportlib.rxjava;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.freedom.coder.androidsupportlib.R;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by weilongzhang on 16/7/15.
 *
 * https://gank.io/post/560e15be2dca930e00da1083
 * http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2016/0429/4196.html
 */
public class RxJavaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);

        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        });

        observable.subscribe(observer);

        final int drawableRes = R.mipmap.ic_launcher;
        final ImageView imageView = (ImageView) findViewById(R.id.iv);
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getTheme().getDrawable(drawableRes);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onNext(Drawable drawable) {
                        imageView.setImageDrawable(drawable);
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(RxJavaActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private Observer<String> observer = new Observer<String>() {
        @Override
        public void onCompleted() {
            Log.d("zwl", "onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            Log.d("zwl", "onError");
        }

        @Override
        public void onNext(String s) {
            Log.d("zwl", "onNext:" + s);
        }
    };


    private Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {

        }
    };
}
