package com.qqq.asdas.view;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;
import com.qqq.asdas.DQDApi.model.specialNewsColumn.SpecialNewsColumnArticle;

import java.util.List;

/**
 * Created by chenxin.
 * Date: 2017/5/24.
 * Time: 15:44.
 */

public interface SpecialNewsColumnView extends MvpLceView<List<SpecialNewsColumnArticle>> {
    void addData(List<SpecialNewsColumnArticle> data);

    void haveLoadMore(boolean b);
}
