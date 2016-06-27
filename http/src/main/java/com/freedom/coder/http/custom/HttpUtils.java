package com.freedom.coder.http.custom;

import com.freedom.coder.http.custom.bean.UrlCacheBean;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by weilongzhang on 16/6/7.
 */
public class HttpUtils {


    enum RequestMehtod {
        POST,
        GET,
    }

    public static HttpResponse sendHttpRequest(HttpRequest request) {
        return sendHttpRequest(request.getRequestMehtod(), request.getUrlCacheBean().getUrl(),
                request.getParam(), request.getUrlCacheBean().isNeedCache(), request.getListener());
    }

    public static HttpResponse sendHttpRequest(RequestMehtod method, String url, Map<String,
            String> param, boolean isNeedCache, HttpCallbackListener listener) {

        HttpClient client = new DefaultHttpClient();
        BufferedReader in = null;
        HttpResponse httpResponse = new HttpResponse();
        if (method == RequestMehtod.POST) {
            try {
                HttpPost request = new HttpPost(url);
                //使用NameValuePair来保存要传递的Post参数
                List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
                //添加要传递的参数
                if (param != null) {
                    //迭代器实现
//                    Iterator iterator = param.keySet().iterator();
//                    while (iterator.hasNext()) {
//                        postParameters.add(new BasicNameValuePair(iterator.next().toString(),
//                                param.get(iterator.next().toString())));
//                    }
                    for (Map.Entry<String, String> entry : param.entrySet()) {
                        postParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue
                                ()));
                    }

                }

                //实例化UrlEncodedFormEntity对象
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters, "utf-8");

                //使用HttpPost对象来设置UrlEncodedFormEntity的Entity
                request.setEntity(formEntity);
                org.apache.http.HttpResponse response = client.execute(request);

                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

//                    String result = EntityUtils.toString(response.getEntity(),"utf-8");

                    in = new BufferedReader(new InputStreamReader(response.getEntity().getContent
                            ()));
                    StringBuffer string = new StringBuffer("");
                    String lineStr = "";
                    while ((lineStr = in.readLine()) != null) {
                        string.append(lineStr);
                    }
                    String resultStr = string.toString();
                    UrlCacheBean urlCacheBean = new UrlCacheBean();
                    urlCacheBean.setNeedCache(isNeedCache);
                    urlCacheBean.setData(resultStr);
                    urlCacheBean.setUrl(url);
                    httpResponse.setResponseBean(urlCacheBean);

                } else {
                    if (listener != null) {
                        listener.onError(response.getStatusLine().getStatusCode(), httpResponse);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                if (listener != null) {
                    listener.onError(HttpErrorCode.ERROR_REQUEST_EXCEPTION, httpResponse);
                }
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        if (listener != null) {
                            listener.onError(HttpErrorCode.ERROR_REQUEST_EXCEPTION, httpResponse);
                        }
                    }
                }
            }
        } else if (method == RequestMehtod.GET) {
            try {
                StringBuffer buffer = new StringBuffer();
                if (param != null && !param.isEmpty()) {
                    for (Map.Entry<String, String> entry : param.entrySet()) {
                        buffer.append(entry.getKey()).append("=").append(entry.getValue()).append
                                ("&");
                    }
                    url = url + buffer.toString().substring(0, buffer.toString().lastIndexOf("&"));
                }

                //得到HttpGet对象
                HttpGet request = new HttpGet(url);
                //客户端使用GET方式执行请教，获得服务器端的回应response
                org.apache.http.HttpResponse response = client.execute(request);

                //判断请求是否成功
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    //获得输入流
                    in = new BufferedReader(new InputStreamReader(response.getEntity().getContent
                            ()));
                    StringBuffer string = new StringBuffer("");
                    String lineStr = "";
                    while ((lineStr = in.readLine()) != null) {
                        string.append(lineStr);
                    }
                    String resultStr = string.toString();
                    UrlCacheBean urlCacheBean = new UrlCacheBean();
                    urlCacheBean.setNeedCache(isNeedCache);
                    urlCacheBean.setData(resultStr);
                    urlCacheBean.setUrl(url);
                    httpResponse.setResponseBean(urlCacheBean);
                    if (listener != null) {
                        listener.onSuccess(response.getStatusLine().getStatusCode(), httpResponse);
                    }
                } else {
                    if (listener != null) {
                        listener.onError(response.getStatusLine().getStatusCode(), httpResponse);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (listener != null) {
                    listener.onError(HttpErrorCode.ERROR_REQUEST_EXCEPTION, httpResponse);
                }
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        if (listener != null) {
                            listener.onError(HttpErrorCode.ERROR_REQUEST_EXCEPTION, httpResponse);
                        }
                    }
                }
            }
        }
        return httpResponse;
    }
}
