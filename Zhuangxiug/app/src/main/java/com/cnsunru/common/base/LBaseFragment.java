package com.cnsunru.common.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cnsunru.common.CommonIntent;
import com.cnsunru.common.intent.IntentPoxy;
import com.cnsunru.common.intent.StartIntent;
import com.cnsunru.common.quest.Config;
import com.sunrun.sunrunframwork.uibase.BaseFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @作者: Liufan
 * @时间: 2017/5/22
 * @功能描述: 预留的, 为增加功能做准备
 */


public abstract class LBaseFragment extends BaseFragment {
    @LayoutRes
    public abstract int getLayoutRes();

    private Unbinder unbinder;
    protected StartIntent startIntent = IntentPoxy.getProxyInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutRes = getLayoutRes();
        if (layoutRes > 0) {
            return inflater.inflate(layoutRes, container, false);
        } else {
            throw new RuntimeException("getLayoutRes should be override to provide the layout resource");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(unbinder!=null){
            unbinder.unbind();
        }
        unbinder = ButterKnife.bind(this, view);
    }

    /**
     * 启用eventBus,不用手动关闭
     */
    public void initEventBus() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
        unbinder.unbind();
        super.onDestroy();
    }

    public void onVisible() {

    }

    public void onInVisible() {

    }

//    public boolean checkLogin() {
//        String user_key = Config.getLoginInfo().getId();
//        if (user_key == null || user_key.equals("")) {
//            CommonIntent.startLoginActivity(getActivity(), false);
//            return false;
//        }
//        return true;
//    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            onInVisible();
        } else {
            onVisible();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            onVisible();
        } else {
            onInVisible();
        }
    }
}
