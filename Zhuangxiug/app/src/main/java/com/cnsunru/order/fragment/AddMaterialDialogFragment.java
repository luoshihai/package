package com.cnsunru.order.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cnsunru.R;
import com.cnsunru.common.base.LBaseDialogFragment;
import com.cnsunru.common.boxing.GlideMediaLoader;
import com.cnsunru.common.intent.IntentPoxy;
import com.cnsunru.common.intent.StartIntent;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.common.widget.TagAdapter;
import com.cnsunru.common.widget.TagFlowLayout;
import com.cnsunru.order.adapters.GoodsAttrAdapter;
import com.cnsunru.order.mode.GoodsDetails;
import com.sunrun.sunrunframwork.adapter.ViewHodler;
import com.sunrun.sunrunframwork.adapter.ViewHolderAdapter;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.uiutils.DisplayUtil;
import com.sunrun.sunrunframwork.uiutils.LayoutUtil;
import com.sunrun.sunrunframwork.uiutils.UIUtils;
import com.sunrun.sunrunframwork.weight.FlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static com.cnsunru.budget.fragment.RoomCustomInfoFragment.EDIT_ROOM_TYPE;
import static com.cnsunru.common.quest.BaseQuestConfig.GET_PRODUCT_DETAIL_CODE;

/**
 * Created by WQ on 2017/9/13.
 *
 * @Describe 添加材料-浮层
 */

public class AddMaterialDialogFragment extends LBaseDialogFragment {


    @BindView(R.id.imgProduct)
    ImageView imgProduct;
    @BindView(R.id.itemTitle)
    TextView itemTitle;
    @BindView(R.id.txtLab1)
    TextView txtLab1;
    @BindView(R.id.txtLab2)
    TextView txtLab2;
    @BindView(R.id.txtLab3)
    TextView txtLab3;
    @BindView(R.id.txtLab4)
    TextView txtLab4;
    @BindView(R.id.txtProductPrice)
    TextView txtProductPrice;
    @BindView(R.id.rightProductLayout)
    LinearLayout rightProductLayout;
    @BindView(R.id.attrRecylv)
    RecyclerView attrRecylv;
    @BindView(R.id.txtCurrentProject)
    TextView txtCurrentProject;
    @BindView(R.id.btnAddProject)
    Button btnAddProject;
    @BindView(R.id.txtCategoryLab)
    TextView txtCategoryLab;
    @BindView(R.id.attrTags)
    TagFlowLayout attrTags;
    @BindView(R.id.addGoods)
    Button addGoods;
    @BindView(R.id.btnCancel)
    Button btnCancel;
    int height;
    int leftPadding;
    ListPopupWindow listPopupWindow;
    String product_id;
    GoodsDetails goodsDetails;
    StartIntent startIntent= IntentPoxy.getProxyInstance();
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        product_id=getArguments().getString("product_id");
        bindDimissListener(view);
        BaseQuestStart.getProductDetails(this, product_id);
    }
    public void nofityUpdate(int requestCode,BaseBean bean){
        switch(requestCode){
            case GET_PRODUCT_DETAIL_CODE:
                if (bean.status == 1) {
                    goodsDetails = bean.Data();
                    setPageData(goodsDetails);
                }
                break;
        }
        super.nofityUpdate(requestCode,bean);
    }
    private void setPageData(GoodsDetails goodsDetails) {
        GlideMediaLoader.load(this, imgProduct, goodsDetails.image);
        itemTitle.setText(goodsDetails.title);
        txtProductPrice.setText(String.format("¥ %s", goodsDetails.price));
        txtLab1.setText(String.format("品牌: %s", goodsDetails.getBrand_name()));
        txtLab2.setText(String.format("规格: 套", goodsDetails.brand_name));
        txtLab3.setText(String.format("宽度: %sm", goodsDetails.width));
        txtLab4.setText(String.format("高度: %sm", goodsDetails.height));

        attrRecylv.setLayoutManager(new LinearLayoutManager(that, LinearLayoutManager.VERTICAL, false));
        GoodsAttrAdapter goodsAttrAdapter = new GoodsAttrAdapter(goodsDetails.spec);
        attrRecylv.setAdapter(goodsAttrAdapter);
        height = DisplayUtil.dp2px(that, 30);
        leftPadding = DisplayUtil.dp2px(that, 15);
//        List<String> attrStrs = Arrays.asList("主卧", "次卧", "厨房", "卫生间", "阳台");
//        final TagAdapter<String> adapter = new TagAdapter<String>(attrStrs) {
//            @Override
//            public View getView(FlowLayout parent, int position, String o) {
//                boolean isSelect = getPreCheckedList().contains(position);
//                View inflate = LayoutInflater.from(that).inflate(R.layout.item_simple_text, parent, false);
//                TextView text = (TextView) inflate.findViewById(R.id.text);
//                text.setText(o);
//                if (isSelect) {
//                    text.setTextColor(Color.WHITE);
//                    text.setBackgroundResource(R.drawable.shape_red_bg);
//                } else {
//                    text.setTextColor(that.getResources().getColor(R.color.text4));
//                    text.setBackground(null);
//                }
//                text.setPadding(leftPadding, 0, leftPadding, 0);
//                LayoutUtil.setLayout(text, WRAP_CONTENT, height);
//                return inflate;
//            }
//        };
//        attrTags.setAdapter(adapter);
//        adapter.setSelectedList(0);
//        attrTags.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
//
//            @Override
//            public boolean onTagClick(View view, int position, FlowLayout parent) {
//                adapter.getPreCheckedList().clear();
//                adapter.setSelectedList(position);
//                return false;
//            }
//        });
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_dialog_add_material;
    }

    public static void showFragment(FragmentManager fragmentManager,String product_id) {
        AddMaterialDialogFragment addGoodsDialogFragment = new AddMaterialDialogFragment();
        Bundle args = new Bundle();
        args.putString("product_id",product_id);
        addGoodsDialogFragment.setArguments(args);
        addGoodsDialogFragment.show(fragmentManager, "AddMaterialDialogFragment");
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        return dialog;
    }

    @OnClick({ R.id.btnAddProject, R.id.addGoods, R.id.btnCancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddProject:
                break;
            case R.id.addGoods://替换材料
                startIntent.startGoodsListActivity(that,3,goodsDetails.category_id);
//                UIUtils.shortM("加入");
                dismissAllowingStateLoss();
                break;
            case R.id.btnCancel:
                dismissAllowingStateLoss();
                break;
        }
    }
}
