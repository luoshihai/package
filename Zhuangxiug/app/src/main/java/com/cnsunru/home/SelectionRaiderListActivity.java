package com.cnsunru.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cnsunru.R;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.quest.BaseQuestConfig;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.common.util.FlowLayoutManager;
import com.cnsunru.common.util.GetEmptyViewUtils;
import com.cnsunru.common.util.PageLimitDelegate;
import com.cnsunru.common.widget.titlebar.TabTitleBar;
import com.cnsunru.home.adapter.SelectionRaiderCategoryAdapter;
import com.cnsunru.home.adapter.SelectionRaiderListAdapter;
import com.cnsunru.home.mode.CategoryListInfo;
import com.cnsunru.home.mode.TraverGuideListInfo;
import com.sunrun.sunrunframwork.bean.BaseBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH;
import static com.cnsunru.common.quest.BaseQuestConfig.PUT_TRAVELGUIDE_GET_CATEGORY_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.PUT_TRAVELGUIDE_GET_LIST_CODE;


/**
 * Created by WQ on 2017/9/6.
 *
 * @Describe 选材攻略
 */

public class SelectionRaiderListActivity extends LBaseActivity implements PageLimitDelegate.DataProvider {
    @BindView(R.id.titleBar)
    TabTitleBar titleBar;
    @BindView(R.id.rvCategory)
    RecyclerView rvCategory;
    @BindView(R.id.imgDropdown)
    ImageView imgDropdown;
    @BindView(R.id.lRecyclerView)
    RecyclerView lRecyclerView;
    @BindView(R.id.lSwipeRefreshLayout)
    SwipeRefreshLayout lSwipeRefreshLayout;
    LinearLayoutManager linearLayoutManager;
    FlowLayoutManager gridLayoutManager;
    SelectionRaiderListAdapter raiderListAdapter;
    SelectionRaiderCategoryAdapter raiderCategoryAdapter;
    boolean isDropDown = false;
    @BindView(R.id.editSearch)
    EditText editSearch;

    private String categoryId,keywords;

    PageLimitDelegate<TraverGuideListInfo.ListBean> pageLimitDelegate = new PageLimitDelegate(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_raiders_list);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        raiderListAdapter = new SelectionRaiderListAdapter();
        lRecyclerView.setLayoutManager(new LinearLayoutManager(that, LinearLayoutManager.VERTICAL, false));
        GetEmptyViewUtils.bindEmptyView(that, raiderListAdapter, 0, "暂无数据", true);
        lRecyclerView.setAdapter(raiderListAdapter);
        pageLimitDelegate.attach(lSwipeRefreshLayout, lRecyclerView, raiderListAdapter);
        raiderListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                startIntent.startSelectionRaiderDetailsActivity(that, "");
                TraverGuideListInfo.ListBean item = raiderListAdapter.getItem(position);
                startIntent.startWebActivity(that, BaseQuestConfig.TRAVELGUIDE_DETAIL + "?id=" + item.getId(), item.getTitle());
            }
        });
        raiderCategoryAdapter = new SelectionRaiderCategoryAdapter();
        rvCategory.setLayoutManager(linearLayoutManager);
        rvCategory.setAdapter(raiderCategoryAdapter);
        raiderCategoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CategoryListInfo item = raiderCategoryAdapter.getItem(position);
                raiderCategoryAdapter.setSelectIndex(position);
                categoryId = item.getId();
                BaseQuestStart.getTraverlGuideGetList(that, categoryId,keywords, 1);
            }
        });
        editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId==IME_ACTION_SEARCH) {
                    keywords=editSearch.getText().toString();
                    pageLimitDelegate.refreshPage();
                }
                return false;
            }
        });
        initData();
    }

    private void initData() {
        BaseQuestStart.getCategoryList(that);
    }

    @OnClick(R.id.imgDropdown)
    public void onClick() {
        isDropDown = !isDropDown;
        if (isDropDown) {
            imgDropdown.setImageResource(R.drawable.home_icon_droplist_up);
            gridLayoutManager = new FlowLayoutManager();
            rvCategory.setLayoutManager(gridLayoutManager);
        } else {
            imgDropdown.setImageResource(R.drawable.home_icon_droplist_n);
            rvCategory.setLayoutManager(linearLayoutManager);
        }
        raiderCategoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void nofityUpdate(int requestCode, BaseBean bean) {
        switch (requestCode) {
            case PUT_TRAVELGUIDE_GET_CATEGORY_CODE:
                List<CategoryListInfo> categoryList = bean.Data();
                if (categoryList != null) {
//                    categoryList.add(0, new CategoryListInfo("", "全部", ""));
                    raiderCategoryAdapter.setNewData(categoryList);
                } else {
                    ArrayList<CategoryListInfo> data = new ArrayList<>();
                    data.add(0, new CategoryListInfo("", "全部", ""));
                    raiderCategoryAdapter.setNewData(data);
                }
                break;
            case PUT_TRAVELGUIDE_GET_LIST_CODE:
                TraverGuideListInfo data = bean.Data();
                if (data != null) {
                    List<TraverGuideListInfo.ListBean> list = data.getList();
                    pageLimitDelegate.setData(list);
                } else {
                    pageLimitDelegate.setData(new ArrayList<TraverGuideListInfo.ListBean>());
                }
                break;
        }
        super.nofityUpdate(requestCode, bean);
    }

    @Override
    public void loadData(int page) {
        BaseQuestStart.getTraverlGuideGetList(that, categoryId,keywords, page);
    }
}
