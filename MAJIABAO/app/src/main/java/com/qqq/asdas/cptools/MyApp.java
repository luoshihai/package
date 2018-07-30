package com.qqq.asdas.cptools;

import android.app.Application;

import com.lzy.okgo.OkGo;

/**
 * Created by jee on 2018/5/19.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.getInstance().init(this);
//        JPushInterface.init(this);
    }
}
