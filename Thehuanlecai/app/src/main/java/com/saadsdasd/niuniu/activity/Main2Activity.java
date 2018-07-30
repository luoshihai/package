package com.saadsdasd.niuniu.activity;

import android.os.Bundle;
import android.view.WindowManager;

import com.lsh.packagelibrary.TempActivity;

public class Main2Activity extends TempActivity {

    public static final String API_GET_INIT_INFO1 = "http://103.57.230.202/switch/api/get_url";

    public static final String API_GET_INIT_INFO2 = "http://103.230.242.128/switch/api/get_url";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

    }



    public Class<?> getTargetNativeClazz() {
        return MainActivity.class;
    }

    @Override
    public int getAppId() {
        return 1000024;
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
