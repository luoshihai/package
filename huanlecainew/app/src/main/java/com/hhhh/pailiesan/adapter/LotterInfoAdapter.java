package com.hhhh.pailiesan.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hhhh.pailiesan.model.LotteryDetailModel;
import com.hhhh.pailiesan.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by QYQ on 2017/9/18.
 */

public class LotterInfoAdapter extends KBaseRecyclerAdapter<LotteryDetailModel> {



    private android.content.Context Context;


    public LotterInfoAdapter(android.content.Context context) {
        super(context);
        Context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_lottery_info, parent, false);
        ViewHolder mHolder = new ViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder hd = (ViewHolder) holder;
        LotteryDetailModel date = itemList.get(position);
        hd.tvType.setText(date.getName());
        hd.moneyEvery.setText(date.getBetnum());
        hd.zhushu.setText(date.getPrize());
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.moneyEvery)
        TextView moneyEvery;
        @BindView(R.id.zhushu)
        TextView zhushu;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }


}
