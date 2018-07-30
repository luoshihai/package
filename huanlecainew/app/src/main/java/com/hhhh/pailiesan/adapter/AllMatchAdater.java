package com.hhhh.pailiesan.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhhh.pailiesan.activity.MatchWebActivity;
import com.hhhh.pailiesan.model.MatchModel;
import com.hhhh.pailiesan.utils.BitmapHelp;
import com.hhhh.pailiesan.R;
import com.lidroid.xutils.BitmapUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by QYQ on 2017/9/13.
 */

public class AllMatchAdater extends KBaseRecyclerAdapter<MatchModel> {



    private android.content.Context Context;
    BitmapUtils bitmapUtils;

    public AllMatchAdater(android.content.Context context) {
        super(context);
        Context = context;
        bitmapUtils = BitmapHelp.getBitmapUtils(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_match_all, parent, false);
        ViewHolder mHolder = new ViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder hd = (ViewHolder) holder;
        final MatchModel date = itemList.get(position);
        hd.tvMatchText2.setText(date.getName());
        hd.tvMatchText3.setText(date.getSs() + "");
        if (!TextUtils.isEmpty(date.getSc())) {
            hd.tvMatchText5.setText(date.getSc());
            hd.tvMatchText5.setTextColor(context.getResources().getColor(R.color.green));
        } else {
            hd.tvMatchText5.setTextColor(context.getResources().getColor(R.color.font_light_black));
            hd.tvMatchText5.setText("未开始");
        }
        hd.tvMatchText4.setText(date.getHome());
        hd.tvMatchText6.setText(date.getAwary());
        bitmapUtils.display(hd.ivMatchLogo1, date.getHome_logo());
        bitmapUtils.display(hd.ivMatchLogo2, date.getGuest_logo());
        hd.llMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MatchWebActivity.class);
                intent.putExtra("id",date.getMatch_id()+"");
                context.startActivity(intent);
            }
        });
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_match_text2)
        TextView tvMatchText2;
        @BindView(R.id.tv_match_text3)
        TextView tvMatchText3;
        @BindView(R.id.tv_match_text5)
        TextView tvMatchText5;
        @BindView(R.id.tv_match_text4)
        TextView tvMatchText4;
        @BindView(R.id.tv_match_text6)
        TextView tvMatchText6;
        @BindView(R.id.iv_match_logo1)
        ImageView ivMatchLogo1;
        @BindView(R.id.iv_match_logo2)
        ImageView ivMatchLogo2;
        @BindView(R.id.ll_match)
        LinearLayout llMatch;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }


}
