package com.cnsunru.user.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cnsunru.R;
import com.cnsunru.budget.dialogs.DeleteConfimDialogFragment;
import com.cnsunru.budget.mode.DefaltCalculateInfo;
import com.cnsunru.common.base.LBaseFragment;
import com.cnsunru.common.dialog.DataLoadDialogFragment;
import com.cnsunru.common.event.MessageEvent;
import com.cnsunru.common.model.PageBean;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.common.util.GetEmptyViewUtils;
import com.cnsunru.common.util.PageLimitDelegate;
import com.cnsunru.common.util.TestData;
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.cnsunru.order.adapters.MallPurchaseListAdapter;
import com.cnsunru.user.mode.MyProjectBean;
import com.sunrun.sunrunframwork.bean.BaseBean;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;

import static com.cnsunru.common.quest.BaseQuestConfig.MY_PROJECT_CODE;

/**
 * Created by WQ on 2017/8/31.
 *
 * @Describe 我的项目
 */

public class MyProjectFragment extends LBaseFragment {

    public static final String MYPROJECT_REFRESH = "MYPROJECT_REFRESH";
    @BindView(R.id.lRecyclerView)
    RecyclerView lRecyclerView;
    @BindView(R.id.lSwipeRefreshLayout)
    SwipeRefreshLayout lSwipeRefreshLayout;
    MallPurchaseListAdapter mallPurchaseListAdapter;
    //分页代理
    PageLimitDelegate<MyProjectBean> pageLimitDelegate=new PageLimitDelegate<MyProjectBean>(new PageLimitDelegate.DataProvider() {
        @Override
        public void loadData(int page) {
            //获取项目列表数据
            BaseQuestStart.my_project(MyProjectFragment.this,type,page);
        }
    });
    String type;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initEventBus();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        type=getArguments().getString("type");
        mallPurchaseListAdapter =new MallPurchaseListAdapter();
        lRecyclerView.setLayoutManager(new LinearLayoutManager(that,LinearLayoutManager.VERTICAL,false));
        GetEmptyViewUtils.bindEmptyView(that, mallPurchaseListAdapter,0,"暂无数据",true);
        lRecyclerView.setAdapter(mallPurchaseListAdapter);
        pageLimitDelegate.attach(lSwipeRefreshLayout,lRecyclerView, mallPurchaseListAdapter);
        mallPurchaseListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
               final MyProjectBean myProjectBean = mallPurchaseListAdapter.getData().get(position);
                if(myProjectBean.isNeedCalculate()){
                    DeleteConfimDialogFragment.showFragment(getChildFragmentManager(), "提示", "项目清单有变化,请重新计算!", new DeleteConfimDialogFragment.ConfirmActionListener() {
                        @Override
                        public void onConfirm(View view) {

                            DataLoadDialogFragment.getInstance(getChildFragmentManager(),new DataLoadDialogFragment.onDataLoadeListener() {
                                @Override
                                public void onDataLoaded(BaseBean bean) {
                                    if(bean.Data() instanceof DefaltCalculateInfo) {
                                        DefaltCalculateInfo data = bean.Data();
                                        getSession().put(DefaltCalculateInfo.class.getName(), data);
                                        startIntent.startProjectListActivity(that);
                                    }
                                }
                            },BaseQuestStart.review_calculate(myProjectBean.id),BaseQuestStart.get_calculate_result(myProjectBean.id) );
                        }
                    });
                }else {
                    DataLoadDialogFragment.getInstance(getChildFragmentManager(),BaseQuestStart.get_calculate_result(myProjectBean.id) , new DataLoadDialogFragment.onDataLoadeListener() {
                        @Override
                        public void onDataLoaded(BaseBean bean) {
                            DefaltCalculateInfo data = bean.Data();
                            getSession().put(DefaltCalculateInfo.class.getName(),data);
                            startIntent.startProjectListActivity(that);
                        }
                    });
                }

            }
        });
    }


    public static MyProjectFragment newInstance(String type) {
        MyProjectFragment mallFragment = new MyProjectFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type",type);
        mallFragment.setArguments(bundle);
        return mallFragment;
    }


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_myproject;
    }


    public void nofityUpdate(int requestCode,BaseBean bean){
        switch(requestCode){
            case MY_PROJECT_CODE:
                if(bean.status==1){
                    List<MyProjectBean> list = PageBean.getList(bean);
                    pageLimitDelegate.setData(list);
                }
                break;
        }
        super.nofityUpdate(requestCode,bean);
    }


    @Override
    public void onResume() {
        pageLimitDelegate.refreshPage();
        super.onResume();
    }
}
