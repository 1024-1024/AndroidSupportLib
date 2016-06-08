package com.freedom.coder.http.custom;

import com.freedom.coder.http.custom.bean.UrlCacheBean;

import java.util.Map;

/**
 * Created by weilongzhang on 16/6/2.
 */
public class HttpRequest {

    private UrlCacheBean urlCacheBean;
    private boolean isLoading;
    private HttpUtils.RequestMehtod requestMehtod;
    private HttpCallbackListener listener;
    private Map<String,String> param;

    public HttpRequest() {
    }

    public HttpRequest(UrlCacheBean urlCacheBean, boolean isLoading, HttpUtils.RequestMehtod
            requestMehtod, HttpCallbackListener listener, Map<String, String> param) {
        this.urlCacheBean = urlCacheBean;
        this.isLoading = isLoading;
        this.requestMehtod = requestMehtod;
        this.listener = listener;
        this.param = param;
    }

    public Map<String, String> getParam() {
        return param;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }

    public UrlCacheBean getUrlCacheBean() {
        return urlCacheBean;
    }

    public void setUrlCacheBean(UrlCacheBean urlCacheBean) {
        this.urlCacheBean = urlCacheBean;
    }

    public HttpCallbackListener getListener() {
        return listener;
    }

    public void setListener(HttpCallbackListener listener) {
        this.listener = listener;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public HttpUtils.RequestMehtod getRequestMehtod() {
        return requestMehtod;
    }

    public void setRequestMehtod(HttpUtils.RequestMehtod requestMehtod) {
        this.requestMehtod = requestMehtod;
    }
}
