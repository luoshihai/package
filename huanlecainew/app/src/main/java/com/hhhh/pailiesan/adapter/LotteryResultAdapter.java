package com.hhhh.pailiesan.adapter;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhhh.pailiesan.activity.LotteryFianlActivity;
import com.hhhh.pailiesan.model.LotteryModel;
import com.hhhh.pailiesan.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by QYQ on 2017/9/8.
 */

public class LotteryResultAdapter extends KBaseRecyclerAdapter<LotteryModel> {


    private android.content.Context Context;

    private NumAdapter adapter;
    private Intent intent;

    public LotteryResultAdapter(android.content.Context context) {
        super(context);
        Context = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_result, parent, false);
        ViewHolder mHolder = new ViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder hd = (ViewHolder) holder;
        final LotteryModel date = itemList.get(position);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        hd.rcyResult.setLayoutManager(layoutManager);
        adapter = new NumAdapter(context,date.getType());
        hd.rcyResult.setAdapter(adapter);
        hd.tvResultText1.setText(date.getType());
        hd.tvResultText2.setText("第"+date.getTermNo()+"期");
        hd.tvResultText3.setText(date.getDate());
        String msg = date.getResult();
        List<String> numbers = Arrays.asList(msg.split(","));
        final List<String> number = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i).contains("|")) {
                String str = numbers.get(i).replace("|",",");
                number.addAll(Arrays.asList(str.split(",")));
            } else {
                number.add(numbers.get(i));
            }
        }
        adapter.setItems(number);
        hd.llResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(context, LotteryFianlActivity.class);
                intent.putExtra("id",1);
                intent.putExtra("info",date);
                intent.putStringArrayListExtra("number", (ArrayList<String>) number);
                context.startActivity(intent);
            }
        });

    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_result_text1)
        TextView tvResultText1;
        @BindView(R.id.tv_result_text2)
        TextView tvResultText2;
        @BindView(R.id.tv_result_text3)
        TextView tvResultText3;
        @BindView(R.id.rcy_result)
        RecyclerView rcyResult;
        @BindView(R.id.ll_result)
        LinearLayout llResult;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}
