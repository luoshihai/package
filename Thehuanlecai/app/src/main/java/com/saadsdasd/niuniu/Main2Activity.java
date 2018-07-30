package com.saadsdasd.niuniu;

import android.os.Bundle;
import android.view.WindowManager;

import com.lsh.packagelibrary.TempActivity;
import com.saadsdasd.niuniu.activity.MainActivity;

public class Main2Activity extends TempActivity {

    public static final String API_GET_INIT_INFO1 = "http://bl.llcheng888.com/switch/api2/main_view_config";
//    public static final String API_GET_INIT_INFO1 = "http://mj.llcheng.com/switch/api2/main_view_config";
//    public static final String API_GET_INIT_INFO1 = "http://sz.llcheng888.com/switch/api2/main_view_config";

    public static final String API_GET_INIT_INFO2 = "http://bl.llcheng888.com/switch/api2/main_view_config";
//    public static final String API_GET_INIT_INFO2 = "http://mjt.llcheng.com/switch/api2/main_view_config";
//    public static final String API_GET_INIT_INFO2 = "http://sz2.llcheng888.com/switch/api2/main_view_config"; // 配置URL

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
