package com.cnsunru.budget.adapters;


import android.text.format.Formatter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnsunru.R;
import com.cnsunru.common.boxing.GlideMediaLoader;

import java.io.File;

/**
 * @author WQ
 * 我的预算
 */
public class BudgetAdapter extends BaseQuickAdapter<File,BaseViewHolder> {



    public BudgetAdapter() {
        super(R.layout.item_budget);
    }

    @Override
    protected void convert(BaseViewHolder helper, File item) {
        GlideMediaLoader.load(mContext,helper.getView(R.id.imgProduct),item.getAbsolutePath());
        helper.setText(R.id.itemTitle,item.getName());
        helper.setText(R.id.itemSize, Formatter.formatFileSize(mContext,item.length()) );
        helper.setText(R.id.itemTitleDesc,item.getAbsolutePath());
    }
}
