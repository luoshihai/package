package com.cnsunru.budget;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnsunru.R;
import com.cnsunru.budget.fragment.MaterialInfoFragment;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.base.ViewPagerFragmentAdapter;
import com.cnsunru.common.util.ViewUtils;
import com.cnsunru.common.widget.NoScrollViewPager;
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.cnsunru.home.mode.LocationStateMode;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.cnsunru.common.util.ViewUtils.radioSwitchListener;


/**
 * Created by WQ on 2017/9/14.
 *
 * @Describe 材料清单
 */

public class MaterialListInfoActivity extends LBaseActivity {
    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.tabLayout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.viewPager)
    NoScrollViewPager viewPager;
    @BindView(R.id.txtLocation)
    TextView txtLocation;
    @BindView(R.id.tableMode)
    ImageView tableMode;
    @BindView(R.id.imgMode)
    ImageView imgMode;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tvMaterialMoney)
    TextView tvMaterialMoney;
    @BindView(R.id.labLeft)
    TextView labLeft;
    @BindView(R.id.tvBaseMoney)
    TextView tvBaseMoney;
    @BindView(R.id.labRight)
    TextView labRight;
    private String[] mTitles = {"全部", "主材", "辅材"};
    ViewPagerFragmentAdapter mVPAdapter;
    private List<Fragment> baseFragments;
    LocationStateMode locationStateMode;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_material_listinfo);
        locationStateMode = new LocationStateMode(this, txtLocation);
        baseFragments = new ArrayList<>();
        baseFragments.add(MaterialInfoFragment.newInstance());
        baseFragments.add(MaterialInfoFragment.newInstance());
        baseFragments.add(MaterialInfoFragment.newInstance());
        mVPAdapter = new ViewPagerFragmentAdapter(this.getSupportFragmentManager());
        mVPAdapter.setFragments(baseFragments);
        viewPager.setAdapter(mVPAdapter);
        viewPager.setOffscreenPageLimit(baseFragments.size());
        setTabData(tabLayout, mTitles);
        viewPager.setCurrentItem(0, false);
        viewPager.setScroll(false);
        radioSwitchListener(0, new ViewUtils.OnRadioSwitchListener() {//切换显示
            @Override
            public void onRadioSwitch(View[] views, int selectIndex) {
                int resTable = selectIndex == 0 ? R.drawable.project_icon_table_c : R.drawable.project_icon_table_n;
                int resImg = selectIndex == 0 ? R.drawable.project_icon_img_n : R.drawable.project_icon_img_c;
                tableMode.setImageResource(resTable);
                imgMode.setImageResource(resImg);
            }
        }, tableMode, imgMode);
        labLeft.setText("主材花费(元)");
        labRight.setText("辅材花费(元)");
    }


    /**
     * 设置标签视图内容,
     *
     * @param tabLayout
     * @param titles    文字
     */
    public void setTabData(SlidingTabLayout tabLayout, String titles[]) {
        tabLayout.setViewPager(viewPager, titles);
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
