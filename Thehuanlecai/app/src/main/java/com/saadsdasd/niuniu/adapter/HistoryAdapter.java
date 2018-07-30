package com.saadsdasd.niuniu.adapter;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.saadsdasd.niuniu.activity.LotteryFianlActivity;
import com.saadsdasd.niuniu.model.HistoryModel;
import com.saadsdasd.niuniu.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by QYQ on 2017/9/19.
 */

public class HistoryAdapter extends KBaseRecyclerAdapter<HistoryModel> {


    private android.content.Context Context;

    private NumAdapter adapter;
    private Intent intent;
    private String Name;
    public HistoryAdapter(android.content.Context context, String name) {
        super(context);
        Context = context;
        this.Name = name;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_history, parent, false);
        ViewHolder mHolder = new ViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder hd = (ViewHolder) holder;
        final HistoryModel date = itemList.get(position);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        hd.rcyResult.setLayoutManager(layoutManager);
        adapter = new NumAdapter(context,date.getType());
        hd.rcyResult.setAdapter(adapter);
        hd.tvResultText2.setText("第"+date.getTerm()+"期");
        hd.tvResultText3.setText(date.getTime());
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
                intent.putExtra("id",2);
                intent.putExtra("name",Name);
                intent.putExtra("info",date);
                intent.putStringArrayListExtra("number", (ArrayList<String>) number);
                context.startActivity(intent);
            }
        });

    }


    static class ViewHolder extends RecyclerView.ViewHolder {
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

