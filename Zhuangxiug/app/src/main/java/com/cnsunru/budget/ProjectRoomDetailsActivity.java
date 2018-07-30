package com.cnsunru.budget;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
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
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import ru.noties.scrollable.CanScrollVerticallyDelegate;
import ru.noties.scrollable.ScrollableLayout;

import static com.cnsunru.budget.MaterialDetailsActivity.DELETE_PROJECT;
import static com.cnsunru.budget.MaterialDetailsActivity.MODIFY_PROJECT;
import static com.cnsunru.budget.ProjectRoomAddProjectItemActivity.ADD_PROJECT;
import static com.cnsunru.budget.fragment.RoomCustomInfoFragment.MODIFY_DOOR_WINDOW;
import static com.cnsunru.budget.fragment.RoomInfoFragment.DEL_HOUSE;
import static com.cnsunru.budget.fragment.RoomInfoFragment.MODIFY_HOUSE;
import static com.cnsunru.common.quest.BaseQuestConfig.GET_ALL_LIST_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.GET_HOUSE_LIST_CODE;
import static com.cnsunru.common.util.ViewUtils.radioSwitchListener;


/**
 * Created by WQ on 2017/9/14.
 *
 * @Describe 详细工程清单
 */

public class ProjectRoomDetailsActivity extends LBaseActivity {
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
    AllListBean.HouseInfoBean house_info;
    public List<MultiItemEntity> first_list;
    boolean isTable=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
        locationStateMode = new LocationStateMode(this, txtLocation);
        locationStateMode.disenableClick();
        initEventBus();
        adapter = new ProjectMaterialsAdapter(that, null){
            @Override
            protected void dealLargeTitle(BaseViewHolder helper, Object item, int position) {
                super.dealLargeTitle(helper, item, position);
                helper.setText(R.id.txtTotal,"编辑房间");
                helper.setText(R.id.txtOther,"添加自定义项目");
                helper.setVisible(R.id.txtOther,true);
                helper.setOnClickListener(R.id.txtTotal, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startIntent.startRoomInfoActivity(that, house_info.id, house_info.house_title);
                    }
                });
                helper.setOnClickListener(R.id.txtOther, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startIntent.startProjectRoomAddProjectItemActivity(that);
                    }
                });
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        radioSwitchListener(0, new ViewUtils.OnRadioSwitchListener() {//切换显示
            @Override
            public void onRadioSwitch(View[] views, int selectIndex) {
                int resTable = selectIndex == 0 ? R.drawable.project_icon_table_c : R.drawable.project_icon_table_n;
                int resImg = selectIndex == 0 ? R.drawable.project_icon_img_n : R.drawable.project_icon_img_c;
                tableMode.setImageResource(resTable);
                imgMode.setImageResource(resImg);
                adapter.setTableMode(isTable=selectIndex == 0);
            }
        }, tableMode, imgMode);

        scrollableLayout.setCanScrollVerticallyDelegate(new CanScrollVerticallyDelegate() {
            @Override
            public boolean canScrollVertically(int direction) {
                return recyclerView.canScrollVertically(direction);
            }
        });

        loadPageData();
    }

    private void loadPageData() {
        UIUtils.showLoadDialog(this, "加载中...");
        BaseQuestStart.get_house_list(this, getIntent().getStringExtra("id"));
    }

    /**
     * 设置头部数据
     */
    private void setTopData(AllListBean item) {
        house_info=item.house_info;
        getSession().put("house_room_info",house_info);
        AllListBean.HouseInfoBean house_info = item.house_info;
        tvMoney.setText(house_info.house_all_money);
        tvMaterialMoney.setText(house_info.house_material_money);
        tvBaseMoney.setText(house_info.house_people_money);
    }

    public void nofityUpdate(int requestCode, BaseBean bean) {
        switch (requestCode) {
            case GET_HOUSE_LIST_CODE:
                if (bean.status == 1) {
                    AllListBean item=bean.Data();

                    ArrayList<AllListBean> items = new ArrayList<>();
                    items.add(item);
                    AllListBean.HouseInfoBean house_info = item.house_info;
                    item.house_title=house_info.house_title;
                    item.house_acreage=house_info.house_acreage;
                    item.house_all_money=house_info.house_all_money;
                    setTopData(item);
                    setData2Page(items);

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
            case DEL_HOUSE:
                finish();
                break;
            case MODIFY_DOOR_WINDOW://重新获取页面数据
            case ADD_PROJECT:
            case MODIFY_HOUSE:
            case  DELETE_PROJECT:
            case MODIFY_PROJECT:
                loadPageData();
                break;
        }
    }


}
