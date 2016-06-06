package com.freedom.coder.http.custom;

import com.freedom.coder.http.custom.bean.UrlCacheBean;

/**
 * Created by weilongzhang on 16/6/6.
 */
public class HttpCacheUtil {


    private static volatile HttpCacheUtil httpCacheUtil;

    private HttpCacheUtil() {
    }

    public static HttpCacheUtil getInstance() {
        if (httpCacheUtil == null) {
            synchronized (HttpCacheUtil.class) {
                if (httpCacheUtil == null) {
                    httpCacheUtil = new HttpCacheUtil();
                }
            }
        }
        return httpCacheUtil;
    }


    public UrlCacheBean getCacheBean(String url) {
        return new UrlCacheBean();
    }
}
