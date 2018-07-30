package com.jyt.huangguan.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.jyt.huangguan.adapter.FragmentViewPagerAdapter;
import com.jyt.huangguan.api.BeanCallback;
import com.jyt.huangguan.bean.BaseJson;
import com.jyt.huangguan.bean.ProgressBean;
import com.jyt.huangguan.helper.IntentKey;
import com.jyt.huangguan.model.ProjectDetailModel;
import com.jyt.huangguan.model.impl.ProjectDetailModelImpl;
import com.jyt.huangguan.view.fragment.FinishConstructionFragment;
import com.jyt.huangguan.view.fragment.UnderConstructionFragment;
import com.jyt.huangguan.view.fragment.ViewConstructFragment;
import com.jyt.huangguan.view.widget.NoScrollViewPager;
import com.jyt.huangguan.R;

import butterknife.BindView;
import okhttp3.Call;

/**
 * 有权限操作对页面
 * 查看施工 施工中 施工完毕
 * Created by v7 on 2017/11/4.
 */

public class ConstructionActivity extends BaseActivity {
    @BindView(R.id.v_tabLayout)
    TabLayout vTabLayout;
    @BindView(R.id.v_viewPager)
    NoScrollViewPager vViewPager;

    FragmentViewPagerAdapter adapter;

    ViewConstructFragment viewConstructFragment;
    UnderConstructionFragment underConstructionFragment;
    FinishConstructionFragment finishConstructionFragment;

    ProjectDetailModel projectDetailModel;

    boolean canEdit;
    ProgressBean progressBean;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_construction;
    }

    @Override
    protected View getContentView() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        projectDetailModel = new ProjectDetailModelImpl();
        projectDetailModel.onCreate(getContext());
        setTextTitle("施工中");
        canEdit = getIntent().getBooleanExtra(IntentKey.EDITABLE,false);
        progressBean = getIntent().getParcelableExtra(IntentKey.DATA);

        vViewPager.setAdapter(adapter = new FragmentViewPagerAdapter(getSupportFragmentManager()));
        vViewPager.setOffscreenPageLimit(3);
        vTabLayout.setupWithViewPager(vViewPager);

        adapter.addFragment(viewConstructFragment = new ViewConstructFragment(),"查看施工");
        adapter.addFragment(underConstructionFragment = new UnderConstructionFragment(),"施工中");
        adapter.addFragment(finishConstructionFragment = new FinishConstructionFragment(),"施工完毕");

        Bundle bundle = new Bundle();
        bundle.putParcelable(IntentKey.PROGRESS,progressBean);
        viewConstructFragment.setProjectDetailModel(projectDetailModel);
        viewConstructFragment.setArguments(bundle);
        underConstructionFragment.setProjectDetailModel(projectDetailModel);
        underConstructionFragment.setArguments(bundle);
        finishConstructionFragment.setProjectDetailModel(projectDetailModel);
        finishConstructionFragment.setArguments(bundle);




        adapter.notifyDataSetChanged();

        vViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTextTitle(adapter.getTitles().get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vViewPager.setCurrentItem(0);
        vTabLayout.setVisibility(View.GONE);
        notifyPage();

    }

    /**
     *是否第一次进入
     */
    public void notifyPage(){

        projectDetailModel.getStatus(progressBean.getProjectId(), "1", new BeanCallback<BaseJson<String>>() {
            @Override
            public void onError(Call call, Exception e, int id) {
                vTabLayout.setVisibility(View.GONE);
            }

            @Override
            public void onResponse(BaseJson<String> response, int id) {

                if ("1".equals(response.data)){
                    Log.e("@#","step1-s");
                    vTabLayout.setVisibility(View.VISIBLE);
                    if (1==progressBean.getPermissionState()){
                        Log.e("@#","step2-s");
                        vTabLayout.setVisibility(View.VISIBLE);
                        finishSet();
                    }else {
                        Log.e("@#","step2-e");
                        vTabLayout.setVisibility(View.GONE);
                    }
                }else {
                    Log.e("@#","step1-e");
                    vTabLayout.setVisibility(View.GONE);
                }


            }
        });
    }

    private void finishSet(){
        if ("1".equals(progressBean.getIsfinish())){
            vTabLayout.setVisibility(View.GONE);
        }else {
            vTabLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        projectDetailModel.onDestroy();
    }


}
