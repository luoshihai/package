package com.cnsunru.order.adapters;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnsunru.R;
import com.cnsunru.common.util.TestData;
import com.cnsunru.common.widget.TagAdapter;
import com.cnsunru.common.widget.TagFlowLayout;
import com.cnsunru.order.mode.GoodsDetails;
import com.sunrun.sunrunframwork.uiutils.DisplayUtil;
import com.sunrun.sunrunframwork.uiutils.LayoutUtil;
import com.sunrun.sunrunframwork.uiutils.TextColorUtils;
import com.sunrun.sunrunframwork.weight.FlowLayout;

import java.util.Arrays;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by WQ on 2017/9/12.
 * @Describe  商城-商品属性适配器
 */

public class GoodsAttrAdapter extends BaseQuickAdapter<GoodsDetails.SpecBean, BaseViewHolder> {
    public GoodsAttrAdapter(@Nullable List<GoodsDetails.SpecBean> data) {
        super(R.layout.item_attr_category, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, GoodsDetails.SpecBean item) {
        final TagFlowLayout attrTags = helper.getView(R.id.attrTags);
     final int   height = DisplayUtil.dp2px(mContext, 30);
        final int leftPadding = DisplayUtil.dp2px(mContext, 15);
        final TagAdapter<GoodsDetails.SpecBean.ValueBean> adapter = new TagAdapter<GoodsDetails.SpecBean.ValueBean>(item.value) {
            @Override
            public View getView(FlowLayout parent, int position, GoodsDetails.SpecBean.ValueBean o) {
                boolean isSelect =getPreCheckedList().contains(position);
                View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_simple_text, parent, false);
                TextView text = (TextView) inflate.findViewById(R.id.text);
                text.setText(o.title);
                if (isSelect) {
                    text.setTextColor(Color.WHITE);
                    text.setBackgroundResource(R.drawable.shape_red_bg);
                } else {
                    text.setTextColor(mContext.getResources().getColor(R.color.text4));
                    text.setBackground(null);
                }
                text.setPadding(leftPadding, 0, leftPadding, 0);
                LayoutUtil.setLayout(text, WRAP_CONTENT, height);
                return inflate;
            }
        };
        helper.setText(R.id.txtCategoryLab,item.title);
        attrTags.setAdapter(adapter);
        adapter.setSelectedList(0);
        attrTags.setOnTagClickListener(new TagFlowLayout.OnTagClickListener(){

            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                adapter.getPreCheckedList().clear();
                adapter.setSelectedList(position);
                return false;
            }
        });
    }
}
