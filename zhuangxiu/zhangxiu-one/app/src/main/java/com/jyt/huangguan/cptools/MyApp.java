package com.jyt.huangguan.cptools;

import android.app.Application;

import com.lzy.okgo.OkGo;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by jee on 2018/5/19.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.getInstance().init(this);
        JPushInterface.init(this);
    }
}
