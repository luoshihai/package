package com.cnsunru.budget.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnsunru.R;
import com.cnsunru.budget.mode.CalculateDefaultData;
import com.cnsunru.budget.mode.DefaltCalculateInfo;
import com.cnsunru.common.base.LBaseFragment;
import com.cnsunru.common.dialog.DataLoadDialogFragment;
import com.cnsunru.common.event.EventConfig;
import com.cnsunru.common.event.LocationBean;
import com.cnsunru.common.event.MessageEvent;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.cnsunru.home.mode.LocationStateMode;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.uiutils.UIUtils;
import com.sunrun.sunrunframwork.utils.EmptyDeal;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.LinkedHashSet;

import butterknife.BindView;
import butterknife.OnClick;

import static com.cnsunru.common.event.EventConfig.WORK_TYPE;
import static com.cnsunru.common.quest.BaseQuestConfig.GET_CALCULATE_GET_DEFAULT_DATA_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.GET_COMMINT_DETAULT_CALCULATE_DETAIL_CODE;

/**
 * Created by WQ on 2017/8/31.
 *
 * @Describe 预算
 */

public class BudgetFragment extends LBaseFragment {
    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.txtLocation)
    TextView txtLocation;
    @BindView(R.id.layLocation)
    View layLocation;
    @BindView(R.id.editArea)
    EditText editArea;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    LocationStateMode locationStateMode;
    @BindView(R.id.tv_house_type)
    TextView tvHouseType;
    @BindView(R.id.imgBottom)
    ImageView imgBottom;
    @BindView(R.id.tv_work_type)
    TextView tvWorkType;

    private StringBuilder builder;

    private LinkedHashSet<String> house_patternTreeSet = new LinkedHashSet<>();
    CalculateDefaultData data;
    private String other_calculate_ids = "";

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initEventBus();
        if (getArguments().getBoolean("needFinish",false)) {
            titleBar.setLeftVisible(View.VISIBLE);
        }
        builder = new StringBuilder();
        locationStateMode = new LocationStateMode(that, txtLocation, "%s");
        initData();
    }

    private void initData() {
        if (data == null)
            BaseQuestStart.getCalculateDefaultData(BudgetFragment.this);
    }


    @Override
    public void nofityUpdate(int requestCode, BaseBean bean) {
        switch (requestCode) {
            case GET_CALCULATE_GET_DEFAULT_DATA_CODE:
                if (bean.status > 0) {
                    data = bean.Data();
                    setData2View(data);
                }
                break;
            case GET_COMMINT_DETAULT_CALCULATE_DETAIL_CODE:
                if (bean.status > 0) {
                    DataLoadDialogFragment.getInstance(getChildFragmentManager(), BaseQuestStart.get_calculate_result(bean.toString()), new DataLoadDialogFragment.onDataLoadeListener() {
                        @Override
                        public void onDataLoaded(BaseBean bean) {
                            DefaltCalculateInfo data = bean.Data();
                            getSession().put(DefaltCalculateInfo.class.getName(), data);
                            startIntent.startProjectListActivity(that);
                            if(getArguments().getBoolean("needFinish",false)){
                                finish();
                            }
                        }
                    });
                } else {
                    UIUtils.shortM(bean);
                }
                break;
        }
        super.nofityUpdate(requestCode, bean);
    }

    private void setData2View(CalculateDefaultData data) {
        if (data != null) {
            txtLocation.setText(String.format("%s", data.getArea_txt()));
            tvHouseType.setText(data.getHours_type_txt());
            editArea.setText(data.getAcreage());

            if(!EmptyDeal.isEmpy(data.hours_type)) {
                house_patternTreeSet.clear();
                house_patternTreeSet.addAll(data.hours_type);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventBusMethod(MessageEvent messageEvent) {
        switch (messageEvent.getType()) {
            case EventConfig.CALCULATETYPEINFO:
                if (builder == null) {
                    builder = new StringBuilder();
                }
                //清空builder里面的数据
                builder.delete(0, builder.length());
                house_patternTreeSet = messageEvent.getHouse_patternTreeSet();
                LinkedHashSet<String> titles = messageEvent.getTitles();
                for (String title : titles) {
                    builder.append(title).append(' ');
                }
                //设置房屋类型
                tvHouseType.setText(builder.toString());
                break;
            case WORK_TYPE:
                other_calculate_ids = messageEvent.getContent();
                tvWorkType.setText(messageEvent.getTitle());
                break;
        }
    }


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_budget;
    }

    public static BudgetFragment newInstance() {
        Bundle args = new Bundle();
        BudgetFragment budgetFragment = new BudgetFragment();
        budgetFragment.setArguments(args);
        return budgetFragment;
    }

    @Override
    public void onVisible() {
        initData();
        super.onVisible();
    }

    @OnClick(R.id.imgBottom)
    public void imgBottom() {//测试跳转
//        startIntent.startProjectDetailsActivity(that);
    }

    @OnClick({R.id.layHoseType, R.id.tv_work_type, R.id.btnSubmit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_work_type:
                startIntent.startSelectWorkTypeActivity(that, other_calculate_ids);
                break;
            case R.id.layHoseType:
                startIntent.startHouseTypeSelectActivity(that);
                break;
            case R.id.btnSubmit:
                String locationContent = txtLocation.getText().toString();
                String tvHouseContent = tvHouseType.getText().toString();
                String mAreaContent = editArea.getText().toString();
                if (TextUtils.isEmpty(locationContent)) {
                    UIUtils.shortM("请选择定位城市");
                    return;
                }
                if (TextUtils.isEmpty(tvHouseContent)) {
                    UIUtils.shortM("请选择房屋类型");
                    return;
                }
                if (TextUtils.isEmpty(mAreaContent)) {
                    UIUtils.shortM("请输入房屋的面积大小");
                    return;
                }
                UIUtils.showLoadDialog(that, "计算中..");
                BaseQuestStart.commitCalcatuDefault(BudgetFragment.this, mAreaContent, house_patternTreeSet, LocationBean.cityId, other_calculate_ids);
                break;
        }
    }


    @Subscribe
    public void onLocationChange(LocationBean locationBean) {
    }

}
