package com.cnsunru.budget;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.cnsunru.R;
import com.cnsunru.budget.dialogs.DeleteConfimDialogFragment;
import com.cnsunru.budget.fragment.RoomCustomInfoFragment;
import com.cnsunru.budget.fragment.RoomInfoFragment;
import com.cnsunru.budget.mode.RoomInfoBean;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.base.ViewPagerFragmentAdapter;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.common.widget.NoScrollViewPager;
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.uiutils.UIUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.cnsunru.budget.fragment.RoomCustomInfoFragment.MODIFY_DOOR_WINDOW;
import static com.cnsunru.common.quest.BaseQuestConfig.EDIT_HOUSE_INFO_CODE;

/**
 * Created by WQ on 2017/9/14.
 *
 * @Describe 房间信息
 */

public class RoomInfoActivity extends LBaseActivity {
    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.tabLayout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.viewPager)
    NoScrollViewPager viewPager;
    ViewPagerFragmentAdapter mVPAdapter;
    RoomInfoFragment roomInfoFragment;
    public final static String EDIT_HOUSE_ID = "edit_house_id";

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        initEventBus();
        setContentView(R.layout.activity_room_info);
        titleBar.setTitle(getIntent().getStringExtra("name"));
        List<Fragment> baseFragments = new ArrayList<>();
        roomInfoFragment = RoomInfoFragment.newInstance();
        String[] mTitles;
        if (getIntent().getStringExtra("id") != null) {
            titleBar.setTitle("编辑房间");
            loadPageData();
            mTitles = new String[]{"房间信息", "自定义编辑"};
            baseFragments.add(roomInfoFragment);
            baseFragments.add(RoomCustomInfoFragment.newInstance());
//            titleBar.setRightVisible(View.GONE);//
        } else {
            mTitles = new String[]{"房间信息"};
            titleBar.setTitle("添加房间");
            tabLayout.setVisibility(View.GONE);
            baseFragments.add(roomInfoFragment);
            RoomInfoBean roomInfoBean = new RoomInfoBean();
            roomInfoBean.house_info = new RoomInfoBean.HouseInfoBean();
            roomInfoBean.door_info = new ArrayList<>();
            roomInfoBean.window_info = new ArrayList<>();
            EventBus.getDefault().postSticky(roomInfoBean);

        }
        mVPAdapter = new ViewPagerFragmentAdapter(this.getSupportFragmentManager());
        mVPAdapter.setFragments(baseFragments);
        viewPager.setAdapter(mVPAdapter);
        viewPager.setOffscreenPageLimit(baseFragments.size());
        setTabData(tabLayout, mTitles);
        viewPager.setCurrentItem(0, false);
        viewPager.setScroll(false);
        titleBar.setRightAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager.getCurrentItem() == 0) {
                    roomInfoFragment.saveHouseInfo();
                }else {
                    finish();
                }
            }
        });

    }

    private void loadPageData() {
        if (getIntent().getStringExtra("id") != null) {
            UIUtils.showLoadDialog(that);
            BaseQuestStart.edit_house_info(this, getIntent().getStringExtra("id"));
            getSession().put(EDIT_HOUSE_ID, getIntent().getStringExtra("id"));
        }
    }

    public void nofityUpdate(int requestCode, BaseBean bean) {
        switch (requestCode) {
            case EDIT_HOUSE_INFO_CODE:
                RoomInfoBean roomInfoBean = bean.Data();
                if (bean.status == 1 && roomInfoBean != null) {
                    roomInfoBean.house_info.name = getIntent().getStringExtra("name");
                    roomInfoBean.house_info.typeValue = getSession().getString("edit_room_type");
                    EventBus.getDefault().postSticky(roomInfoBean);
                } else {
                    UIUtils.shortM(bean);
                }
                break;
        }
        super.nofityUpdate(requestCode, bean);
    }

    @Subscribe
    public void eventAction(String action) {
        switch (action) {

            case MODIFY_DOOR_WINDOW://重新获取页面数据
                loadPageData();
                break;
        }
    }

    @Override
    public void onBackPressed() {
//        if(roomInfoFragment.hasModify){
//            DeleteConfimDialogFragment.showFragment(getSupportFragmentManager(), "警告", "修改尚未保存,是否保存?", new DeleteConfimDialogFragment.ConfirmActionListener() {
//                @Override
//                public void onConfirm(View view) {
//                    roomInfoFragment.saveHouseInfo();
//                }
//            });
//        }else
        {
            super.onBackPressed();
        }

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
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                titleBar.setRightText(position == 0 ? "保存" : "确认");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        getSession().remove(EDIT_HOUSE_ID);
        super.onDestroy();
    }
}
