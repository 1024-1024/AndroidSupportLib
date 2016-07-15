package com.freedom.coder.androidsupportlib.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.freedom.coder.androidsupportlib.R;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by weilongzhang on 16/7/15.
 *
 * @link https://gank.io/post/560e15be2dca930e00da1083
 *
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

    }


    private Observer<String> observer = new Observer<String>() {
        @Override
        public void onCompleted() {
            Log.d("zwl","onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            Log.d("zwl","onError");
        }

        @Override
        public void onNext(String s) {
            Log.d("zwl","onNext:" + s);
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
