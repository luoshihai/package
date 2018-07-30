package com.cnsunru.budget;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.cnsunru.R;
import com.cnsunru.budget.adapters.ProjectMaterialsAdapter;
import com.cnsunru.budget.mode.AllListBean;
import com.cnsunru.budget.mode.DefaltCalculateInfo;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.common.util.ViewUtils;
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.cnsunru.home.mode.LocationStateMode;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.uiutils.UIUtils;
import com.sunrun.sunrunframwork.utils.EmptyDeal;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import ru.noties.scrollable.CanScrollVerticallyDelegate;
import ru.noties.scrollable.ScrollableLayout;

import static com.cnsunru.budget.MaterialDetailsActivity.DELETE_PROJECT;
import static com.cnsunru.budget.MaterialDetailsActivity.MODIFY_PROJECT;
import static com.cnsunru.budget.fragment.RoomCustomInfoFragment.MODIFY_DOOR_WINDOW;
import static com.cnsunru.budget.fragment.RoomInfoFragment.DEL_HOUSE;
import static com.cnsunru.budget.fragment.RoomInfoFragment.MODIFY_HOUSE;
import static com.cnsunru.common.quest.BaseQuestConfig.GET_ALL_LIST_CODE;
import static com.cnsunru.common.util.ViewUtils.radioSwitchListener;


/**
 * Created by WQ on 2017/9/14.
 *
 * @Describe 详细工程清单
 */

public class ProjectDetailsActivity extends LBaseActivity {
    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.txtLocation)
    TextView txtLocation;
    @BindView(R.id.tableMode)
    ImageView tableMode;
    @BindView(R.id.imgMode)
    ImageView imgMode;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tvBaseMoney)
    TextView tvBaseMoney;
    @BindView(R.id.tvMaterialMoney)
    TextView tvMaterialMoney;
    @BindView(R.id.scrollableLayout)
    ScrollableLayout scrollableLayout;
    ProjectMaterialsAdapter adapter;
    LocationStateMode locationStateMode;
    public List<MultiItemEntity> first_list;
    DefaltCalculateInfo defaltCalculateInfo;
    boolean isTable=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEventBus();
        setContentView(R.layout.activity_project_details);
        locationStateMode = new LocationStateMode(this, txtLocation);
        locationStateMode.disenableClick();
        adapter = new ProjectMaterialsAdapter(that, null);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        radioSwitchListener(0, new ViewUtils.OnRadioSwitchListener() {//切换显示
            @Override
            public void onRadioSwitch(View[] views, int selectIndex) {
                int resTable = selectIndex == 0 ? R.drawable.project_icon_table_c : R.drawable.project_icon_table_n;
                int resImg = selectIndex == 0 ? R.drawable.project_icon_img_n : R.drawable.project_icon_img_c;
                tableMode.setImageResource(resTable);
                imgMode.setImageResource(resImg);
                adapter.setTableMode(selectIndex == 0);
            }
        }, tableMode, imgMode);

        scrollableLayout.setCanScrollVerticallyDelegate(new CanScrollVerticallyDelegate() {
            @Override
            public boolean canScrollVertically(int direction) {
                return recyclerView.canScrollVertically(direction);
            }
        });
        defaltCalculateInfo = getSession().getObject(DefaltCalculateInfo.class.getName(), DefaltCalculateInfo.class);
        setTopData();
        loadPageData();
    }

    private void loadPageData() {
        UIUtils.showLoadDialog(this, "加载中...");
        BaseQuestStart.get_all_list(this, getIntent().getStringExtra("id"));
    }

    /**
     * 设置头部数据
     */
    private void setTopData() {
        tvMoney.setText(defaltCalculateInfo.getProject_all_money());
        tvMaterialMoney.setText(defaltCalculateInfo.getMaterial_all_money());
        tvBaseMoney.setText(defaltCalculateInfo.getPeople_all_money());
    }

    public void nofityUpdate(int requestCode, BaseBean bean) {
        switch (requestCode) {
            case GET_ALL_LIST_CODE:
                if (bean.status == 1) {
                    List<AllListBean> allListBeans = bean.Data();
                    setData2Page(allListBeans);
                } else {
                    UIUtils.shortM(bean);
                }
                break;
        }
        super.nofityUpdate(requestCode, bean);
    }

    /**
     * 设置页面数据,
     * @param allListBeans
     */
    private void setData2Page(List<AllListBean> allListBeans) {
        first_list = AllListBean.converList(allListBeans);
        adapter.setNewData(first_list);
        adapter.setTableMode(isTable);
        adapter.notifyDataSetChanged();
    }

    @Subscribe
    public void eventAction(String action){
        switch (action)
        {
            case MODIFY_DOOR_WINDOW://重新获取页面数据
            case DEL_HOUSE:
            case MODIFY_HOUSE:
            case  DELETE_PROJECT:
            case MODIFY_PROJECT:
                loadPageData();
                break;
        }
    }

}
