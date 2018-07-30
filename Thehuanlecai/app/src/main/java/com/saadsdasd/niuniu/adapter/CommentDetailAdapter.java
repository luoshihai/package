package com.saadsdasd.niuniu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.saadsdasd.niuniu.R;
import com.saadsdasd.niuniu.model.CommentModel;
import com.saadsdasd.niuniu.utils.BitmapHelp;
import com.saadsdasd.niuniu.utils.RoundImageView;
import com.lidroid.xutils.BitmapUtils;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by QYQ on 2017/9/22.
 */

public class CommentDetailAdapter extends KBaseRecyclerAdapter<CommentModel> {



    private android.content.Context Context;
    BitmapUtils bitmapUtils;
    public CommentDetailAdapter(android.content.Context context) {
        super(context);
        Context = context;
        bitmapUtils = BitmapHelp.getBitmapUtils(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_comment, parent, false);
        ViewHolder mHolder = new ViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder hd = (ViewHolder) holder;
        CommentModel date = itemList.get(position);

        Glide.with(context).load(date.getUser().getIcon()).into(hd.ivHeadimg);
        //bitmapUtils.display(hd.ivHeadimg,date.getUser().getIcon());
        hd.tvName1.setText(date.getUser().getName());
        /*
        /long类型的时间戳转为日期
         */
        SimpleDateFormat long2FormatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String re_StrTime = long2FormatTime.format(date.getPublish_time());
        hd.tvTime1.setText(re_StrTime);
        hd.tvContent1.setText(date.getContent());
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_headimg)
        RoundImageView ivHeadimg;
        @BindView(R.id.tv_name1)
        TextView tvName1;
        @BindView(R.id.tv_time1)
        TextView tvTime1;
        @BindView(R.id.tv_content1)
        TextView tvContent1;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
