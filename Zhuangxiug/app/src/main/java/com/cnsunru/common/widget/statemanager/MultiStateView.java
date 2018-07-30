package com.cnsunru.common.widget.statemanager;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cnsunru.R;

/**
 * 多状态视图
 * Created by WQ on 2017/9/22.
 */

public class MultiStateView {
    View contentView;
    ViewGroup multiStateLayout;
    private StateViewAdapter stateViewAdapter;
    SparseArray<View>viewStates=new SparseArray<>();
    public MultiStateView(View contentView) {
        this.contentView=contentView;
        ViewGroup rootView= (ViewGroup) contentView.getParent();
        multiStateLayout= (ViewGroup) LayoutInflater.from(contentView.getContext()).inflate(R.layout.view_multistate_layout, rootView,false);
        rootView.removeView(contentView);
        rootView.addView(multiStateLayout);
    }

    public void setStateViewAdapter(StateViewAdapter stateViewAdapter) {
        this.stateViewAdapter = stateViewAdapter;
    }

    /**
     * 切换状态
     * @param currentState
     */
    public void switchState(int currentState){
        StateViewAdapter stateViewAdapter = getStateViewAdapter();
        View stateView = getStateView(stateViewAdapter, currentState);
        multiStateLayout.removeAllViews();
        multiStateLayout.addView(stateView);
        stateViewAdapter.onShowStateView(currentState,stateView);
    }


    /**
     * 显示内容
     */
    public void showContent(){
        multiStateLayout.removeAllViews();
        multiStateLayout.addView(contentView);
    }
    private View getStateView(StateViewAdapter stateViewAdapter,int currentState){
        View layoutView = viewStates.get(currentState);
        if(layoutView==null){
            int layoutId = stateViewAdapter.getStateLayout()[currentState];
            layoutView = LayoutInflater.from(multiStateLayout.getContext()).inflate(layoutId, multiStateLayout, false);
            viewStates.put(currentState,layoutView);
        }
        return layoutView;
    }

    /**
     * 获取视图状态适配器
     * @return
     */
    private StateViewAdapter getStateViewAdapter(){
        if(stateViewAdapter==null){
            stateViewAdapter=new SimpleStateViewAdapter();
        }
        return stateViewAdapter;
    }
}
