package com.qqq.asdas.view;

import com.qqq.asdas.DQDApi.model.twins.Feedlist;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;

import java.util.List;

/**
 * Created by chenxin.
 * Date: 2017/5/21.
 * Time: 13:11.
 */

public interface TwinsView extends MvpLceView<List<Feedlist>> {
    void addData(List<Feedlist> list);

    void haveLoadMore(boolean b);
}
