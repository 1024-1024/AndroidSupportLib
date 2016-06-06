package com.freedom.coder.http.custom;

import android.os.AsyncTask;

import com.freedom.coder.http.custom.bean.UrlCacheBean;

import java.util.Map;

/**
 * Created by weilongzhang on 16/6/2.
 */
public class HttpSender {

    public static void sendPost(UrlCacheBean urlCacheBean, Map paramMap, final boolean
            showLoading) {
        boolean needCache = urlCacheBean.isNeedCache();
        final String url = urlCacheBean.getUrl();

        if (needCache) {
            //从缓存中取数据
            new AsyncTask<String, Void, UrlCacheBean>() {

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    if (showLoading) {

                    }
                }

                @Override
                protected UrlCacheBean doInBackground(String... params) {
                    UrlCacheBean cacheBean = HttpCacheUtil.getInstance().getCacheBean(params[0]);
                    return cacheBean;
                }

                @Override
                protected void onPostExecute(UrlCacheBean urlCacheBean) {
                    super.onPostExecute(urlCacheBean);

                    if (urlCacheBean == null) {
                        getDataFromServer(url);
                    }

                }

            }.execute(url);

        } else {
            getDataFromServer(url);
        }
    }

    private static void getDataFromServer(String url) {

    }

    public static void sendGet(UrlCacheBean urlCacheBean, Map paramMap) {

    }

}
