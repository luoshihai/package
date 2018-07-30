package com.hhhh.pailiesan.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhhh.pailiesan.activity.ForecastActivity;
import com.hhhh.pailiesan.model.ForecastTypeModel;
import com.hhhh.pailiesan.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by QYQ on 2017/9/25.
 */

public class ForecastTypeAdapter extends KBaseRecyclerAdapter<ForecastTypeModel> {



    private android.content.Context Context;

    public ForecastTypeAdapter(android.content.Context context) {
        super(context);
        Context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_forecast_type, parent, false);
        ViewHolder mHolder = new ViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder hd = (ViewHolder) holder;
        final ForecastTypeModel date = itemList.get(position);
        hd.tvTab3Text1.setText(date.getName());
        hd.tvTab3Text2.setText(date.getTitle());
        if ("双色球".equals(date.getName())) {
            hd.ivTab3.setImageResource(R.mipmap.ssq);
        } else if ("超级大乐透".equals(date.getName())) {
            hd.ivTab3.setImageResource(R.mipmap.dlt);
        } else if ("福彩3D".equals(date.getName())) {
            hd.ivTab3.setImageResource(R.mipmap.threed);
        } else if ("排列三".equals(date.getName())) {
            hd.ivTab3.setImageResource(R.mipmap.pls);
        } else if ("竞彩足球".equals(date.getName())) {
            hd.ivTab3.setImageResource(R.mipmap.zuqiu);
        } else if ("足彩".equals(date.getName())) {
            hd.ivTab3.setImageResource(R.mipmap.zucai);
        } else if ("北京单场".equals(date.getName())) {
            hd.ivTab3.setImageResource(R.mipmap.bjdc);
        }
        hd.llYuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ForecastActivity.class);
                intent.putExtra("gid", date.getGid() + "");
                context.startActivity(intent);
            }
        });
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_tab3)
        ImageView ivTab3;
        @BindView(R.id.tv_tab3_text1)
        TextView tvTab3Text1;
        @BindView(R.id.tv_tab3_text2)
        TextView tvTab3Text2;
        @BindView(R.id.ll_yuce)
        LinearLayout llYuce;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}
