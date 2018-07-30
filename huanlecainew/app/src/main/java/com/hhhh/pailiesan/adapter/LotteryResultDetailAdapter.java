package com.hhhh.pailiesan.adapter;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhhh.pailiesan.activity.Lotteryfinal2Activity;
import com.hhhh.pailiesan.model.LotteryResultDetailModel;
import com.hhhh.pailiesan.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by QYQ on 2017/9/6.
 */

public class LotteryResultDetailAdapter extends KBaseRecyclerAdapter<LotteryResultDetailModel> {



    private android.content.Context Context;
    private NumAdapter adapter;

    public LotteryResultDetailAdapter(android.content.Context context) {
        super(context);
        Context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_lottery_detail, parent, false);
        ViewHolder mHolder = new ViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder hd = (ViewHolder) holder;
        final LotteryResultDetailModel date = itemList.get(position);
        adapter = new NumAdapter(context, date.getLotteryName());
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        hd.rcyTab011.setLayoutManager(layoutManager);
        hd.rcyTab011.setAdapter(adapter);
        String msg = date.getLotteryNumber();
        List<String> numbers = Arrays.asList(msg.split(","));
        final List<String> number = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i).contains(":")) {
                number.addAll(Arrays.asList(numbers.get(i).split(":")));
            } else {
                number.add(numbers.get(i));
            }
        }
        adapter.setItems(number);
        hd.tvTab1.setText("第" + date.getIssue() + "期");
        hd.tvTab2.setText(date.getHtime());
        hd.llLotteryDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Lotteryfinal2Activity.class);
                intent.putExtra("issue",date.getIssue());
                intent.putExtra("id",date.getLotId()+"");
                intent.putStringArrayListExtra("number", (ArrayList<String>) number);
                context.startActivity(intent);
            }
        });
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_tab1)
        TextView tvTab1;
        @BindView(R.id.tv_tab2)
        TextView tvTab2;
        @BindView(R.id.rcy_tab011)
        RecyclerView rcyTab011;
        @BindView(R.id.ll_lottery_detail)
        LinearLayout llLotteryDetail;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }


}
