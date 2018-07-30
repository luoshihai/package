package com.cnsunru.budget;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cnsunru.R;
import com.cnsunru.budget.adapters.HoseTypeAdapter;
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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import butterknife.BindView;

import static com.cnsunru.common.quest.BaseQuestConfig.GET_CALCULATE_GETAPARTMENT_CODE;

/**
 * 房屋类型
 */
public class HouseTypeSelectActivity extends LBaseActivity {

    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.lRecyclerView)
    RecyclerView lRecyclerView;
    HoseTypeAdapter recordAdapter;
    private List<CalculateTypeInfo> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetRequestDialog netRequestDialog=new NetRequestDialog(this);
        setContentView(R.layout.activity_house_type);
        int type = getIntent().getIntExtra("type", 0);
        recordAdapter = new HoseTypeAdapter();
        lRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        lRecyclerView.setAdapter(recordAdapter);
        initData();

        titleBar.setRightAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinkedHashSet<String> house_patternTreeSet = new LinkedHashSet<String>();
                LinkedHashSet<String> titles=new LinkedHashSet<String>();
                List<CalculateTypeInfo> data = recordAdapter.getData();
                for (CalculateTypeInfo calculateTypeInfo : data) {
                    if(calculateTypeInfo.getChoose_num()!=null)
                    for (CalculateTypeInfo.ChooseNumBean chooseNumBean : calculateTypeInfo.getChoose_num()) {
                        if(chooseNumBean.getStatus()==1) {
                            house_patternTreeSet.add(chooseNumBean.getValue());
                            titles.add(chooseNumBean.getTitle());
                        }
                    }
                }
//                LinkedHashSet<String> titles = recordAdapter.getTitles();
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setType(EventConfig.CALCULATETYPEINFO);
                messageEvent.setTitles(titles);
                messageEvent.setHouse_patternTreeSet(house_patternTreeSet);
                EventBus.getDefault().post(messageEvent);
                finish();
            }
        });
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
        BaseQuestStart.getCalculateTypeData(that);
    }


    @Override
    public void nofityUpdate(int requestCode, BaseBean bean) {
        switch (requestCode) {
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
