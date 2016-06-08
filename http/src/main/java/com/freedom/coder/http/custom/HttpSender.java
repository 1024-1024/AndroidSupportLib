package com.freedom.coder.http.custom;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.freedom.coder.http.custom.bean.UrlCacheBean;

import java.util.Map;

/**
 * Created by weilongzhang on 16/6/2.
 */
public class HttpSender {

    public static void sendPost(final UrlCacheBean urlCacheBean, final Map<String, String>
            paramMap, final boolean showLoading, final HttpCallbackListener listener) {
        HttpRequest request = new HttpRequest();
        request.setUrlCacheBean(urlCacheBean);
        request.setRequestMehtod(HttpUtils.RequestMehtod.POST);
        request.setLoading(showLoading);
        request.setListener(listener);
        request.setParam(paramMap);
        sendRequest(request);
    }

    public static void sendGet(UrlCacheBean urlCacheBean, Map<String, String> paramMap, final
    boolean showLoading, final HttpCallbackListener listener) {
        HttpRequest request = new HttpRequest();
        request.setUrlCacheBean(urlCacheBean);
        request.setRequestMehtod(HttpUtils.RequestMehtod.GET);
        request.setLoading(showLoading);
        request.setListener(listener);
        request.setParam(paramMap);
        sendRequest(request);
    }


    private static void sendRequest(final HttpRequest request) {
        boolean needCache = request.getUrlCacheBean().isNeedCache();
        final String url = request.getUrlCacheBean().getUrl();
        if (needCache) {
            //从缓存中取数据
            new AsyncTask<String, Void, Void>() {

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    if (request.isLoading()) {

                    }
                }

                @Override
                protected Void doInBackground(String... params) {
                    UrlCacheBean cacheBean = HttpCacheUtils.getInstance().getCacheBean(params[0]);
                    if (TextUtils.isEmpty(cacheBean.getData())) {
                        HttpUtils.sendHttpRequest(request.getRequestMehtod(), params[0], request
                                .getParam(), request.getListener());
                    } else {
                        if (request.getListener() != null) {
                            request.getListener().onCache(cacheBean.getData());
                        }
                    }
                    return null;
                }
            }.execute(url);
        } else {
            HttpUtils.sendHttpRequest(HttpUtils.RequestMehtod.POST, url, request.getParam(),
                    request.getListener());
        }
    }

}
