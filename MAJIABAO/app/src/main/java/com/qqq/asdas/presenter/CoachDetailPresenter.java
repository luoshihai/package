package com.qqq.asdas.presenter;

import android.content.Intent;

import com.qqq.asdas.DQDApi.MyRetrofit;
import com.qqq.asdas.DQDApi.model.coach.Coach;
import com.qqq.asdas.view.activities.CoachDetailActivity;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.qqq.asdas.view.CoachDetailView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chenxin.
 * Date: 2017/5/12.
 * Time: 15:18.
 */

public class CoachDetailPresenter extends MvpBasePresenter<CoachDetailView> {
    public void ladData(Intent intent) {
        String id = "";
        if (intent == null || (id = intent.getStringExtra(CoachDetailActivity.ID)) == null || id.equals("")) {
            getView().showError(new Exception("请求参数错误"), false);
        } else {
            MyRetrofit.getMyRetrofit().getApiService().getCoachDetail(id).enqueue(new Callback<Coach>() {
                @Override
                public void onResponse(Call<Coach> call, Response<Coach> response) {
                    try {
                        if (isViewAttached()) {
                            getView().setData(response.body());
                            getView().showContent();
                        }
                    } catch (Exception e) {
                        getView().showError(new Exception("数据解析错误"), false);
                    }
                }

                @Override
                public void onFailure(Call<Coach> call, Throwable t) {
                    if (isViewAttached()) {
                        getView().showError(new Exception("网络请求错误\n请检查网络连接情况后\n点击重新加载"), false);
                    }
                }
            });
        }
    }
}
