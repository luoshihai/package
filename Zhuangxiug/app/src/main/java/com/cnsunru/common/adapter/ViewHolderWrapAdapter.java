package com.cnsunru.common.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sunrun.sunrunframwork.adapter.ViewHodler;
import com.sunrun.sunrunframwork.adapter.ViewHolderAdapter;

/**
 * Created by WQ on 2017/9/8.
 */

public class ViewHolderWrapAdapter extends BaseAdapter {
    BaseQuickAdapter baseQuickAdapter;
    public ViewHolderWrapAdapter(Context context,BaseQuickAdapter baseQuickAdapter) {
        this.baseQuickAdapter=baseQuickAdapter;
    }


    @Override
    public int getCount() {
        return baseQuickAdapter.getItemCount();
    }

    @Override
    public Object getItem(int position) {
        return baseQuickAdapter.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return baseQuickAdapter.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
