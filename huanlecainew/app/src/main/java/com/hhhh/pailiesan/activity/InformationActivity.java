package com.hhhh.pailiesan.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hhhh.pailiesan.base.KBaseActivity;
import com.hhhh.pailiesan.base.KBaseFragment;
import com.hhhh.pailiesan.fragment.BasketBallNewsFragment;
import com.hhhh.pailiesan.fragment.FootBallNewsFragment;
import com.hhhh.pailiesan.fragment.FragmentPagerAdapter;
import com.hhhh.pailiesan.fragment.NumberNewsFragment;
import com.hhhh.pailiesan.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by QYQ on 2017/9/25.
 */

public class InformationActivity extends KBaseActivity {
    @BindView(R.id.type1_rbt3)
    RadioButton type1Rbt3;
    @BindView(R.id.type2_rbt3)
    RadioButton type2Rbt3;
    @BindView(R.id.type3_rbt3)
    RadioButton type3Rbt3;
    @BindView(R.id.type_rgp3)
    RadioGroup typeRgp3;
    @BindView(R.id.viewpager3)
    ViewPager viewpager3;
    private FootBallNewsFragment footBallNewsFragment;
    private BasketBallNewsFragment basketBallNewsFragment;
    private NumberNewsFragment numberNewsFragment;
    private List<KBaseFragment> mFragmentList;
    private FragmentPagerAdapter mAdapter;
    @Override
    protected void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_information);
    }

    @Override
    protected void initView() {
        super.initView();
        setTitleName("资讯");
        setTitleBack(true,0);
        initFragment();
        typeRgp3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                changeTab();
            }
        });
    }
    private void changeTab() {
        if (type1Rbt3.isChecked()) {
            viewpager3.setCurrentItem(0);
        } else if (type2Rbt3.isChecked()) {
            viewpager3.setCurrentItem(1);
        } else {
            viewpager3.setCurrentItem(2);
        }
    }

    @Override
    protected void getData() {
        super.getData();
    }
    private void initFragment() {
        mFragmentList = new ArrayList<KBaseFragment>();
        footBallNewsFragment = new FootBallNewsFragment();//足球
        basketBallNewsFragment = new BasketBallNewsFragment();//篮球
        numberNewsFragment = new NumberNewsFragment();//数字彩
        mFragmentList.add(footBallNewsFragment);
        mFragmentList.add(basketBallNewsFragment);
        mFragmentList.add(numberNewsFragment);
        mAdapter = new FragmentPagerAdapter(this.getSupportFragmentManager(), mFragmentList, null);
        viewpager3.setAdapter(mAdapter);
        setViewPage();
    }
    private void setViewPage() {
        // 设置离屏的数量
        viewpager3.setOffscreenPageLimit(mFragmentList.size());
        mAdapter.notifyDataSetChanged();
        viewpager3.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    type1Rbt3.setChecked(true);
                } else if (position == 1) {
                    type2Rbt3.setChecked(true);
                } else {
                    type3Rbt3.setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
