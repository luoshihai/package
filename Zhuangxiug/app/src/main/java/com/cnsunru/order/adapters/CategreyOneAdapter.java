package com.cnsunru.order.adapters;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnsunru.R;
import com.cnsunru.order.mode.GoodsCategory;
import com.sunrun.sunrunframwork.uiutils.TextColorUtils;

import java.util.List;

/**
 * Created by WQ on 2017/9/12.
 * @Describe  商城分类
 */

public class CategreyOneAdapter extends BaseQuickAdapter<GoodsCategory, BaseViewHolder> {
    int position;

    public CategreyOneAdapter(@Nullable List<GoodsCategory> data, int position) {
        super(R.layout.item_categrey_one, data);
        this.position = position;
    }

    public void setPosition(int currentPosition) {
        position = currentPosition;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsCategory item) {
        TextView tvClassOne = helper.getView(R.id.tv_cate_grey_one);
        tvClassOne.setText(item.title);
        if (position == helper.getLayoutPosition()) {
            TextColorUtils.setCompoundDrawables(tvClassOne,R.drawable.shop_icon_checked,0,0,0);
            tvClassOne.getPaint().setFakeBoldText(true);
        } else {
            tvClassOne.getPaint().setFakeBoldText(false);
            TextColorUtils.setCompoundDrawables(tvClassOne,0,0,0,0);
        }
    }
}
