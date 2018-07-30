package com.cnsunru.common;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.bilibili.boxing.BoxingCrop;
import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.loader.IBoxingMediaLoader;
import com.cnsunru.common.boxing.BoxingGlideLoader;
import com.cnsunru.common.boxing.BoxingUcrop;
import com.cnsunru.common.boxing.FixDefaultMediaLoader;
import com.cnsunru.common.util.OtherDataConvert;
import com.sunrun.sunrunframwork.app.BaseApplication;
import com.sunrun.sunrunframwork.common.DefaultMediaLoader;
import com.sunrun.sunrunframwork.http.NetServer;
import com.tencent.bugly.Bugly;


/**
 * Created by WQ on 2017/5/24.
 */

public class CommonApp extends BaseApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        //图片选择库使用的图片加载器
        IBoxingMediaLoader loader = new BoxingGlideLoader();
        BoxingMediaLoader.getInstance().init(loader);
        //设置图片剪裁库
        BoxingCrop.getInstance().init(new BoxingUcrop());
        //设置基础框架使用的图片加载库
        ((DefaultMediaLoader) DefaultMediaLoader.getInstance()).init(new FixDefaultMediaLoader());
        //设置网络请求,数据解析方案
        NetServer.Settings.getSetting().setDataConvert(new OtherDataConvert());

//        Bugly.init(getApplicationContext(), "9ce5b9ba5f", false);
        Bugly.init(getApplicationContext(), "0c3b5f9893", false);

        MultiDex.install(this);
//        String processName = getCurProcessName(this);
//        CustomActivityOnCrash.install(this);
        //初始化极光sdk
//        JPushInterface.setDebugMode(true);//正式版的时候设置false，关闭调试
//        JPushInterface.init(this);
//        PushHelper.initPush(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


}
