package com.cnsunru.home.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnsunru.R;

/**
 * @author WQ
 * 公司方案列表
 */
public class CompanyPlanListAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public CompanyPlanListAdapter() {
        super(R.layout.item_company_plan);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setImageResource(R.id.imgProduct,R.drawable.project_img_banner);
    }
}
