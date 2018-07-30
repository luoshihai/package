package com.cnsunru.budget.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cnsunru.R;
import com.cnsunru.budget.mode.RoomInfoBean;
import com.cnsunru.common.event.OperateEvent;
import com.cnsunru.common.model.WeightCalculateMode;
import com.sunrun.sunrunframwork.adapter.ViewHodler;
import com.sunrun.sunrunframwork.adapter.ViewHolderAdapter;
import com.sunrun.sunrunframwork.uiutils.DisplayUtil;
import com.sunrun.sunrunframwork.uiutils.LayoutUtil;
import com.sunrun.sunrunframwork.uiutils.ScreenUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import win.smartown.android.library.tableLayout.TableLayout;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;


/**
 * Created by WQ on 2017/9/14.
 *
 * @Describe 门的信息适配器
 */

public class DoorInfoAdapter extends ViewHolderAdapter<RoomInfoBean.DoorItemBean> {
    final String[] fields = {"名称", "长度", "宽度", "操作"};
    WeightCalculateMode weightCalculateMode;

    public DoorInfoAdapter(Context context, List<RoomInfoBean.DoorItemBean> data) {
        super(context, data, R.layout.item_door_info_table);
        weightCalculateMode = new WeightCalculateMode(375, ScreenUtils.WHD(context)[0]);
    }

    @Override
    public void fillView(ViewHodler viewHodler,final RoomInfoBean.DoorItemBean item, int position) {
//        viewHodler.setVisibility(R.id.layBigTitle, position == 0);//一级标题
        viewHodler.setText(R.id.txtLab, item.title);//一级标题内容
        viewHodler.setVisibility(R.id.viewGrap, position > 0);//二级标题
        TableLayout materialsTable = viewHodler.getView(R.id.materialsTable);
      final   List<RoomInfoBean.DoorInfoBean> listInfos = item.listInfos;
        materialsTable.setAdapter(new BaseTableAdapter() {
            @Override
            public void onInterceptItemView(TextView itemView,final int row, int col) {
                itemView.setPadding(DisplayUtil.dp2px(mContext, 5), 0, DisplayUtil.dp2px(mContext, 5), 0);
                int width = (int) weightCalculateMode.getValForWeight((col == fields.length - 1) ? 75 : 100);
                LayoutUtil.setLayout(itemView, width, DisplayUtil.dp2px(mContext, 30));
//                LayoutUtil.setLayout(itemView, WRAP_CONTENT, DisplayUtil.dp2px(mContext, 30));
                if (row != 0 && col == 0) {
                    itemView.setTextColor(mContext.getResources().getColor(R.color.main_color_red));
                }
                //row=0是表头
                if(row!=0&&"移除".equals(itemView.getText())){
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RoomInfoBean.DoorInfoBean doorInfoBean = listInfos.get(row - 1);
                            String action=item.type==0?"del_door":"del_window";
                            EventBus.getDefault().post(OperateEvent.create(action,doorInfoBean));
                        }
                    });
                }
                itemView.setSingleLine(true);
                itemView.setEllipsize(TextUtils.TruncateAt.END);
            }

            @Override
            public int getRowCount() {
                return listInfos.size();
            }

            @Override
            public String getItemVal(int row, int col) {
                RoomInfoBean.DoorInfoBean doorInfoBean = listInfos.get(row);
                switch (col){
                    case 0:return String.valueOf(doorInfoBean.title).replaceAll("\n"," ");
                    case 1:return String.format("%sm",doorInfoBean.length);
                    case 2:return String.format("%sm",doorInfoBean.width);
                    case 3:return "移除";
                }
                return "";
            }

            @Override
            public String[] getHeadFields() {
                return fields;
            }
        });
    }
}
