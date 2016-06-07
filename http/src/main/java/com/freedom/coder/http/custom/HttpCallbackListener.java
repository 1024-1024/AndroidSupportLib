package com.freedom.coder.http.custom;

/**
 * Created by weilongzhang on 16/6/7.
 */
public interface HttpCallbackListener {

    void onSuccess(String successData);
    void onError(int errorCode);
    void onCache(String cacheData);

}
