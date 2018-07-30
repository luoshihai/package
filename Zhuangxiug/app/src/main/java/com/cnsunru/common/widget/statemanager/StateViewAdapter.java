package com.cnsunru.common.widget.statemanager;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by WQ on 2017/9/22.
 */
public interface StateViewAdapter {
    int getStateCount();

    int[] getStateLayout();

    void onShowStateView(int state, View view);
}
