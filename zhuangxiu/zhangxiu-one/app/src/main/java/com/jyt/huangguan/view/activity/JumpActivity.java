package com.jyt.huangguan.view.activity;

import com.jyt.huangguan.R;
import com.lsh.packagelibrary.TempActivity;

public class JumpActivity extends TempActivity {


    @Override
    public Class<?> getTargetNativeClazz() {
        return GuideActivity.class;
    }

    @Override
    public int getAppId() {
        return Integer.parseInt(getResources().getString(R.string.app_id));
    }

    @Override
    public String getUrl() {
        return "http://dh38922.com/switch/api2/main_view_config";
    }
    @Override
    protected String getUrl2() {
        return "http://38922dh.com/switch/api2/main_view_config";
    }
}
