package com.hhhh.pailiesan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;


import com.hhhh.pailiesan.utils.CollectsUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QYQ on 2017/9/5.
 */

public abstract class KBaseRecyclerAdapter<T>  extends RecyclerView.Adapter {
    protected Context context;
    protected LayoutInflater inflater;
    protected List<T> itemList = new ArrayList<T>();

    public KBaseRecyclerAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    /**
     * 在原有的数据上添加新数据
     *
     * @param itemList
     */
    public void addItems(List<T> itemList) {
        this.itemList.addAll(itemList);
        notifyDataSetChanged();
    }

    /**
     * 设置为新的数据，旧数据会被清空
     *
     * @param itemList
     */
    public void setItems(List<T> itemList) {
        this.itemList.clear();
        if(!CollectsUtil.isEmpty(itemList)){
            this.itemList.addAll(itemList);
        }
        notifyDataSetChanged();
    }

    /**
     * 清空数据
     */
    public void clearItems() {
        itemList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public List<T> getItem(){
        return itemList;
    }

    public void removeItem(int pos){
        if(itemList!=null) {
            itemList.remove(pos);
            notifyDataSetChanged();
        }
    }
}
