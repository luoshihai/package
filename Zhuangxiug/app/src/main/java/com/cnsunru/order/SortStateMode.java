package com.cnsunru.order;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cnsunru.R;
import com.cnsunru.common.util.ViewUtils;
import com.sunrun.sunrunframwork.uiutils.TextColorUtils;

import java.util.HashMap;

/**
 * 排序按钮状态处理
 * Created by WQ on 2017/9/12.
 */

public class SortStateMode {
    HashMap<View,String[]> sorts=new HashMap<>();

    public SortStateMode() {
    }

    public void bindView(View ...layTime){
        if(layTime.length>0)
        sorts.put(layTime[0],new String[]{null,"1","2"});
        if(layTime.length>1)
        sorts.put(layTime[1],new String[]{null,"3","4"});
        if(layTime.length>2)
        sorts.put(layTime[2],new String[]{null,"5","6"});

    }

    public void stateSwitch(View view, int state){
        TextView  textView = (TextView) ((ViewGroup) view).getChildAt(0);
        int rightDraw=0;
        switch (state){
            case 0:
                rightDraw= R.drawable.site_icon_sort_normal;
                break;
            case 1:
                rightDraw= R.drawable.site_icon_sort_down;
                break;
            case 2:
                rightDraw= R.drawable.site_icon_sort_up;
                break;
        }
        TextColorUtils.setCompoundDrawables(textView,0,0,rightDraw,0);
    }

    public String  getSort(View view,int state){
        return sorts.get(view)[state];
    }

    public void clearState(View ingoreView,View...views){
        ViewUtils.clearMultiState(ingoreView,views);
        for (View view : views) {
            if(view!=ingoreView) {
                stateSwitch(view, 0);
            }
        }
    }
}
