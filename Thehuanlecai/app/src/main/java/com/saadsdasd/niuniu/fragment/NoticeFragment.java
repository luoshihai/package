package com.saadsdasd.niuniu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saadsdasd.niuniu.base.KBaseFragment;
import com.saadsdasd.niuniu.R;

/**
 * Created by QYQ on 2017/9/22.
 */

public class NoticeFragment extends KBaseFragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice,null);
        return view;
    }
}
