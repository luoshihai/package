package com.cnsunru.budget.adapters;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnsunru.R;
import com.cnsunru.budget.mode.CalculateTypeInfo;
import com.cnsunru.common.widget.TagAdapter;
import com.cnsunru.common.widget.TagFlowLayout;
import com.sunrun.sunrunframwork.uiutils.DisplayUtil;
import com.sunrun.sunrunframwork.uiutils.LayoutUtil;
import com.sunrun.sunrunframwork.utils.ScreenUtils;
import com.sunrun.sunrunframwork.weight.FlowLayout;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by WQ on 2017/9/12.
 *
 * @Describe 房屋类型适配器
 */

public class HoseTypeAdapter extends BaseQuickAdapter<CalculateTypeInfo, BaseViewHolder> {

    static List<HashSet<Integer>> selecteds = new ArrayList<>();


    public HoseTypeAdapter() {
        super(R.layout.item_house_type);
    }


    @Override
    protected void convert(final BaseViewHolder helper, CalculateTypeInfo item) {
        final TagFlowLayout typeTags = helper.getView(R.id.typeTags);
        final List<CalculateTypeInfo.ChooseNumBean> choose_numList = item.getChoose_num();
        helper.setText(R.id.txtTypeLab, item.getTitle());
        final int height = DisplayUtil.dp2px(mContext, 30);
        final int width = (ScreenUtils.WHD(mContext)[0] - DisplayUtil.dp2px(mContext, 40)) / 3;
        final int leftPadding = DisplayUtil.dp2px(mContext, 15);
        final TagAdapter<CalculateTypeInfo.ChooseNumBean> adapter = new TagAdapter<CalculateTypeInfo.ChooseNumBean>(choose_numList) {
            @Override
            public View getView(FlowLayout parent, int position, CalculateTypeInfo.ChooseNumBean chooseNumBean) {
                //这个的position是表示的是  里面一层的position
                boolean isSelect = getPreCheckedList().contains(position);
                chooseNumBean.setStatus(isSelect ? 1 : 0);
                View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_simple_text, parent, false);
                TextView text = (TextView) inflate.findViewById(R.id.text);
                text.setText(chooseNumBean.getTitle());
                int status = chooseNumBean.getStatus();
                if (status == 1) {
                    CalculateTypeInfo calculateTypeInfo = getData().get(helper.getPosition());
                    text.setTextColor(Color.WHITE);
                    text.setBackgroundResource(R.drawable.shape_red_bg);
                } else {
                    text.setTextColor(mContext.getResources().getColor(R.color.main_text_color));
                    text.setBackgroundResource(R.drawable.shape_gray_round_bg);
                }
                text.setPadding(leftPadding, 0, leftPadding, 0);
                LayoutUtil.setLayout(text, width, height);
                return inflate;
            }
        };

        typeTags.setAdapter(adapter);
//        adapter.setSelectedList(selecteds.get(helper.getLayoutPosition()));
        adapter.getPreCheckedList().addAll(selecteds.get(helper.getLayoutPosition()));
        adapter.notifyDataChanged();
        typeTags.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {

            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Log.e("position", position + "");//这个的position是表示的是  里面一层的position
//                adapter.getPreCheckedList().clear();
                HashSet<Integer> preCheckedList = adapter.getPreCheckedList();
                if (preCheckedList.contains(position)) {
                    preCheckedList.remove(position);
                } else {
                    preCheckedList.add(position);
                }
                if (selecteds.size() > helper.getLayoutPosition()) {
                    selecteds.set(helper.getLayoutPosition(), preCheckedList);
                } else {
                    selecteds.add(preCheckedList);
                }

                adapter.notifyDataChanged();

//                adapter.setSelectedList(position);
                return false;
            }
        });
    }

    @Override
    public void setNewData(@Nullable List<CalculateTypeInfo> data) {
        super.setNewData(data);
        if (data != null && (selecteds.size() != data.size())) {
            for (CalculateTypeInfo calculateTypeInfo : data) {
                HashSet<Integer> selects = new HashSet<>();
                List<CalculateTypeInfo.ChooseNumBean> choose_num = calculateTypeInfo.getChoose_num();
                if (choose_num != null) {
                    for (int i = 0, len = choose_num.size(); i < len; i++) {
                        CalculateTypeInfo.ChooseNumBean chooseNumBean = choose_num.get(i);
                        if (chooseNumBean.getStatus() == 1) {
                            selects.add(i);
                        }
                    }
                }
                selecteds.add(selects);
            }
        }

    }
}
