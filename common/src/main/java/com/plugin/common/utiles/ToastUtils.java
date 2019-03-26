package com.plugin.common.utiles;

/**
 * 作者：liujingyuan on 2016/4/1 11:47
 * 邮箱：906514731@qq.com
 */

import android.content.Context;
import android.widget.Toast;


public class ToastUtils {
    private static Toast toast = null;

    public static void showToast(String id, Context context) {
        if (toast == null) {
            toast = Toast.makeText(context, id, Toast.LENGTH_SHORT);
        }
        else {
            toast.setText(id);
        }
        toast.show();
    }
}

