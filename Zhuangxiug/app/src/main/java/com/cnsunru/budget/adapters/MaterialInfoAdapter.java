package com.cnsunru.budget.adapters;

import android.content.Context;
import android.widget.TextView;

import com.cnsunru.R;
import com.sunrun.sunrunframwork.adapter.ViewHodler;
import com.sunrun.sunrunframwork.adapter.ViewHolderAdapter;
import com.sunrun.sunrunframwork.uiutils.DisplayUtil;
import com.sunrun.sunrunframwork.uiutils.LayoutUtil;

import java.util.ArrayList;
import java.util.List;

import win.smartown.android.library.tableLayout.TableLayout;


/**
 * Created by WQ on 2017/9/14.
 *
 * @Describe 材料清单, 材料清单适配器
 */

public class MaterialInfoAdapter extends ViewHolderAdapter {
    final String[] fields = {"材料名称", "数量", "单价", "小计"};

    public MaterialInfoAdapter(Context context, List data) {
        super(context, data, R.layout.item_materials_table);
    }

    @Override
    public void fillView(ViewHodler viewHodler, Object o, int position) {
//        viewHodler.setVisibility(R.id.layBigTitle, position == 0);//一级标题
        viewHodler.setText(R.id.txtLab,String.valueOf(o));//一级标题内容
        viewHodler.setVisibility(R.id.viewGrap, position>0 );//二级标题
        viewHodler.setVisibility(R.id.txtSmallTitle, false);//二级标题
        TableLayout materialsTable = viewHodler.getView(R.id.materialsTable);
        //fields是表格中要显示的数据对应到Content类中的成员变量名，其定义顺序要与表格中显示的相同
        final ArrayList<String[]> tables = new ArrayList<>();
        tables.add(new String[]{"铺设实木复合地板", "18.0", "m²", "¥1325.0"});
        tables.add(new String[]{"铺设实木复合地板", "18.0", "m²", "¥1325.0"});
        tables.add(new String[]{"铺设实木复合地板", "18.0", "m²", "¥1325.0"});
        materialsTable.setAdapter(new BaseTableAdapter() {
            @Override
            public void onInterceptItemView(TextView itemView, int row, int col) {
                itemView.setPadding(DisplayUtil.dp2px(mContext, 5), 0, DisplayUtil.dp2px(mContext, 5), 0);
                if (row == 0) {
                    LayoutUtil.setLayout(itemView, DisplayUtil.dp2px(mContext, 100), DisplayUtil.dp2px(mContext, 30));
                } else {
                    LayoutUtil.setLayout(itemView, DisplayUtil.dp2px(mContext, 100), DisplayUtil.dp2px(mContext, 42));
                    if (col == 0) {
                        itemView.setTextColor(mContext.getResources().getColor(R.color.main_color_red));
                    }
                }
            }

            @Override
            public int getRowCount() {
                return tables.size();
            }

            @Override
            public String getItemVal(int row, int col) {
                return tables.get(row)[col];
            }

            @Override
            public String[] getHeadFields() {
                return fields;
            }
        });
    }
}
