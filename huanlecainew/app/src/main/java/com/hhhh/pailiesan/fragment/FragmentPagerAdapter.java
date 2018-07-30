package com.hhhh.pailiesan.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.hhhh.pailiesan.base.KBaseFragment;

import java.util.List;

/**
 * Created by QYQ on 2017/9/13.
 */

public class FragmentPagerAdapter extends FragmentStatePagerAdapter {//FragmentPagerAdapter

    private List<KBaseFragment> fragmentList;
    private List<String> titleList;

    public FragmentPagerAdapter(FragmentManager fm, List<KBaseFragment> fragmentList, List<String> titleList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titleList = titleList;
    }



    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
