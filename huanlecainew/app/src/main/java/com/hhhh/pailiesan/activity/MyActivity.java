package com.hhhh.pailiesan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hhhh.pailiesan.R;
import com.hhhh.pailiesan.fragment.MatchFragment;
import com.hhhh.pailiesan.fragment.MyFragment;
import com.hhhh.pailiesan.fragment.TabNewOneFragment;
import com.hhhh.pailiesan.fragment.TabThreeFragment;
import com.hhhh.pailiesan.model.LoginModel;
import com.hhhh.pailiesan.utils.CollectsUtil;
import com.hhhh.pailiesan.utils.ExtraName;
import com.hhhh.pailiesan.utils.Tools;
import com.hhhh.pailiesan.utils.UserCacheData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by QYQ on 2017/9/8.
 */

public class MyActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.radio_home)
    RadioButton radioHome;
    @BindView(R.id.radio_shop)
    RadioButton radioShop;
    @BindView(R.id.radio_me)
    RadioButton radioMe;
    @BindView(R.id.rg_tab)
    RadioGroup mRadioGroupTab;
    @BindView(R.id.tab_bottom_layout)
    LinearLayout tabBottomLayout;
    @BindView(R.id.radio_mine)
    RadioButton radioMine;
    private FragmentManager mFragmentManager = getSupportFragmentManager();
    private TabNewOneFragment tabOneFragment;
    private String tabOneFragmentTag = "tabOneFragmentTag";
    private MatchFragment tabTwoFragment;
    private String tabTwoFragmentTag = "tabTwoFragmentTag";
    private TabThreeFragment tabThreeFragment;
    private String tabThreeFragmentTag = "tabThreeFragmentTag";
    private MyFragment myFragment;
    private String myFragmentTag = "myFragmentTag";
    private List<String> tagList = new ArrayList<>();
    private final int TAB_ONE = 0, TAB_TWO = 1, TAB_THREE = 2, TAB_FOUR = 3;
    private String curFragmentTag;
    RadioButton[] radioButtons;
    private int curTab, lastTab = 0;
    private TextView title;
    private boolean isQuit = false;
    private Timer timer = new Timer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.bind(this);
        initGone();
        initFragment();
        initWidget();
        login();
    }

    private void initGone() {
//    <string name="app_shouye">显示</string>
//    <string name="app_zuqiu">显示</string>
//    <string name="app_liaotian">显示</string>
//    <string name="app_shezhi">显示</string>
        if (!Tools.getResoult(this, R.string.app_shouye)) {
            radioHome.setVisibility(View.GONE);
        }
        if (!Tools.getResoult(this, R.string.app_zuqiu)) {
            radioShop.setVisibility(View.GONE);
        }
        if (!Tools.getResoult(this, R.string.app_liaotian)) {
            radioMe.setVisibility(View.GONE);
        }
        if (!Tools.getResoult(this, R.string.app_shezhi)) {
            radioMine.setVisibility(View.GONE);
        }
    }

    /***
     * 初始化Fragment
     */
    private void initFragment() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        tabOneFragment = new TabNewOneFragment();
        transaction.add(R.id.content, tabOneFragment, tabOneFragmentTag);
        tabTwoFragment = new MatchFragment();
        transaction.add(R.id.content, tabTwoFragment, tabTwoFragmentTag);
        tabThreeFragment = new TabThreeFragment();
        transaction.add(R.id.content, tabThreeFragment, tabThreeFragmentTag);
        myFragment = new MyFragment();
        transaction.add(R.id.content, myFragment, myFragmentTag);
        transaction.commitAllowingStateLoss();
        tagList.add(tabOneFragmentTag);
        tagList.add(tabTwoFragmentTag);
        tagList.add(tabThreeFragmentTag);
        tagList.add(myFragmentTag);
    }

    private void initWidget() {
        title = (TextView) toolbar.findViewById(R.id.title_name);
        mRadioGroupTab.setOnCheckedChangeListener(this);
        radioButtons = new RadioButton[]{radioHome, radioShop, radioMe, radioMine};
        int pos = getIntent().getIntExtra("pos", 0);
        mRadioGroupTab.check(radioButtons[pos].getId());
        setTabSelection(pos);
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     */
    private void setTabSelection(int index) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideAllFragments(transaction);
        curFragmentTag = tagList.get(index);
        switch (index) {
            case TAB_ONE:
                if (tabOneFragment == null) {
                    tabOneFragment = new TabNewOneFragment();
                } else {
                    transaction.show(tabOneFragment);
                }
                break;
            case TAB_TWO:
                if (tabTwoFragment == null) {
                    tabTwoFragment = new MatchFragment();
                    transaction.add(R.id.content, tabTwoFragment);
                } else {
                    transaction.show(tabTwoFragment);
                }
                break;

            case TAB_THREE:
                if (tabThreeFragment == null) {
                    tabThreeFragment = new TabThreeFragment();
                    transaction.add(R.id.content, tabThreeFragment);
                } else {
                    transaction.show(tabThreeFragment);
                }
                break;

            case TAB_FOUR:
                if (myFragment == null) {
                    myFragment = new MyFragment();
                    transaction.add(R.id.content, myFragment);
                } else {
                    transaction.show(myFragment);
                }
                break;
        }
        transaction.commit();
        curTab = index;
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    public void hideAllFragments(FragmentTransaction transaction) {
        if (null != tabOneFragment) {
            transaction.hide(tabOneFragment);
        }
        if (null != tabTwoFragment) {
            transaction.hide(tabTwoFragment);
        }

        if (null != tabThreeFragment) {
            transaction.hide(tabThreeFragment);
        }
        if (null != myFragment) {
            transaction.hide(myFragment);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.radio_home:

                title.setText(getResources().getString(R.string.app_table_1));
                setTabSelection(TAB_ONE);
                break;
            case R.id.radio_shop:
                title.setText(getResources().getString(R.string.app_table_2));
                setTabSelection(TAB_TWO);
                break;
            case R.id.radio_me:
                title.setText(getResources().getString(R.string.app_table_3));

                setTabSelection(TAB_THREE);
                break;
            case R.id.radio_mine:
                title.setText(getResources().getString(R.string.app_table_4));
                setTabSelection(TAB_FOUR);
        }
    }

    /*在fragment的管理类中，我们要实现这部操作，而他的主要作用是，当D这个activity回传数据到
    这里碎片管理器下面的fragnment中时，往往会经过这个管理器中的onActivityResult的方法。*/
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*在这里，我们通过碎片管理器中的Tag，就是每个碎片的名称，来获取对应的fragment*/
        Fragment f = mFragmentManager.findFragmentByTag(curFragmentTag);
        /*然后在碎片中调用重写的onActivityResult方法*/
        f.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int flag = intent.getIntExtra(ExtraName.TAB_INDEX, 0);
        if (CollectsUtil.isNotEmpty(radioButtons)) {
            mRadioGroupTab.check(radioButtons[flag].getId());
            setTabSelection(flag);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isQuit == false) {
                isQuit = true;
                Toast.makeText(MyActivity.this, "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();
                TimerTask task = null;
                task = new TimerTask() {
                    @Override
                    public void run() {
                        isQuit = false;
                    }
                };
                timer.schedule(task, 2000);
            } else {
                finish();
            }
        }
        return false;
    }

    private void login() {
        String url = "http://api.icaipiao123.com/api/v6/user/phonelogin";
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("passwd", "123456")
                .add("phone", "13823269614")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Log.e("登录", str);
                Gson gson = new Gson();
                LoginModel date = gson.fromJson(str, LoginModel.class);
                if ("0".equals(date.getStatus())) {//登录成功
                    UserCacheData.put(MyActivity.this, "phone", "13823269614");
                    UserCacheData.put(MyActivity.this, "password", "123456");
                    UserCacheData.put(MyActivity.this, "sid", date.getData().getSid());
                    UserCacheData.put(MyActivity.this, "isLogin", true);
                } else {

                }
            }
        });
    }
}
