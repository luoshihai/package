package com.cnsunru.budget.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;


import com.cnsunru.R;
import com.cnsunru.budget.mode.WorkTypeBean;
import com.cnsunru.common.adapter.Selectable;
import com.cnsunru.common.adapter.SelectableHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cnsunrun on 2017/8/4.
 * <p>
 * 选择圈子行业列表适配器
 */

public class SelectWorkTypeAdapter extends BaseAdapter implements Selectable {

    private Context context;
    private List<WorkTypeBean> data;
    SelectableHelper<WorkTypeBean> selectableHelper = new SelectableHelper<>();

    @SuppressLint("UseSparseArrays")
    public SelectWorkTypeAdapter(Context context, List<WorkTypeBean> data) {
        this.context = context;
        this.data = data;
        selectableHelper.setData(data);
        selectMode(MULTISELECT);
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_select_work_type, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.itemCheck.setChecked(isSelected(position));
        viewHolder.itemTitle.setText(data.get(position).getTitle());
        return convertView;
    }

    public void setSelectPosition(int selectPosition) {
        selectableHelper.setSelectPosition(selectPosition);
        notifyDataSetChanged();
    }


    @Deprecated
    @Override
    public int getSelectPosition() {
        return selectableHelper.getSelectPosition();
    }

    @Override
    public int getSelectCount() {
        return selectableHelper.getSelectCount();
    }
    @Deprecated
    @Override
    public WorkTypeBean getSelectItem() {
        return selectableHelper.getSelectItem();
    }

    @Override
    public boolean isSelected(int position) {
        return selectableHelper.isSelected(position);
    }

    @Override
    public <T extends SelectableEntity> boolean isSelected(T item) {
        return selectableHelper.isSelected(item);
    }

    @Override
    public void selectAll(boolean isSelect) {
        selectableHelper.selectAll(isSelect);
    }

    @Override
    public void selectMode(int mode) {
        selectableHelper.selectMode(mode);
    }

    @Override
    public <T> List<T> getAllCheckData() {
        return (List<T>) selectableHelper.getAllCheckData();
    }

    public static class ViewHolder {
        @BindView(R.id.item_title)
        TextView itemTitle;
        @BindView(R.id.item_check)
        public RadioButton itemCheck;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
