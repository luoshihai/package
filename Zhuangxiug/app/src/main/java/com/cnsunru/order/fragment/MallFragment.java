package com.cnsunru.order.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cnsunru.R;
import com.cnsunru.common.base.LBaseFragment;
import com.cnsunru.common.boxing.GlideMediaLoader;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.common.widget.titlebar.TabTitleBar;
import com.cnsunru.home.mode.LocationStateMode;
import com.cnsunru.home.mode.QRScannerMode;
import com.cnsunru.order.adapters.CategreyOneAdapter;
import com.cnsunru.order.mode.GoodsCategory;
import com.cnsunru.user.mode.GoMyProjectMode;
import com.sunrun.sunrunframwork.adapter.ViewHodler;
import com.sunrun.sunrunframwork.adapter.ViewHolderAdapter;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.utils.EmptyDeal;
import com.sunrun.sunrunframwork.weight.GridViewForScroll;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.cnsunru.common.intent.StartIntent.SCAN_QR_REQUEST;
import static com.cnsunru.common.quest.BaseQuestConfig.GET_CATEGORY_CODE;

/**
 * 商城
 * Created by WQ on 2017/9/12.
 */

public class MallFragment extends LBaseFragment {
    @BindView(R.id.editSearch)
    EditText editSearch;
    @BindView(R.id.titleContainer)
    LinearLayout titleContainer;
    @BindView(R.id.titleBar)
    TabTitleBar titleBar;
    @BindView(R.id.txtLocation)
    TextView txtLocation;

    @BindView(R.id.recyclerViewOne)
    RecyclerView recyclerViewOne;
    @BindView(R.id.imgBanner)
    ImageView imgBanner;
    @BindView(R.id.txtName)
    TextView txtName;
    @BindView(R.id.gridCategory)
    GridViewForScroll gridCategory;
    @BindView(R.id.btnGoProject)
    View btnGoProject;
    @BindView(R.id.lSwipeRefreshLayout)
    SwipeRefreshLayout lSwipeRefreshLayout;

    @BindView(R.id.scrollView)
    ScrollView scrollView;

    LocationStateMode locationStateMode;
    private CategreyOneAdapter categeryOneAdapter;
    int position = 0,from_type;
    List<GoodsCategory> data;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        List<String> mDataOne = Arrays.asList("建材", "家具", "电器", "整装套餐"
//        );

        from_type=getArguments().getInt("from_type",0);
        categeryOneAdapter = new CategreyOneAdapter(null, position);
        recyclerViewOne.setAdapter(categeryOneAdapter);
        recyclerViewOne.setLayoutManager(new LinearLayoutManager(that, LinearLayoutManager.VERTICAL, false));
        //recyclerViewOne.addItemDecoration(new DivideLineItemDecoration(this, getResources().getColor(R.color.backgroud_color), 1));
        categeryOneAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                setRightListData(position);
            }
        });
        locationStateMode = new LocationStateMode(that, txtLocation);
        if(from_type==5){
            titleBar.setVisibility(View.GONE);//隐藏原有标题栏
            locationStateMode.disenableAll();//禁用位置模块
            btnGoProject.setVisibility(View.GONE);
        }
        new GoMyProjectMode(btnGoProject);
        loadPageData();
        lSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadPageData();
            }
        });
        lSwipeRefreshLayout.setOnChildScrollUpCallback(new SwipeRefreshLayout.OnChildScrollUpCallback() {
            @Override
            public boolean canChildScrollUp(SwipeRefreshLayout parent, @Nullable View child) {
                if(gridCategory.getChildCount()<=0)return false;//右边的子分类为空时,可以刷新
                int scrollY = scrollView.getScrollY();//scrollview滚动到顶部时,才能下拉刷新
                return  scrollY>0;
            }
        });

        editSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent.startSearchGoodsActivity(that,String.valueOf(1));
            }
        });
    }

    private void setRightListData(int position) {
        categeryOneAdapter.setPosition(position);
        this.position=position;
        if(EmptyDeal.isEmpy(data))return;
        GoodsCategory goodsCategory = data.get(position);
       final List<GoodsCategory.ChildBean> child = goodsCategory._child;
//        List<String> mDataTwo = Arrays.asList("瓷砖", "卫浴", "橱柜","瓷砖", "卫浴", "橱柜"
//        );
        GlideMediaLoader.load(that,imgBanner,goodsCategory.image,R.drawable.shop_img_banner);
        gridCategory.setAdapter(new ViewHolderAdapter<GoodsCategory.ChildBean>(that,child,R.layout.item_categrey_info_icon) {
            @Override
            public void fillView(ViewHodler viewHodler, GoodsCategory.ChildBean item, int i) {
                viewHodler.setText(R.id.txtName,item.title);
                GlideMediaLoader.load(mContext,viewHodler.getView(R.id.imgCover),item.image,R.drawable.ic_placeholder_image);
            }
        });
        gridCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startIntent.startGoodsListActivity(that,from_type,child.get(position).id);
            }
        });
    }

   public void loadPageData(){
       lSwipeRefreshLayout.setRefreshing(true);
       BaseQuestStart.get_category(this);
   }

    public void nofityUpdate(int requestCode,BaseBean bean){
        switch(requestCode){
            case GET_CATEGORY_CODE:
                if(bean.status==1){
                   data = bean.Data();
                    categeryOneAdapter.setNewData(data);
                    setRightListData(position);
                }
                break;
        }
        super.nofityUpdate(requestCode,bean);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_mall;
    }

    @OnClick(R.id.layScanQR)
    public void onClick() {
        startIntent.startScanQRActivity(that);
    }

    public static MallFragment newInstance() {
        MallFragment mallFragment = new MallFragment();
        Bundle bundle = new Bundle();
        mallFragment.setArguments(bundle);
        return mallFragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        QRScannerMode.dealResult(that,requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void requestFinish() {
        lSwipeRefreshLayout.setRefreshing(false);
        super.requestFinish();
    }

}
