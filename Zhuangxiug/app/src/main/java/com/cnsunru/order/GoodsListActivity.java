package com.cnsunru.order;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cnsunru.R;
import com.cnsunru.budget.ProductCategorySelectActivity;
import com.cnsunru.common.CommonApp;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.model.PageBean;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.common.util.GetEmptyViewUtils;
import com.cnsunru.common.util.PageLimitDelegate;
import com.cnsunru.common.util.ViewUtils;
import com.cnsunru.common.widget.SpaceItemDecoration;
import com.cnsunru.common.widget.titlebar.TabTitleBar;
import com.cnsunru.home.mode.HomeHotGoods;
import com.cnsunru.order.adapters.GoodsListAdapter;
import com.cnsunru.order.dialog.FilterPopupWindow;
import com.cnsunru.user.mode.GoMyProjectMode;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.uiutils.DisplayUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;

import static com.cnsunru.common.quest.BaseQuestConfig.GET_CATEGORY_PRODUCT_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.GET_EXPLOSION_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.GET_PRODUCT_LIMITED_CODE;

/**
 * 商品列表
 */
public class GoodsListActivity extends LBaseActivity {

    @BindView(R.id.titleBar)
    TabTitleBar titleBar;
    @BindView(R.id.lRecyclerView)
    RecyclerView lRecyclerView;
    @BindView(R.id.lSwipeRefreshLayout)
    SwipeRefreshLayout lSwipeRefreshLayout;
    @BindView(R.id.editSearch)
    EditText editSearch;
    @BindView(R.id.titleContainer)
    LinearLayout titleContainer;
    @BindView(R.id.txtTimeLab)
    TextView txtTimeLab;
    @BindView(R.id.layTime)
    FrameLayout layTime;
    @BindView(R.id.txtHotLab)
    TextView txtHotLab;
    @BindView(R.id.layHot)
    FrameLayout layHot;
    @BindView(R.id.txtPriceLab)
    TextView txtPriceLab;
    @BindView(R.id.layPrice)
    FrameLayout layPrice;
    @BindView(R.id.btnGoProject)
    View btnGoProject;
    SortStateMode sortStateMode = new SortStateMode();
    GoodsListAdapter recordAdapter;
    FilterPopupWindow filterPopupWindow;
    int type = 0;//0 分类商品列表, 1 限时商品,2 爆款商品,3 预算过来的,4 搜索商品 , 5 商品选择
    PageLimitDelegate pageLimitDelegate = new PageLimitDelegate(new PageLimitDelegate.DataProvider() {
        @Override
        public void loadData(int page) {
            switch (type) {
                case 0:
                    BaseQuestStart.get_category_product(that,cid, filterPopupWindow.brand_id, filterPopupWindow.price_min, filterPopupWindow.price_max, filterPopupWindow.attr_value_id, keywords, sort, page);
                    break;
                case 1:
                    BaseQuestStart.getProductLimited(that, filterPopupWindow.brand_id, filterPopupWindow.price_min, filterPopupWindow.price_max, filterPopupWindow.attr_value_id, keywords, sort, page);
                    break;
                case 2:
                    BaseQuestStart.getExplosion(that, filterPopupWindow.brand_id, filterPopupWindow.price_min, filterPopupWindow.price_max, filterPopupWindow.attr_value_id, keywords, sort, page);
                    break;
                case 3:
                case 5:
                    BaseQuestStart.get_category_product(that,cid, filterPopupWindow.brand_id, filterPopupWindow.price_min, filterPopupWindow.price_max, filterPopupWindow.attr_value_id, keywords, sort, page);
                    break;
                case 4:break;
            }
        }
    });

    String cid,sort,keywords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodslist);
        type = getIntent().getIntExtra("type", 0);
        cid = getIntent().getStringExtra("cid");

        //从预算过来的话,进行标记,后面的操作不一样
        GoodsDetailsActivity.isSelectMaterial= type == 3;

        filterPopupWindow = FilterPopupWindow.create(that, type, cid);
        filterPopupWindow.onViewCreated(null);
        recordAdapter = new GoodsListAdapter();
        recordAdapter.setType(type);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(that, 2, GridLayoutManager.VERTICAL, false);
//        lRecyclerView.setLayoutManager(new LinearLayoutManager(that,LinearLayoutManager.VERTICAL,false));
        lRecyclerView.addItemDecoration(new SpaceItemDecoration(2, DisplayUtil.dp2px(that, 10)));
        lRecyclerView.setLayoutManager(gridLayoutManager);
        GetEmptyViewUtils.bindEmptyView(that, recordAdapter, 0, "暂无数据", true);
        lRecyclerView.setAdapter(recordAdapter);
        pageLimitDelegate.attach(lSwipeRefreshLayout, lRecyclerView, recordAdapter);
        titleBar.setRightAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!filterPopupWindow.isShowing()) {
                    filterPopupWindow.setBtnOkListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            pageLimitDelegate.refreshPage();
                        }
                    });
                    filterPopupWindow.showAsDropDown(layTime);
                }else {
                    filterPopupWindow.dismiss();
                }
            }
        });
        recordAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(type==5){
                    HomeHotGoods goods = recordAdapter.getData().get(position);
                    CommonApp.getInstance().closeActivitys(ProductCategorySelectActivity.class,GoodsListActivity.class);
                    EventBus.getDefault().post(goods);
                }else {
                    startIntent.startGoodsDetailsActivity(that, recordAdapter.getItem(position).id);
                }
            }
        });
        editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_SEARCH){
                    keywords=editSearch.getText().toString();
                    pageLimitDelegate.refreshPage();
                    editSearch.requestFocus();
                }
                return true;
            }
        });

        sortStateMode.bindView(layPrice,layHot, layTime);
        ViewUtils.multiStateClickListener(3, 0, new ViewUtils.OnStateSwitchListener() {
            @Override
            public void onStateSwitch(View view, int currentState) {
                sortStateMode.clearState(view,layHot, layTime, layPrice);
                sortStateMode.stateSwitch(view, currentState);
                sort=sortStateMode.getSort(view,currentState);
                pageLimitDelegate.refreshPage();
            }
        }, layHot, layTime, layPrice);
        new GoMyProjectMode(btnGoProject);
    }

    public void nofityUpdate(int requestCode, BaseBean bean) {
        switch (requestCode) {
            case GET_CATEGORY_PRODUCT_CODE:
            case GET_PRODUCT_LIMITED_CODE:
            case GET_EXPLOSION_CODE:
                List<HomeHotGoods> data = PageBean.getList(bean);
                pageLimitDelegate.setData(data);
                recordAdapter.setPageBean((PageBean) bean.Data());
                break;
        }
        super.nofityUpdate(requestCode, bean);
    }

    @Override
    public void onBackPressed() {
        if(filterPopupWindow.isShowing()){
            filterPopupWindow.dismiss();
        }else {
            super.onBackPressed();
        }
    }
}
