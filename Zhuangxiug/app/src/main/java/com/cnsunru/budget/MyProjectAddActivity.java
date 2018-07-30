package com.cnsunru.budget;

import android.os.Bundle;

import com.cnsunru.R;
import com.cnsunru.budget.fragment.BudgetFragment;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.cnsunru.order.fragment.MallFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 产品分类选择, 这里直接加载商城页面
 * Created by WQ on 2017/10/17.
 */

public class MyProjectAddActivity extends LBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activit_myproject_add);
        BudgetFragment mallFragment = new BudgetFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("needFinish", true);
        mallFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_my_container, mallFragment)
                .commitAllowingStateLoss();
    }
}
