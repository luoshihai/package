package com.cnsunru.budget;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.cnsunru.R;
import com.cnsunru.budget.adapters.ProjectListAdapter;
import com.cnsunru.budget.dialogs.InputDialogFragment;
import com.cnsunru.budget.mode.AllListBean;
import com.cnsunru.budget.mode.RoomInfoBean;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.common.util.Tool;
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.cnsunru.home.mode.HomeHotGoods;
import com.sunrun.sunrunframwork.adapter.ViewHodler;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.uiutils.UIUtils;
import com.sunrun.sunrunframwork.weight.ListViewForScroll;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.cnsunru.budget.mode.AllListBean.*;
import static com.cnsunru.common.quest.BaseQuestConfig.ADD_PROJECT_CUSTOMIZE_CODE;

/**
 * Created by WQ on 2017/10/12.
 *
 * @Describe 添加自定义项目
 */

public class ProjectRoomAddProjectItemActivity extends LBaseActivity {

    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.listInfos)
    ListViewForScroll listInfos;
    ProjectListAdapter adapter;
    List<RoomInfoBean.HouseItemBean> roomBaseField;
    HouseInfoBean house_info;
    public static final String ADD_PROJECT = "add_project";
    HomeHotGoods goods;
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        initEventBus();
        setContentView(R.layout.activity_room_addproject_info);
        house_info = getSession().getObject("house_room_info", HouseInfoBean.class);
        titleBar.setRightAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProject();
            }
        });
        int inputNumberType = EditorInfo.TYPE_CLASS_NUMBER | EditorInfo.TYPE_NUMBER_FLAG_DECIMAL;
        roomBaseField = Arrays.asList(
                new RoomInfoBean.HouseItemBean("施工项名称", "", "", "请输入施工项名称")
                        .inputType(InputType.TYPE_CLASS_TEXT),
                new RoomInfoBean.HouseItemBean("主材人工费用", "", "", "请输入主材人工费用")
                        .inputType(inputNumberType),
                new RoomInfoBean.HouseItemBean("产品选择", "", "", "请选择产品")
                        .inputType(inputNumberType),
                new RoomInfoBean.HouseItemBean("辅材费用", "", "", "请输入辅材费用")
                        .inputType(inputNumberType),
                new RoomInfoBean.HouseItemBean("人工费用", "", "", "请输入人工费用")
                        .inputType(inputNumberType),
                new RoomInfoBean.HouseItemBean("损耗", "", "", "请输入损耗")
                        .inputType(inputNumberType),
                new RoomInfoBean.HouseItemBean("施工面积", "", "", "请输入施工面积")
                        .inputType(inputNumberType)
        );

        adapter = new ProjectListAdapter<RoomInfoBean.HouseItemBean>(that, roomBaseField, R.layout.project_list_item) {
            @Override
            public void fillView(final ViewHodler viewHodler, final RoomInfoBean.HouseItemBean item, final int positoin) {
                viewHodler.setText(R.id.tv_name, item.title);
                TextView tvValue = viewHodler.getView(R.id.tv_area);
                tvValue.setHint(item.hint);
                if (!TextUtils.isEmpty(item.value)) {
                    viewHodler.setText(R.id.tv_area, String.format("%s%s", item.value, item.unit));
                } else {
                    viewHodler.setText(R.id.tv_area, "");
                }
                viewHodler.setClickListener(R.id.layItem, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(positoin==2){
                            startIntent.startProductCategorySelectActivity(that);
                            return;
                        }
                        String title = item.title;
                        String hint = String.format("请输入%s", title);
                        InputDialogFragment.showFragment(getSupportFragmentManager(), title, hint, item.value, item.inputType, new InputDialogFragment.ConfirmInputListener() {
                            @Override
                            public void onConfirmInput(EditText editText) {
                                item.value = editText.getText().toString();
                                viewHodler.setText(R.id.tv_area, String.format("%s%s", item.value, item.unit));
                            }
                        });
                    }
                });

            }
        };
        listInfos.setAdapter(adapter);
    }

    public void nofityUpdate(int requestCode, BaseBean bean) {
        switch (requestCode) {
            case ADD_PROJECT_CUSTOMIZE_CODE:
                UIUtils.shortM(bean);
                if (bean.status == 1) {
                    EventBus.getDefault().post(ADD_PROJECT);
                    finish();

                }
                break;
        }
        super.nofityUpdate(requestCode, bean);
    }

    public void saveProject() {
        if(goods==null){
            UIUtils.shortM("请选择产品");
            return;
        }
        String[] values = Tool.list2Array(roomBaseField, new Tool.BaseBeanTypeConvert<RoomInfoBean.HouseItemBean, String>(String.class) {
            @Override
            public String convert(RoomInfoBean.HouseItemBean houseItemBean) {
                return houseItemBean.value;
            }
        });
        UIUtils.showLoadDialog(that);
        BaseQuestStart.add_project_customize(this, goods.id,goods.price, house_info.calculate_project_info_id, house_info.id, values);
    }

    @Subscribe
    public void onProductSelected(HomeHotGoods goods){
        //产品选择后的结果
        roomBaseField.get(2).value=goods.title;
        this.goods=goods;
        adapter.notifyDataSetChanged();
    }
}
