package com.qqq.asdas.cptools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.qqq.asdas.SplashActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qqq.asdas.R;

import java.util.HashMap;
import java.util.Map;


/**
 * 作者：WangJintao
 * 时间：2017/11/29
 * 邮箱：wangjintao1988@163.com
 */
//TODO  第一个界面  ACticity的界面.
public class WelcomeActivity extends Activity {


    public boolean FLAG;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

//
        Window window = getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        wl.alpha = 0.0f;//这句就是设置窗口里控件的透明度的．０.０全透明．１.０不透明．
        window.setAttributes(wl);
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // // 透明导航栏
            getWindow().addFlags(  WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        }
//        addShortcut(this, "桌面快捷方式");
        getData();
    }


    public static void addShortcut(Activity cx, String name) {
        // TODO: 2017/6/25  创建快捷方式的intent广播
        Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        // TODO: 2017/6/25 添加快捷名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
        //  快捷图标是允许重复
        shortcut.putExtra("duplicate", false);
        // 快捷图标
        Intent.ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(cx, R.mipmap.qqq);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);
        // TODO: 2017/6/25 我们下次启动要用的Intent信息
        Intent carryIntent = new Intent("android.intent.action.AAA");
        carryIntent.putExtra("name", name);
        carryIntent.putExtra("url", "https://www.google.com/");
        carryIntent.setClassName(cx.getPackageName(), WebActivity.class.getName());
        carryIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //添加携带的Intent
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, carryIntent);
        // TODO: 2017/6/25  发送广播
        cx.sendBroadcast(shortcut);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void getData() {
        OkGo.<String>post(CpTools.API_GET_INIT_INFO1)
                .params("order_id", CpTools.APPID)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            Log.e(GsonUtils.TAG, "onSuccess: "+response.body() );
                            PlayBean bean = GsonUtils.fromJson(response.body(), PlayBean.class);

                            if (bean.errno == 0) {
                                if (bean.jump) {
//                                  initInfoBean.getData()
                                    startActivity(new Intent(WelcomeActivity.this, UiActivity.class));
                                    SpUtils a = new SpUtils(getApplication());
                                    a.putString("URL", bean.data);
                                    a.putString("DATA", bean.downUrl);
                                     String aaa  =   a.getString("DATA","");
                                    Log.e("test 000 ", "bean.downurl: "+ bean.downUrl );
                                    Log.e("test 000 ", "bean.downurl: "+ aaa );
                                    finish();
                                } else {
                                    gotoMain();
                                }

                            } else {
                                Log.e("www", bean.errmsg + ",errno=" + bean.errno) ;
                                getData2();
                            }
                        } catch (Exception e) {
                            getData2();
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        if (!FLAG) {
                            getData2();
                        } else {
                            gotoMain();
                        }
                    }
                });
    }

    private void getData2() {
        FLAG = true;
        Map<String, String> httpParams = new HashMap<>();
        httpParams.put("order_id", CpTools.APPID);
//        httpParams.put("type", "android");
        OkGo.<String>post(CpTools.API_GET_INIT_INFO2)
                .params(httpParams)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {


                        try {
                            PlayBean initInfoBean = GsonUtils.fromJson(response.body(), PlayBean.class);
                            if (initInfoBean.errno == 0) {
                                if (initInfoBean.jump) {

                                    startActivity(new Intent(WelcomeActivity.this, UiActivity.class));
                                    SpUtils a = new SpUtils(getApplication());
                                    a.putString("URL", initInfoBean.data);
                                    a.putString("DATA", initInfoBean.downUrl);
//
                                    finish();
                                } else {
                                    gotoMain();
                                }
                            } else {
                                Log.e("www", initInfoBean.errmsg + ",errno=" + initInfoBean.errno);
                                gotoMain();
                            }
                        } catch (Exception e) {
                            gotoMain();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        gotoMain();
                    }
                });
    }

    private void gotoMain() {
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
        finish();
    }


//    public void createDeskShortCut() {
//        SharedPreferences preferences = getSharedPreferences("first",
//                Context.MODE_PRIVATE);
//        boolean isFirst = preferences.getBoolean("isfrist", false);
//        if (isFirst) {
//            return;
//        }
//        SharedPreferences.Editor edit = preferences.edit();
//        edit.putBoolean("isfrist", true);
//        edit.commit();
//
//
//        Log.i("coder", "------createShortCut--------");
//        // 创建快捷方式的Intent
//        Intent shortcutIntent = new Intent(
//                "com.android.launcher.action.INSTALL_SHORTCUT");
//        // 不允许重复创建
//        shortcutIntent.putExtra("duplicate", false);
//        // 需要现实的名称
//        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME,
//                "AAAAA");
//
//        // 快捷图片
//        Parcelable icon = Intent.ShortcutIconResource.fromContext(
//                getApplicationContext(), R.drawable.ic_arrow_back_white_24dp);
//
//        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
//
//        Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
//
//        // 点击快捷图片，运行的程序主入口
//        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
//        // 下面两个属性是为了当应用程序卸载时桌面上的快捷方式会删除
//        intent.setAction("android.intent.action.MAIN");
//        intent.addCategory("android.intent.category.LAUNCHER");
//        shortcutIntent.putExtra("url", "");
//        // 发送广播。OK
//        sendBroadcast(shortcutIntent);
//
//
////        //第一种设置快捷方式图片
//        Parcelable dasd = Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.drawable.ic_arrow_back_white_24dp);
////        shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, dasd);
////addShortcut(this,"创建APP",dasd);
//    }


    /**
     * 创建快捷方式
     *
     * @param context
     * @param shotcutName
     * @param bitmap
     * @param actionIntent
     * @param allowRepeat
     */
    public static void addShortcut(Context context, String shotcutName, Bitmap bitmap, Intent actionIntent, boolean allowRepeat) {
        Intent shortcutintent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        //是否允许重复创建
        shortcutintent.putExtra("duplicate", allowRepeat);
        //快捷方式的名称
        shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shotcutName);
        //设置快捷方式图片
        shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_ICON, bitmap);
        //设置快捷方式动作
        shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, actionIntent);
        //向系统发送广播
        context.sendBroadcast(shortcutintent);

    }


    public static void deleteShortcut(String activityName, Context context) {
        Intent shortcutIntent = new Intent();
        shortcutIntent.setClassName(context.getPackageName(), activityName);
        shortcutIntent.addCategory(Intent.CATEGORY_DEFAULT);

        Intent shortcut = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, "test_shortcut");
        shortcut.putExtra("duplicate", false); //不允许重复创建
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        //快捷方式的图标
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(context, R.mipmap.qqq));
        context.sendBroadcast(shortcut);
    }
}
