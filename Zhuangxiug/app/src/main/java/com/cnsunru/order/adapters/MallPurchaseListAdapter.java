package com.cnsunru.order.adapters;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnsunru.R;
import com.cnsunru.user.mode.MyProjectBean;

/**
 * Created by WQ on 2017/8/31.
 * @Describe 我的预算项目列表
 */

public class MallPurchaseListAdapter extends BaseQuickAdapter<MyProjectBean,BaseViewHolder> {
    String payStatusText[]={"未支付","已支付"};
    public MallPurchaseListAdapter() {
        super(R.layout.item_mall_purchase_);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyProjectBean item) {
        helper.setText(R.id.itemTitle,item.title);
        helper.setText(R.id.itemMoney,String.format("¥%s",item.project_all_money));
        helper.setText(R.id.itemOrderNo,String.format("流水号%s",item.order_no));
        helper.setText(R.id.itemTime,item.add_time);
        helper.setText(R.id.itemState,payStatusText[item.is_pay]);
//        helper.setText(R.id.itemNumber,item.change_num);
        helper.setVisible(R.id.itemNumber,(item.isNeedCalculate()));
    }
}
