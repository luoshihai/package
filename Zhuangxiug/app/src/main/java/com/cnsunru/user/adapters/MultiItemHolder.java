package com.cnsunru.user.adapters;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class MultiItemHolder implements MultiItemEntity {

        public static final int ITEM_SHOPPINGCAR = 0;
        public static final int ITEM_FAVORITE = 1;

        public List<String> productList;
        private int itemType;
        private int lookSpan;

        public MultiItemHolder(int lookSpan,int item, List<String> productList) {
            this.lookSpan = lookSpan;
            this.itemType = item;
            this.productList = productList;
        }

        @Override
        public int getItemType() {
            return itemType;
        }

    public int getLookSpan() {
        return lookSpan;
    }
}