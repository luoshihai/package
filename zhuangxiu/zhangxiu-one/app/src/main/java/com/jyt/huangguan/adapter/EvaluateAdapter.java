package com.jyt.huangguan.adapter;

import android.view.ViewGroup;

import com.jyt.huangguan.view.viewholder.BaseViewHolder;
import com.jyt.huangguan.view.viewholder.EvaluateViewHolder;

/**
 * @author LinWei on 2017/11/7 10:41
 */
public class EvaluateAdapter extends BaseRcvAdapter {
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        EvaluateViewHolder holder=new EvaluateViewHolder(parent);
        return holder;
    }
}
