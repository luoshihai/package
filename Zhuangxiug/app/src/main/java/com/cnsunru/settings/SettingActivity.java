package com.cnsunru.settings;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cnsunru.R;
import com.cnsunru.common.CommonApp;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.quest.Config;
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.sunrun.sunrunframwork.uiutils.UIUtils;
import com.sunrun.sunrunframwork.utils.AHandler;
import com.sunrun.sunrunframwork.utils.DataCleanManager;
import com.sunrun.sunrunframwork.view.ItemView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置
 */
public class SettingActivity extends LBaseActivity {

    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.setting_password)
    ItemView settingPassword;
    @BindView(R.id.settting_about)
    ItemView setttingAbout;
    @BindView(R.id.settting_clearCache)
    ItemView setttingClearCache;
    @BindView(R.id.settting_logout)
    TextView setttingLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_center);
        setttingClearCache.setRightText(DataCleanManager.getTotalCacheSize(that));
    }
    AHandler.Task task;
    @OnClick({R.id.setting_password,  R.id.settting_about, R.id.settting_clearCache, R.id.settting_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_password:
                startIntent.startModifyPasswordActivity(this);
                break;
            case R.id.settting_about:
                startIntent.startAboutActivity(that);
                break;
            case R.id.settting_clearCache:
                UIUtils.showLoadDialog(that,"清理中..");
                task = new AHandler.Task() {
                    @Override
                    public void task() {
                        DataCleanManager.clearAllCache(that);
                        super.task();
                    }

                    @Override
                    public void update() {
                        super.update();
                        UIUtils.cancelLoadDialog();
                        UIUtils.shortM("清除成功");
                        setttingClearCache.setRightText(DataCleanManager.getTotalCacheSize(that));
                    }
                };
                AHandler.runTask(task);

                break;
            case R.id.settting_logout:
                CommonApp.getInstance().closeAllActivity();
                Config.putLoginInfo(null);
                startIntent.startLoginActivity(that);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        AHandler.cancel(task);
        super.onDestroy();
    }
}
