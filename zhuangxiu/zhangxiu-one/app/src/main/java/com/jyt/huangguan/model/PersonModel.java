package com.jyt.huangguan.model;

import com.jyt.huangguan.api.BeanCallback;
import com.jyt.huangguan.api.Const;
import com.jyt.huangguan.api.Path;
import com.jyt.huangguan.bean.BaseJson;
import com.jyt.huangguan.bean.UserBean;
import com.jyt.huangguan.util.BaseUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;

/**
 * @author LinWei on 2017/11/20 13:36
 */
public class PersonModel {
    public void getPersonInfo(final OngetPersonInfoListener listener){
        OkHttpUtils
                .get()
                .url(Path.URL_MapDatas)
                .addParams("token", BaseUtil.getSpString(Const.UserToken))
                .addParams("method","getUserDetail")
                .addParams("page","0")
                .addParams("searchValue",BaseUtil.getSpString(Const.USERID))
                .build()
                .execute(new BeanCallback<BaseJson<List<UserBean>>>() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(BaseJson<List<UserBean>> response, int id) {
                        if (listener!=null && response.ret && response.data.size()>0){
                            listener.Result(true,response.data.get(0));
                        }
                    }
                });
    }

    public interface OngetPersonInfoListener{
        void Result(boolean isSuccess,UserBean data);
    }

    public void getAboutUs(final OngetAboutUsListener listener){
        OkHttpUtils
                .get()
                .url(Path.URL_MapDatas)
                .addParams("token", BaseUtil.getSpString(Const.UserToken))
                .addParams("method","getAboutUs")
                .addParams("page","0")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (listener!=null){
                            try {
                                JSONObject jsonObject =new JSONObject(response);
                                boolean ret=jsonObject.getBoolean("ret");
                                if (ret){
                                    String aboutContent=jsonObject.getJSONObject("data").getString("aboutContent");
                                    listener.Result(true,aboutContent);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    public interface OngetAboutUsListener{
        void Result(boolean isSuccess , String data);
    }


}
