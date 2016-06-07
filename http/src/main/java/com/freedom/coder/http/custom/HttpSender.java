package com.freedom.coder.http.custom;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.freedom.coder.http.custom.bean.UrlCacheBean;

import java.util.HashMap;

/**
 * Created by weilongzhang on 16/6/2.
 */
public class HttpSender {

    public static void sendPost(final UrlCacheBean urlCacheBean, final HashMap<String, String>
            paramMap, final boolean showLoading, final HttpCallbackListener listener) {
        boolean needCache = urlCacheBean.isNeedCache();
        final String url = urlCacheBean.getUrl();

        if (needCache) {
            //从缓存中取数据
            new AsyncTask<String, Void, Void>() {

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    if (showLoading) {

                    }
                }

                @Override
                protected Void doInBackground(String... params) {
                    UrlCacheBean cacheBean = HttpCacheUtils.getInstance().getCacheBean(params[0]);
                    if (TextUtils.isEmpty(cacheBean.getData())) {
                        HttpUtils.sendHttpRequest(HttpUtils.RequestMehtod.POST, params[0],
                                paramMap, listener);
                    } else {
                        if (listener != null) {
                            listener.onCache(cacheBean.getData());
                        }
                    }
                    return null;
                }
            }.execute(url);
        } else {
            HttpUtils.sendHttpRequest(HttpUtils.RequestMehtod.POST, url, paramMap, listener);
        }
    }

    public static void sendGet(UrlCacheBean urlCacheBean, HashMap<String, String> paramMap) {

    }

}
