package com.cnsunru.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.cnsunru.R;
import com.cnsunru.budget.fragment.BudgetFragment;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.base.ViewPagerFragmentAdapter;
import com.cnsunru.common.event.MessageEvent;
import com.cnsunru.common.model.BottomTab;
import com.cnsunru.home.fragment.HomeFragment;
import com.cnsunru.order.fragment.MallFragment;
import com.cnsunru.user.fragment.MyProjectFragment;
import com.cnsunru.user.fragment.UserMineFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayout.widget.MsgView;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.uiutils.UIUtils;
import com.sunrun.sunrunframwork.utils.Utils;
import com.sunrun.sunrunframwork.utils.log.Logger;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.sunrun.sunrunframwork.uiutils.DisplayUtil.dp2px;


/**
 * 主导航页
 */
public class NavigatorActivity extends LBaseActivity {
    private List<Fragment> baseFragments;
    ViewPagerFragmentAdapter mVPAdapter;
    private String[] mTitles = {"首页", "商城", "预算", "我的"};
    private int[] mIconUnselectIds = {
            R.drawable.ic_nav_btn_home_n,
            R.drawable.ic_nav_btn_shop_n,
            R.drawable.ic_nav_btn_budget_n, R.drawable.ic_nav_btn_me_n
    };
    private int[] mIconSelectIds = {
            R.drawable.ic_nav_btn_home_c,
            R.drawable.ic_nav_btn_shop_c,
            R.drawable.ic_nav_btn_budget_c, R.drawable.ic_nav_btn_me_c
    };
    @BindView(R.id.vp_main_center)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    CommonTabLayout mTabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.E("初始化");
        initEventBus();
        setContentView(R.layout.activity_main);
        List<Fragment> fs = getSupportFragmentManager().getFragments();
        if (fs != null)// 处理因为低内存产生的一些问题
            for (Fragment fragment2 : fs) {
                getSupportFragmentManager().beginTransaction().remove(fragment2)
                        .commitAllowingStateLoss();
            }

        baseFragments = new ArrayList<>();
        baseFragments.add(HomeFragment.newInstance());
        baseFragments.add(MallFragment.newInstance());
        baseFragments.add(BudgetFragment.newInstance());
        baseFragments.add(UserMineFragment.newInstance());
        mVPAdapter = new ViewPagerFragmentAdapter(this.getSupportFragmentManager());
        mVPAdapter.setFragments(baseFragments);
        mViewPager.setAdapter(mVPAdapter);
        mViewPager.setOffscreenPageLimit(baseFragments.size());
        setTabData(mTabLayout, mTitles, mIconSelectIds, mIconUnselectIds);
        bindTabEvent(mTabLayout, mViewPager);
        mViewPager.setCurrentItem(0);
//        mTabLayout.showDot(0);
//        mTabLayout.showDot(1);
        setMsgViewMargin(mTabLayout);
//        AHandler.runTask(new AHandler.Task() {
//            @Override
//            public void task() {
//                Connection conn = DBConnection.getConn();
//                try {
//                    Statement statement = conn.createStatement();
//                    ResultSet resultSet = statement.executeQuery("SELECT * FROM `user` ");
//                    while (resultSet.next()){
//                        Logger.D("userId: "+resultSet.getString(1));
//                    }
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//
//                Logger.D("连接成功: "+conn);
//            }
//        });

    }


    @Override
    public void nofityUpdate(int requestCode, BaseBean bean) {
        super.nofityUpdate(requestCode, bean);
    }


    @Override
    public void onBackPressed() {
        if (!Utils.isQuck(2000)) {
            UIUtils.shortM("再按一次退出程序");
        } else {
            finish();
        }
    }


    /**
     * 设置标签视图内容,
     *
     * @param tabLayout
     * @param titles        文字
     * @param selDrawable   图标/选中
     * @param unselDrawable 图标/未选中
     */
    public void setTabData(CommonTabLayout tabLayout, String titles[], int selDrawable[], int unselDrawable[]) {
        ArrayList<CustomTabEntity> customTabEntities = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            customTabEntities.add(BottomTab.createTab(titles[i], selDrawable[i], unselDrawable[i]));
        }
        tabLayout.setTabData(customTabEntities);
        setMsgViewMargin(tabLayout);
    }

    void setMsgViewMargin(CommonTabLayout tabLayout) {

        for (int i = 0, len = tabLayout.getTabCount(); i < len; i++) {
            tabLayout.setMsgMargin(i, -3, 0);
            MsgView msgView = tabLayout.getMsgView(i);
            msgView.setBackgroundColor(Color.parseColor("#FF3F4E"));
            UIUtils.setViewWH(msgView, dp2px(this, 7), dp2px(this, 7));
        }
    }

    int currentIndex;

    /**
     * 绑定标签视图和viewPager 事件
     *
     * @param tabLayout
     * @param viewPager
     */
    void bindTabEvent(final CommonTabLayout tabLayout, final ViewPager viewPager) {
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
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
                tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setCurrnetPage(int index) {
        mViewPager.setCurrentItem(index);
    }


    @Subscribe
    public void eventBusMethod(MessageEvent event){

    }

}
