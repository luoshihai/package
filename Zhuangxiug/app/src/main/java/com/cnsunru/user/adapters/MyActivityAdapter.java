package com.cnsunru.user.adapters;


import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnsunru.R;
import com.cnsunru.common.boxing.GlideMediaLoader;
import com.cnsunru.common.model.MyActivityInfo;

/**
 * @author WQ
 * 参加的活动
 */
public class MyActivityAdapter extends BaseQuickAdapter<MyActivityInfo.ListBean,BaseViewHolder> {
    boolean isSmall;
    public boolean needBtn=true;
    public MyActivityAdapter() {
        super(R.layout.item_my_activity_);
    }
    public MyActivityAdapter(boolean isSmall) {
        super(R.layout.item_my_activity_small);
        this.isSmall=isSmall;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyActivityInfo.ListBean item) {
        helper.setText(R.id.itemTitle,item.getTitle());
        helper.setText(R.id.itemTitleDesc,String.format("活动时间: %s~%s",item.getStart_date(),item.getEnd_date()));
        helper.setText(R.id.txtProductPrice,"¥"+item.getPrice());
        TextView btnGo=helper.getView(R.id.btnGo);
        btnGo.setEnabled(item.getIs_sign()!=1);
        btnGo.setText(item.getIs_sign()!=1?"查看详情":"已报名");
        helper.setVisible(R.id.btnGo,needBtn);
        GlideMediaLoader.load(mContext,helper.getView(R.id.imgProduct),item.getCover());


    }
}
