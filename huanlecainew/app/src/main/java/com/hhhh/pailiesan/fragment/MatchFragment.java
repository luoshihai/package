package com.hhhh.pailiesan.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hhhh.pailiesan.R;
import com.hhhh.pailiesan.base.KBaseFragment;
import com.hhhh.pailiesan.utils.Tools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by QYQ on 2017/9/25.
 */

public class MatchFragment extends KBaseFragment {


    @BindView(R.id.type1_rbt)
    RadioButton type1Rbt;
    @BindView(R.id.type2_rbt)
    RadioButton type2Rbt;
    @BindView(R.id.type3_rbt)
    RadioButton type3Rbt;
    @BindView(R.id.type_rgp)
    RadioGroup typeRgp;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    Unbinder unbinder;
    private AllMatchFragment allMatch;
    private FootBallMatchFragment footBallMatch;
    private ZCMatchFragment zhongCaoMatch;
    private List<KBaseFragment> mFragmentList;
    private FragmentPagerAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initFragment();
        initGoneView();
        return view;
    }
//    <string name="app_quanbu">显示</string>
//    <string name="app_jingzhu">显示</string>
//    <string name="app_zhongchao">显示</string>

    private void initGoneView() {
        if (!Tools.getResoult(getActivity(), R.string.app_quanbu)) {
            type1Rbt.setVisibility(View.GONE);
        }
        if (!Tools.getResoult(getActivity(), R.string.app_jingzhu)) {
            type2Rbt.setVisibility(View.GONE);
        }
        if (!Tools.getResoult(getActivity(), R.string.app_zhongchao)) {
            type3Rbt.setVisibility(View.GONE);
        }
    }


    private void initView(){
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
        } else {
            viewpager.setCurrentItem(2);
        }
    }
    private void initFragment() {
        mFragmentList = new ArrayList<KBaseFragment>();
        allMatch = new AllMatchFragment();//全部
        footBallMatch = new FootBallMatchFragment();//竞足
        zhongCaoMatch = new ZCMatchFragment();//中超
        mFragmentList.add(allMatch);
        mFragmentList.add(footBallMatch);
        mFragmentList.add(zhongCaoMatch);
        mAdapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager(), mFragmentList, null);
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
                }else {
                    type3Rbt.setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }
}
