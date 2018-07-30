package com.cnsunru.budget;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cnsunru.R;
import com.cnsunru.budget.adapters.HoseTypeAdapter;
import com.cnsunru.budget.adapters.RoomTypeAdapter;
import com.cnsunru.budget.mode.CalculateTypeInfo;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.event.EventConfig;
import com.cnsunru.common.event.MessageEvent;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.uiutils.NetRequestDialog;
import com.sunrun.sunrunframwork.uiutils.UIUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.LinkedHashSet;
import java.util.List;

import butterknife.BindView;

import static com.cnsunru.common.quest.BaseQuestConfig.ADD_HOUSE_TYPE_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.GET_CALCULATE_GETAPARTMENT_CODE;

/**
 * 房屋类型
 */
public class RoomTypeSelectActivity extends LBaseActivity {

    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.lRecyclerView)
    RecyclerView lRecyclerView;
    RoomTypeAdapter recordAdapter;
    private List<CalculateTypeInfo> data;
    String projectId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_type);
        int type = getIntent().getIntExtra("type", 0);
        projectId=getIntent().getStringExtra("projectId");
        recordAdapter = new RoomTypeAdapter();
        lRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        lRecyclerView.setAdapter(recordAdapter);
        initData();

        titleBar.setRightAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setType(EventConfig.ROOM_TYPE_SELECT);
                messageEvent.setTitle(recordAdapter.selectTitle);
                messageEvent.setContent(recordAdapter.selectValue);
                EventBus.getDefault().post(messageEvent);
                finish();
            }
        });
        titleBar.setTitle("选择房间类型");
//        recordAdapter.setType(type);

    }

    public int getSelect(CalculateTypeInfo calculateTypeInfo){
        if(calculateTypeInfo==null || calculateTypeInfo.getChoose_num()==null)return 0;
        for (int i = 0; i < calculateTypeInfo.getChoose_num().size(); i++) {
            if(calculateTypeInfo.getChoose_num().get(i).getStatus()==1)return i;
        }
        return 0;
    }

    private void initData() {
        UIUtils.showLoadDialog(that);
        BaseQuestStart.add_house_type(that,projectId);
    }


    @Override
    public void nofityUpdate(int requestCode, BaseBean bean) {
        switch (requestCode) {
            case ADD_HOUSE_TYPE_CODE:
            case GET_CALCULATE_GETAPARTMENT_CODE:
                if (bean.status > 0) {
                    data = bean.Data();
                    if (data != null) {
                        recordAdapter.setNewData(data);
                    }

                }
                break;
        }
        super.nofityUpdate(requestCode, bean);
    }
}
