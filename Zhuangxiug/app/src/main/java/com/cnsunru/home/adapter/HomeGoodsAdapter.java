package com.cnsunru.home.adapter;

import android.content.Context;
import android.widget.TextView;

import com.cnsunru.R;
import com.cnsunru.common.boxing.GlideMediaLoader;
import com.cnsunru.home.mode.HomeHotGoods;
import com.sunrun.sunrunframwork.adapter.ViewHodler;
import com.sunrun.sunrunframwork.adapter.ViewHolderAdapter;
import com.sunrun.sunrunframwork.utils.EmptyDeal;

import java.util.List;

/**
 * 首页商品适配器
 * Created by WQ on 2017/9/18.
 */

public class HomeGoodsAdapter extends ViewHolderAdapter<HomeHotGoods> {
    public HomeGoodsAdapter(Context context, List<HomeHotGoods> data) {
        super(context, data, R.layout.item_hot_goods_);
    }

    @Override
    public void fillView(ViewHodler viewHodler, HomeHotGoods item, int position) {
        viewHodler.setText(R.id.itemTitle,item.title);
        viewHodler.setText(R.id.itemTitleDesc,item.description);
        viewHodler.setText(R.id.txtProductPrice,String.format("¥ %s",item.explosion_price));
        viewHodler.setText(R.id.txtProductOldPrice,String.format("原价: ¥%s",item.price));
        viewHodler.setVisibility(R.id.itemTitleDesc, !EmptyDeal.isEmpy(item.description));
        TextView oldPrice = viewHodler.getView(R.id.txtProductOldPrice);
        oldPrice.getPaint().setStrikeThruText(true);
        GlideMediaLoader.load(mContext,viewHodler.getView(R.id.imgProduct),item.image);
    }
}
