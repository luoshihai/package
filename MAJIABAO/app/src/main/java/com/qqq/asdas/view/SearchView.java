package com.qqq.asdas.view;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;
import com.qqq.asdas.DQDApi.model.search.News;
import com.qqq.asdas.DQDApi.model.search.Search;

import java.util.List;

/**
 * Created by chenxin.
 * Date: 2017/5/25.
 * Time: 22:18.
 */

public interface SearchView extends MvpLceView<Search> {
    void loadData(String keyword);

    void addNews(List<News> news);
    void haveLoadMore(boolean b);
}
