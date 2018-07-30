package com.saadsdasd.niuniu.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.saadsdasd.niuniu.activity.TabTwoDetailActivity;
import com.saadsdasd.niuniu.model.TabTwoModel;
import com.saadsdasd.niuniu.utils.BitmapHelp;
import com.saadsdasd.niuniu.R;
import com.lidroid.xutils.BitmapUtils;

import butterknife.BindView;
import butterknife.ButterKnife;



/**
 * Created by QYQ on 2017/9/8.
 */

public class TabTwoAdapter extends KBaseRecyclerAdapter<TabTwoModel> {


    private android.content.Context Context;

    BitmapUtils bitmapUtils;

    public TabTwoAdapter(Context context) {
        super(context);
        Context = context;
        bitmapUtils = BitmapHelp.getBitmapUtils(context);
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_tab2, parent, false);
        ViewHolder mHolder = new ViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder hd = (ViewHolder) holder;
        final TabTwoModel bean = itemList.get(position);
        bitmapUtils.display(hd.ivTab2, bean.getPhoto());
        hd.tvTab2Text1.setText(bean.getTitle());
        hd.tvTab2Text2.setText(bean.getSummary());
        hd.tvTab2Text3.setText(bean.getDate());
        hd.llTab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TabTwoDetailActivity.class);
                intent.putExtra("url",bean.getContentUrl()+"");
                context.startActivity(intent);
            }
        });
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_tab2)
        ImageView ivTab2;
        @BindView(R.id.tv_tab2_text1)
        TextView tvTab2Text1;
        @BindView(R.id.tv_tab2_text2)
        TextView tvTab2Text2;
        @BindView(R.id.tv_tab2_text3)
        TextView tvTab2Text3;
        @BindView(R.id.ll_tab2)
        LinearLayout llTab2;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}
