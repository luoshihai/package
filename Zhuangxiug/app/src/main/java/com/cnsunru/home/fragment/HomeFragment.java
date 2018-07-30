package com.cnsunru.home.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.ListPopupWindow;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnsunru.R;
import com.cnsunru.common.CommonIntent;
import com.cnsunru.common.base.LBaseFragment;
import com.cnsunru.common.boxing.GlideMediaLoader;
import com.cnsunru.common.event.LocationBean;
import com.cnsunru.common.event.MessageEvent;
import com.cnsunru.common.model.PageBean;
import com.cnsunru.common.model.TestMode;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.common.quest.Config;
import com.cnsunru.common.util.BannerUtils;
import com.cnsunru.common.util.ViewUtils;
import com.cnsunru.common.widget.titlebar.TabTitleBar;
import com.cnsunru.home.adapter.HomeGoodsAdapter;
import com.cnsunru.home.mode.BannerBean;
import com.cnsunru.home.mode.CouponBean;
import com.cnsunru.home.mode.HomeHotGoods;
import com.cnsunru.home.mode.LocationStateMode;
import com.cnsunru.home.mode.QRScannerMode;
import com.cnsunru.order.mode.GoodsDetails;
import com.loopj.android.http.RequestParams;
import com.sunrun.sunrunframwork.adapter.ViewHodler;
import com.sunrun.sunrunframwork.adapter.ViewHolderAdapter;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.http.NetUtils;
import com.sunrun.sunrunframwork.http.utils.JsonDeal;
import com.sunrun.sunrunframwork.uiutils.DisplayUtil;
import com.sunrun.sunrunframwork.uiutils.LayoutUtil;
import com.sunrun.sunrunframwork.uiutils.TextColorUtils;
import com.sunrun.sunrunframwork.uiutils.UIUtils;
import com.sunrun.sunrunframwork.utils.AHandler;
import com.sunrun.sunrunframwork.utils.EmptyDeal;
import com.sunrun.sunrunframwork.utils.log.Logger;
import com.sunrun.sunrunframwork.weight.GridViewForScroll;
import com.sunrun.sunrunframwork.weight.ListViewForScroll;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

import static com.cnsunru.common.intent.StartIntent.SCAN_QR_REQUEST;
import static com.cnsunru.common.quest.BaseQuestConfig.BANNER_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.DISCOUNT_LIST_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.GET_DISCOUNT_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.GET_EXPLOSION_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.GET_PRODUCT_LIMITED_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.PUT_AREA_CODE;
import static com.cnsunru.common.util.ViewUtils.bindViewGroupChildClickListener;

/**
 * 首页
 * Created by WQ on 2017/4/21.
 */

public class HomeFragment extends LBaseFragment {

    @BindView(R.id.editSearch)
    EditText editSearch;
    @BindView(R.id.layScanQR)
    LinearLayout layScanQR;
    @BindView(R.id.titleContainer)
    LinearLayout titleContainer;
    @BindView(R.id.titleBar)
    TabTitleBar titleBar;
    @BindView(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    @BindView(R.id.lay1)
    LinearLayout lay1;
    @BindView(R.id.lay2)
    LinearLayout lay2;
    @BindView(R.id.lay3)
    LinearLayout lay3;
    @BindView(R.id.lay4)
    LinearLayout lay4;
    @BindView(R.id.hotMore)
    TextView hotMore;
    @BindView(R.id.hotGridview)
    GridViewForScroll hotGridview;
    @BindView(R.id.labImg)
    ImageView labImg;
    @BindView(R.id.txtLimitTime)
    TextView txtLimitTime;
    @BindView(R.id.flashMore)
    TextView flashMore;
    @BindView(R.id.flashGridview)
    GridViewForScroll flashGridview;
    @BindView(R.id.couponMore)
    TextView couponMore;
    @BindView(R.id.discountList)
    ListViewForScroll discountList;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.layFuncs)
    LinearLayout layFuncs;

    @BindView(R.id.txtContent)
    TextView txtContent;
    @BindView(R.id.txtWorkSite)
    TextView txtWorkSite;
    @BindView(R.id.txtRaudersArticle)
    TextView txtRaudersArticle;
    @BindView(R.id.txtLocation)
    TextView txtLocation;

    @BindView(R.id.layHot)
    View layHot;
    @BindView(R.id.layLimite)
    View layLimite;
    @BindView(R.id.layCoupon)
    View layCoupon;
    @BindView(R.id.lSwipeRefreshLayout)
    SwipeRefreshLayout lSwipeRefreshLayout;
    @BindView(R.id.txtSearchType)
    TextView txtSearchType;
    int searchType=0;//标示搜索类型

    LocationStateMode locationStateMode;//位置信息,状态管理
    ListPopupWindow listPopupWindow;//搜索类型切换窗
    AHandler.Task task;//限时倒计时任务
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEventBus();

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViewGroupChildClickListener(layFuncs, new ViewUtils.OnGroupChildClickListener() {
            @Override
            public void onChildClick(ViewGroup viewGroup, View child, int position) {
                switch (position) {
                    case 0:
                        startIntent.startMyActivityActivity(that, 1);
                        break;
                    case 1:
                        startIntent.startWorkSiteListActivity(that);
                        break;
                    case 2:
                        startIntent.startSelectionRaiderListActivity(that);
                        break;
                    case 3:
                        startIntent.startCompanyListActivity(that);
                        break;
                }
            }
        });

        editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    startIntent.startSearchGoodsActivity(that,String.valueOf(searchType+1));
                    return true;
                }
                return false;
            }
        });
        editSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent.startSearchGoodsActivity(that,String.valueOf(searchType+1));
            }
        });
        locationStateMode = new LocationStateMode(that, txtLocation);

        loadHomeData();
        lSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadHomeData();
            }
        });
    }

    private void loadHomeData() {
        lSwipeRefreshLayout.setRefreshing(true);
        BaseQuestStart.getBanner(this);
        BaseQuestStart.getExplosion(this);
        BaseQuestStart.getProductLimited(this);
        BaseQuestStart.getDiscountList(this);
    }


    @OnClick(R.id.txtContent)
    public void txtContent() {
       // startIntent.startSearchGoodsActivity(that);
    }

    @OnClick(R.id.txtWorkSite)
    public void txtWorkSite() {
        startIntent.startWorkSiteListActivity(that);
    }

    @OnClick(R.id.txtRaudersArticle)
    public void txtRaudersArticle() {
        startIntent.startSelectionRaiderListActivity(that);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        QRScannerMode.dealResult(that,requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        homeFragment.setArguments(bundle);
        return homeFragment;
    }


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_home;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    public void nofityUpdate(int requestCode, BaseBean bean) {
        switch (requestCode) {
            case BANNER_CODE:
                if (bean.status == 1) {
                    List<BannerBean> beanList = bean.Data();
                    setBannerData(beanList);
                }
                break;
            case GET_EXPLOSION_CODE:
                if (bean.status == 1) {
                    List<HomeHotGoods> homeHotGoods = bean.Data();
                    setHotGoodsData(homeHotGoods);
                }
                break;
            case GET_PRODUCT_LIMITED_CODE:
                if (bean.status == 1) {
                    PageBean<HomeHotGoods> pageBean = bean.Data();
                    setLimiteGoodsData(pageBean);
                }
                break;
            case DISCOUNT_LIST_CODE:
                if (bean.status == 1) {
                    List<CouponBean> homeCoupons = bean.Data();
                    setCouponData(homeCoupons);
                }
                break;
            case PUT_AREA_CODE:
                if (bean.status == 1) {
                    JSONObject jobj = JsonDeal.createJsonObj(bean.toString());
                    String id = jobj.optString("id");
                    locationBean.cityId = id;
                    EventBus.getDefault().post(locationBean);
                } else {
                    UIUtils.shortM(bean);
                }
                break;
            case GET_DISCOUNT_CODE:
                UIUtils.shortM(bean);
                if (bean.status == 1) {
                    BaseQuestStart.getDiscountList(this);//刷新优惠劵
                }
                break;
            case 1:
                BaseBean baseBean = JsonDeal.createBean(bean.toString(),TestMode.class);
                TestMode testMode=baseBean.Data();
                Logger.D(testMode);
                break;
        }
        super.nofityUpdate(requestCode, bean);
    }

    private void setCouponData(List<CouponBean> homeCoupons) {
//        final List<String> testData = TestData.createTestData(2, TestData.TEST_IMAGE);
        if(EmptyDeal.isEmpy(homeCoupons)){
            layCoupon.setVisibility(View.GONE);
            return;
        }
        layCoupon.setVisibility(View.VISIBLE);
        discountList.setAdapter(new ViewHolderAdapter<CouponBean>(that, homeCoupons, R.layout.item_my_coupon) {
            @Override
            public void fillView(ViewHodler viewHodler, final CouponBean item, int position) {
                viewHodler.setText(R.id.itemTitle, item.title);
                viewHodler.setText(R.id.itemTitleDesc, String.format("%s~%s", item.start_time, item.end_time));
                viewHodler.setText(R.id.itemPrice, item.discount_money);
                GlideMediaLoader.load(mContext, viewHodler.getView(R.id.imgProduct), item.cover);
                viewHodler.setClickListener(R.id.itemState, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UIUtils.showLoadDialog(that, "领取中...");
                        BaseQuestStart.getDiscount(HomeFragment.this, item.id);
                    }
                });
                viewHodler.setText(R.id.itemState, item.is_receive == 1 ? "已领取" : "点击领取");
                viewHodler.getView(R.id.itemState).setEnabled(item.is_receive != 1);
            }
        });
    }


    /**
     * 限时商品
     * @param pageBean
     */
    private void setLimiteGoodsData(final PageBean<HomeHotGoods> pageBean) {
        final List<HomeHotGoods> homeHotGoods = pageBean.list;
        if(EmptyDeal.isEmpy(homeHotGoods)){
            layLimite.setVisibility(View.GONE);
            return;
        }
        layLimite.setVisibility(View.VISIBLE);
        flashGridview.setAdapter(new HomeGoodsAdapter(that, homeHotGoods) {
            @Override
            public void fillView(ViewHodler viewHodler, HomeHotGoods item, int position) {
                super.fillView(viewHodler, item, position);
                viewHodler.setText(R.id.txtProductPrice, String.format("¥ %s", item.limited_price));
                //viewHodler.setVisibility(R.id.txtProductOldPrice,false);
            }
        });
        flashGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startIntent.startGoodsDetailsActivity(that, homeHotGoods.get(position).id);
            }
        });
        AHandler.cancel(task);
        task = new AHandler.Task() {
            {
                txtLimitTime.setVisibility(View.VISIBLE);
            }
            @Override
            public void update() {
                String limiteTime = pageBean.getLimteTime();
                Logger.D("首页限时购倒计时:" + limiteTime);
                txtLimitTime.setText( limiteTime);
            }
        };
        AHandler.runTask(task, 0, 1000);
    }

    /**
     * 爆款商品
     * @param homeHotGoods
     */
    private void setHotGoodsData(final List<HomeHotGoods> homeHotGoods) {
        if(EmptyDeal.isEmpy(homeHotGoods)){
            layHot.setVisibility(View.GONE);
            return;
        }
        layHot.setVisibility(View.VISIBLE);
        hotGridview.setAdapter(new HomeGoodsAdapter(that, homeHotGoods));
        hotGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startIntent.startGoodsDetailsActivity(that, homeHotGoods.get(position).id);
            }
        });
    }

    /**
     * 轮播图
     * @param beanList
     */
    private void setBannerData(final List<BannerBean> beanList) {
        if(convenientBanner==null)return;
        BannerUtils.setNetBanner(convenientBanner, beanList, true);
        convenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if(!EmptyDeal.isEmpy(beanList.get(position).url)) {
                    CommonIntent.OpenUrl(that, beanList.get(position).url);
                }
                //pictureShow.setArgment(bannerTestData, position);
                //pictureShow.show();
            }
        });
    }

    @Subscribe
    public void eventBusMethod(String action) {
        switch (action){
            case "refresh_home_page":
                loadHomeData();
                break;
            case "refresh_home_discount":
                BaseQuestStart.getDiscountList(this);
                break;
        }

    }

    LocationBean locationBean;
    @Override
    public void requestFinish() {
        lSwipeRefreshLayout.setRefreshing(false);
        super.requestFinish();
    }
    @Subscribe
    public void onLocationChange(LocationBean locationBean) {
        this.locationBean = locationBean;
        if (!locationBean.hasId()) {//如果没有id则为定位所得,去设置一次默认城市
            BaseQuestStart.put_area(this, locationBean.city);
        }

        Logger.D("所在城市信息变更: " + locationBean);

    }


    @OnClick({R.id.hotMore, R.id.flashMore, R.id.couponMore, R.id.layScanQR,R.id.txtSearchType})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hotMore://爆款更多
                startIntent.startGoodsListActivity(that, 2, null);
                break;
            case R.id.flashMore://限时更多
                startIntent.startGoodsListActivity(that, 1, null);
                break;
            case R.id.couponMore://优惠劵更多
                startIntent.startMyCouponActivity(that, 0);
                break;
            case R.id.layScanQR://扫码
                startIntent.startScanQRActivity(that);
                break;
            case R.id.txtSearchType://搜索类型切换
                if (listPopupWindow == null || !listPopupWindow.isShowing()) {
                    if (listPopupWindow == null) {
                        listPopupWindow = new ListPopupWindow(that);
                        listPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                            @Override
                            public void onDismiss() {
                                //isShow = false;
                                TextColorUtils.setCompoundDrawables(txtSearchType, 0, 0, R.drawable.home_icon_droplist_n, 0);
                            }
                        });
                        final List<String> attrStrs = Arrays.asList("产品", "工地");
                        //设置最大高度为120
                        int itemHeight= DisplayUtil.dp2px(that, 40);
                        int totalHeight=itemHeight*EmptyDeal.size(attrStrs);
                        int adviceHeight=Math.min(totalHeight,DisplayUtil.dp2px(that, 120));
                        int width=view.getWidth();
                        listPopupWindow.setHeight(adviceHeight);
                        listPopupWindow.setWidth(width);
                        if(Build.VERSION.SDK_INT<=Build.VERSION_CODES.KITKAT) {
                            listPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                        }
                        listPopupWindow.setAdapter(new ViewHolderAdapter<String>(that, attrStrs, R.layout.item_simple_list_text) {
                            @Override
                            public void fillView(ViewHodler viewHodler, String item, int i) {
                                viewHodler.setText(R.id.text, item);
                            }
                        });
                        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                searchType = position;
                                txtSearchType.setText(attrStrs.get(position));
                                listPopupWindow.dismiss();

                            }
                        });
                    }
                    listPopupWindow.setSelection(searchType);
                    listPopupWindow.setAnimationStyle(R.style.PopupAnimation);
                    listPopupWindow.setAnchorView(view);
                    listPopupWindow.setModal(true);
                    listPopupWindow.setDropDownGravity(Gravity.BOTTOM);
                    listPopupWindow.show();
                    ListView listView = listPopupWindow.getListView();
                    listView.setDivider(null);
                    listView.setVerticalScrollBarEnabled(false);
                    listView.setHorizontalScrollBarEnabled(false);
                    TextColorUtils.setCompoundDrawables(txtSearchType, 0, 0, R.drawable.home_icon_droplist_up, 0);
                } else {
                    TextColorUtils.setCompoundDrawables(txtSearchType, 0, 0, R.drawable.home_icon_droplist_n, 0);
                    listPopupWindow.dismiss();
                }

                break;
        }
    }

    @Override
    public void onStop() {
        AHandler.cancel(task);
        super.onStop();
    }
}
