package com.cnsunru.budget.activity;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.cnsunru.R;
import com.cnsunru.budget.adapters.ProjectListAdapter;
import com.cnsunru.budget.dialogs.DeleteConfimDialogFragment;
import com.cnsunru.budget.dialogs.SaveProjectDialogFragment;
import com.cnsunru.budget.mode.DefaltCalculateInfo;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.dialog.DataLoadDialogFragment;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.common.util.PageLimitDelegate;
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.cnsunru.home.mode.LocationStateMode;
import com.sunrun.sunrunframwork.adapter.ViewHodler;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.utils.AHandler;
import com.sunrun.sunrunframwork.utils.log.Logger;
import com.sunrun.sunrunframwork.weight.ListViewForScroll;

import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.cnsunru.budget.MaterialDetailsActivity.DELETE_PROJECT;
import static com.cnsunru.budget.ProjectRoomAddProjectItemActivity.ADD_PROJECT;
import static com.cnsunru.budget.dialogs.SaveProjectDialogFragment.SAVE_PROJECT;
import static com.cnsunru.budget.fragment.RoomCustomInfoFragment.MODIFY_DOOR_WINDOW;
import static com.cnsunru.budget.fragment.RoomInfoFragment.DEL_HOUSE;
import static com.cnsunru.budget.fragment.RoomInfoFragment.MODIFY_HOUSE;
import static com.cnsunru.common.quest.BaseQuestConfig.GET_CALCULATE_RESULT_CODE;
import static com.cnsunru.common.util.TestData.createTestData;

public class ProjectListActivity extends LBaseActivity {

    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.listViewForScroll)
    ListViewForScroll listViewForScroll;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.txtLocation)
    TextView txtLocation;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tvBaseMoney)
    TextView tvBaseMoney;
    @BindView(R.id.tvMaterialMoney)
    TextView tvMaterialMoney;
    @BindView(R.id.tvHouseType)
    TextView tvHouseType;
    @BindView(R.id.tvHouseArea)
    TextView tvHouseArea;

    @BindView(R.id.see_container)
    LinearLayout seeContainer;
    @BindView(R.id.goMaterialInfo)
    LinearLayout goMaterialInfo;
    @BindView(R.id.left_container)
    RelativeLayout leftContainer;
    LocationStateMode locationStateMode;
    PageLimitDelegate pageLimitDelegate = new PageLimitDelegate(new PageLimitDelegate.DataProvider() {
        @Override
        public void loadData(int page) {
            pageLimitDelegate.setData(createTestData(10, String.class));
        }
    });
    public static final String EVENT_REFRESH_PAGE = "refresh_ProjectListActivity";
    private View footView;
    DefaltCalculateInfo defaltCalculateInfo;
    boolean isNeedSave = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEventBus();
        setContentView(R.layout.activity_project_list);
        locationStateMode = new LocationStateMode(this, txtLocation);
        locationStateMode.disenableClick();
        defaltCalculateInfo = getSession().getObject(DefaltCalculateInfo.class.getName(), DefaltCalculateInfo.class);
        initListView();
    }

    private void initListView() {
        isNeedSave = "0".equals(defaltCalculateInfo.getStatus());
        tvMoney.setText(defaltCalculateInfo.getProject_all_money());
        tvMaterialMoney.setText(defaltCalculateInfo.getMaterial_all_money());
        tvBaseMoney.setText(defaltCalculateInfo.getPeople_all_money());
        tvHouseType.setText(defaltCalculateInfo.getHours_type_txt());
        tvHouseArea.setText(String.format("面积: %s㎡", defaltCalculateInfo.getAcreage()));
        final List<DefaltCalculateInfo.ListBean> roomList = defaltCalculateInfo.getList();
        ProjectListAdapter adapter = new ProjectListAdapter<DefaltCalculateInfo.ListBean>(that, roomList, R.layout.project_list_item) {
            @Override
            public void fillView(ViewHodler viewHodler, DefaltCalculateInfo.ListBean item, int i) {
                viewHodler.setText(R.id.tv_name, item.getHouse_title());
                viewHodler.setText(R.id.tv_area, String.format("%s㎡", item.getHouse_acreage()));
                viewHodler.setText(R.id.tv_money, String.format("¥%s", item.getHouse_all_money()));
                viewHodler.setVisibility(R.id.tv_money, true);
            }
        };
        listViewForScroll.setAdapter(adapter);
        if (listViewForScroll.getFooterViewsCount() == 0) {
            footView = LayoutInflater.from(that).inflate(R.layout.project_list_foot_view, null);
            listViewForScroll.addFooterView(footView);
        }
        listViewForScroll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DefaltCalculateInfo.ListBean listBean = roomList.get(position);
                getSession().put("edit_room_type", listBean.getHouse_type());
                startIntent.startProjectRoomDetailsActivity(that, listBean.getId());

            }
        });
        footView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent.startRoomInfoActivity(that, null, "");
            }
        });
        scrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollView.smoothScrollTo(0, 0);
            }
        }, 100);


    }


    @OnClick({R.id.see_container, R.id.goMaterialInfo, R.id.laySave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.see_container:
                startIntent.startProjectDetailsActivity(that, defaltCalculateInfo.getId());
                break;
            case R.id.goMaterialInfo:
                startIntent.startMaterialListInfoActivity(that);
                break;
            case R.id.laySave:
                SaveProjectDialogFragment.showFragment(getSupportFragmentManager(), defaltCalculateInfo.getId(), defaltCalculateInfo.getTitle());
                break;
        }

    }

    public void nofityUpdate(int requestCode, BaseBean bean) {
        switch (requestCode) {
            case GET_CALCULATE_RESULT_CODE:
                if (bean.status == 1) {
                    defaltCalculateInfo = bean.Data();
                    getSession().put(DefaltCalculateInfo.class.getName(), defaltCalculateInfo);
                    initListView();
                }
                break;
        }
        super.nofityUpdate(requestCode, bean);
    }

    @Override
    public void onBackPressed() {
        if(isNeedSave){
            /**
             * 提示保存
             */
            DeleteConfimDialogFragment.showFragment(getSupportFragmentManager(), "提示", "计算结果尚未保存,是否保存?","保存", new DeleteConfimDialogFragment.ConfirmActionListener() {
                @Override
                public void onConfirm(View view) {
                    //保存项目
                    SaveProjectDialogFragment.showFragment(getSupportFragmentManager(), defaltCalculateInfo.getId(), defaltCalculateInfo.getTitle());
                }
            }, new DeleteConfimDialogFragment.CancelActionListener() {
                @Override
                public void onConfirm(View view) {
                    finish();
                }
            });
        }else {
            super.onBackPressed();
        }
    }

    @Subscribe
    public void eventAction(String action) {
        switch (action) {
            case SAVE_PROJECT:
                isNeedSave = false;
                break;
            case DELETE_PROJECT:
                break;
            case MODIFY_DOOR_WINDOW://重新获取页面数据
            case MODIFY_HOUSE:
            case DEL_HOUSE:
            case ADD_PROJECT:
            case EVENT_REFRESH_PAGE://重新获取页面数据
                requestAsynPost(BaseQuestStart.get_calculate_result(defaltCalculateInfo.getId()));
                break;

        }
    }
}
