package fjnu.edu.cn.xjsscttjh.base;


import android.content.Context;
import android.support.multidex.MultiDex;

import org.xutils.x;

//import cn.jpush.android.api.JPushInterface;
import java.util.ArrayList;

import fjnu.edu.cn.xjsscttjh.activity.MainActivity;
import momo.cn.edu.fjnu.androidutils.base.BaseApplication;

/**
 * Created by gaofei on 2017/9/8.
 * 应用基本Application
 */

public class AppBaseApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        //JPushInterface.init(this);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        MultiDex.install(this);
    }
}
