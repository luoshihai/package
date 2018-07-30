package com.saadsdasd.niuniu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saadsdasd.niuniu.model.IntegrationModel;
import com.saadsdasd.niuniu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by QYQ on 2017/9/25.
 */

public class IntegrationAdapter extends KBaseRecyclerAdapter<IntegrationModel> {



    private android.content.Context Context;
    private String url;

    public IntegrationAdapter(android.content.Context context) {
        super(context);
        Context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_integration, parent, false);
        ViewHolder mHolder = new ViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder hd = (ViewHolder) holder;
        IntegrationModel model = itemList.get(position);
        hd.tvQiandao.setText(model.getType());
        hd.tvAdd.setText(model.getIntegration());

    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_qiandao)
        TextView tvQiandao;
        @BindView(R.id.tv_add)
        TextView tvAdd;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }


}
