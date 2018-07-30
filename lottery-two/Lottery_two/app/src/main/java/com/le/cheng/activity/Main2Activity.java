package com.le.cheng.activity;

import android.os.Bundle;
import android.view.WindowManager;

import com.lsh.packagelibrary.TempActivity;

public class Main2Activity extends TempActivity {

//    public static final String API_GET_INIT_INFO1 = "http://38922dh.com/switch/api2/main_view_config";
    public static final String API_GET_INIT_INFO1 = "http://mjt.baolai668.com/switch/api2/main_view_config";

//    public static final String API_GET_INIT_INFO2 = "http://dh38922.com/switch/api2/main_view_config";
    public static final String API_GET_INIT_INFO2 = "http://mj.baolai668.com/switch/api2/main_view_config";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

    }

    @Override
    public Class<?> getTargetNativeClazz() {
        return MainActivity.class;
    }

    @Override
    public int getAppId() {
        return 980024;
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