package com.freedom.coder.http.custom;

import com.freedom.coder.http.custom.bean.UrlCacheBean;

/**
 * Created by weilongzhang on 16/6/6.
 */
public class HttpCacheUtils {


    private static volatile HttpCacheUtils httpCacheUtils;

    private HttpCacheUtils() {
    }

    public static HttpCacheUtils getInstance() {
        if (httpCacheUtils == null) {
            synchronized (HttpCacheUtils.class) {
                if (httpCacheUtils == null) {
                    httpCacheUtils = new HttpCacheUtils();
                }
            }
        }
        return httpCacheUtils;
    }


    public UrlCacheBean getCacheBean(String url) {
        return new UrlCacheBean();
    }
}
