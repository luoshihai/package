package com.hhhh.pailiesan.utils;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by tgw on 2017/8/7.
 */

public class Tools {
    /**
     * 获取状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            statusBarHeight = resources.getDimensionPixelSize(identifier);
        }
        return statusBarHeight;
    }

    public static boolean getResoult(Context context, int id) {
        if (context.getResources().getString(id).equals("显示")) {
            return true;
        }
        return false;
    }

}
