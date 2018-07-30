package com.cnsunru.home.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnsunru.R;
import com.cnsunru.common.boxing.GlideMediaLoader;
import com.cnsunru.home.mode.TraverGuideListInfo;

/**
 * @author WQ
 * 选材攻略列表适配器
 */
public class SelectionRaiderListAdapter extends BaseQuickAdapter<TraverGuideListInfo.ListBean,BaseViewHolder> {

    public SelectionRaiderListAdapter() {
        super(R.layout.item_raiders_article_);
    }

    @Override
    protected void convert(BaseViewHolder helper, TraverGuideListInfo.ListBean item) {
        GlideMediaLoader.load(mContext,helper.getView(R.id.imgProduct),item.getCover());
        helper.setText(R.id.itemTitle,item.getTitle());
        helper.setText(R.id.itemTitleDesc,item.getDescription());
        helper.setText(R.id.txtlookNum,String.format("查看数:%s",item.getViews()));
        helper.setText(R.id.txtTime,item.getAdd_time());
    }
}
