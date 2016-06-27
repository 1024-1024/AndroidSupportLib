package com.freedom.coder.http.custom;

import com.freedom.coder.http.custom.bean.UrlCacheBean;

/**
 * Created by weilongzhang on 16/6/2.
 */
public class HttpResponse {

    private UrlCacheBean responseBean;

    public UrlCacheBean getResponseBean() {
        return responseBean;
    }

    public void setResponseBean(UrlCacheBean responseBean) {
        this.responseBean = responseBean;
    }
}
