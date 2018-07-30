package com.cnsunru.user.adapters;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnsunru.R;
import com.cnsunru.common.widget.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @时间: 2017/5/18
 * @功能描述:
 */


public class ShoppingCarMultiTypeAdapter extends BaseMultiItemQuickAdapter<MultiItemHolder, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ShoppingCarMultiTypeAdapter(List<MultiItemHolder> data) {
        super(data);
        addItemType(MultiItemHolder.ITEM_SHOPPINGCAR, R.layout.view_shoppingcar_product_placeholder);
        addItemType(MultiItemHolder.ITEM_FAVORITE, R.layout.view_shoppingcar_favorite_product_placeholder);
    }

    ShoppingCarProductAdapter mShoppingCarProductAdapter;
    ShoppingCarFavoriteProductAdapter mShoppingCarFavoriteProductAdapter;

    @Override
    protected void convert(BaseViewHolder helper, MultiItemHolder item) {
        switch (helper.getItemViewType()) {
            case MultiItemHolder.ITEM_SHOPPINGCAR:
                initShoppingCarItem(helper.itemView.getContext(), helper, item);
                break;
            case MultiItemHolder.ITEM_FAVORITE:
                initGuessYourFavorite(helper.itemView.getContext(), helper, item);
                break;
        }
    }

    private void initGuessYourFavorite(Context context, BaseViewHolder helper, MultiItemHolder item) {
        if (mShoppingCarFavoriteProductAdapter == null) {
            mShoppingCarFavoriteProductAdapter = new ShoppingCarFavoriteProductAdapter();
            RecyclerView recyclerView = helper.getView(R.id.recyclerView);
            recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
            mShoppingCarFavoriteProductAdapter.bindToRecyclerView(recyclerView);
        }
        mShoppingCarFavoriteProductAdapter.setNewData(item.productList);
    }

    private void initShoppingCarItem(Context context, BaseViewHolder helper, MultiItemHolder item) {
        if (mShoppingCarProductAdapter == null) {
            mShoppingCarProductAdapter = new ShoppingCarProductAdapter();
            RecyclerView recyclerView = helper.getView(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.addItemDecoration(new RecyclerViewDivider(context, RecyclerViewDivider.VERTICAL, R.drawable.divider_inner_drawable));
            mShoppingCarProductAdapter.bindToRecyclerView(recyclerView);
        }
        mShoppingCarProductAdapter.setNewData(item.productList);
    }

    public static class MultipartViewHolder extends BaseViewHolder {

        public MultipartViewHolder(View view) {
            super(view);
            int itemViewType = getItemViewType();

        }
    }


}
