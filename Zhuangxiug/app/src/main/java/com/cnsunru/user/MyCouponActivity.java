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
import com.cnsunru.common.model.PageBean;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.common.util.GetEmptyViewUtils;
import com.cnsunru.common.util.PageLimitDelegate;
import com.cnsunru.common.util.TestData;
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.cnsunru.home.fragment.HomeFragment;
import com.cnsunru.home.mode.CouponBean;
import com.cnsunru.user.adapters.ConsumptionRecordAdapter;
import com.cnsunru.user.adapters.MyCouponAdapter;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.uiutils.UIUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;

import static com.cnsunru.common.quest.BaseQuestConfig.DISCOUNT_LIST_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.GET_DISCOUNT_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.MY_DISCOUNT_CODE;

/**
 * 优惠劵
 */
public class MyCouponActivity extends LBaseActivity {

    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.lRecyclerView)
    RecyclerView lRecyclerView;
    @BindView(R.id.lSwipeRefreshLayout)
    SwipeRefreshLayout lSwipeRefreshLayout;
    MyCouponAdapter recordAdapter;
    int type = 0;
    PageLimitDelegate<CouponBean> pageLimitDelegate = new PageLimitDelegate<CouponBean>(new PageLimitDelegate.DataProvider() {
        @Override
        public void loadData(int page) {
            if (type == 0) {
                BaseQuestStart.getDiscountList(that, page);
            } else {
                BaseQuestStart.getMyDiscount(that, page);
            }

        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coupon);
        type = getIntent().getIntExtra("type", type);
        titleBar.setTitle(type == 0 ? "优惠券列表" : "我的优惠劵");
        recordAdapter = new MyCouponAdapter(type);
        lRecyclerView.setLayoutManager(new LinearLayoutManager(that, LinearLayoutManager.VERTICAL, false));
        GetEmptyViewUtils.bindEmptyView(that, recordAdapter, 0, "暂无数据", true);
        lRecyclerView.setAdapter(recordAdapter);
        pageLimitDelegate.attach(lSwipeRefreshLayout, lRecyclerView, recordAdapter);
        recordAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                UIUtils.showLoadDialog(that, "领取中...");
                BaseQuestStart.getDiscount(that, recordAdapter.getItem(position).id);
            }
        });
    }

    public void nofityUpdate(int requestCode, BaseBean bean) {
        switch (requestCode) {
            case MY_DISCOUNT_CODE:
            case DISCOUNT_LIST_CODE:

                List<CouponBean> data = PageBean.getList(bean);
                pageLimitDelegate.setData(data);
                break;
            case GET_DISCOUNT_CODE:
                UIUtils.shortM(bean);
                if (bean.status == 1) {
                    EventBus.getDefault().post("refresh_home_discount");//刷新首页优惠劵列表
                    pageLimitDelegate.refreshPage();
                }
                break;
        }
        super.nofityUpdate(requestCode, bean);
    }

}
