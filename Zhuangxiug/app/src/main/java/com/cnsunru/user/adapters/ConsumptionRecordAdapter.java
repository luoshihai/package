package com.cnsunru.user.adapters;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnsunru.R;

/**
 * @author WQ
 * 资金记录
 */
public class ConsumptionRecordAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public ConsumptionRecordAdapter() {
        super(R.layout.item_consumption_record);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
