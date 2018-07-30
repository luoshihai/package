package com.hhhh.pailiesan;

import android.app.Application;

/**
 * Created by tgw on 2017/8/9.
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

 //       初始化sdk
//        JPushInterface.setDebugMode(false);//正式版的时候设置false，关闭调试
//        JPushInterface.init(this);
        //建议添加tag标签，发送消息的之后就可以指定tag标签来发送了
//        Set<String> set = new HashSet<>();
//        set.add("andfixdemo");//名字任意，可多添加几个
//        JPushInterface.setTags(this, set, null);//设置标签
    }
}
