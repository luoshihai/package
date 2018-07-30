//package com.cnsunrun.order.dialog;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.GridView;
//import android.widget.ListView;
//
//import com.cnsunrun.R;
//import com.cnsunrun.common.base.LBaseDialogFragment;
//import com.cnsunrun.common.util.TestData;
//import com.sunrun.sunrunframwork.adapter.ViewHodler;
//import com.sunrun.sunrunframwork.adapter.ViewHolderAdapter;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//
///**
// * 筛选对话框
// * Created by WQ on 2017/8/29.
// */
//
//public class ShopFilterDialog extends LBaseDialogFragment {
////    @BindView(R.id.listFilter)
//    ListView listFilter;
//    @BindView(R.id.btnSubmit)
//    Button btnSubmit;
//    @BindView(R.id.btnCancel)
//    Button btnCancel;
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        listFilter.setAdapter(new ViewHolderAdapter(that, TestData.createTestData(4,String.class),R.layout.item_filter_category) {
//            @Override
//            public void fillView(ViewHodler viewHodler, Object o, int i) {
//                GridView gridCategory = viewHodler.getView(R.id.gridCategory);
//                gridCategory.setAdapter(new ViewHolderAdapter(mContext,TestData.createTestData(3,String.class),R.layout.item_filter_categroyitem) {
//                    @Override
//                    public void fillView(ViewHodler viewHodler, Object o, int i) {
//
//                    }
//                });
//            }
//        });
//    }
//
//    public static ShopFilterDialog newInstance() {
//        Bundle args = new Bundle();
//        ShopFilterDialog fragment = new ShopFilterDialog();
//        fragment.setArguments(args);
//        return fragment;
//    }
//    @Override
//    protected int getLayoutRes() {
//        return R.layout.dialog_filter;
//    }
//
//
//    @OnClick({R.id.btnSubmit, R.id.btnCancel})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btnSubmit:
//                dismiss();
//                break;
//            case R.id.btnCancel:
//                dismiss();
//                break;
//        }
//    }
//
//}
