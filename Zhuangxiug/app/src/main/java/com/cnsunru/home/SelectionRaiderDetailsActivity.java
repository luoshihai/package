package com.cnsunru.home;

import android.os.Bundle;

import com.cnsunru.R;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.widget.titlebar.TitleBar;

import butterknife.BindView;

/**
 * Created by WQ on 2017/9/4.
 * @Describe 攻略详情
 */

public class SelectionRaiderDetailsActivity extends LBaseActivity {
    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_raider_details);

    }
}
