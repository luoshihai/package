package com.cnsunru.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
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
import com.cnsunru.common.widget.titlebar.TabTitleBar;
import com.cnsunru.home.adapter.CompanyListAdapter;
import com.cnsunru.home.mode.CampanyListInfo;
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
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.cnsunru.common.quest.BaseQuestConfig.GET_GET_CAMPANY_LIST_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.PUT_AREA_CODE;

/**
 * Created by WQ on 2017/9/4.
 *
 * @Describe 公司列表
 */

public class CompanyListActivity extends LBaseActivity implements PageLimitDelegate.DataProvider {
    @BindView(R.id.titleBar)
    TabTitleBar titleBar;
    @BindView(R.id.lRecyclerView)
    RecyclerView lRecyclerView;
    @BindView(R.id.lSwipeRefreshLayout)
    SwipeRefreshLayout lSwipeRefreshLayout;
    CompanyListAdapter companyListAdapter;
    PageLimitDelegate<CampanyListInfo.ListBean> pageLimitDelegate = new PageLimitDelegate(this);
    SortStateMode sortStateMode = new SortStateMode();
    @BindView(R.id.editSearch)
    EditText editSearch;
    @BindView(R.id.titleContainer)
    LinearLayout titleContainer;
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
    private LocationBean locationBean;
    private String sort;
    private LocationStateMode locationStateMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEventBus();
        setContentView(R.layout.activity_companylist);
        ButterKnife.bind(this);
        locationStateMode = new LocationStateMode(that, txtLocation);
        companyListAdapter = new CompanyListAdapter();
        lRecyclerView.setLayoutManager(new LinearLayoutManager(that, LinearLayoutManager.VERTICAL, false));
        GetEmptyViewUtils.bindEmptyView(that, companyListAdapter, 0, "暂无数据", true);
        lRecyclerView.setAdapter(companyListAdapter);
        pageLimitDelegate.attach(lSwipeRefreshLayout, lRecyclerView, companyListAdapter);
        companyListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                startIntent.startCompanyDetailsActivity(that, "");
                CampanyListInfo.ListBean item = companyListAdapter.getItem(position);
//                startIntent.startCompanyDetailsWebActivity(that, BaseQuestConfig.COMPANY_DETAIL_INFO+"?id="+item.getId(),item.getCompany_name(),item.getId());
                startIntent.startWebActivity(that, BaseQuestConfig.COMPANY_DETAIL_INFO+"?id="+item.getId(),item.getCompany_name());
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
        }, layDistance, layTime);
    }

    @OnClick(R.id.txtLocation)
    public void onClick() {
    }

    @Override
    public void loadData(int page) {
        if (locationBean != null) {
            BaseQuestStart.getCampanyList(that, locationBean.cityId, (float) locationBean.latitude, (float) locationBean.longitude, sort, 1);
        }
    }


    @Subscribe
    public void eventBusMethod(LocationBean locationBean) {
        this.locationBean = locationBean;
        if (txtLocation != null) {
            txtLocation.setText(String.format("所在城市: %s", locationBean.city));
            BaseQuestStart.put_area(this,locationBean.city);
//            BaseQuestStart.getCampanyList(that, 2, (float) locationBean.latitude, (float) locationBean.longitude, sort);
        }
    }

    @Override
    public void nofityUpdate(int requestCode, BaseBean bean) {
        switch (requestCode) {
            case GET_GET_CAMPANY_LIST_CODE:
                if (bean.status > 0) {
                    CampanyListInfo data = bean.Data();
                    List<CampanyListInfo.ListBean> list = data.getList();
                    if (list != null) {
                        pageLimitDelegate.setData(list);
                    }
                } else {
                    pageLimitDelegate.setData(new ArrayList<CampanyListInfo.ListBean>());
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
}
