package com.cnsunru.home.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnsunru.R;
import com.cnsunru.common.boxing.GlideMediaLoader;
import com.cnsunru.home.mode.BuidingListInfo;

/**
 * @author WQ
 * 在建工地
 */
public class WorkSiteListAdapter extends BaseQuickAdapter<BuidingListInfo.ListBean,BaseViewHolder> {

    public WorkSiteListAdapter() {
        super(R.layout.item_worksite_);
    }

    @Override
    protected void convert(BaseViewHolder helper, BuidingListInfo.ListBean item) {
        GlideMediaLoader.load(mContext,helper.getView(R.id.imgProduct),item.getCover());
        helper.setText(R.id.itemTitle,item.getTitle());
        helper.setText(R.id.itemAcreage,String.format("建筑面积 %sm²",item.getAcreage()));
        helper.setText(R.id.txtProductPrice,String.format("¥%s",item.getPrice()));
        helper.setText(R.id.txtDistance,String.format("%skm",item.getDistance()));
        helper.setVisible(R.id.txtDistance,item.getDistance()!=null);
    }
}
