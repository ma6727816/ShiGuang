package com.person.shiguang.util;

import android.util.Log;

/**
 * 打印Log的工具类
 * Created by Garbled on 2016/4/8.
 */
public class LogUtils {

    private static final int state = 3;

    public static void E(String value) {
        if (state >= 3) {
            Log.e("TAG", value);
        }
    }

    public static void W(String value) {
        if (state >= 2) {
            Log.w("TAG", value);
        }
    }

    public static void I(String value) {
        if (state >= 1) {
            Log.i("TAG", value);
        }
    }
}
