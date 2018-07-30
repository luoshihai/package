package com.cnsunru.home.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnsunru.R;
import com.cnsunru.common.boxing.GlideMediaLoader;
import com.cnsunru.home.mode.CampanyListInfo;

/**
 * @author WQ
 * 公司
 */
public class CompanyListAdapter extends BaseQuickAdapter<CampanyListInfo.ListBean,BaseViewHolder> {

    public CompanyListAdapter() {
        super(R.layout.item_company_);
    }

    @Override
    protected void convert(BaseViewHolder helper, CampanyListInfo.ListBean item) {
        GlideMediaLoader.load(mContext,helper.getView(R.id.imgProduct),item.getImage());
        helper.setText(R.id.itemTitle,item.getCompany_name());
        helper.setText(R.id.itemnum,item.getDesign_num());
        helper.setText(R.id.txtDistance,String.format("%skm",item.getDistance()));
    }
}
