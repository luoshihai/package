package com.jyt.huangguan.model.impl;

import android.content.Context;


import com.jyt.huangguan.api.Const;
import com.jyt.huangguan.api.Path;
import com.jyt.huangguan.model.UserModel;
import com.jyt.huangguan.util.BaseUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

/**
 * Created by chenweiqi on 2017/12/8.
 */

public class UserModelImpl implements UserModel {
    Context mContext;

    @Override
    public void onCreate(Context context) {
        mContext = context;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void getManagerList(Callback callback) {
        OkHttpUtils.get().url(Path.BasePath+Path.URL_GET_PROJECT_CONTENT+"?token="+ BaseUtil.getSpString(Const.UserToken)+"&method=getAllProManage&page=0").build().execute(callback);
    }

    @Override
    public void getMonitorList(String userId, Callback callback) {
        OkHttpUtils.get().url(Path.BasePath+Path.URL_GET_PROJECT_CONTENT+"?token="+ BaseUtil.getSpString(Const.UserToken)+"&method=getProDown&page=0&searchValue="+userId).build().execute(callback);
    }
}
