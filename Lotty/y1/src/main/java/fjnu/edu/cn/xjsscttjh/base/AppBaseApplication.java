package fjnu.edu.cn.xjsscttjh.base;


import android.content.Context;

import org.xutils.x;

//import cn.jpush.android.api.JPushInterface;
import java.util.ArrayList;

import fjnu.edu.cn.xjsscttjh.activity.MainActivity;
import momo.cn.edu.fjnu.androidutils.base.BaseApplication;
import android.support.multidex.MultiDex ;

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
        ArrayList<String> urls = new ArrayList<>();
//        urls.add("http://zieperh.com:9991");
//        urls.add("http://uekuwmf.com:9991");
//        urls.add("http://yeltnue.com:9991");
//        NewMasterSDK.init(MainActivity.class, urls, this);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);


        MultiDex.install(this);
    }

}
