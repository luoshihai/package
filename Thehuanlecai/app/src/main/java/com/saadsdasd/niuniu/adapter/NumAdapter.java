package com.saadsdasd.niuniu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saadsdasd.niuniu.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by QYQ on 2017/9/5.
 */

public class NumAdapter extends KBaseRecyclerAdapter<String> {


    private android.content.Context Context;
    private String name;
    public NumAdapter(android.content.Context context, String lotteryName) {
        super(context);
        Context = context;
        this.name = lotteryName;
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_result_num, parent, false);
        ViewHolder mHolder = new ViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder hd = (ViewHolder) holder;
        String number = itemList.get(position);
        if("半全场".equals(name)||"胜负彩".equals(name)||"任选九".equals(name)||"进球彩".equals(name)||"任九场".equals(name)){
            hd.tvNumber.setBackgroundResource(R.drawable.cfx);
            hd.tvNumber.setTextColor(context.getResources().getColor(R.color.font_theme_color));
        }else if("新快3".equals(name)||"广东11选5".equals(name)||"11运夺金".equals(name)||"新11选5".equals(name)
                ||"时时彩".equals(name)||"幸运快3".equals(name)||"福彩3D".equals(name)||"排列三".equals(name)
                ||"排列五".equals(name)||"七星彩".equals(name)){
            hd.tvNumber.setWidth(70);
            hd.tvNumber.setHeight(70);
            hd.tvNumber.setBackgroundResource(R.drawable.shape);
            hd.tvNumber.setTextColor(context.getResources().getColor(R.color.white));
        }else if("双色球".equals(name)||"七乐彩".equals(name)){
            hd.tvNumber.setWidth(70);
            hd.tvNumber.setHeight(70);
            hd.tvNumber.setTextColor(context.getResources().getColor(R.color.white));
            if(position==(itemList.size()-1)){
                hd.tvNumber.setBackgroundResource(R.drawable.shape_green);
            }else {
                hd.tvNumber.setBackgroundResource(R.drawable.shape);
            }
        }else if("大乐透".equals(name)||"超级大乐透".equals(name)){
            hd.tvNumber.setWidth(70);
            hd.tvNumber.setHeight(70);
            hd.tvNumber.setTextColor(context.getResources().getColor(R.color.white));
            if(position==(itemList.size()-1)||position==(itemList.size()-2)){
                hd.tvNumber.setBackgroundResource(R.drawable.shape_green);
            }else {
                hd.tvNumber.setBackgroundResource(R.drawable.shape);
            }
        }
        hd.tvNumber.setText(number);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_number)
        TextView tvNumber;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }


}
