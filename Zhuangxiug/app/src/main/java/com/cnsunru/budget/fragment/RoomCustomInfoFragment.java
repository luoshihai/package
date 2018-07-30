package com.cnsunru.budget.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.cnsunru.R;
import com.cnsunru.budget.adapters.DoorInfoAdapter;
import com.cnsunru.budget.adapters.ProjectListAdapter;
import com.cnsunru.budget.dialogs.DeleteConfimDialogFragment;
import com.cnsunru.budget.dialogs.InputDialogFragment;
import com.cnsunru.budget.mode.RoomInfoBean;
import com.cnsunru.common.base.LBaseFragment;
import com.cnsunru.common.dialog.DataLoadDialogFragment;
import com.cnsunru.common.event.OperateEvent;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.common.widget.SwitchView;
import com.sunrun.sunrunframwork.adapter.ViewHodler;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.http.utils.JsonDeal;
import com.sunrun.sunrunframwork.uiutils.UIUtils;
import com.sunrun.sunrunframwork.weight.ListViewForScroll;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.cnsunru.budget.fragment.RoomInfoFragment.MODIFY_HOUSE;
import static com.cnsunru.common.quest.BaseQuestConfig.CHANGE_DRY_WET_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.DEL_DOOR_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.DEL_WINDOWS_CODE;

/**
 * Created by WQ on 2017/9/14.
 *
 * @Describe 房间自定义编辑Fragment
 */

public class RoomCustomInfoFragment extends LBaseFragment {
    public static final String EDIT_ROOM_TYPE = "custom_info_edit_room_type";//1添加门 2,添加窗 3.材料替换
    @BindView(R.id.listInfos)
    ListViewForScroll listInfos;
    @BindView(R.id.listDoors)
    ListViewForScroll listDoors;
    @BindView(R.id.layBigTitle)
    View layBigTitle;


    List<RoomInfoBean.HouseItemBean> roomBaseField;
    List<RoomInfoBean.DoorInfoBean> door_info;
    List<RoomInfoBean.DoorInfoBean> window_info;
    DoorInfoAdapter materialInfoAdapter;
    String houseId;
    public static final String MODIFY_DOOR_WINDOW = "MODIFY_DOOR_WINDOW";
    @BindView(R.id.btnDryWet)
    SwitchView btnDryWet;

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        houseId = that.getIntent().getStringExtra("id");
        initEventBus();
        btnDryWet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeDryWet();

            }
        });
    }

    /**
     * 干湿分区切换
     */
    private void changeDryWet() {
        UIUtils.showLoadDialog(that,"操作中..");
        boolean opened = btnDryWet.isOpened();
        BaseQuestStart.change_dry_wet(this,houseId,opened?"1":"0");
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_room_custom_info;
    }

    @Subscribe(sticky = true)
    public void refreshPage(RoomInfoBean roomInfoBean) {
        RoomInfoBean.GanshiBean ganshi = roomInfoBean.ganshi;
        if(ganshi !=null){
            layBigTitle.setVisibility(ganshi.isShowGanShi()?View.VISIBLE:View.GONE);
            btnDryWet.setOpened(ganshi.isOpen());
        }
//        btnDryWet.setOpened(house_info.);
        ArrayList<RoomInfoBean.DoorItemBean> doorItems = new ArrayList<>();
        door_info = roomInfoBean.door_info;
        doorItems.add(new RoomInfoBean.DoorItemBean("添加门", "添加门", door_info, 0));
        window_info = roomInfoBean.window_info;
        doorItems.add(new RoomInfoBean.DoorItemBean("添加窗", "添加窗", window_info, 1));
        materialInfoAdapter = new DoorInfoAdapter(that, doorItems) {
            @Override
            public void fillView(ViewHodler viewHodler, RoomInfoBean.DoorItemBean item, final int position) {
                super.fillView(viewHodler, item, position);
                viewHodler.setClickListener(R.id.txtTotal, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position == 0) {
                            addDoor();
                        } else {
                            addWindow();
                        }
                    }
                });
            }
        };
        listDoors.setAdapter(materialInfoAdapter);
    }

    private void addDoor() {
        getSession().put(EDIT_ROOM_TYPE, 1);
        DataLoadDialogFragment.getInstance(getChildFragmentManager(), BaseQuestStart.add_door_category(), new DataLoadDialogFragment.onDataLoadeListener() {
            @Override
            public void onDataLoaded(BaseBean bean) {
                String cid = JsonDeal.createJsonObj(bean.toString()).optString("cid");
                startIntent.startGoodsListActivity(that, 3, cid);
            }
        });
    }

    private void addWindow() {
//        if(true){
//            UIUtils.shortM("添加窗");
//            return;
//        }
        getSession().put(EDIT_ROOM_TYPE, 2);
        DataLoadDialogFragment.getInstance(getChildFragmentManager(), BaseQuestStart.add_window_category(), new DataLoadDialogFragment.onDataLoadeListener() {
            @Override
            public void onDataLoaded(BaseBean bean) {
                String cid = JsonDeal.createJsonObj(bean.toString()).optString("cid");
                startIntent.startGoodsListActivity(that, 3, cid);
            }
        });
    }


    public static RoomCustomInfoFragment newInstance() {
        Bundle args = new Bundle();
        RoomCustomInfoFragment fragment = new RoomCustomInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void nofityUpdate(int requestCode, BaseBean bean) {
        switch (requestCode) {
            case DEL_DOOR_CODE:
            case DEL_WINDOWS_CODE:
                UIUtils.shortM(bean);
                if (bean.status == 1) {
                    if (requestCode == DEL_DOOR_CODE) {
                        door_info.remove(doorInfoBean);
                    } else {
                        window_info.remove(doorInfoBean);
                    }
                    materialInfoAdapter.notifyDataSetChanged();
                }
                break;
            case CHANGE_DRY_WET_CODE:
                UIUtils.shortM(bean);
                if (bean.status == 1) {
                    EventBus.getDefault().post(MODIFY_DOOR_WINDOW);
                }
                break;
        }
        super.nofityUpdate(requestCode, bean);
    }

    RoomInfoBean.DoorInfoBean doorInfoBean;

    @Subscribe
    public void dealDoor(final OperateEvent operateEvent) {
        doorInfoBean = operateEvent.getData();
        String title = "警告";
        String content = String.format("是否删除 \"%s\"", doorInfoBean.title);
        DeleteConfimDialogFragment.showFragment(getChildFragmentManager(), title, content, new DeleteConfimDialogFragment.ConfirmActionListener() {
            @Override
            public void onConfirm(View view) {
                doDeleAction(operateEvent);
            }
        });
    }


    private void doDeleAction(OperateEvent operateEvent) {
        UIUtils.showLoadDialog(that, "操作中..");

        if ("del_door".equals(operateEvent.action)) {
            BaseQuestStart.del_door(this, houseId, doorInfoBean.id);
        } else if ("del_window".equals(operateEvent.action)) {
            BaseQuestStart.del_windows(this, houseId, doorInfoBean.id);
        }
    }

//    干湿分区旧代码
    //        RoomInfoBean.HouseInfoBean house_info = roomInfoBean.house_info;
//        int inputNumberType = EditorInfo.TYPE_CLASS_NUMBER | EditorInfo.TYPE_NUMBER_FLAG_DECIMAL;
//        roomBaseField = Arrays.asList(
//                new RoomInfoBean.HouseItemBean("湿区周长", house_info.perimeter, "m", "请输入湿区周长")
//                        .inputType(inputNumberType)
//                ,
//                new RoomInfoBean.HouseItemBean("湿区面积", house_info.acreage, "㎡", "请输入湿区面积")
//                        .inputType(inputNumberType)
//        );
//        ProjectListAdapter adapter = new ProjectListAdapter<RoomInfoBean.HouseItemBean>(that, roomBaseField, R.layout.project_list_item) {
//            @Override
//            public void fillView(final ViewHodler viewHodler, final RoomInfoBean.HouseItemBean item, int i) {
//                viewHodler.setText(R.id.tv_name, item.title);
//                TextView tvValue = viewHodler.getView(R.id.tv_area);
//                tvValue.setHint(item.hint);
//                if (item.value != null) {
//                    viewHodler.setText(R.id.tv_area, String.format("%s%s", item.value, item.unit));
//                } else {
//                    viewHodler.setText(R.id.tv_area, "");
//                }
//                viewHodler.setClickListener(R.id.layItem, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        String title = item.title;
//                        String hint = String.format("请输入%s", title);
//                        InputDialogFragment.showFragment(getChildFragmentManager(), title, hint, item.value, item.inputType, new InputDialogFragment.ConfirmInputListener() {
//                            @Override
//                            public void onConfirmInput(EditText editText) {
//                                item.value = editText.getText().toString();
//                                viewHodler.setText(R.id.tv_area, String.format("%s%s", item.value, item.unit));
//                            }
//                        });
//                    }
//                });
//            }
//        };
//        listInfos.setAdapter(adapter);

}
