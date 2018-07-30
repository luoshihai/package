package com.cnsunru.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cnsunru.R;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.event.LocationBean;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.common.util.CenterLayoutManager;
import com.cnsunru.common.util.GetEmptyViewUtils;
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.cnsunru.home.adapter.CityListAdapter;
import com.cnsunru.home.mode.CityMode;
import com.cnsunru.home.mode.LocationStateMode;
import com.cnsunru.home.mode.SideBarSortMode;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.uiutils.UIUtils;
import com.sunrun.sunrunframwork.view.sidebar.SideBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;

import static com.cnsunru.common.quest.BaseQuestConfig.GET_AREA_CODE;

/**
 * Created by WQ on 2017/9/13.
 *
 * @Describe 城市选择
 */

public class CitySelectActivity extends LBaseActivity {

    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.cityRecyclerView)
    RecyclerView cityRecyclerView;
    @BindView(R.id.sideBar)
    SideBar sideBar;
    @BindView(R.id.txtTip)
    TextView txtTip;
    @BindView(R.id.editSearch)
    EditText editSearch;
    @BindView(R.id.locationText)
    TextView locationText;
    CityListAdapter cityListAdapter;
    SideBarSortMode sideBarSortMode = new SideBarSortMode();
    List<CityMode> cityModes;
    LocationBean locationBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
        initEventBus();
        sideBar.setTextView(txtTip);
        cityListAdapter = new CityListAdapter(sideBarSortMode);
        cityRecyclerView.setLayoutManager(new CenterLayoutManager(that, LinearLayoutManager.VERTICAL, false));
        GetEmptyViewUtils.bindEmptyView(that, cityListAdapter, 0, "暂无数据", true);
        cityRecyclerView.setAdapter(cityListAdapter);
        cityListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LocationBean locationBean=new LocationBean();
                CityMode cityMode=sideBarSortMode.getItem(position);
                locationBean.city=cityMode.getTitle();
                locationBean.cityId=cityMode.getId();
                EventBus.getDefault().post(locationBean);
                finish();
            }
        });

        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = sideBarSortMode.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    ((LinearLayoutManager)cityRecyclerView.getLayoutManager()).scrollToPositionWithOffset(position,0);
                }
            }
        });

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<CityMode> data = sideBarSortMode.getData(editSearch.getText().toString());
                cityListAdapter.setNewData(data);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        UIUtils.showLoadDialog(that);
        BaseQuestStart.getArea(this);
        if(LocationStateMode.locationBean!=null){
            eventBusMethod(LocationStateMode.locationBean);
        }
    }

    public void nofityUpdate(int requestCode,BaseBean bean){
        switch(requestCode){
            case GET_AREA_CODE:
                if(bean.status==1) {
                    cityModes=bean.Data();
                    sideBarSortMode.setSourceDateList(cityModes);
                    cityListAdapter.setNewData(cityModes);
                }
                break;
        }
        super.nofityUpdate(requestCode,bean);
    }

    @Subscribe
    public void eventBusMethod(LocationBean locationBean) {
        this.locationBean = locationBean;
        if (locationText != null) {
            locationText.setText(String.format("当前城市: %s", locationBean.city));
        }
    }
}
