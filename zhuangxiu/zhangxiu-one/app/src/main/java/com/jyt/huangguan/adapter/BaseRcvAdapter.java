package com.jyt.huangguan.adapter;

import android.support.v7.widget.RecyclerView;

import com.jyt.huangguan.view.viewholder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenweiqi on 2017/10/30.
 */

public abstract class BaseRcvAdapter extends RecyclerView.Adapter<BaseViewHolder> {


    List dataList;
    BaseViewHolder.OnViewHolderClickListener onViewHolderClickListener;
    BaseViewHolder.OnViewHolderLongClickListener onViewHolderLongClickListener;

    public List getDataList() {
        return dataList;
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }

    public void setOnViewHolderClickListener(BaseViewHolder.OnViewHolderClickListener onViewHolderClickListener) {
        this.onViewHolderClickListener = onViewHolderClickListener;
    }

    public void setOnViewHolderLongClickListener(BaseViewHolder.OnViewHolderLongClickListener onViewHolderLongClickListener) {
        this.onViewHolderLongClickListener = onViewHolderLongClickListener;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setData(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        if (dataList!=null){
            return dataList.size();
        }
        return 0;
    }

    public void notifyData(List dataList){
        if (dataList==null){
            dataList= new ArrayList();
        }
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public void LoadMoreData(List moreData){
        if (moreData!=null){
            dataList.addAll(moreData);
            notifyDataSetChanged();
        }
    }


}
