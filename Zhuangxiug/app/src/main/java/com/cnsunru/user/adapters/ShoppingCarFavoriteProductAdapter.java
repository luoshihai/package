package com.cnsunru.user.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnsunru.R;

/**
 * @作者: Liufan
 * @时间: 2017/5/18
 * @功能描述:
 */


public class ShoppingCarFavoriteProductAdapter extends BaseQuickAdapter<String,ShoppingCarFavoriteProductAdapter.FavoriteProductViewHolder> {

    public ShoppingCarFavoriteProductAdapter() {
        super(R.layout.item_guess_you_favorite_product);
    }

    @Override
    protected void convert(FavoriteProductViewHolder helper, String item) {

    }

    class FavoriteProductViewHolder extends BaseViewHolder{

        private ImageView imgProduct;

        public FavoriteProductViewHolder(View view) {
            super(view);

            Context context = view.getContext();
            int sw = context.getResources().getDisplayMetrics().widthPixels / 2;

            imgProduct = getView(R.id.imgProduct);
            ViewGroup.LayoutParams layoutParams = imgProduct.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.width = layoutParams.height = sw;
                imgProduct.setLayoutParams(layoutParams);
            }

        }
    }
}
