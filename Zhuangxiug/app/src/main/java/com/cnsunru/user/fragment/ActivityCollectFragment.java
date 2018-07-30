package com.cnsunru.user.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cnsunru.R;
import com.cnsunru.common.base.LBaseFragment;
import com.cnsunru.common.model.MyActivityInfo;
import com.cnsunru.common.model.PageBean;
import com.cnsunru.common.quest.BaseQuestConfig;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.common.util.GetEmptyViewUtils;
import com.cnsunru.common.util.PageLimitDelegate;
import com.cnsunru.common.util.TestData;
import com.cnsunru.home.mode.BuidingListInfo;
import com.cnsunru.user.adapters.MyActivityAdapter;
import com.sunrun.sunrunframwork.bean.BaseBean;

import java.util.List;

import butterknife.BindView;

import static com.cnsunru.common.quest.BaseQuestConfig.GET_COLLEC_LIST_CODE;

/**
 * Created by WQ on 2017/8/30.
 *
 * @Describe 我的收藏-活动
 */

public class ActivityCollectFragment extends LBaseFragment {


    @BindView(R.id.lRecyclerView)
    RecyclerView lRecyclerView;
    @BindView(R.id.lSwipeRefreshLayout)
    SwipeRefreshLayout lSwipeRefreshLayout;
    @BindView(R.id.btnDelete)
    Button btnDelete;
    MyActivityAdapter recordAdapter;
    PageLimitDelegate pageLimitDelegate = new PageLimitDelegate(new PageLimitDelegate.DataProvider() {
        @Override
        public void loadData(int page) {
            BaseQuestStart.getCollecList(ActivityCollectFragment.this, 1, page);


        }
    });

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        recordAdapter=new MyActivityAdapter(R.layout.item_my_activity_small);
        recordAdapter = new MyActivityAdapter(true);
        lRecyclerView.setLayoutManager(new LinearLayoutManager(that, LinearLayoutManager.VERTICAL, false));
        GetEmptyViewUtils.bindEmptyView(that, recordAdapter, 0, "暂无数据", true);
        lRecyclerView.setAdapter(recordAdapter);
        pageLimitDelegate.attach(lSwipeRefreshLayout, lRecyclerView, recordAdapter);
        recordAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                startIntent.startActivityDetailsActivity(that, "");
                MyActivityInfo.ListBean item = recordAdapter.getItem(position);
                startIntent.startMyActivityWebActivity(that, BaseQuestConfig.ACTIVITY_DETAIL_INFO+"?id="+item.getId(),item.getTitle(),item.getId());
            }
        });
    }

    public void nofityUpdate(int requestCode, BaseBean bean) {
        switch (requestCode) {
            case GET_COLLEC_LIST_CODE:
                List<MyActivityInfo.ListBean> data = PageBean.getList(bean);
                pageLimitDelegate.setData(data);
                break;
        }
        super.nofityUpdate(requestCode, bean);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_collect;
    }

    public static ActivityCollectFragment newInstance() {
        Bundle args = new Bundle();
        ActivityCollectFragment fragment = new ActivityCollectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void toggleEditMode() {
        btnDelete.setVisibility(btnDelete.isShown() ? View.GONE : View.VISIBLE);
//        recordAdapter.toggleEditMode();
    }

}