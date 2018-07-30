package com.cnsunru.budget.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.cnsunru.R;
import com.cnsunru.budget.adapters.BaseTableAdapter;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.sunrun.sunrunframwork.uiutils.DisplayUtil;
import com.sunrun.sunrunframwork.uiutils.LayoutUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import win.smartown.android.library.tableLayout.TableLayout;

public class DetailedEngineeringList extends LBaseActivity {

    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.materialsTable1)
    TableLayout materialsTable1;
    @BindView(R.id.materialsTable2)
    TableLayout materialsTable2;
    final String[] fields = {"材料名称", "数量", "单价", "小计"};
    private ArrayList<String[]> tables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_engineering_list);
        ButterKnife.bind(this);
        initMaterialsTable();
    }

    private void initMaterialsTable() {
        //fields是表格中要显示的数据对应到Content类中的成员变量名，其定义顺序要与表格中显示的相同
        tables = new ArrayList<>();
        tables.add(new String[]{"铺设实木复合地板", "18.0", "m²", "¥1325.0"});
        tables.add(new String[]{"铺设实木复合地板", "18.0", "m²", "¥1325.0"});
        tables.add(new String[]{"铺设实木复合地板", "18.0", "m²", "¥1325.0"});

        materialsTable1.setAdapter(adapter);
        materialsTable2.setAdapter(adapter);
    }


    BaseTableAdapter adapter = new BaseTableAdapter() {
        @Override
        public void onInterceptItemView(TextView itemView, int row, int col) {
            itemView.setPadding(DisplayUtil.dp2px(that, 5), 0, DisplayUtil.dp2px(that, 5), 0);
            if (row == 0) {
                LayoutUtil.setLayout(itemView, DisplayUtil.dp2px(that, 100), DisplayUtil.dp2px(that, 30));
            } else {
                LayoutUtil.setLayout(itemView, DisplayUtil.dp2px(that, 100), DisplayUtil.dp2px(that, 42));
                if (col == 0) {
                    itemView.setTextColor(that.getResources().getColor(R.color.main_color_red));
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
    };
}
