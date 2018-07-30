package com.saadsdasd.niuniu.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.saadsdasd.niuniu.base.KBaseActivity;
import com.saadsdasd.niuniu.base.KBaseFragment;
import com.saadsdasd.niuniu.fragment.FragmentPagerAdapter;
import com.saadsdasd.niuniu.fragment.NoticeFragment;
import com.saadsdasd.niuniu.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by QYQ on 2017/9/22.
 */

public class MyNoticeActivity extends KBaseActivity {
    @BindView(R.id.type1_rbt)
    RadioButton type1Rbt;
    @BindView(R.id.type2_rbt)
    RadioButton type2Rbt;
    @BindView(R.id.type_rgp)
    RadioGroup typeRgp;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private NoticeFragment noticeFragment1;
    private NoticeFragment noticeFragment2;
    private List<KBaseFragment> mFragmentList;
    private FragmentPagerAdapter mAdapter;
    @Override
    protected void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_mynotice);
    }

    @Override
    protected void initView() {
        super.initView();
        setTitleName("通知");
        setTitleBack(true,0);
        initFragment();
        typeRgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                changeTab();
            }
        });

    }
    private void changeTab() {
        if (type1Rbt.isChecked()) {
            viewpager.setCurrentItem(0);
        }else if(type2Rbt.isChecked()){
            viewpager.setCurrentItem(1);
        }
    }
    private void initFragment() {
        mFragmentList = new ArrayList<KBaseFragment>();
        noticeFragment1 = new NoticeFragment();
        noticeFragment2 = new NoticeFragment();
        mFragmentList.add(noticeFragment1);
        mFragmentList.add(noticeFragment2);
        mAdapter = new FragmentPagerAdapter(this.getSupportFragmentManager(), mFragmentList, null);
        viewpager.setAdapter(mAdapter);
        setViewPage();
    }
    private void setViewPage() {
        // 设置离屏的数量
        viewpager.setOffscreenPageLimit(mFragmentList.size());
        mAdapter.notifyDataSetChanged();
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    type1Rbt.setChecked(true);
                } else if(position==1){
                    type2Rbt.setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    @Override
    protected void getData() {
        super.getData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
