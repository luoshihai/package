package com.hhhh.pailiesan.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhhh.pailiesan.activity.TabTwoDetailActivity;
import com.hhhh.pailiesan.model.NewModel;
import com.hhhh.pailiesan.utils.BitmapHelp;
import com.hhhh.pailiesan.R;
import com.lidroid.xutils.BitmapUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by QYQ on 2017/9/14.
 */

public class NewsAdapter extends KBaseRecyclerAdapter<NewModel> {



    private android.content.Context Context;
    BitmapUtils bitmapUtils;

    public NewsAdapter(android.content.Context context) {
        super(context);
        Context = context;
        bitmapUtils = BitmapHelp.getBitmapUtils(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_new, parent, false);
        ViewHolder mHolder = new ViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder hd = (ViewHolder) holder;
        final NewModel date = itemList.get(position);
        hd.tvNewTitle.setText(date.getTitle());
        if(!TextUtils.isEmpty(date.getShorttitle())){
            hd.tvNewTime.setText(date.getShorttitle());
        }else {
            hd.tvNewTime.setText(date.getPubdate());
        }
        bitmapUtils.display(hd.ivNew,date.getImage());
        hd.llNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TabTwoDetailActivity.class);
                intent.putExtra("url",date.getUrl());
                context.startActivity(intent);
            }
        });
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_new)
        ImageView ivNew;
        @BindView(R.id.tv_new_title)
        TextView tvNewTitle;
        @BindView(R.id.tv_new_time)
        TextView tvNewTime;
        @BindView(R.id.ll_new)
        LinearLayout llNew;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }


}
