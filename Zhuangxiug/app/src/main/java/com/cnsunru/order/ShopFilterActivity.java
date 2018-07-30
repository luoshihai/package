package com.cnsunru.order;

import android.os.Bundle;

import com.cnsunru.R;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.order.fragment.MallFragment;

/**
 * Created by WQ on 2017/9/18.
 */

public class ShopFilterActivity extends LBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopfilter);
        MallFragment mallFragment = MallFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContent,mallFragment).commitAllowingStateLoss();
    }
}
