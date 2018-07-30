package com.cnsunru.common.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.sunrun.sunrunframwork.uiutils.UIUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WQ on 2017/6/15.
 */

public class Tool {

    public static void opWxin(Context context){
        if(isWeixinAvilible(context)){
            Intent intent = new Intent();
            ComponentName cmp=new ComponentName("com.tencent.mm","com.tencent.mm.ui.LauncherUI");
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            context.startActivity(intent);
        }else {
            UIUtils.shortM("为检测到微信程序或者微信版本过低");
        }
    }

    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static  void goToMarket(Context context){
        try {
            Uri uri = Uri.parse("market://details?id="+context.getPackageName());
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
            UIUtils.shortM("未检测到应用市场!");
        }

    }

    public static boolean  isNumber(String numberStr){
        try {
            Double.parseDouble(numberStr);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
    public static boolean  isIntNumber(String numberStr){
        try {
            Long.parseLong(numberStr);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    public static <T,U>  U[] list2Array(List<T> source,BeanTypeConvert<T,U> convert){
        U[]aimArr= (U[]) Array.newInstance(convert.getArrClass(),source.size());
        for (int i = 0; i < source.size(); i++) {
            aimArr[i]=convert.convert(source.get(i));
        }
        return  aimArr;
    }

    public interface BeanTypeConvert<T,U>{
        public U convert(T t);
        public Class<?extends U>getArrClass();
    }

    public static abstract class BaseBeanTypeConvert<T,U> implements BeanTypeConvert<T,U>{
        Class<U>clazz;

        public  BaseBeanTypeConvert(Class< U> clazz) {
            this.clazz = clazz;
        }

        @Override
        public Class<? extends U> getArrClass() {
            return clazz;
        }
    }

}
