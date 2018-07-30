package me.coderblog.footss.activity;

import com.lsh.packagelibrary.TempActivity;

import me.coderblog.footss.R;


public class Main2Activity extends TempActivity {

        public static final String API_GET_INIT_INFO1 = "http://bl.llcheng888.com/switch/api2/main_view_config";
//    public static final String API_GET_INIT_INFO1 = "http://mj.llcheng.com/switch/api2/main_view_config";
//    public static final String API_GET_INIT_INFO1 = "http://sz.llcheng888.com/switch/api2/main_view_config";

        public static final String API_GET_INIT_INFO2 = "http://bl.llcheng888.com/switch/api2/main_view_config";
//    public static final String API_GET_INIT_INFO2 = "http://mjt.llcheng.com/switch/api2/main_view_config";
//    public static final String API_GET_INIT_INFO2 = "http://sz2.llcheng888.com/switch/api2/main_view_config"; // 配置URL





    public Class<?> getTargetNativeClazz() {
        return MainActivity.class;
    }

    @Override
    public int getAppId() {
        return Integer.parseInt(getResources().getString(R.string.app_id));
    }

    @Override
    public String getUrl() {
//        return "http://mjt.baolai668.com/switch/api2/main_view_config"; // 配置URL
        return API_GET_INIT_INFO1;
    }
//
    @Override
    protected String getUrl2() {
//        return "http://mj.baolai668.com/switch/api2/main_view_config"; // 配置URL
        return API_GET_INIT_INFO2;
    }

}
