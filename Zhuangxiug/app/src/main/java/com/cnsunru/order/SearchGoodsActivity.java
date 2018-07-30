package com.cnsunru.order;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cnsunru.R;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.model.PageBean;
import com.cnsunru.common.quest.BaseQuestConfig;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.common.util.GetEmptyViewUtils;
import com.cnsunru.common.util.TestData;
import com.cnsunru.common.widget.TagAdapter;
import com.cnsunru.common.widget.TagFlowLayout;
import com.cnsunru.common.widget.titlebar.TabTitleBar;
import com.cnsunru.home.mode.BuidingListInfo;
import com.cnsunru.home.mode.SearchHistoryBean;
import com.cnsunru.home.mode.SearchHistoryMode;
import com.cnsunru.home.mode.SearchHotBean;
import com.cnsunru.home.mode.SearchResultBean;
import com.sunrun.sunrunframwork.adapter.ViewHodler;
import com.sunrun.sunrunframwork.adapter.ViewHolderAdapter;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.uiutils.DisplayUtil;
import com.sunrun.sunrunframwork.uiutils.LayoutUtil;
import com.sunrun.sunrunframwork.uiutils.UIUtils;
import com.sunrun.sunrunframwork.utils.EmptyDeal;
import com.sunrun.sunrunframwork.weight.FlowLayout;
import com.sunrun.sunrunframwork.weight.ListViewForScroll;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH;
import static com.cnsunru.common.quest.BaseQuestConfig.SEARCH_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.SEARCH_HOT_CODE;

/**
 * 商品搜索历史
 */
public class SearchGoodsActivity extends LBaseActivity {

    @BindView(R.id.editSearch)
    EditText editSearch;
    @BindView(R.id.titleContainer)
    LinearLayout titleContainer;
    @BindView(R.id.titleBar)
    TabTitleBar titleBar;
    @BindView(R.id.tagHotLable)
    TagFlowLayout tagHotLable;
    @BindView(R.id.listSearchHistory)
    ListViewForScroll listSearchHistory;
    @BindView(R.id.txtClearHistory)
    TextView txtClearHistory;
    @BindView(R.id.resultList)
    ListView resultList;

    int leftPadding;
    int topPadding;
    SearchHistoryMode searchHostoryMode;
    ViewHolderAdapter<SearchHistoryBean> searchHisAdapter;
    String searchType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_goods);
        searchType = getIntent().getStringExtra("searchType");
        leftPadding = DisplayUtil.dp2px(that, 15);
        topPadding = DisplayUtil.dp2px(that, 5);
        titleBar.setRightAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == IME_ACTION_SEARCH) {
                    searchHostoryMode.addHistory(editSearch.getText().toString(), searchType);
                    searchHisAdapter.notifyDataSetChanged();
                    if (TextUtils.isEmpty(editSearch.getText().toString())) {
                        resultList.setVisibility(View.GONE);
                    } else {
                        BaseQuestStart.search(that, editSearch.getText().toString(), searchType);
                    }
//                    startIntent.startGoodsListActivity(that, 4, null);
                }
                return false;
            }
        });

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(editSearch.getText().toString())) {
                    resultList.setVisibility(View.GONE);
                } else {
                    //BaseQuestStart.search(that,editSearch.getText().toString(),searchType);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        searchHostoryMode = new SearchHistoryMode(getSession());
        searchHisAdapter = new ViewHolderAdapter<SearchHistoryBean>(that, searchHostoryMode.getSearchHistorys(), R.layout.item_search_history) {
            @Override
            public void fillView(ViewHodler viewHodler, SearchHistoryBean item, final int position) {
                viewHodler.setText(R.id.title, item.keywords);
                viewHodler.setClickListener(R.id.btnDelete, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        searchHostoryMode.clearHistory(position);
                        notifyDataSetChanged();
                    }
                });
            }
        };
        listSearchHistory.setAdapter(searchHisAdapter);
        listSearchHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SearchHistoryBean searchHistoryBean = searchHisAdapter.getData().get(position);
                editSearch.setText( searchHistoryBean.keywords);
                searchType=searchHistoryBean.seachType;
                BaseQuestStart.search(that, editSearch.getText().toString(), searchType);
            }
        });
        BaseQuestStart.getSearchHot(this, searchType);
    }

    public void nofityUpdate(int requestCode, BaseBean bean) {
        switch (requestCode) {
            case SEARCH_HOT_CODE:
                if (bean.status == 1) {
                    List<SearchHotBean> searchHots = bean.Data();
                    setSearchHotData(searchHots);
                }
                break;
            case SEARCH_CODE:
                if (bean.status == 1) {
                    List<SearchResultBean> searchResults = PageBean.getList(bean);
                    setSearchResultData(searchResults);
                }
                break;
        }
        super.nofityUpdate(requestCode, bean);
    }

    private void setSearchHotData(final List<SearchHotBean> searchHots) {
        tagHotLable.setAdapter(new TagAdapter<SearchHotBean>(searchHots) {
            @Override
            public View getView(FlowLayout parent, int position, SearchHotBean item) {
                View layout = getLayoutInflater().inflate(R.layout.item_simple_text, parent, false);
                TextView text = (TextView) layout.findViewById(R.id.text);
                text.setBackgroundResource(R.drawable.shap_white_round);
                text.setPadding(leftPadding, topPadding, leftPadding, topPadding);
                text.setText(item.keywords);
                return layout;
            }
        });
        tagHotLable.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                editSearch.setText(searchHots.get(position).keywords);
                BaseQuestStart.search(that, editSearch.getText().toString(), searchType);
                return false;
            }
        });
    }

    private void setSearchResultData(final List<SearchResultBean> searchResultData) {
        if (TextUtils.isEmpty(editSearch.getText().toString())) {
            resultList.setVisibility(View.GONE);
        } else {
            resultList.setVisibility(View.VISIBLE);
        }
        resultList.setAdapter(new ViewHolderAdapter<SearchResultBean>(that, searchResultData, R.layout.item_select_work_type) {
            @Override
            public void fillView(ViewHodler viewHodler, SearchResultBean searchResultBean, int i) {
                viewHodler.setText(R.id.item_title, searchResultBean.title);
            }
        });
        GetEmptyViewUtils.bindEmptyView(resultList, 0, "没有搜索到相关结果", EmptyDeal.isEmpy(searchResultData));
        if (EmptyDeal.isEmpy(searchResultData)) {
            UIUtils.shortM("没有搜索到相关结果");
        }
        resultList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SearchResultBean searchResultBean = searchResultData.get(position);
                if (searchResultBean.type == 1) {
                    startIntent.startGoodsDetailsActivity(that, searchResultBean.id);
                } else {
                    startIntent.startWorkSiteListWebActivity(that, BaseQuestConfig.BUIDINGSITE_DETAIL_INFO + "?id=" + searchResultBean.id, searchResultBean.title, searchResultBean.id);
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (searchHostoryMode != null) {
            searchHostoryMode.save();
        }
    }

    @OnClick(R.id.txtClearHistory)
    public void onClick() {
        searchHostoryMode.clearAllHistory();
        searchHisAdapter.notifyDataSetChanged();
    }
}
