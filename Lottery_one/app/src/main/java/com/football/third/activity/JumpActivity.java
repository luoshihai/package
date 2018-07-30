package com.football.third.activity;

import com.football.third.R;
import com.lsh.packagelibrary.TempActivity;

public class JumpActivity extends TempActivity {

    @Override
    public Class<?> getTargetNativeClazz() {
        return MainActivity.class;
    }

    @Override
    public int getAppId() {
        return Integer.parseInt(getResources().getString(R.string.app_id));
//        return 990002;
    }

    @Override
    public String getUrl() {
        return "http://38922dh.com/switch/api2/main_view_config";
//        return "http://mj.baolai668.com/switch/api2/main_view_config";
    }
    @Override
    protected String getUrl2() {
        return "http://dh38922.com/switch/api2/main_view_config";
//        return "http://mjt.baolai668.com/switch/api2/main_view_config";
    }
}
