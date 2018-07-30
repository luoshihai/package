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
import java.util.HashSet;
import java.util.List;

/**
 * Created by WQ on 2017/9/12.
 * @Describe  房间类型适配器
 */

public class RoomTypeAdapter extends BaseQuickAdapter<CalculateTypeInfo, BaseViewHolder> {

    public static  String selectValue="";
    public static  String selectTitle="";
    public RoomTypeAdapter() {
        super(R.layout.item_house_type);
    }


    @Override
    protected void convert(final BaseViewHolder helper, final CalculateTypeInfo item) {
        final TagFlowLayout typeTags = helper.getView(R.id.typeTags);
        final List<CalculateTypeInfo.ChooseNumBean> choose_numList = item.getChoose_num();
        helper.setText(R.id.txtTypeLab,item.getTitle());
        final int  height = DisplayUtil.dp2px(mContext, 30);
        final  int width = (ScreenUtils.WHD(mContext)[0] - DisplayUtil.dp2px(mContext, 40))/3;
        final int leftPadding = DisplayUtil.dp2px(mContext, 15);
        final TagAdapter<CalculateTypeInfo.ChooseNumBean> adapter = new TagAdapter<CalculateTypeInfo.ChooseNumBean>(choose_numList){
            @Override
            public View getView(FlowLayout parent, int position, CalculateTypeInfo.ChooseNumBean chooseNumBean) {
                //这个的position是表示的是  里面一层的position
                boolean isSelect =selectValue.equals(chooseNumBean.getValue());
                chooseNumBean.setStatus(isSelect ?  1: 0);
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
        adapter.notifyDataChanged();
        typeTags.setOnTagClickListener(new TagFlowLayout.OnTagClickListener(){

            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Log.e("position",position+"");//这个的position是表示的是  里面一层的position
                CalculateTypeInfo.ChooseNumBean chooseNumBean = item.getChoose_num().get(position);
                selectValue= chooseNumBean.getValue();
                selectTitle=chooseNumBean.getTitle();
                notifyDataSetChanged();
                return false;
            }
        });
    }

    @Override
    public void setNewData(@Nullable List<CalculateTypeInfo> data) {
        super.setNewData(data);

    }
}
