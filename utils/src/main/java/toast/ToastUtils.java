package toast;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by weilongzhang on 16/7/2.
 */
public class ToastUtils {

    public static void showLong(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showShort(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showLongWithRID(Context context,int id ) {
        String message = context.getResources().getString(id);
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }

    public static void showShortWithRID(Context context,int id ) {
        String message = context.getResources().getString(id);
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

}
