package com.cnsunru.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.cnsunru.R;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.base.ViewPagerFragmentAdapter;
import com.cnsunru.common.widget.NoScrollViewPager;
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.cnsunru.user.fragment.ActivityCollectFragment;
import com.cnsunru.user.fragment.WorkSiteCollectFragment;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 我的收藏
 */
public class MyCollectActivity extends LBaseActivity {

    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.tabLayout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.viewPager)
    NoScrollViewPager viewPager;
    private String[] mTitles = {"活动", "在建工地"};
    ViewPagerFragmentAdapter mVPAdapter;
    private List<Fragment> baseFragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);
        baseFragments = new ArrayList<>();
        baseFragments.add(ActivityCollectFragment.newInstance());
        baseFragments.add(WorkSiteCollectFragment.newInstance());
        mVPAdapter = new ViewPagerFragmentAdapter(this.getSupportFragmentManager());
        mVPAdapter.setFragments(baseFragments);
        viewPager.setAdapter(mVPAdapter);
        viewPager.setOffscreenPageLimit(baseFragments.size());
        setTabData(tabLayout,mTitles);
        viewPager.setCurrentItem(0,false);
        viewPager.setScroll(false);
        titleBar.setRightAction(new View.OnClickListener() {
            boolean isEdit=false;
            @Override
            public void onClick(View v) {
                isEdit=!isEdit;
                for (Fragment baseFragment : baseFragments) {
                    ((WorkSiteCollectFragment) baseFragment).toggleEditMode();
                }
                titleBar.setRightText(isEdit?"完成":"编辑");
            }
        });
    }

    /**
     * 设置标签视图内容,
     *
     * @param tabLayout
     * @param titles        文字
     */
    public void setTabData(SlidingTabLayout tabLayout, String titles[]) {
        tabLayout.setViewPager(viewPager,titles);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
//        setMsgViewMargin(tabLayout);
    }
}
