package com.jyt.huangguan.model;

import android.util.Log;

import com.jyt.huangguan.api.BeanCallback;
import com.jyt.huangguan.bean.BaseJson;
import com.jyt.huangguan.api.Path;
import com.jyt.huangguan.bean.UserBean;
import com.jyt.huangguan.util.BaseUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;

/**
 * @author LinWei on 2017/10/30 14:05
 */
public class LoginModel {


    public void ToLogin(String tel, String pwd, final LoginResultListener listener){
        OkHttpUtils.post()
                .url(Path.URL_LOGIN)
                .addParams("tel",tel)
                .addParams("pwd",pwd)
                .build()
                .execute(new BeanCallback<BaseJson<UserBean>>() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (listener!=null){
                            listener.Result(false,null);
                            Log.e("@#","model---login "+ e.getMessage());
                            BaseUtil.makeText("登录失败");
                        }
                    }

                    @Override
                    public void onResponse(BaseJson<UserBean> response, int id) {
                        if (listener!=null){
                            if (response.ret){
                                listener.Result(true,response.data);
                            }else {
                                listener.Result(false,null);
                                Log.e("@#","model---login "+response.forUser);
                                BaseUtil.makeText(response.forUser);
                            }
                        }

                    }
                });


    }

    public interface LoginResultListener{
        void Result(boolean isSuccess,UserBean user);
    }

    public void ToShopLogin(String tel, String pwd, final LoginResultListener listener){
        OkHttpUtils.post()
                .url(Path.URL_LOGIN_SHPO)
                .addParams("loginaccount",tel)
                .addParams("pwd",pwd)
                .build()
                .execute(new BeanCallback<BaseJson<UserBean>>() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (listener!=null){
                            listener.Result(false,null);
                            Log.e("@#","model---login "+ e.getMessage());
                        }
                    }

                    @Override
                    public void onResponse(BaseJson<UserBean> response, int id) {
                        if (listener!=null){
                            if (response.ret){

                                listener.Result(true,response.data);
                            }else {
                                listener.Result(false,null);
                                Log.e("@#","model---login "+response.forUser);
                                BaseUtil.makeText(response.forUser);
                            }
                        }

                    }
                });


    }


    public void ToBrandLogin(String tel, String pwd, final LoginResultListener listener){
        OkHttpUtils.post()
                .url(Path.URL_LOGIN_BRAND)
                .addParams("tel",tel)
                .addParams("pwd",pwd)
                .build()
                .execute(new BeanCallback<BaseJson<UserBean>>() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (listener!=null){
                            listener.Result(false,null);
                            Log.e("@#","model---login "+ e.getMessage());
                        }
                    }

                    @Override
                    public void onResponse(BaseJson<UserBean> response, int id) {
                        if (listener!=null){
                            if (response.ret){
                                listener.Result(true,response.data);
                            }else {
                                listener.Result(false,null);
                                Log.e("@#","model---login "+response.forUser);
                                BaseUtil.makeText(response.forUser);
                            }
                        }

                    }
                });


    }




}
