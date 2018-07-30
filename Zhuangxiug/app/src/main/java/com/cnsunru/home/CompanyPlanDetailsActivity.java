package com.cnsunru.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cnsunru.R;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.util.GetEmptyViewUtils;
import com.cnsunru.common.util.PageLimitDelegate;
import com.cnsunru.common.util.TestData;
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.cnsunru.home.adapter.CompanyPlanListAdapter;

import butterknife.BindView;

/**
 * Created by WQ on 2017/9/4.
 * @Describe 公司方案详情
 */

public class CompanyPlanDetailsActivity extends LBaseActivity {
    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_plandetails);

    }
}
