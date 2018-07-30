package com.jyt.huangguan.view.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jyt.huangguan.adapter.FragmentViewPagerAdapter;
import com.jyt.huangguan.api.Const;
import com.jyt.huangguan.bean.SearchBean;
import com.jyt.huangguan.helper.IntentKey;
import com.jyt.huangguan.view.fragment.BaseFragment;
import com.jyt.huangguan.view.fragment.ShopNewsFragment;
import com.jyt.huangguan.view.fragment.ShopProgressFragment;
import com.jyt.huangguan.view.widget.NoScrollViewPager;
import com.jyt.huangguan.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author LinWei on 2017/11/2 13:42
 */
public class ShopActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.vp_shop_container)
    NoScrollViewPager mVpContainer;
    @BindView(R.id.iv_shop_news)
    ImageView mIvNews;
    @BindView(R.id.tv_shop_news)
    TextView mTvNews;
    @BindView(R.id.ll_shop_news)
    LinearLayout mLlNews;
    @BindView(R.id.iv_shop_progress)
    ImageView mIvProgress;
    @BindView(R.id.tv_shop_progress)
    TextView mTvProgress;
    @BindView(R.id.ll_shop_progress)
    LinearLayout mLlProgress;
    private List<BaseFragment> flist;
    private ShopNewsFragment mNewsFragment;
    private ShopProgressFragment mProgressFragment;
    private FragmentViewPagerAdapter mAdapter;
    private SearchBean mShopInfo;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop;
    }

    @Override
    protected View getContentView() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
//        initShop();
        initVP();
        initListener();
    }


    private void init(){
        mShopInfo = (SearchBean) getIntent().getSerializableExtra(IntentKey.SHOPINFO);
        if (mShopInfo==null){
            //当通过推送进入该界面时，要将工程ID赋予mShopInfo
            Log.e("@#","jpush enter");
            mShopInfo =new SearchBean();
            mShopInfo.setProjectId(getIntent().getStringExtra(IntentKey.PROJECTID));
            mShopInfo.setProjectName(getIntent().getStringExtra(IntentKey.SHOPNAME));
//            if (getIntent().getStringExtra(IntentKey.PROJECTID)==null || getIntent().getStringExtra(IntentKey.SHOPNAME)==null){
//                //店主
//                mShopInfo.setProjectName(Const.getProjectname());
//                mShopInfo.setProjectId(Const.getProjectid());
//            }else {
//                //内部人员 品牌方
//                mShopInfo.setProjectId(getIntent().getStringExtra(IntentKey.PROJECTID));
//                mShopInfo.setProjectName(getIntent().getStringExtra(IntentKey.SHOPNAME));
//            }
        }
        Log.e("@#","projectID="+ mShopInfo.getProjectId());


        flist=new ArrayList<>();
        Bundle bundle = new Bundle();
        bundle.putSerializable(IntentKey.SHOPINFO,mShopInfo);
        mNewsFragment=new ShopNewsFragment();
        mNewsFragment.setArguments(bundle);
        mProgressFragment=new ShopProgressFragment();
        mProgressFragment.setArguments(bundle);
        mAdapter=new FragmentViewPagerAdapter(getSupportFragmentManager());
        setTextTitle(mShopInfo.getProjectName());
    }

    private void initShop(){
        //店主界面设置
        hideBackBtn();
        setFunctionText("退出");

    }

    private void initVP(){
        flist.add(mNewsFragment);
        flist.add(mProgressFragment);
        mAdapter.setFragments(flist);
        mVpContainer.setOffscreenPageLimit(2);
        mVpContainer.setAdapter(mAdapter);
        mVpContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        setOffBg(0);
                        break;
                    case 1:
                        setOffBg(1);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public SearchBean getShopInfo(){
        return mShopInfo;
    }

    private void initListener(){
        mLlNews.setOnClickListener(this);
        mLlProgress.setOnClickListener(this);
    }

    public void setOffBg(int selecor){
        mIvNews.setImageDrawable(getResources().getDrawable(R.mipmap.tab_jibenxinxi_off));
        mTvNews.setTextColor(getResources().getColor(R.color.vp_text));
        mIvProgress.setImageDrawable(getResources().getDrawable(R.mipmap.tab_jindu_off));
        mTvProgress.setTextColor(getResources().getColor(R.color.vp_text));
        switch (selecor) {
            case 0:
                mIvNews.setImageDrawable(getResources().getDrawable(R.mipmap.tab_jibenxinxi_on));
                mTvNews.setTextColor(getResources().getColor(R.color.white));
                break;
            case 1:
                mIvProgress.setImageDrawable(getResources().getDrawable(R.mipmap.tab_jindu_on));
                mTvProgress.setTextColor(getResources().getColor(R.color.white));
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_shop_news:
                setOffBg(0);
                mVpContainer.setCurrentItem(0);
                break;
            case R.id.ll_shop_progress:
                setOffBg(1);
                mVpContainer.setCurrentItem(1);
                break;
            default:
                break;
        }
    }




    @Override
    public void onFunctionClick() {
        super.onFunctionClick();
        Const.Logout(ShopActivity.this);
        finish();
    }

//    /**
//     * 双击退出
//     * 店主
//     */
//    private long mPressedTime = 0;
//    @Override
//    public void onBackPressed() {
//        long mNowTime = System.currentTimeMillis();//获取第一次按键时间
//        if((mNowTime - mPressedTime) > 2000){//比较两次按键时间差
//            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
//            mPressedTime = mNowTime;
//        }
//        else{//退出程序
//            this.finish();
//        }
//    }
}
