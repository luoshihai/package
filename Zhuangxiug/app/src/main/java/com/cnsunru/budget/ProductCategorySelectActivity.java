package com.cnsunru.budget;

import android.os.Bundle;

import com.cnsunru.R;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.order.fragment.MallFragment;

/**
 * 产品分类选择, 这里直接加载商城页面
 * Created by WQ on 2017/10/17.
 */

public class ProductCategorySelectActivity extends LBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activit_product_category_select);
        MallFragment mallFragment = new MallFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("from_type", 5);
        mallFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_my_container, mallFragment)
                .commitAllowingStateLoss();
    }
}
