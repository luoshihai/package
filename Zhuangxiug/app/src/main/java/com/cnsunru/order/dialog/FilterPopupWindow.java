package com.cnsunru.order.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.cnsunru.R;
import com.cnsunru.budget.mode.FilterAttrBean;
import com.cnsunru.budget.mode.FilterAttrBean.AttrBean;
import com.cnsunru.common.base.LBasePopupWindow;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.common.widget.TagAdapter;
import com.cnsunru.common.widget.TagFlowLayout;
import com.sunrun.sunrunframwork.adapter.ViewHodler;
import com.sunrun.sunrunframwork.adapter.ViewHolderAdapter;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.uiutils.DisplayUtil;
import com.sunrun.sunrunframwork.uiutils.LayoutUtil;
import com.sunrun.sunrunframwork.weight.FlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static com.cnsunru.common.quest.BaseQuestConfig.GET_SPEC_ATTR_CODE;

/**
 * 筛选弹窗
 * Created by WQ on 2017/9/19.
 */

public class FilterPopupWindow extends LBasePopupWindow {

    @BindView(R.id.brandTags)
    TagFlowLayout brandTags;
    @BindView(R.id.editMinPrice)
    EditText editMinPrice;
    @BindView(R.id.editMaxPrice)
    EditText editMaxPrice;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.btnCancel)
    Button btnCancel;
    @BindView(R.id.labTxt3)
    TextView labTxt3;

    @BindView(R.id.listAttrs)ListView listAttrs;
    int type = 0;//0 分类商品列表, 1 限时商品,2 爆款商品,,,3 预算过来的,4 搜索商品
    String cid;
    public String brand_id, price_min, price_max, attr_value_id;
    View.OnClickListener btnOkListener;
    boolean isLoaded;
    private Object attr_obj;
    public FilterPopupWindow(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
    }

    @Override
    public void onViewCreated(View view) {
        super.onViewCreated(view);
        if (!isLoaded) {
            if (type == 0|| type==3||type==5) {
                BaseQuestStart.get_category_attr(this, cid);
            } else {
                BaseQuestStart.get_spec_attr(this, type);
            }
        }

    }

    public void nofityUpdate(int requestCode, BaseBean bean) {
        switch (requestCode) {
            case GET_SPEC_ATTR_CODE:
                if (bean.status == 1) {
                    FilterAttrBean data = bean.Data();
                    setData2View(data);
                }
                break;
        }
        super.nofityUpdate(requestCode, bean);
    }

    @Override
    public void inited() {

    }

     ViewHolderAdapter<AttrBean> attrAdapter;
    private void setData2View(FilterAttrBean filterAttrBean) {
        List<AttrBean> attr = filterAttrBean.getAttNotEmpty();
       attrAdapter = new ViewHolderAdapter<AttrBean>(that, attr, R.layout.item_dialog_filter_attr) {
            @Override
            public void fillView(ViewHodler viewHodler, AttrBean attrBean, int i) {
                TagFlowLayout styleTags = viewHodler.getView(R.id.styleTags);

                final List<AttrBean.ChildBean> child = attrBean.child;
                final TagAdapter styleAdapter = createTagAdapter(child);
                styleTags.setAdapter(styleAdapter);
                viewHodler.setText(R.id.labTxt1, attrBean.title);
//                styleAdapter.setSelectedList(0);
                styleTags.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {

                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {
//                        boolean isSel=styleAdapter.getPreCheckedList().contains(position);

                        boolean isSel=attr_obj==child.get(position);
                        styleAdapter.getPreCheckedList().clear();
                        if(isSel) {
                            attr_obj=null;
                            attr_value_id=null;
                            styleAdapter.notifyDataChanged();
                        }else {
                            styleAdapter.setSelectedList(position);
                            attr_obj = child.get(position);
                            attr_value_id = child.get(position).attr_value_id;

                        }
                        attrAdapter.notifyDataSetChanged();
                        return false;
                    }
                });
            }
        }; listAttrs.setAdapter(attrAdapter);


        final List<FilterAttrBean.BrandBean> brand = filterAttrBean.brand;
        final TagAdapter brandAdapter = createTagAdapter(brand);
        brandTags.setAdapter(brandAdapter);
        brandAdapter.setSelectedList(0);
        brandTags.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {

            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                boolean isSel=brandAdapter.getPreCheckedList().contains(position);
                brandAdapter.getPreCheckedList().clear();
                if(isSel) {
                    brand_id=null;
                    brandAdapter.notifyDataChanged();
                }else {
                    brandAdapter.setSelectedList(position);
                    brand_id = brand.get(position).brand_id;


                }
                return false;
            }
        });
        isLoaded = true;
    }

    public TagAdapter createTagAdapter(List data) {
        final int height = DisplayUtil.dp2px(that, 30);
        final int leftPadding = DisplayUtil.dp2px(that, 15);
        TagAdapter adapter = new TagAdapter<Object>(data) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                //属性单选的
                boolean isSelect = o instanceof FilterAttrBean.BrandBean ?getPreCheckedList().contains(position):attr_obj==o;
                View inflate = LayoutInflater.from(that).inflate(R.layout.item_simple_text, parent, false);
                TextView text = (TextView) inflate.findViewById(R.id.text);
                text.setText(String.valueOf(o));

                if (isSelect) {
                    text.setTextColor(Color.WHITE);
                    text.setBackgroundResource(R.drawable.shape_red_bg);
                } else {
                    text.setTextColor(that.getResources().getColor(R.color.main_text_color));
                    text.setBackground(null);
                }
                text.setPadding(leftPadding, 0, leftPadding, 0);
                LayoutUtil.setLayout(text, WRAP_CONTENT, height);
                return inflate;
            }
        };
        return adapter;
    }

    public void setArgument(int type, String cid) {
        this.type = type;
        this.cid = cid;
    }

    public static FilterPopupWindow create(Context context, int type, String cid) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_filter, null);
        FilterPopupWindow filterPopupWindow = new FilterPopupWindow(inflate, MATCH_PARENT, MATCH_PARENT, true);
        filterPopupWindow.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.black_lucency_aa)));
        filterPopupWindow.setOutsideTouchable(true);
        filterPopupWindow.setArgument(type, cid);
        return filterPopupWindow;
    }

    public void setBtnOkListener(View.OnClickListener btnOkListener) {
        this.btnOkListener = btnOkListener;
    }

    @OnClick({R.id.btnSubmit, R.id.btnCancel})
    public void onClick(View view) {
        dismiss();
        switch (view.getId()) {
            case R.id.btnSubmit:
                if (btnOkListener != null) {
                    price_min = editMinPrice.getText().toString();
                    price_max = editMaxPrice.getText().toString();
                    btnOkListener.onClick(view);
                }
                break;
            case R.id.btnCancel:
                break;
        }
    }
}
