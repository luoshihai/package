package com.cnsunru.home.adapter;


import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnsunru.R;
import com.cnsunru.home.mode.CategoryListInfo;
import com.sunrun.sunrunframwork.uiutils.DisplayUtil;
import com.sunrun.sunrunframwork.uiutils.LayoutUtil;
import com.sunrun.sunrunframwork.utils.ScreenUtils;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * @author WQ
 * 选材攻略分类适配器
 */
public class SelectionRaiderCategoryAdapter extends BaseQuickAdapter<CategoryListInfo,BaseViewHolder> {
    int width;
    int selectIndex=0;
    public SelectionRaiderCategoryAdapter() {
        super(R.layout.item_simple_text);
    }

    @Override
    protected void convert(BaseViewHolder helper, CategoryListInfo item) {
        int position = helper.getLayoutPosition();
        int color=mContext.getResources().getColor(selectIndex==position?R.color.main_color_red:R.color.text4);
        helper.setTextColor(R.id.text,color);
        helper.setText(R.id.text,item.getTitle());
        View view = helper.getView(R.id.text);
        view.setPadding(DisplayUtil.dp2px(mContext,5),0,DisplayUtil.dp2px(mContext,5),0);
//        if(width==0) {
//            width = (ScreenUtils.WHD(mContext)[0] -DisplayUtil.dp2px(mContext,44)) / 7;
//        }

        LayoutUtil.setLayout(helper.getView(R.id.text),WRAP_CONTENT, DisplayUtil.dp2px(mContext,44));
//        LayoutUtil.setLayout(helper.getView(R.id.text),WRAP_CONTENT, WRAP_CONTENT);
    }

    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
        notifyDataSetChanged();
    }
}
