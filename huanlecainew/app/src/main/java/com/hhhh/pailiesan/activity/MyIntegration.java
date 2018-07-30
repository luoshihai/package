package com.hhhh.pailiesan.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhhh.pailiesan.adapter.IntegrationAdapter;
import com.hhhh.pailiesan.base.KBaseActivity;
import com.hhhh.pailiesan.model.IntegrationModel;
import com.hhhh.pailiesan.utils.UserCacheData;
import com.hhhh.pailiesan.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by QYQ on 2017/9/25.
 */

public class MyIntegration extends KBaseActivity {
    @BindView(R.id.tv_integration)
    TextView tvIntegration;
    @BindView(R.id.rcy_integration)
    RecyclerView rcyIntegration;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    private IntegrationAdapter adapter;
    private List<IntegrationModel> bean = new ArrayList<>();
    @Override
    protected void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_integration);
    }

    @Override
    protected void initView() {
        super.initView();
        setTitleName("我的积分");
        setTitleBack(true,0);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcyIntegration.setLayoutManager(layoutManager);
        adapter = new IntegrationAdapter(this);
        rcyIntegration.setAdapter(adapter);
        tvIntegration.setText((String)UserCacheData.get(this,"integration","0"));
    }

    @Override
    protected void getData() {
        super.getData();
        if((Boolean)UserCacheData.get(this,"sign",false)){
            IntegrationModel model = new IntegrationModel();
            model.setType("积分签到：积分签到得5分");
            model.setIntegration("+5");
            bean.add(model);
            adapter.setItems(bean);
        }else {
            rcyIntegration.setVisibility(View.GONE);
            llEmpty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
