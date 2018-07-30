package com.cnsunru.budget.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cnsunru.R;
import com.cnsunru.budget.adapters.MaterialInfoAdapter;
import com.cnsunru.common.base.LBaseFragment;
import com.sunrun.sunrunframwork.weight.ListViewForScroll;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by WQ on 2017/9/14.
 * @Describe 材料清单的Fragment
 */

public class MaterialInfoFragment extends LBaseFragment {
    @BindView(R.id.listView)
    ListViewForScroll listView;
    MaterialInfoAdapter adapter;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<String> stringList = Arrays.asList("瓷砖", "地板","天花板");
        adapter = new MaterialInfoAdapter(that, stringList);
        listView.setAdapter(adapter);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_materialinfo;
    }

    public static MaterialInfoFragment newInstance() {
        Bundle args = new Bundle();
        MaterialInfoFragment fragment = new MaterialInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
