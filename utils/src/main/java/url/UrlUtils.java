package url;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by weilongzhang on 16/7/4.
 */
public class UrlUtils {

    public static String  concatUrl(String url , Map<String,String> params) {
        StringBuilder newUrl = new StringBuilder(url);
        if (params != null) {
            newUrl.append("?");
            for (Map.Entry<String,String> param : params.entrySet()) {
                String key = param.getKey();
                String value = param.getValue();
                try {
                    newUrl.append(URLEncoder.encode(key,"utf-8")).append("=").append(URLEncoder.encode(value,"utf-8")).append("&");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return newUrl.toString();
    }
}
