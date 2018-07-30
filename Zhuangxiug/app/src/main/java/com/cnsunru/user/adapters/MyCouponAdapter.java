package com.cnsunru.user.adapters;


import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnsunru.R;
import com.cnsunru.common.boxing.GlideMediaLoader;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.home.fragment.HomeFragment;
import com.cnsunru.home.mode.CouponBean;
import com.sunrun.sunrunframwork.uiutils.UIUtils;

/**
 * @author WQ
 * 优惠劵
 */
public class MyCouponAdapter extends BaseQuickAdapter<CouponBean,BaseViewHolder> {
    int type;//0列表, 1 我的优惠劵
    public MyCouponAdapter(int type) {
        super(R.layout.item_my_coupon);
        this.type=type;
    }


    @Override
    protected void convert(BaseViewHolder helper, CouponBean item) {
        helper.setText(R.id.itemTitle,item.title);
        helper.setText(R.id.itemTitleDesc,String.format("%s~%s",item.start_time,item.end_time));
        helper.setText(R.id.itemPrice,item.discount_money);
        GlideMediaLoader.load(mContext,helper.getView(R.id.imgProduct),item.cover);
        if(type==0){
            helper.setText(R.id.itemState, item.is_receive == 1 ? "已领取" : "点击领取");
            helper.getView(R.id.itemState).setEnabled(item.is_receive != 1);
          helper.addOnClickListener(R.id.itemState);
        }else {
            helper.setText(R.id.itemState, item.is_use == 0 ? "未使用" : "已使用");
        }
//        helper.setOnClickListener()
    }
}
