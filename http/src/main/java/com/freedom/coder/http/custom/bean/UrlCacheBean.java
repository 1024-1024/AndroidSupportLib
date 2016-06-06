package com.freedom.coder.http.custom.bean;

/**
 * Created by weilongzhang on 16/6/6.
 */
public class UrlCacheBean {

    private boolean isNeedCache;
    private String url;

    public UrlCacheBean(boolean isNeedCache, String url) {
        this.isNeedCache = isNeedCache;
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
