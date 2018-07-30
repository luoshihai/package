package com.cnsunru.order.adapters;


import android.widget.GridView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnsunru.R;
import com.cnsunru.common.util.TestData;
import com.sunrun.sunrunframwork.adapter.ViewHodler;
import com.sunrun.sunrunframwork.adapter.ViewHolderAdapter;

import java.util.List;

/**
 * Created by WQ on 2017/8/31.
 * @Describe 商城预算详情列表
 */

public class MallPurchaseDetailsListAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public MallPurchaseDetailsListAdapter() {
        super(R.layout.item_purchase_goods);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        GridView gridCategory=helper.getView(R.id.gridCategory);
        List<String> testData = TestData.createTestData(6, String.class);
        gridCategory.setAdapter(new ViewHolderAdapter(mContext, testData,R.layout.item_purchase_goods_child) {
            @Override
            public void fillView(ViewHodler viewHodler, Object o, int i) {

            }
        });
    }
}
