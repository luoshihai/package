package com.le.cheng.volley;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

/** volley错误失败封装工具类
 * Created by dwb on 2017/12/5.
 */

public class VolleyErrorHelper {
    /**
     * volley返回信息
     *
     *
     * @param error
     * @param context
     * @return String
     */
    public static String getMessage(Object error, Context context){
        if(error instanceof TimeoutError){
            return "连接服务器超时";
        }else if(isServerProblem(error)){
            return handleServerError(error,context);
        }else if(isNetworkError(error)){
            return "当前网络不稳定，请重新再试";
        }else if(isNoConnectionError(error)){
            return "无网络连接";
        }
        return "当前网络不稳定，请重新再试";
    }

    private static  boolean isNetworkError(Object error) {
        return (error instanceof NetworkError);
    }

    private static  boolean isNoConnectionError(Object error) {
        return (error instanceof NoConnectionError);
    }

    private static boolean isServerProblem(Object error) {
        return (error instanceof ServerError) || (error instanceof AuthFailureError);
    }

    private static String handleServerError(Object err, Context context) {
        VolleyError error=(VolleyError) err;
        NetworkResponse response=error.networkResponse;
        if(response!=null){
            switch (response.statusCode) {
                case 404:
                case 422:
                case 401:
                    return "连接服务器失败";

                default:
                    return "连接服务器超时";
            }
        }
        return "当前网络不稳定，请重新再试";
    }
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            // 获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            // 获取NetworkInfo对象
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            //判断NetworkInfo对象是否为空
            if (networkInfo != null)
                return networkInfo.isAvailable();
        }
        return false;
    }

 }
