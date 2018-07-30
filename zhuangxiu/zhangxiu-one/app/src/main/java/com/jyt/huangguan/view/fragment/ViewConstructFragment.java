package com.jyt.huangguan.view.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.jyt.huangguan.adapter.ConstructionAdapter;
import com.jyt.huangguan.api.BeanCallback;
import com.jyt.huangguan.bean.BaseJson;
import com.jyt.huangguan.bean.ConstructionBean;
import com.jyt.huangguan.bean.ProgressBean;
import com.jyt.huangguan.helper.IntentHelper;
import com.jyt.huangguan.helper.IntentKey;
import com.jyt.huangguan.itemDecoration.SpacesItemDecoration;
import com.jyt.huangguan.model.ProjectDetailModel;
import com.jyt.huangguan.util.BaseUtil;
import com.jyt.huangguan.view.activity.ConstructionActivity;
import com.jyt.huangguan.view.dialog.DatePickerDialog;
import com.jyt.huangguan.view.widget.JumpItem;
import com.jyt.huangguan.view.widget.TitleAndFlowImages;
import com.jyt.huangguan.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * 查看施工
 * Created by v7 on 2017/11/4.
 */

public class ViewConstructFragment extends BaseFragment {
    @BindView(R.id.v_emptyMsg)
    RelativeLayout vEmptyMsg;
    @BindView(R.id.jt_time)
    JumpItem mJtTime;
    @BindView(R.id.rv_data)
    RecyclerView mRvData;
    @BindView(R.id.btn_push)
    Button mBtnPush;
    Unbinder unbinder;

    ProgressBean mBean;
    String FinishTime;
    boolean isFinish;
    ProjectDetailModel projectDetailModel;
    DatePickerDialog mDatePicker;
    ConstructionAdapter mAdapter;
    List<ConstructionBean> list;
    private ViewConstructReceiver mReceiver;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_view_construct;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initData();
        initListener();

    }

    private void init() {
        mBean = getArguments().getParcelable(IntentKey.PROGRESS);
        mDatePicker = new DatePickerDialog(getContext());
        mAdapter = new ConstructionAdapter();
        list = new ArrayList<>();
        IntentFilter filter =new IntentFilter();
        filter.addAction(IntentKey.ActionConstruct);
        mReceiver = new ViewConstructReceiver();
        getActivity().registerReceiver(mReceiver,filter);
        mRvData.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mRvData.addItemDecoration(new SpacesItemDecoration(0,12));
        mRvData.setAdapter(mAdapter);
    }



    private void initListener(){
       mJtTime.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (isFinish){
                   mDatePicker.show();
               }

           }
       });
        mDatePicker.setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onSelected(String date) {
                mJtTime.setNext(false,date);
                FinishTime = date+" 0000";
            }
        });

        mBtnPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(FinishTime)){
                    BaseUtil.makeText("请输入预计到货日期");
                    return;
                }

                projectDetailModel.setFinishTime(mBean.getProjectId(), FinishTime, new BeanCallback<BaseJson>() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(BaseJson response, int id) {
                        if (response.ret){
                           try {
                               initData();
                               ConstructionActivity activity= (ConstructionActivity) getActivity();
                               activity.notifyPage();
                           } catch (Exception e){
                               getActivity().finish();
                           }
                        }
                    }
                });

            }
        });

        mAdapter.setOnImageClickListener(new TitleAndFlowImages.OnImageClickListener() {
            @Override
            public void onImaegClick(String imagePath) {
                int index1=0;
                int index2=0;
                for (int i = 0; i < list.size(); i++) {
                    for (int j = 0; j < list.get(i).getConstructionList().size(); j++) {
                        if (imagePath.equals(list.get(i).getConstructionList().get(j).getConstructionUrl())){
                            index1 = i;
                            index2 = j;
                            break;
                        }
                    }
                }
                List<String> imageList = new ArrayList();
                for (int i = 0; i < list.get(index1).getConstructionList().size(); i++) {
                    imageList.add(list.get(index1).getConstructionList().get(i).getConstructionUrl());
                }
                IntentHelper.openBrowseImagesActivity(getContext(),imageList,index2);
//                IntentHelper.openBrowseImagesActivity(getContext(),imagePath);
            }
        });
    }
    private boolean isFirst;
    private void initData(){
        //初始化界面
        list.clear();
        projectDetailModel.getConstructionData(mBean.getProjectId(), new BeanCallback<BaseJson<ConstructionBean>>() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(BaseJson<ConstructionBean> response, int id) {
                if (response.data.getFinishTime() != null) {
                    vEmptyMsg.setVisibility(View.GONE);
                    mJtTime.setNext(false,BaseUtil.getTime(response.data.getFinishTime().split(" ")[0]));
                    if (response.data!=null && response.data.getConstructionList()!=null && response.data.getConstructionList().size()>0){
                        String time=response.data.getConstructionList().get(0).getConstructionDate();
                        ConstructionBean detailBean = new ConstructionBean();
                        //整理数据
                        for (int i = 0; i < response.data.getConstructionList().size(); i++) {
                            //时间一致
                            if ("1".equals(response.data.getConstructionList().get(i).getConstructionType())){
                                if (!isFirst){
                                    if (!isFirst){
                                        if (detailBean.getConstructionList().size()>0){
                                            list.add(detailBean);
                                        }
                                        detailBean= new ConstructionBean();
                                        time=response.data.getConstructionList().get(i).getConstructionDate();
                                        isFirst=true;
                                    }
                                }
                                if (!time.equals(response.data.getConstructionList().get(i).getConstructionDate())){
                                    list.add(detailBean);
                                    detailBean= new ConstructionBean();
                                    time=response.data.getConstructionList().get(i).getConstructionDate();
                                }else {
                                    detailBean.getConstructionList().add(response.data.getConstructionList().get(i));

                                }

                            }else if (("2".equals(response.data.getConstructionList().get(i).getConstructionType()))){

                                detailBean.getConstructionList().add(response.data.getConstructionList().get(i));
                            }
                            //记得最后一组
                            if (i==response.data.getConstructionList().size()-1){
                                if (detailBean.getConstructionList().size()>0){
                                    list.add(detailBean);
                                }
                            }


                        }
                        if (list.size()>0){
                            mAdapter.notifyData(list);
                            isFinish=false;
                        }
                    }

                } else {
                    vEmptyMsg.setVisibility(View.VISIBLE);
                    isFinish=true;
                }
            }
        });
    }

    public void setProjectDetailModel(ProjectDetailModel projectDetailModel) {
        this.projectDetailModel = projectDetailModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private class ViewConstructReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            initData();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mReceiver);
    }
}
