package com.freedom.coder.http.custom;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.text.TextUtils;

import com.freedom.coder.http.custom.bean.UrlCacheBean;

import java.util.Map;

/**
 * Created by weilongzhang on 16/6/2.
 */
public class HttpSender {

    public static void sendPost(Context context,final UrlCacheBean urlCacheBean, final Map<String, String>
            paramMap, final boolean showLoading, final HttpCallbackListener listener) {
        HttpRequest request = new HttpRequest();
        request.setUrlCacheBean(urlCacheBean);
        request.setRequestMehtod(HttpUtils.RequestMehtod.POST);
        request.setLoading(showLoading);
        request.setListener(listener);
        request.setParam(paramMap);
        sendRequest(context,request);
    }

    public static void sendGet(Context context, UrlCacheBean urlCacheBean, Map<String, String> paramMap, final
    boolean showLoading, final HttpCallbackListener listener) {
        HttpRequest request = new HttpRequest();
        request.setUrlCacheBean(urlCacheBean);
        request.setRequestMehtod(HttpUtils.RequestMehtod.GET);
        request.setLoading(showLoading);
        request.setListener(listener);
        request.setParam(paramMap);
        sendRequest(context,request);
    }


    private static void sendRequest(final Context context, final HttpRequest request) {
        final boolean needCache = request.getUrlCacheBean().isNeedCache();
        final ContentLoadingProgressBar bar = new ContentLoadingProgressBar(context);
        new AsyncTask<String, Void, HttpResponse>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                if (request.isLoading()) {
                    bar.show();
                }
            }

            @Override
            protected HttpResponse doInBackground(String... params) {
                HttpResponse response = new HttpResponse();
                if (needCache) {
                    UrlCacheBean cacheBean = HttpCacheUtils.getInstance().getCacheBean(params[0]);
                    if (TextUtils.isEmpty(cacheBean.getData())) {
                        response = HttpUtils.sendHttpRequest(request);
                    } else {
                        if (request.getListener() != null) {
                            response = request.getListener().onCache(cacheBean.getData());
                        }
                    }
                } else {
                    response = HttpUtils.sendHttpRequest(request);
                }
                return response;
            }

            @Override
            protected void onPostExecute(HttpResponse httpResponse) {
                super.onPostExecute(httpResponse);

                if (httpResponse == null || httpResponse.getResponseBean() == null ||
                        TextUtils.isEmpty(httpResponse.getResponseBean().getData())) {
                    if (request.getListener() != null) {
                        request.getListener().onError(HttpErrorCode.ERROR_REQUEST_EXCEPTION,
                                httpResponse);
                    }
                } else if (!TextUtils.isEmpty(httpResponse.getResponseBean().getData())) {
                    if (request.getListener() != null) {
                        request.getListener().onSuccess(HttpErrorCode.SUCCESS, httpResponse);
                    }
                }
                bar.hide();
            }
        }.execute();
    }

}
