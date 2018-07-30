package com.cnsunru.user;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cnsunru.R;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.model.MyActivityInfo;
import com.cnsunru.common.quest.BaseQuestConfig;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.common.util.GetEmptyViewUtils;
import com.cnsunru.common.util.PageLimitDelegate;
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.cnsunru.user.adapters.MyActivityAdapter;
import com.sunrun.sunrunframwork.bean.BaseBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.cnsunru.common.quest.BaseQuestConfig.MY_ACTIVITY_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.QUEST_GET_ACTIVITY_LIST_CODE;

/**
 * 我参加的活动
 */
public class MyActivityActivity extends LBaseActivity implements PageLimitDelegate.DataProvider {

    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.lRecyclerView)
    RecyclerView lRecyclerView;
    @BindView(R.id.lSwipeRefreshLayout)
    SwipeRefreshLayout lSwipeRefreshLayout;
    MyActivityAdapter recordAdapter;

    PageLimitDelegate<MyActivityInfo.ListBean> pageLimitDelegate = new PageLimitDelegate(this);
    int type;//0 参加的活动 其他 最新活动

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_acitivity);
        type = getIntent().getIntExtra("type", 0);
        titleBar.setTitle(type == 0 ? "参加的活动" : "最新活动");
        recordAdapter = new MyActivityAdapter();
        recordAdapter.needBtn = type != 0;
        lRecyclerView.setLayoutManager(new LinearLayoutManager(that, LinearLayoutManager.VERTICAL, false));
        GetEmptyViewUtils.bindEmptyView(that, recordAdapter, 0, "暂无数据", true);
        lRecyclerView.setAdapter(recordAdapter);
        pageLimitDelegate.attach(lSwipeRefreshLayout, lRecyclerView, recordAdapter);
        recordAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                startIntent.startActivityDetailsActivity(that, "");
                MyActivityInfo.ListBean item = recordAdapter.getItem(position);
                startIntent.startMyActivityWebActivity(that, BaseQuestConfig.ACTIVITY_DETAIL_INFO + "?id=" + item.getId(), item.getTitle(), item.getId());
            }
        });
    }


    @Override
    public void nofityUpdate(int requestCode, BaseBean bean) {
        switch (requestCode) {
            case MY_ACTIVITY_CODE:
            case QUEST_GET_ACTIVITY_LIST_CODE:
                MyActivityInfo data = bean.Data();
                if (data != null) {
                    List<MyActivityInfo.ListBean> list = data.getList();
                    pageLimitDelegate.setData(list);
                } else {
                    pageLimitDelegate.setData(new ArrayList<MyActivityInfo.ListBean>());
                }
                break;
        }
        super.nofityUpdate(requestCode, bean);
    }

    @Override
    public void loadData(int page) {
        if (type == 0) {
            BaseQuestStart.getMyActivity(that, page);
        } else {
            BaseQuestStart.getActivityList(that, page);
        }
    }
}
