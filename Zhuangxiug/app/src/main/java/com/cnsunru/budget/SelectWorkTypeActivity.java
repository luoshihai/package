package com.cnsunru.budget;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cnsunru.R;
import com.cnsunru.budget.adapters.SelectWorkTypeAdapter;
import com.cnsunru.budget.mode.WorkTypeBean;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.event.MessageEvent;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.uiutils.UIUtils;
import com.sunrun.sunrunframwork.utils.EmptyDeal;
import com.sunrun.sunrunframwork.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;

import static com.cnsunru.common.event.EventConfig.WORK_TYPE;
import static com.cnsunru.common.quest.BaseQuestConfig.GET_LIST_PROJECT_OTHER_CODE;

/**
 * Created by cnsunrun on 2017/8/24.
 * <p>
 * 额外施工项选择
 */

public class SelectWorkTypeActivity extends LBaseActivity {

    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.list_view)
    ListView listView;
    private SelectWorkTypeAdapter selectCircleTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_work_type);
        titleBar.setLeftAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        titleBar.setRightAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSelectType();

            }
        });
        //获取施工清单
        UIUtils.showLoadDialog(that);
        BaseQuestStart.get_list_project_other(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void nofityUpdate(int requestCode, BaseBean bean) {
        switch (requestCode) {
            case GET_LIST_PROJECT_OTHER_CODE:
                if (bean.status != 0) {
                    List<WorkTypeBean>    circleTypeInfoList = bean.Data();
                    selectCircleTypeAdapter = new SelectWorkTypeAdapter(this, circleTypeInfoList);
                    if(!EmptyDeal.isEmpy(circleTypeInfoList)) {
                        String ids[] = getIntent().getStringExtra("ids").split(",");
                        for (int i = 0; i < circleTypeInfoList.size(); i++) {
                            WorkTypeBean workTypeBean = circleTypeInfoList.get(i);
                            if(ids[i].equals(workTypeBean.getId())){
                                selectCircleTypeAdapter.setSelectPosition(i);
                            }
                        }
                    }
                    listView.setAdapter(selectCircleTypeAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            selectCircleTypeAdapter.setSelectPosition(position);
                        }
                    });
                }
                break;
        }
        super.nofityUpdate(requestCode, bean);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    private void saveSelectType() {
        List<WorkTypeBean> allCheckData = selectCircleTypeAdapter.getAllCheckData();
//        WorkTypeBean selectItem = selectCircleTypeAdapter.getSelectItem();
        if (EmptyDeal.isEmpy(allCheckData)) {
            UIUtils.shortM("请选择施工项");
            return;
        }
        String ids = Utils.listToString(allCheckData, new Utils.DefaultToString<WorkTypeBean>(",") {
            @Override
            public String getString(WorkTypeBean workTypeBean) {
                return workTypeBean.getId();
            }
        });
        String titles = Utils.listToString(allCheckData, new Utils.DefaultToString<WorkTypeBean>(",") {
            @Override
            public String getString(WorkTypeBean workTypeBean) {
                return workTypeBean.getTitle();
            }
        });
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setType(WORK_TYPE);
//        messageEvent.setId(selectId);
        messageEvent.setTitle(titles);
        messageEvent.setContent(ids);
        EventBus.getDefault().post(messageEvent);
        onBackPressed();
    }

}

