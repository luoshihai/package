package com.cnsunru.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.cnsunru.R;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.base.ViewPagerFragmentAdapter;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.common.util.PageLimitDelegate;
import com.cnsunru.common.widget.NoScrollViewPager;
import com.cnsunru.common.widget.titlebar.TabTitleBar;
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.cnsunru.user.fragment.ActivityCollectFragment;
import com.cnsunru.user.fragment.MyProjectFragment;
import com.cnsunru.user.fragment.WorkSiteCollectFragment;
import com.cnsunru.user.mode.MyProjectBean;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 我的项目
 */
public class MyProjectActivity extends LBaseActivity {

    @BindView(R.id.titleBar)
    TabTitleBar titleBar;
    @BindView(R.id.tabLayout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.viewPager)
    NoScrollViewPager viewPager;
    private String[] mTitles = {"全部", "未支付","已支付"};
    ViewPagerFragmentAdapter mVPAdapter;
    private List<Fragment> baseFragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_project);
        baseFragments = new ArrayList<>();
        baseFragments.add(MyProjectFragment.newInstance(null));
        baseFragments.add(MyProjectFragment.newInstance("1"));
        baseFragments.add(MyProjectFragment.newInstance("2"));
        mVPAdapter = new ViewPagerFragmentAdapter(this.getSupportFragmentManager());
        mVPAdapter.setFragments(baseFragments);
        viewPager.setAdapter(mVPAdapter);
        viewPager.setOffscreenPageLimit(baseFragments.size());
        setTabData(tabLayout,mTitles);
        viewPager.setCurrentItem(0,false);
        viewPager.setScroll(false);
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
