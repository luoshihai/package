package com.qqq.asdas;

import com.qqq.asdas.DQDApi.model.news.DaoMaster;
import com.qqq.asdas.DQDApi.model.news.DaoSession;
import com.qqq.asdas.cptools.MyApp;
import com.squareup.leakcanary.LeakCanary;
import com.umeng.commonsdk.UMConfigure;

import org.greenrobot.greendao.database.Database;

/**
 * Created by chenxin.
 * Date: 2017/5/20.
 * Time: 19:37.
 */

public class App extends MyApp {
    private static App app;
    private DaoSession daoSession;

    public static App getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "msisuzney-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        app = this;
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "d885c99e6524776aab69082a34ef419e");
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
