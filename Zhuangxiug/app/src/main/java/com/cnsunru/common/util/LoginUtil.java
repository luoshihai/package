package com.cnsunru.common.util;

import android.content.Context;

import com.cnsunru.common.CommonApp;
import com.cnsunru.common.quest.Config;
import com.sunrun.sunrunframwork.http.cache.ACache;


/**
 * 登录工具类
 * Created by WQ on 2017/1/10.
 */

public class LoginUtil {
    public static void exitLogin(Context context) {
        exitLogin(context, false);
    }

    public static void exitLogin(Context context, boolean isOrtherLogin) {
        Config.putLoginInfo(null);
        CommonApp.getInstance().closeAllActivity();
        ACache.get(context).clear();
//        LoginActivity.isLogin(context,true);

    }
}
