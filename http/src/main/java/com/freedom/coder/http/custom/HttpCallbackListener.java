package com.freedom.coder.http.custom;

/**
 * Created by weilongzhang on 16/6/7.
 */
public abstract class HttpCallbackListener {

    public abstract void onSuccess(int code, HttpResponse successData);

    public abstract void onError(int errorCode, HttpResponse successData);

    protected abstract HttpResponse onCache(String cacheData);

}
