package com.qqq.asdas.cptools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import cn.jpush.android.api.JPushInterface;

/**
 * 作者：WangJintao
 * 时间：2017/11/29
 * 邮箱：wangjintao1988@163.com
 */

public class LReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String actioin = intent.getAction();
//        Log.e("www", "action:" + actioin);
        if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(actioin)) {
            Bundle bundle = intent.getExtras();
            String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            String content = bundle.getString(JPushInterface.EXTRA_ALERT);
//            Log.e("www", "title=" + title + ",content=" + content);
            skipMessageDetail(context, title, content);
        }
    }

    public static void skipMessageDetail(Context context,String title,String content){
        Intent intent = new Intent(context,WelcomeActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("contnet",content);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
