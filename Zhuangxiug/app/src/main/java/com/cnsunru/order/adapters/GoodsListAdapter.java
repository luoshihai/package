package com.cnsunru.order.adapters;


import android.app.Activity;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnsunru.R;
import com.cnsunru.common.boxing.GlideMediaLoader;
import com.cnsunru.common.model.PageBean;
import com.cnsunru.home.mode.HomeHotGoods;
import com.sunrun.sunrunframwork.utils.AHandler;
import com.sunrun.sunrunframwork.utils.EmptyDeal;
import com.sunrun.sunrunframwork.utils.log.Logger;

import java.util.HashSet;
import java.util.Set;

/**
 * @author WQ
 * 商品
 */
public class GoodsListAdapter extends BaseQuickAdapter<HomeHotGoods,BaseViewHolder> {

    int type ;
    PageBean pageBean;
    Set<BaseViewHolder> holderSet=new HashSet<>();
    public GoodsListAdapter() {
        super(R.layout.item_hot_goods_);
    }
    AHandler.Task task;
    public void setPageBean(final PageBean pageBean) {
        if(pageBean==null)return;
        this.pageBean = pageBean;
        if(type==1){
            AHandler.cancel(task);
            task = new AHandler.Task() {
                @Override
                public void update() {
                    Activity activitt = (Activity) mContext;
                    if (activitt != null && (activitt.isDestroyed() || activitt.isFinishing())) {
                        cancel();
                        return;
                    }
                    String limiteTime=pageBean.getLimteTime();
                    Logger.D("列表限时购倒计时:"+limiteTime);
                    for (BaseViewHolder baseViewHolder : holderSet) {
                        baseViewHolder.setText(R.id.txtLimitTime, limiteTime);
                    }
                }
            };
            AHandler.runTask(task,0,1000);
        }
    }

    public void setType(int type) {
        this.type = type;
        notifyDataSetChanged();

    }

    @Override
    protected void convert(BaseViewHolder helper, HomeHotGoods item) {
        helper.setVisible(R.id.txtLimitTime,type==1);
        helper.setVisible(R.id.txtProductOldPrice,false);
        helper.setText(R.id.itemTitle,item.title);
        helper.setText(R.id.itemTitleDesc,item.description);
        helper.setVisible(R.id.itemTitleDesc, !EmptyDeal.isEmpy(item.description));
        helper.setText(R.id.txtProductPrice,String.format("¥ %s",item.getPrice()));
        helper.setText(R.id.txtProductOldPrice,String.format("原价: ¥%s",item.price));
        TextView oldPrice = helper.getView(R.id.txtProductOldPrice);
        oldPrice.getPaint().setStrikeThruText(true);
        GlideMediaLoader.load(mContext,helper.getView(R.id.imgProduct),item.image);
        if(type==1){
            holderSet.add(helper);
        }
    }
}
