package com.cnsunru.common.widget.statemanager;

import android.view.View;

import com.cnsunru.R;

/**
 * Created by WQ on 2017/9/22.
 */

public class SimpleStateViewAdapter implements StateViewAdapter {
    public static final int STATE_LODING = 0;
    public static final int STATE_EMPTY = 1;
    public static final int STATE_ERR_N = 2;
    public static final int STATE_ERR_NET = 3;
    int[]stateLayouts =new int[]{R.layout.view_simple_loding,R.layout.view_simple_empty,R.layout.view_simple_error};
    @Override
    public int getStateCount() {
        return stateLayouts.length;
    }

    @Override
    public int[] getStateLayout() {
        return stateLayouts;
    }

    @Override
    public void onShowStateView(int state, View view) {
    }
}
