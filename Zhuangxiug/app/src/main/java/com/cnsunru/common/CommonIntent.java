package com.cnsunru.common;

import android.app.Activity;
import android.content.Intent;

import com.cnsunru.budget.activity.ProjectListActivity;
import com.cnsunru.main.NavigatorActivity;
import com.cnsunru.settings.SettingActivity;
import com.cnsunru.user.EditUserInfoActivity;
import com.sunrun.sunrunframwork.uibase.BaseActivity;
import com.sunrun.sunrunframwork.utils.BaseStartIntent;


/**
 * 页面跳转帮助类
 * Created by WQ on 2017/5/24.
 */

public class CommonIntent extends BaseStartIntent {
    public static void startNavigatorActivity(BaseActivity that) {
        that.startActivity(new Intent(that, NavigatorActivity.class));
    }
    public static void startSettingActivity(Activity that) {
        that.startActivity(new Intent(that, SettingActivity.class));
    }
    public static void startEditUserInfoActivity(Activity that) {
        that.startActivity(new Intent(that, EditUserInfoActivity.class));
    }

    /**
     *  工程清单
     * @param that
     */
    public static void startProjectListActivity(Activity that) {
        that.startActivity(new Intent(that, ProjectListActivity.class));
    }
}
