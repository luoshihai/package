package com.hhhh.pailiesan.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hhhh.pailiesan.base.KBaseActivity;
import com.hhhh.pailiesan.base.KBaseFragment;
import com.hhhh.pailiesan.fragment.ForecastFragment;
import com.hhhh.pailiesan.fragment.FragmentPagerAdapter;
import com.hhhh.pailiesan.fragment.KJ3Fragment;
import com.hhhh.pailiesan.fragment.LotterFragment;
import com.hhhh.pailiesan.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by QYQ on 2017/9/13.
 */

public class LotteryActivity extends KBaseActivity {

    @BindView(R.id.type1_rbt1)
    RadioButton type1Rbt1;
    @BindView(R.id.type2_rbt1)
    RadioButton type2Rbt1;
    @BindView(R.id.type3_rbt1)
    RadioButton type3Rbt1;
    @BindView(R.id.type_rgp1)
    RadioGroup typeRgp1;
    @BindView(R.id.viewpager1)
    ViewPager viewpager1;
    private LotterFragment lotterFragment;
    private ForecastFragment forecastFragment;
    private KJ3Fragment kj3Fragment;
    private List<KBaseFragment> mFragmentList;
    private FragmentPagerAdapter mAdapter;

    @Override
    protected void setRootView() {
        super.setRootView();
        setContentView(R.layout.atcivity_lottery);
    }

    @Override
    protected void getData() {
        super.getData();
    }

    @Override
    protected void initView() {
        super.initView();
        setTitleName("开奖结果");
        setTitleBack(true,0);
        initFragment();
        typeRgp1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                changeTab();
            }
        });
    }


    private void changeTab() {
        if (type1Rbt1.isChecked()) {
            viewpager1.setCurrentItem(0);
        } else if (type2Rbt1.isChecked()) {
            viewpager1.setCurrentItem(1);
        } else {
            viewpager1.setCurrentItem(2);
        }
    }

    private void initFragment() {
        mFragmentList = new ArrayList<KBaseFragment>();
        lotterFragment = new LotterFragment();
        forecastFragment = new ForecastFragment();
        kj3Fragment = new KJ3Fragment();
        mFragmentList.add(lotterFragment);
        mFragmentList.add(forecastFragment);
        mFragmentList.add(kj3Fragment);
        mAdapter = new FragmentPagerAdapter(this.getSupportFragmentManager(), mFragmentList, null);
        viewpager1.setAdapter(mAdapter);
        setViewPage();
    }

    private void setViewPage() {
        // 设置离屏的数量
        viewpager1.setOffscreenPageLimit(mFragmentList.size());
        mAdapter.notifyDataSetChanged();
        viewpager1.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    type1Rbt1.setChecked(true);
                } else if (position == 1) {
                    type2Rbt1.setChecked(true);
                } else {
                    type3Rbt1.setChecked(true);
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
