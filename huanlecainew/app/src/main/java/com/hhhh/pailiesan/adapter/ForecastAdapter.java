package com.hhhh.pailiesan.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhhh.pailiesan.activity.ForecastResultActivity;
import com.hhhh.pailiesan.model.ForecastModel;
import com.hhhh.pailiesan.utils.DateUtils;
import com.hhhh.pailiesan.R;

import butterknife.BindView;
import butterknife.ButterKnife;



/**
 * Created by QYQ on 2017/9/11.
 */

public class ForecastAdapter extends KBaseRecyclerAdapter<ForecastModel> {


    private android.content.Context Context;
    private String url;
    public ForecastAdapter(android.content.Context context) {
        super(context);
        Context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_forecast, parent, false);
        ViewHolder mHolder = new ViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder hd = (ViewHolder) holder;
        ForecastModel date = itemList.get(position);
        hd.tvForecastText1.setText(date.getNtitle());
        hd.tvForecastText2.setText(date.getDescription());
        String time = DateUtils.timedate(date.getNdate());
        hd.tvForecastText3.setText(time);
        url = "http://mobile.9188.com"+date.getArcurl();
        hd.llItemYuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ForecastResultActivity.class);
                intent.putExtra("url",url);
                context.startActivity(intent);
            }
        });

    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_forecast_text1)
        TextView tvForecastText1;
        @BindView(R.id.tv_forecast_text2)
        TextView tvForecastText2;
        @BindView(R.id.tv_forecast_text3)
        TextView tvForecastText3;
        @BindView(R.id.ll_item_yuce)
        LinearLayout llItemYuce;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }


}
