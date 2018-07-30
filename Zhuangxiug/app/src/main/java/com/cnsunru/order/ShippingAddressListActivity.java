package com.cnsunru.order;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnsunru.R;
import com.cnsunru.common.base.LBaseActivity;

import java.util.Arrays;

public class ShippingAddressListActivity extends LBaseActivity {

    private RecyclerView mRecyclerView;
    private ShippingAddressListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_address_list);

        initRecyclerView();
        initAdapterData();
    }

    private void initAdapterData() {
        mAdapter.setNewData(Arrays.asList(new String[2]));
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.lRecyclerView);
        mAdapter = new ShippingAddressListAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

    }

    static class ShippingAddressListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public ShippingAddressListAdapter() {
            super(R.layout.item_shipping_address);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
        }
    }
}
