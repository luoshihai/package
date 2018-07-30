package com.cnsunru.home.mode;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.sunrun.sunrunframwork.http.cache.NetSession;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索结果
 * Created by WQ on 2017/9/18.
 */

public class SearchHistoryMode {
    private NetSession session;
    private List<SearchHistoryBean> searchHistorys;
    private String KEY=SearchHistoryMode.class.getName();
    public SearchHistoryMode(NetSession session) {
        this.session = session;
        searchHistorys=session.getBean(KEY,new TypeToken<List<SearchHistoryBean>>(){});
        if(searchHistorys==null){
            searchHistorys=new ArrayList<>();
        }
    }
    public void addHistory(String keywords,String searchType){
        if(TextUtils.isEmpty(keywords))return;
        SearchHistoryBean searchHistoryBean=new SearchHistoryBean();
        searchHistoryBean.keywords=keywords;
        searchHistoryBean.seachType=searchType;
        searchHistorys.remove(keywords);//移除重复项
        searchHistorys.add(0,searchHistoryBean);
        if(searchHistorys.size()>10){
            searchHistorys.remove(searchHistorys.size()-1);
        }
    }
    public void clearHistory(int position){
        searchHistorys.remove(position);
    }
    public void clearAllHistory(){
        searchHistorys.clear();
    }

    public List<SearchHistoryBean>getSearchHistorys(){
        return searchHistorys;
    }

    public void save(){
        session.put(KEY,searchHistorys);
    }

}
