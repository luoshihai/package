package com.cnsunru.budget.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.cnsunru.R;
import com.cnsunru.budget.mode.AllListBean;
import com.cnsunru.common.boxing.GlideMediaLoader;
import com.cnsunru.common.intent.IntentPoxy;
import com.cnsunru.common.intent.StartIntent;
import com.sunrun.sunrunframwork.adapter.ViewHodler;
import com.sunrun.sunrunframwork.adapter.ViewHolderAdapter;
import com.sunrun.sunrunframwork.uiutils.DisplayUtil;
import com.sunrun.sunrunframwork.uiutils.LayoutUtil;
import com.sunrun.sunrunframwork.utils.EmptyDeal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import win.smartown.android.library.tableLayout.TableAdapter;
import win.smartown.android.library.tableLayout.TableLayout;


/**
 * Created by WQ on 2017/9/14.
 *
 * @Describe 项目, 材料清单适配器
 */

public class ProjectMaterialsAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    private StartIntent startIntent = IntentPoxy.getProxyInstance();
    private String[] fields = {"施工项目", "工程量", "单位", "小计","主材人工","主材单价","辅料","人工","损耗"};
    private boolean isTableMode = true;
    private List<MultiItemEntity> second_list = new ArrayList<>();
    private List<MultiItemEntity> sourceData = new ArrayList<>();

    public ProjectMaterialsAdapter(Context context, List<MultiItemEntity> data) {
        super(data);
        addItemType(0, R.layout.item_materials_table);
        addItemType(1, R.layout.item_materials_img);
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    /**
     * 切换视图模式
     *
     * @param tableMode true的时候是表格模式
     */
    public void setTableMode(boolean tableMode) {
        isTableMode = tableMode;
        if (isTableMode) {//切换视图显示模式时,使用不同的数据源
            mData = sourceData;
        } else {
            mData = second_list;
        }
//        setNewData();
        notifyDataSetChanged();
    }


    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        int layoutPosition = helper.getLayoutPosition();
        dealLargeTitle(helper, item, layoutPosition);
        if (isTableMode) {
            dealTableMode(helper, (AllListBean.FirstListBean) item, layoutPosition);
        } else {
            dealImageMode(helper, (AllListBean.FirstListBean.SecondListBean) item, layoutPosition);
        }
    }


    /**
     * 处理大标题及显示的内容
     *
     * @param helper
     * @param item
     * @param position
     */
    protected void dealLargeTitle(BaseViewHolder helper, Object item, int position) {
        AllListBean parentInfo = null;
        if (item instanceof AllListBean.FirstListBean) {
            parentInfo = ((AllListBean.FirstListBean) item).parentInfo;
        } else {
            AllListBean.FirstListBean.SecondListBean parent = (AllListBean.FirstListBean.SecondListBean) item;
            if (parent.parent != null) {
                parentInfo = parent.parent.parentInfo;
            }
        }
        helper.setVisible(R.id.layBigTitle, parentInfo != null);//一级标题
        if (parentInfo != null) {
            helper.setText(R.id.txtLab, parentInfo.house_title);
            helper.setText(R.id.txtArea, String.format("面积: %sm²", parentInfo.house_acreage));
            helper.setText(R.id.txtTotal, String.format("花费: ¥%s", parentInfo.house_all_money));
        }
    }

    /**
     * 图片模式下的内容处理
     *
     * @param helper
     * @param item
     * @param position
     */
    protected void dealImageMode(BaseViewHolder helper,final AllListBean.FirstListBean.SecondListBean item, int position) {

        helper.setVisible(R.id.txtSmallTitle, item.parent != null);//二级标题
        AllListBean.FirstListBean.SecondListBean multiItemEntity = null;
        if (position + 1 < getItemCount()) {
            multiItemEntity = (AllListBean.FirstListBean.SecondListBean) second_list.get(position + 1);
        }
        GlideMediaLoader.load(mContext,helper.getView(R.id.imgProduct),item.icon,R.drawable.ic_no_product_img);
        boolean hasGrap = (position + 1 < getItemCount()) && (multiItemEntity == null || multiItemEntity.parent == null);
        helper.setVisible(R.id.viewGrap, hasGrap);
//        txtTitle  //二级标题
        if (item.parent != null)
            helper.setText(R.id.txtSmallTitle, item.parent.title);
        helper.setText(R.id.itemTitle, item.title);
        helper.setText(R.id.txtLab1, String.format("工程量: %s%s", item.calculate_num, item.list_unit));
        helper.setText(R.id.txtLab2, String.format("主材人工: %s", item.main_people_fee));
        helper.setText(R.id.txtLab3, String.format("主材单价: ¥%s", item.main_product_fee));
        helper.setText(R.id.txtLab4, String.format("辅料: ¥%s", item.second_product_fee));
        helper.setText(R.id.txtLab5, String.format("人工: ¥%s", item.people_fee));
        helper.setText(R.id.txtLab6, String.format("损耗: ¥%s", item.loss_fee));
        helper.setText(R.id.txtSumMoney, String.format("小计: ¥%s", item.calculate_money));
        helper.setOnClickListener(R.id.layContent, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent.startMaterialDetailsActivity(mContext, item.id, item.title);
            }
        });
    }

    /**
     * 表格模式下的内容处理
     *
     * @param helper
     * @param item
     * @param position
     */
    protected void dealTableMode(BaseViewHolder helper, final AllListBean.FirstListBean item, int position) {

//        txtSmallTitle  //二级标题
        helper.setText(R.id.txtSmallTitle, item.title);
        TableLayout materialsTable = helper.getView(R.id.materialsTable);
        final List<AllListBean.FirstListBean.SecondListBean> second_list = item.getSecond_list();
        materialsTable.setAdapter(new BaseTableAdapter() {
            @Override
            public void onInterceptItemView(TextView itemView, final int row, int col) {
                itemView.setPadding(DisplayUtil.dp2px(mContext, 5), 0, DisplayUtil.dp2px(mContext, 5), 0);
                if (row == 0) {
                    LayoutUtil.setLayout(itemView, DisplayUtil.dp2px(mContext, 100), DisplayUtil.dp2px(mContext, 30));
                } else {
                    LayoutUtil.setLayout(itemView, DisplayUtil.dp2px(mContext, 100), DisplayUtil.dp2px(mContext, 42));
                    if (col == 0) {
                        itemView.setTextColor(mContext.getResources().getColor(R.color.main_color_red));
                    }
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final AllListBean.FirstListBean.SecondListBean secondListBean = second_list.get(row - 1);
                            startIntent.startMaterialDetailsActivity(mContext, secondListBean.id, secondListBean.title);
                        }
                    });
                }

            }

            @Override
            public int getRowCount() {

                return EmptyDeal.size(second_list);
            }

            @Override
            public String getItemVal(int row, int col) {//计算行列返回的数据
                AllListBean.FirstListBean.SecondListBean secondListBean = second_list.get(row);
                switch (col) {
//                    {"施工项目", "工程量", "单位", "小计","主材人工","主材单价","辅料","人工","损耗"};
                    case 0://施工项目
                        return secondListBean.title;
                    case 1://工程量
                        return secondListBean.calculate_num;
                    case 2://单位
                        return secondListBean.list_unit;
                    case 3://小计
                        return secondListBean.calculate_money;
                    case 4://主材人工
                        return secondListBean.main_people_fee;
                    case 5://主材单价
                        return secondListBean.main_product_fee;
                    case 6://辅料
                        return secondListBean. second_product_fee;
                    case 7://人工
                        return secondListBean.people_fee;
                    case 8://损耗
                        return secondListBean.loss_fee;
                }
                return "";
            }

            @Override
            public String[] getHeadFields() {
                return fields;
            }
        });
    }


    @Override
    protected int getDefItemViewType(int position) {
        //这里的视图类型和实体类无关,纯粹的显示哪种模式
        return isTableMode ? 0 : 1;
    }

    @Override
    public int getItemCount() {
        return isTableMode ? super.getItemCount() : second_list.size();
    }

    @Override
    public void setNewData(@Nullable List<MultiItemEntity> data) {
        this.sourceData = data;
        super.setNewData(data);
        if (!EmptyDeal.isEmpy(data)) {
            second_list.clear();
            for (Object item : data) {//抽取所有子级作为图文模式下的数据源
                AllListBean.FirstListBean firstListBean = (AllListBean.FirstListBean) item;
                if (!EmptyDeal.isEmpy(firstListBean.getSecond_list())) {
                    firstListBean.getSecond_list().get(0).parent = firstListBean;
                    second_list.addAll(firstListBean.getSecond_list());
                }

            }
        }
    }
}
