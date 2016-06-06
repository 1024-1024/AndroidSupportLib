package com.freedom.coder.http.custom.bean;

/**
 * Created by weilongzhang on 16/6/6.
 */
public class UrlCacheBean {

    private String data;
    private boolean isNeedCache;
    private String url;

    public UrlCacheBean(boolean isNeedCache, String url) {
        this.isNeedCache = isNeedCache;
        this.url = url;
    }

    public UrlCacheBean(String data, boolean isNeedCache, String url) {
        this.data = data;
        this.isNeedCache = isNeedCache;
        this.url = url;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setNeedCache(boolean needCache) {
        isNeedCache = needCache;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UrlCacheBean() {
    }

    public boolean isNeedCache() {
        return isNeedCache;
    }

    public String getUrl() {
        return url;
    }


}
