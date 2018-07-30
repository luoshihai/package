package com.cnsunru.user.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.CommonTextView;
import com.cnsunru.R;
import com.cnsunru.common.base.LBaseFragment;
import com.cnsunru.common.boxing.GlideMediaLoader;
import com.cnsunru.common.model.LoginInfo;
import com.cnsunru.common.quest.Config;
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.makeramen.roundedimageview.RoundedImageView;
import com.sunrun.sunrunframwork.utils.EmptyDeal;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by WQ on 2017/8/30.
 * @Describe 个人主页
 */

public class UserMineFragment extends LBaseFragment {

    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.imgUserHead)
    RoundedImageView imgUserHead;
    @BindView(R.id.txtUsername)
    TextView txtUsername;
    @BindView(R.id.txtUserDesc)
    TextView txtUserDesc;
    @BindView(R.id.layUserInfo)
    RelativeLayout layUserInfo;
    @BindView(R.id.itemConsumptionRecord)
    CommonTextView itemConsumptionRecord;
    @BindView(R.id.itemBudget)
    CommonTextView itemBudget;
    @BindView(R.id.itemCoupon)
    CommonTextView itemCoupon;
    @BindView(R.id.itemCollect)
    CommonTextView itemCollect;
    @BindView(R.id.itemActivity)
    CommonTextView itemActivity;
    LoginInfo loginInfo;
    public static UserMineFragment newInstance() {
        UserMineFragment fragment = new UserMineFragment();
        return fragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleBar.setRightAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent.startSettingActivity(that);
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        setUserInfo();
    }

    private void setUserInfo() {
        loginInfo = Config.getLoginInfo();
        txtUsername.setText(loginInfo.getNickname());
        txtUserDesc.setText(loginInfo.getDescription());
        txtUserDesc.setVisibility(EmptyDeal.isEmpy(loginInfo.getDescription())?View.GONE:View.VISIBLE);
        GlideMediaLoader.loadHead(this,imgUserHead,loginInfo.getAvatar());
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_user_mine;
    }


    @OnClick({R.id.layUserInfo,R.id.itemConsumptionRecord, R.id.itemBudget, R.id.itemCoupon, R.id.itemCollect, R.id.itemActivity})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layUserInfo:
                startIntent.startEditUserInfoActivity(that, 0);
                break;
            case R.id.itemConsumptionRecord:
                startIntent.startConsumptionRecordActivity(that);
                break;
            case R.id.itemBudget:
                startIntent.startMyProjectActivity(that);
                break;
            case R.id.itemCoupon:
                startIntent.startMyCouponActivity(that,1);
                break;
            case R.id.itemCollect:
                startIntent.startMyCollectActivity(that);
                break;
            case R.id.itemActivity:
                startIntent.startMyActivityActivity(that);
                break;
        }
    }
}
