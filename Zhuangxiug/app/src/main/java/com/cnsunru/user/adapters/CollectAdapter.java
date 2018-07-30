package com.cnsunru.user.adapters;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnsunru.R;

/**
 * @author WQ
 * 我的收藏
 */
public class CollectAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    boolean isEditMode=false;

    public boolean toggleEditMode() {
        isEditMode =!isEditMode;
        notifyDataSetChanged();
        return isEditMode;
    }

    public CollectAdapter() {
        super(R.layout.item_my_collect_);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setVisible(R.id.cbSelect,isEditMode);
    }
}
