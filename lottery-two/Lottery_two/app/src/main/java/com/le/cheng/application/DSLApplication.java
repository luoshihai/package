package com.le.cheng.application;

import android.app.Activity;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.le.cheng.utils.Constant;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dwb on 2018/3/26.
 */

public class DSLApplication extends android.support.multidex.MultiDexApplication{
    private List<Activity> activities = new ArrayList<Activity>();
    // 建立请求队列
    public static RequestQueue queue;
    private static DSLApplication app;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        queue = Volley.newRequestQueue(getApplicationContext());
        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        Constant.WIDTHPIXELS = displayMetrics.widthPixels;
        Constant.HEIGHTPIXELS = displayMetrics.heightPixels;
        Constant.DENSITYDPI = displayMetrics.densityDpi;
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Constant.SCREEN_WIDTH = wm.getDefaultDisplay().getWidth();
        Constant.SCREEN_HEIGHT = wm.getDefaultDisplay().getHeight();

    }

    /**
     * Volley请求队列
     * @return
     */
    public static RequestQueue getHttpQueue() {
        return queue;
    }
    public static DSLApplication getInstance() {
        return app;
    }
    public void addActivity(Activity activity) {
        activities.add(activity);
    }
    @Override
    public void onTerminate() {
        super.onTerminate();

        for (Activity activity : activities) {
            activity.finish();
        }
        System.exit(0);
     }
}
