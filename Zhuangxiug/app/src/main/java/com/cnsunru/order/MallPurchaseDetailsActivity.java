package com.cnsunru.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cnsunru.R;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.util.GetEmptyViewUtils;
import com.cnsunru.common.util.PageLimitDelegate;
import com.cnsunru.common.util.TestData;
import com.cnsunru.common.widget.titlebar.TabTitleBar;
import com.cnsunru.order.adapters.MallPurchaseDetailsListAdapter;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.cnsunru.common.intent.StartIntent.SCAN_QR_REQUEST;

/**
 * 商城-预算详情
 */
public class MallPurchaseDetailsActivity extends LBaseActivity {


    @BindView(R.id.editSearch)
    EditText editSearch;
    @BindView(R.id.titleContainer)
    LinearLayout titleContainer;
    @BindView(R.id.titleBar)
    TabTitleBar titleBar;
    @BindView(R.id.txtLocation)
    TextView txtLocation;
    @BindView(R.id.lRecyclerView)
    RecyclerView lRecyclerView;
    @BindView(R.id.lSwipeRefreshLayout)
    SwipeRefreshLayout lSwipeRefreshLayout;

    MallPurchaseDetailsListAdapter detailsListAdapter;
    PageLimitDelegate pageLimitDelegate=new PageLimitDelegate(new PageLimitDelegate.DataProvider() {
        @Override
        public void loadData(int page) {
            pageLimitDelegate.setData(TestData.createTestData(10,String.class));
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mall);
        detailsListAdapter =new MallPurchaseDetailsListAdapter();
        lRecyclerView.setLayoutManager(new LinearLayoutManager(that,LinearLayoutManager.VERTICAL,false));
        GetEmptyViewUtils.bindEmptyView(that, detailsListAdapter,0,"暂无数据",true);
        lRecyclerView.setAdapter(detailsListAdapter);
        pageLimitDelegate.attach(lSwipeRefreshLayout,lRecyclerView, detailsListAdapter);
        detailsListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startIntent.startGoodsDetailsActivity(that,"");
            }
        });

    }

    public void onClick() {
        startIntent.startScanQRActivity(that);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        /* 处理二维码扫描结果
                */
        if (requestCode == SCAN_QR_REQUEST) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(that, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(that, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
