package com.cnsunru.budget.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.cnsunru.R;
import com.cnsunru.budget.ProjectRoomDetailsActivity;
import com.cnsunru.budget.adapters.DoorInfoAdapter;
import com.cnsunru.budget.adapters.ProjectListAdapter;
import com.cnsunru.budget.dialogs.DeleteConfimDialogFragment;
import com.cnsunru.budget.dialogs.InputDialogFragment;
import com.cnsunru.budget.mode.DefaltCalculateInfo;
import com.cnsunru.budget.mode.RoomInfoBean;
import com.cnsunru.common.CommonApp;
import com.cnsunru.common.base.LBaseFragment;
import com.cnsunru.common.event.EventConfig;
import com.cnsunru.common.event.MessageEvent;
import com.cnsunru.common.quest.BaseQuestStart;
import com.sunrun.sunrunframwork.adapter.ViewHodler;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.uiutils.UIUtils;
import com.sunrun.sunrunframwork.weight.ListViewForScroll;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.cnsunru.budget.activity.ProjectListActivity.EVENT_REFRESH_PAGE;
import static com.cnsunru.common.quest.BaseQuestConfig.ADD_HOUSE_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.DEL_HOUSE_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.EDIT_HOUSE_CODE;

/**
 * Created by WQ on 2017/9/14.
 *
 * @Describe 房间基本信息
 */

public class RoomInfoFragment extends LBaseFragment {
    @BindView(R.id.listInfos)
    ListViewForScroll listInfos;
    @BindView(R.id.layDelete)
    View layDelete;
    List<RoomInfoBean.HouseItemBean> roomBaseField;
    RoomInfoBean.HouseInfoBean house_info;
    RoomInfoBean roomInfoBean;
    ProjectListAdapter adapter;
    DefaltCalculateInfo defaltCalculateInfo;
    public boolean hasModify;
    public static final String DEL_HOUSE = "del_house";
    public static final String MODIFY_HOUSE = "modify_house";
    String id;

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initEventBus();
        id= that.getIntent().getStringExtra("id");
        defaltCalculateInfo = getSession().getObject(DefaltCalculateInfo.class.getName(), DefaltCalculateInfo.class);

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_room_info;
    }

    @OnClick(R.id.btnDelete)
    public void btnDelete(View view) {
        DeleteConfimDialogFragment.showFragment(getChildFragmentManager(), new DeleteConfimDialogFragment.ConfirmActionListener() {
            @Override
            public void onConfirm(View view) {
                UIUtils.showLoadDialog(that, "删除中..");
                BaseQuestStart.del_house(RoomInfoFragment.this, that.getIntent().getStringExtra("id"));
            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void nofityUpdate(int requestCode, BaseBean bean) {
        switch (requestCode) {
            case DEL_HOUSE_CODE:
                UIUtils.shortM(bean);
                if (bean.status == 1) {
                    EventBus.getDefault().post(DEL_HOUSE);
                    finish();
                }
                break;
            case ADD_HOUSE_CODE:
            case EDIT_HOUSE_CODE:
                UIUtils.shortM(bean);
                if (bean.status == 1) {
                    EventBus.getDefault().post(MODIFY_HOUSE);
                    finish();
                }
                break;
        }
        super.nofityUpdate(requestCode, bean);
    }

    @Subscribe(sticky = true)
    public void refreshPage(RoomInfoBean roomInfoBean) {
        this.roomInfoBean = roomInfoBean;
        house_info = roomInfoBean.house_info;
        layDelete.setVisibility(id == null ? View.GONE : View.VISIBLE);
         int inputNumberType=EditorInfo.TYPE_CLASS_NUMBER| EditorInfo.TYPE_NUMBER_FLAG_DECIMAL;
        roomBaseField = Arrays.asList(
                new RoomInfoBean.HouseItemBean("房间类型", house_info.title, "", "请选择房间类型"),
                new RoomInfoBean.HouseItemBean("房间长度", house_info.length, "m", "请输入房间长度").
                        inputType(inputNumberType),
                new RoomInfoBean.HouseItemBean("房间宽度", house_info.width, "m", "请输入房间宽度").
                        inputType(inputNumberType),
                new RoomInfoBean.HouseItemBean("房间高度", house_info.high, "m", "请输入房间高度").
                        inputType(inputNumberType),
                new RoomInfoBean.HouseItemBean("房间周长", house_info.perimeter, "m", "请输入房间周长").
                        inputType(inputNumberType),
                new RoomInfoBean.HouseItemBean("房间面积", house_info.acreage, "㎡", "请输入房间面积").
                        inputType(inputNumberType)
        );
        adapter = new ProjectListAdapter<RoomInfoBean.HouseItemBean>(that, roomBaseField, R.layout.project_list_item) {
            @Override
            public void fillView(final ViewHodler viewHodler, final RoomInfoBean.HouseItemBean item, final int positoin) {

                viewHodler.setText(R.id.tv_name, item.title);
                TextView tvValue = viewHodler.getView(R.id.tv_area);
                tvValue.setHint(item.hint);
                if (item.value != null) {
                    viewHodler.setText(R.id.tv_area, String.format("%s%s", item.value, item.unit));
                } else {
                    viewHodler.setText(R.id.tv_area, "");
                }
                if (id != null && positoin == 0) {
                    viewHodler.setVisibility(R.id.imgRight, false);
                    viewHodler.setClickListener(R.id.layItem,null);
                } else{
                    viewHodler.setVisibility(R.id.imgRight, true);
                    viewHodler.setClickListener(R.id.layItem, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (positoin == 0) {
                                startIntent.startRoomTypeSelectActivity(that, defaltCalculateInfo.getId());
                                return;
                            }
                            String title = item.title;
                            String hint = String.format("请输入%s", title);
                            InputDialogFragment.showFragment(getChildFragmentManager(), title, hint, item.value, item.inputType, new InputDialogFragment.ConfirmInputListener() {
                                @Override
                                public void onConfirmInput(EditText editText) {
                                    item.value = editText.getText().toString();
                                    viewHodler.setText(R.id.tv_area, String.format("%s%s", item.value, item.unit));
                                    hasModify = true;
//                                if(get)
//                                saveHouseInfo();
                                }
                            });

                        }
                    });
            }

        }
    }

    ;
    listInfos.setAdapter(adapter);

}

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventBusMethod(MessageEvent messageEvent) {
        switch (messageEvent.getType()) {
            case EventConfig.ROOM_TYPE_SELECT:
                house_info.title = messageEvent.getTitle();
                house_info.typeValue = messageEvent.getContent();
                hasModify = true;
                refreshPage(roomInfoBean);
                break;
        }
    }


    /**
     * 保存房间信息
     */
    public void saveHouseInfo() {
        house_info.title = roomBaseField.get(0).value;
        house_info.length = roomBaseField.get(1).value;
        house_info.width = roomBaseField.get(2).value;
        house_info.high = roomBaseField.get(3).value;
        house_info.perimeter = roomBaseField.get(4).value;
        house_info.acreage = roomBaseField.get(5).value;
        UIUtils.showLoadDialog(that);
        if (id == null) {
            BaseQuestStart.add_house(this, defaltCalculateInfo.getId(), house_info.typeValue,
                    house_info.length,
                    house_info.width,
                    house_info.high,
                    house_info.acreage,
                    house_info.perimeter
            );
        } else {
            String id = that.getIntent().getStringExtra("id");
            BaseQuestStart.edit_house(this,
                    id
                    , house_info.typeValue,
                    house_info.length,
                    house_info.width,
                    house_info.high,
                    house_info.acreage,
                    house_info.perimeter);
        }
    }


    public static RoomInfoFragment newInstance() {
        Bundle args = new Bundle();
        RoomInfoFragment fragment = new RoomInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
