package com.cnsunru.main;

import android.os.Bundle;
import android.view.WindowManager;

import com.cnsunru.R;
import com.lsh.packagelibrary.TempActivity;

public class Main2Activity extends TempActivity {

//    public static final String API_GET_INIT_INFO1 = "http://mj.baolai668.com/switch/api2/main_view_config";

//    public static final String API_GET_INIT_INFO2 = "http://mjt.baolai668.com/switch/api2/main_view_config";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

    }



    public Class<?> getTargetNativeClazz() {
        return SplashActivity.class;
    }

    @Override
    public int getAppId() {
        return Integer.parseInt(getResources().getString(R.string.app_id));
    }

    @Override
    public String getUrl() {
        return getResources().getString(R.string.app_url1);
    }

    @Override
    protected String getUrl2() {
        return getResources().getString(R.string.app_url2);
    }

}
