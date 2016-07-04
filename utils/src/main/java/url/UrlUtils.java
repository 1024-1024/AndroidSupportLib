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
            for (Map.Entry<String,String> param : params.entrySet()) {
                String key = param.getKey();
                String value = param.getValue();
                newUrl.append(key).append("=").append(value).append("&");
            }
        }
        try {
            return URLEncoder.encode(newUrl.toString(),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
