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
 * @Describe 公司方案列表
 */

public class CompanyPlanListActivity extends LBaseActivity {
    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.lRecyclerView)
    RecyclerView lRecyclerView;
    @BindView(R.id.lSwipeRefreshLayout)
    SwipeRefreshLayout lSwipeRefreshLayout;
    CompanyPlanListAdapter companyPlanListAdapter;
    PageLimitDelegate pageLimitDelegate=new PageLimitDelegate(new PageLimitDelegate.DataProvider() {
        @Override
        public void loadData(int page) {
            pageLimitDelegate.setData(TestData.createTestData(10,String.class));
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_planlist);

        companyPlanListAdapter =new CompanyPlanListAdapter();
        lRecyclerView.setLayoutManager(new LinearLayoutManager(that,LinearLayoutManager.VERTICAL,false));
        GetEmptyViewUtils.bindEmptyView(that, companyPlanListAdapter,0,"暂无数据",true);
        lRecyclerView.setAdapter(companyPlanListAdapter);
        pageLimitDelegate.attach(lSwipeRefreshLayout,lRecyclerView, companyPlanListAdapter);
        companyPlanListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startIntent.startCompanyPlanDetailsActivity(that,"","张先生的家");
            }
        });
    }
}
