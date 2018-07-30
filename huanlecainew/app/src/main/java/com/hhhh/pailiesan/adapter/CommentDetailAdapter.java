package com.hhhh.pailiesan.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lidroid.xutils.BitmapUtils;
import com.hhhh.pailiesan.R;
import com.hhhh.pailiesan.model.CommentModel;
import com.hhhh.pailiesan.utils.BitmapHelp;
import com.hhhh.pailiesan.utils.RoundImageView;

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
//        Picasso.with(context).load(date.getUser().getIcon()).into(hd.ivHeadimg);
        Glide
                .with(context)
                .load(date.getUser().getIcon())
                //.placeholder(R.mipmap.ic_launcher) //设置占位图
                .error(R.mipmap.empty) //设置错误图片
                .crossFade() //设置淡入淡出效果，默认300ms，可以传参
                //.dontAnimate() //不显示动画效果
                .into(hd.ivHeadimg);

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
