package com.saadsdasd.niuniu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.saadsdasd.niuniu.R;
import com.saadsdasd.niuniu.model.TabThreeModel;
import com.saadsdasd.niuniu.utils.BitmapHelp;
import com.saadsdasd.niuniu.utils.RoundImageView;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.util.LogUtils;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by QYQ on 2017/9/8.
 */

public class TabThreeAdapter extends KBaseRecyclerAdapter<TabThreeModel> implements View.OnClickListener {



    private android.content.Context Context;
    BitmapUtils bitmapUtils;
    private int zan = 0;
    //声明自定义的监听接口
    private OnRecyclerviewItemClickListener mOnRecyclerviewItemClickListener = null;
    public TabThreeAdapter(android.content.Context context,OnRecyclerviewItemClickListener itemClickListener) {
        super(context);
        Context = context;
        bitmapUtils = BitmapHelp.getBitmapUtils(context);
        this.mOnRecyclerviewItemClickListener = itemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_tab3, parent, false);
        //这里 我们可以拿到点击的item的view 对象，所以在这里给view设置点击监听，
        view.setOnClickListener(this);
        ViewHolder mHolder = new ViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolder hd = (ViewHolder) holder;
        final TabThreeModel date = itemList.get(position);
        hd.tvName.setText(date.getUser().getName());
        Glide.with(context).load(date.getUser().getIcon()).into(hd.ivHeadImg);
        /*
        /long类型的时间戳转为日期
         */
        SimpleDateFormat long2FormatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String re_StrTime = long2FormatTime.format(date.getPublish_time());
        hd.tvTime.setText(re_StrTime);
        hd.tvContent.setText(date.getContent());
        if (date.getImages() != null) {
            hd.ivContent.setVisibility(View.VISIBLE);
            LogUtils.e("date.getImages()  : "+date.getImages());
            bitmapUtils.display(hd.ivContent, date.getImages().get(0));
        }else {
            hd.ivContent.setVisibility(View.GONE);
        }
        if (date.getPosition() != null) {
            hd.tvLocation.setText(date.getPosition().getLandmarker() + "");
        }
        final int zanNum = date.getThumb_up_count();
        hd.tvZanNum.setText(date.getThumb_up_count() + "");
        hd.tvCommentNum.setText(date.getComment_count() + "");
        hd.llZan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zan++;
                if (zan % 2 == 0) {
                    hd.tvZanNum.setText((zanNum)+"");
                    hd.ivZan.setImageResource(R.mipmap.zan_normal);
                    hd.tvZanNum.setTextColor(context.getResources().getColor(R.color.font_gray));
                    Toast.makeText(context,"取消成功",Toast.LENGTH_SHORT).show();
                } else {
                    hd.tvZanNum.setText((zanNum+1)+"");
                    hd.ivZan.setImageResource(R.mipmap.zan_selected);
                    hd.tvZanNum.setTextColor(context.getResources().getColor(R.color.tab_theme_color));
                    Toast.makeText(context,"点赞成功",Toast.LENGTH_SHORT).show();
                }
            }
        });
        hd.itemView.setTag(position);//给view设置tag以作为参数传递到监听回调方法中
    }

    @Override
    public void onClick(View v) {
        //将监听传递给自定义接口
        mOnRecyclerviewItemClickListener.onItemClickListener(v, ((int) v.getTag()));
    }

    public interface OnRecyclerviewItemClickListener {
        void onItemClickListener(View v,int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_headImg)
        RoundImageView ivHeadImg;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.iv_content)
        ImageView ivContent;
        @BindView(R.id.iv_location)
        ImageView ivLocation;
        @BindView(R.id.tv_location)
        TextView tvLocation;
        @BindView(R.id.tv_commentNum)
        TextView tvCommentNum;
        @BindView(R.id.ll_comment)
        LinearLayout llComment;
        @BindView(R.id.tv_zanNum)
        TextView tvZanNum;
        @BindView(R.id.ll_zan)
        LinearLayout llZan;
        @BindView(R.id.ll_taolun)
        LinearLayout llTaolun;
        @BindView(R.id.iv_zan)
        ImageView ivZan;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}
