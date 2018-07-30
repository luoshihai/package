package com.saadsdasd.niuniu.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.saadsdasd.niuniu.activity.ForecastResultActivity;
import com.saadsdasd.niuniu.model.NoticeModel;
import com.saadsdasd.niuniu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by QYQ on 2017/9/21.
 */

public class NoticeAdapter extends KBaseRecyclerAdapter<NoticeModel> {


    private android.content.Context Context;

    public NoticeAdapter(android.content.Context context) {
        super(context);
        Context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_notice, parent, false);
        ViewHolder mHolder = new ViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder hd = (ViewHolder) holder;
        final NoticeModel model = itemList.get(position);
        hd.tvNoticeTitle.setText(model.getTitle());
        hd.tvNoticeTime.setText(model.getDate());
        hd.notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ForecastResultActivity.class);
                intent.putExtra("type",1);
                intent.putExtra("url",model.getUrl());
                context.startActivity(intent);
            }
        });
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_noticeTitle)
        TextView tvNoticeTitle;
        @BindView(R.id.tv_noticeTime)
        TextView tvNoticeTime;
        @BindView(R.id.notice)
        LinearLayout notice;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }


}
