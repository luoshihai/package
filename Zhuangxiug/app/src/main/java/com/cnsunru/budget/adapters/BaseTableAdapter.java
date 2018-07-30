package com.cnsunru.budget.adapters;

import android.widget.TextView;

import win.smartown.android.library.tableLayout.TableAdapter;

/**
 * 表格布局适配器优化
 * Created by WQ on 2017/9/14.
 */

public abstract class BaseTableAdapter implements TableAdapter {

    /**
     * 获取行数
     *
     * @return
     */
    public abstract int getRowCount();

    /**
     * 获取行列对应的条目值
     *
     * @param row
     * @param col
     * @return
     */
    public abstract String getItemVal(int row, int col);

    public abstract String[] getHeadFields();

    @Override
    final public int getColumnCount() {
        return getHeadFields().length;
    }

    @Override
    public String[] getColumnContent(int position) {
        int rowCount = getRowCount();
        String contents[] = new String[rowCount + 1];
        String[] headFields = getHeadFields();
        contents[0] = headFields[position];
        for (int j = 0; j < rowCount; j++) {
            contents[j + 1] = getItemVal(j, position);
        }
        return contents;
    }

    @Override
    public void onInterceptItemView(TextView itemView, int row, int col) {

    }
}
