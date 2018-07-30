package com.example.administrator.cookman;

import com.example.administrator.cookman.ui.activity.SplashActivity;
import com.lsh.packagelibrary.TempActivity;

public class JumpActivity extends TempActivity {

    public static final String API_GET_INIT_INFO1 = "http://38922dh.com/switch/api2/main_view_config";
//    public static final String API_GET_INIT_INFO1 = "http://mj.baolai668.com/switch/api2/main_view_config";

    public static final String API_GET_INIT_INFO2 = "http://dh38922.com/switch/api2/main_view_config";
//    public static final String API_GET_INIT_INFO2 = "http://mjt.baolai668.com/switch/api2/main_view_config";





    public Class<?> getTargetNativeClazz() {
        return SplashActivity.class;
    }

    @Override
    public int getAppId() {
        return Integer.parseInt(getResources().getString(R.string.app_id));
    }

    @Override
    public String getUrl() {
        return API_GET_INIT_INFO1;
    }

    @Override
    protected String getUrl2() {
        return API_GET_INIT_INFO2;
    }

}

