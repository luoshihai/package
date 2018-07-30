package com.hhhh.pailiesan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.hhhh.pailiesan.R;
import com.hhhh.pailiesan.model.ServerSettingObj;
import com.hhhh.pailiesan.utils.Loadcomplete;

public class MainActivity extends Activity implements Loadcomplete {
    private String appId = "com.huaren.zuqiujinqiu"; //请替换成你的AppID
    //private String appId = "com.ssq.jihua";
    //private String appId = "com.caipiao.huangdu"; //测试AppID
    public ServerSettingObj obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        TimerTask ts = new TimerTask() {
//            @Override
//            public void run() {
//                LottyApiLogic.getInstance().checkApiState(MainActivity.this, appId, MainActivity.this);
//            }
//        };
//        new Timer().schedule(ts, 1000);
//        LottyApiLogic.getInstance().checkApiState(MainActivity.this, appId, MainActivity.this);
//        showHomePage();
        showNavipage();
    }

    @Override
    public void onloadSuccess(ServerSettingObj obj) {
        this.obj = obj;
        if (obj.isShowWap()) {
            if (obj.getWapUrl().length() > 0) {
                showHomePage();
                return;
            }
        }
        showNavipage();
    }

    /**
     * 这里跳转到webView显示网页
     */
    private boolean showHomePage() {

        Intent it = new Intent();
        it.setClass(MainActivity.this, WebActivity.class);
        it.putExtra("settingObj", obj);
        startActivity(it);
        finish();
        return true;
    }

    /**
     * 这里跳转到自己的原生界面
     */
    private boolean showNavipage() {
        Intent it = new Intent();
        it.setClass(MainActivity.this, MyActivity.class);
        startActivity(it);
        finish();
        return true;
    }
}


