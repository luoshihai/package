package com.jyt.huangguan.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jyt.huangguan.adapter.FragmentViewPagerAdapter;
import com.jyt.huangguan.api.Const;
import com.jyt.huangguan.view.fragment.BaseFragment;
import com.jyt.huangguan.view.fragment.BrandFragment;
import com.jyt.huangguan.view.fragment.InfoFragment;
import com.jyt.huangguan.view.fragment.MapBrandFragment;
import com.jyt.huangguan.view.fragment.MapFragment;
import com.jyt.huangguan.view.fragment.MoreFragment;
import com.jyt.huangguan.view.fragment.ProjectFragment;
import com.jyt.huangguan.view.widget.NoScrollViewPager;
import com.jyt.huangguan.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author LinWei on 2017/10/30 14:32
 */
public class ContentActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.vp_content_container)
    NoScrollViewPager mVpContainer;
    @BindView(R.id.iv_content_map)
    ImageView mIvMap;
    @BindView(R.id.tv_content_map)
    TextView mTvMap;
    @BindView(R.id.ll_content_map)
    LinearLayout mLlMap;
    @BindView(R.id.iv_content_project)
    ImageView mIvProject;
    @BindView(R.id.tv_content_project)
    TextView mTvProject;
    @BindView(R.id.ll_content_project)
    LinearLayout mLlProject;
    @BindView(R.id.iv_content_more)
    ImageView mIvMore;
    @BindView(R.id.tv_content_more)
    TextView mTvMore;
    @BindView(R.id.ll_content_more)
    LinearLayout mLlMore;
    private boolean isFirst;
    private List<BaseFragment> flist;
    private MapBrandFragment mMapBrandFragment;
    private MapFragment mMapFragment;
    private ProjectFragment mProjectFragment;
    private MoreFragment mMoreFragment;
    private FragmentViewPagerAdapter vpAdapter;
    private BrandFragment mBrandFragment;
    private InfoFragment mInfoFragment;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_content;
    }

    @Override
    protected View getContentView() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HideActionBar();
        init();
        initlistener();
        setTabBg(0);

    }

    private void init() {

        Log.e("@#","token=" + Const.gettokenSession());
        flist=new ArrayList<>();
        //内部人员
        mMapFragment=new MapFragment();
        mProjectFragment=new ProjectFragment();
        mMoreFragment=new MoreFragment();
        flist.add(mMapFragment);
        flist.add(mProjectFragment);
        flist.add(mMoreFragment);

        //品牌方
//        mMapBrandFragment = new MapBrandFragment();
//        mBrandFragment = new BrandFragment();
//        mInfoFragment = new InfoFragment();
//        flist.add(mMapBrandFragment);
//        flist.add(mBrandFragment);
//        flist.add(mInfoFragment);
        vpAdapter=new FragmentViewPagerAdapter(getSupportFragmentManager());
        vpAdapter.setFragments(flist);
        mVpContainer.setAdapter(vpAdapter);
        mVpContainer.setOffscreenPageLimit(3);
    }


    private void initlistener(){
        mLlMap.setOnClickListener(this);
        mLlProject.setOnClickListener(this);
        mLlMore.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_content_map:
                setTabBg(0);
                break;
            case R.id.ll_content_project:
                setTabBg(1);
                break;
            case R.id.ll_content_more:
                setTabBg(2);
                break;
            default:
                break;
        }
    }

    /**
     * 下方Tab的点击处理
     * @param selecor
     */
    public void setTabBg(int selecor){
        mIvMap.setImageDrawable(getResources().getDrawable(R.mipmap.map_off));
        mIvProject.setImageDrawable(getResources().getDrawable(R.mipmap.project_off));
        mIvMore.setImageDrawable(getResources().getDrawable(R.mipmap.more_off));//内部人员
//        mIvMore.setImageDrawable(getResources().getDrawable(R.mipmap.brand_off));//品牌方
        mTvMap.setTextColor(getResources().getColor(R.color.vp_text));
        mTvProject.setTextColor(getResources().getColor(R.color.vp_text));
        mTvMore.setTextColor(getResources().getColor(R.color.vp_text));
        switch (selecor) {
            case 0:
                mIvMap.setImageDrawable(getResources().getDrawable(R.mipmap.map_on));
                mTvMap.setTextColor(getResources().getColor(R.color.white));

                break;
            case 1:
                mIvProject.setImageDrawable(getResources().getDrawable(R.mipmap.project_on));
                mTvProject.setTextColor(getResources().getColor(R.color.white));

                break;
            case 2:
                mIvMore.setImageDrawable(getResources().getDrawable(R.mipmap.more_on));
//                mIvMore.setImageDrawable(getResources().getDrawable(R.mipmap.brand_on));
                mTvMore.setTextColor(getResources().getColor(R.color.white));

                break;
            default:
                break;
        }
        resumeSelector();
        mVpContainer.setCurrentItem(selecor);

    }

    private void resumeSelector(){
        if (!isFirst){
            isFirst=true;
            return;
        }

        if (mMapBrandFragment!=null){
            mMapBrandFragment.initSelecotr();
        }
        if (mMapFragment!=null){
            mMapFragment.initSelecotr();
        }
        if (mProjectFragment!=null){
            mProjectFragment.SetSelector();
        }
        if (mBrandFragment !=null){
            mBrandFragment.initSelector();
        }
    }


    /**
     * 双击退出
     */
    private long mPressedTime = 0;
    @Override
    public void onBackPressed() {
        long mNowTime = System.currentTimeMillis();//获取第一次按键时间
        if((mNowTime - mPressedTime) > 2000){//比较两次按键时间差
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mPressedTime = mNowTime;
        }
        else{//退出程序
            this.finish();
            System.exit(0);
        }
    }


}
