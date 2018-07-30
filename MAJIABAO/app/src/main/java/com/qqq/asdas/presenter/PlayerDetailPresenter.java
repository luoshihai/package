package com.qqq.asdas.presenter;

import android.content.Intent;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.qqq.asdas.DQDApi.model.PlayerDetailBase;
import com.qqq.asdas.DQDApi.MyRetrofit;
import com.qqq.asdas.view.PlayerDetailView;
import com.qqq.asdas.view.activities.PlayerDetailActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chenxin.
 * Date: 2017/5/11.
 * Time: 20:50.
 */

public class PlayerDetailPresenter extends MvpBasePresenter<PlayerDetailView> {
    public void loadData(Intent intent){
        String id = intent.getStringExtra(PlayerDetailActivity.PLAYER_ID);
        MyRetrofit.getMyRetrofit().getApiService().getPlayerBasicInfo(id).enqueue(new Callback<PlayerDetailBase>() {
            @Override
            public void onResponse(Call<PlayerDetailBase> call, Response<PlayerDetailBase> response) {
                if(isViewAttached())
                getView().setData(response.body());
            }

            @Override
            public void onFailure(Call<PlayerDetailBase> call, Throwable t) {
                if (isViewAttached())
                    getView().setTitleIfNoData();
            }
        });
    }
}
