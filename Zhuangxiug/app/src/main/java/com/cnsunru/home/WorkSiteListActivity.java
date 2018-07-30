package com.cnsunru.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cnsunru.R;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.event.LocationBean;
import com.cnsunru.common.quest.BaseQuestConfig;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.common.util.GetEmptyViewUtils;
import com.cnsunru.common.util.PageLimitDelegate;
import com.cnsunru.common.util.ViewUtils;
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.cnsunru.home.adapter.WorkSiteListAdapter;
import com.cnsunru.home.mode.BuidingListInfo;
import com.cnsunru.home.mode.LocationStateMode;
import com.cnsunru.order.SortStateMode;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.http.utils.JsonDeal;
import com.sunrun.sunrunframwork.uiutils.UIUtils;

import org.greenrobot.eventbus.Subscribe;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.cnsunru.common.quest.BaseQuestConfig.GET_BUILDINGSITE_GET_LIST_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.PUT_AREA_CODE;

/**
 * Created by WQ on 2017/9/5.
 *
 * @Describe 在建工地列表
 */

public class WorkSiteListActivity extends LBaseActivity implements PageLimitDelegate.DataProvider {
    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.txtLocation)
    TextView txtLocation;
    @BindView(R.id.txtDistanceLab)
    TextView txtDistanceLab;
    @BindView(R.id.layDistance)
    FrameLayout layDistance;
    @BindView(R.id.txtTimeLab)
    TextView txtTimeLab;
    @BindView(R.id.layTime)
    FrameLayout layTime;
    @BindView(R.id.lRecyclerView)
    RecyclerView lRecyclerView;
    @BindView(R.id.lSwipeRefreshLayout)
    SwipeRefreshLayout lSwipeRefreshLayout;
    WorkSiteListAdapter workSiteListAdapter;

    PageLimitDelegate<BuidingListInfo.ListBean> pageLimitDelegate = new PageLimitDelegate(this);
    SortStateMode sortStateMode = new SortStateMode();
    private String sort;
    private String cityId;


    private LocationStateMode locationStateMode;
    private LocationBean locationBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEventBus();
        setContentView(R.layout.activity_worksite_list);
        locationStateMode = new LocationStateMode(that, txtLocation);
        workSiteListAdapter = new WorkSiteListAdapter();
        lRecyclerView.setLayoutManager(new LinearLayoutManager(that, LinearLayoutManager.VERTICAL, false));
        GetEmptyViewUtils.bindEmptyView(that, workSiteListAdapter, 0, "暂无数据", true);
        lRecyclerView.setAdapter(workSiteListAdapter);
        pageLimitDelegate.attach(lSwipeRefreshLayout, lRecyclerView, workSiteListAdapter);
        workSiteListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                startIntent.startWorkSiteDetailsActivity(that, "");
                BuidingListInfo.ListBean item = workSiteListAdapter.getItem(position);
                startIntent.startWorkSiteListWebActivity(that, BaseQuestConfig.BUIDINGSITE_DETAIL_INFO+"?id="+item.getId(),item.getTitle(),item.getId());
            }
        });
        sortStateMode.bindView(layTime,layDistance);
        ViewUtils.multiStateClickListener(3, 0, new ViewUtils.OnStateSwitchListener() {
            @Override
            public void onStateSwitch(View view, int currentState) {
                sortStateMode.clearState(view, layTime, layDistance);
                sortStateMode.stateSwitch(view, currentState);
                sort=sortStateMode.getSort(view,currentState);
                pageLimitDelegate.refreshPage();
            }
        },  layTime,layDistance);
    }

    @OnClick({R.id.layDistance, R.id.layTime})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layDistance:
                break;
            case R.id.layTime:
                break;
        }
    }

    @Override
    public void loadData(int page) {
        if (locationBean != null) {
            BaseQuestStart.getBuildingList(that, locationBean.cityId, (float) locationBean.latitude, (float) locationBean.longitude, sort, page);
        }
    }

    @Override
    public void nofityUpdate(int requestCode, BaseBean bean) {
        switch (requestCode) {
            case GET_BUILDINGSITE_GET_LIST_CODE:
                if (bean.status > 0) {
                    BuidingListInfo data = bean.Data();
                    List<BuidingListInfo.ListBean> list = data.getList();
                    if (list != null) {
                        pageLimitDelegate.setData(list);
                    } else {
                        pageLimitDelegate.setData(new ArrayList<BuidingListInfo.ListBean>());
                    }
                }
                break;
            case PUT_AREA_CODE:
                if(bean.status==1){
                    JSONObject jobj= JsonDeal.createJsonObj(bean.toString());
                    String id = jobj.optString("id");
                    locationBean.cityId=id;
                    BaseQuestStart.getBuildingList(that, locationBean.cityId, (float) locationBean.latitude, (float) locationBean.longitude, sort, 1);
                }else {
                    UIUtils.shortM(bean.msg);
                }
                break;
        }
        super.nofityUpdate(requestCode, bean);
    }

    @Subscribe
    public void eventBusMethod(LocationBean locationBean) {
        this.locationBean = locationBean;
        if (txtLocation != null) {
            txtLocation.setText(String.format("所在城市: %s", locationBean.city));
            BaseQuestStart.put_area(this,locationBean.city);
//            BaseQuestStart.getBuildingList(that, cityId, (float) locationBean.latitude, (float) locationBean.longitude, sort, 1);
        }
    }
}
