package com.cnsunru.budget.adapters;

import android.content.Context;

import com.cnsunru.budget.mode.DefaltCalculateInfo;
import com.sunrun.sunrunframwork.adapter.ViewHodler;
import com.sunrun.sunrunframwork.adapter.ViewHolderAdapter;

import java.util.List;

/**
 * Created by cnsunrun on 2017-09-14.
 */

public class ProjectListAdapter<T> extends ViewHolderAdapter<T> {

    public ProjectListAdapter(Context context, List<T> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void fillView(ViewHodler viewHodler, T item, int i) {

    }
}
