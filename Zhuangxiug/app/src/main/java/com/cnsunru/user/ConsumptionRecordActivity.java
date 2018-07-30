package com.cnsunru.user;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cnsunru.R;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.util.GetEmptyViewUtils;
import com.cnsunru.common.util.PageLimitDelegate;
import com.cnsunru.common.util.TestData;
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.cnsunru.user.adapters.ConsumptionRecordAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 资金记录
 */
public class ConsumptionRecordActivity extends LBaseActivity {

    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.lRecyclerView)
    RecyclerView lRecyclerView;
    @BindView(R.id.lSwipeRefreshLayout)
    SwipeRefreshLayout lSwipeRefreshLayout;
    ConsumptionRecordAdapter recordAdapter;
    PageLimitDelegate pageLimitDelegate=new PageLimitDelegate(new PageLimitDelegate.DataProvider() {
        @Override
        public void loadData(int page) {
            pageLimitDelegate.setData(TestData.createTestData(10,String.class));
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption_record);
        recordAdapter=new ConsumptionRecordAdapter();
        lRecyclerView.setLayoutManager(new LinearLayoutManager(that,LinearLayoutManager.VERTICAL,false));
        GetEmptyViewUtils.bindEmptyView(that,recordAdapter,0,"暂无数据",true);
        lRecyclerView.setAdapter(recordAdapter);
        pageLimitDelegate.attach(lSwipeRefreshLayout,lRecyclerView,recordAdapter);
    }

}
