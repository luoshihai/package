package com.cnsunru.common;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import com.cnsunru.R;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.common.util.WebViewTool;
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.cnsunru.user.mode.CollectSignInfo;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.uiutils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.cnsunru.common.quest.BaseQuestConfig.GET_ADD_COLLECT_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.GET_DEL_COLLECT_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.GET_IS_COLLECT_SIGN_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.GET_SIGN_ACTIVITY_CODE;

/**
 * Created by cnsunrun on 2017/4/14.
 * <p>
 * Web界面
 */

public class MyActivityWebActivity extends LBaseActivity {

    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.title_bar)
    TitleBar mTitleLayout;
    @BindView(R.id.iv_collet)
    CheckBox ivCollet;
    @BindView(R.id.collet_cantainer)
    RelativeLayout colletCantainer;
    @BindView(R.id.tv_atonce)
    Button tvAtonce;
    private String url;
    private String title;
    private String id;
    private int is_collect;
    private int is_sign;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_myactivity_web);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent != null) {
            parseIntent(intent);
        }
        initData();
        mTitleLayout.setTitle(title);
        setUpWebView();
    }

    private void initData() {
        BaseQuestStart.getIsCellectSign(that, id);
    }

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    private void setUpWebView() {
        WebViewTool.webviewDefaultConfig(mWebView);
        //网页的url
        mWebView.loadUrl(url);
    }

    @OnClick({R.id.collet_cantainer,R.id.iv_collet, R.id.tv_atonce})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.collet_cantainer:
                if (is_collect == 0) {
                    //为收藏  点击了就是请求收藏的接口
                    BaseQuestStart.addCollect(that, id,1);
                } else {
                    //已经收藏 点击了之后就是请求取消收藏的接口
                    BaseQuestStart.delCollect(that, new String[]{id},1);
                }
                break;
            case R.id.iv_collet:
                if (is_collect == 0) {
                    //为收藏  点击了就是请求收藏的接口
                    BaseQuestStart.addCollect(that, id,1);
                } else {
                    //已经收藏 点击了之后就是请求取消收藏的接口
                    BaseQuestStart.delCollect(that, new String[]{id},1);
                }
                break;
            case R.id.tv_atonce:
                if (is_sign == 0) {
                    BaseQuestStart.joinActivity(that,id);
                }
                break;
        }
    }

    public void setData2BottomView(CollectSignInfo data) {
        if (data != null) {
            is_collect = data.getIs_collect();
            is_sign = data.getIs_sign();
            ivCollet.setChecked(is_collect == 1);
            tvAtonce.setEnabled(is_sign != 1);
        }
    }



    @Override
    protected void onNewIntent(Intent intent) {

        super.onNewIntent(intent);
        parseIntent(intent);
    }

    private void parseIntent(Intent intent) {
        url = intent.getStringExtra("web_url");
        title = intent.getStringExtra("title");
        id = intent.getStringExtra("id");
        if (TextUtils.isEmpty(url)) {
            finish();
        }
    }

    @Override
    public void nofityUpdate(int requestCode, BaseBean bean) {
        switch (requestCode) {
            case GET_IS_COLLECT_SIGN_CODE:
                if (bean.status > 0) {
                    CollectSignInfo data = bean.Data();
                    setData2BottomView(data);
                }
                break;
            case GET_ADD_COLLECT_CODE:
                UIUtils.shortM(bean.msg);
                if (bean.status > 0) {

                    is_collect = 1;
                    ivCollet.setChecked(is_collect == 1);
                }
                break;
            case GET_DEL_COLLECT_CODE:
                UIUtils.shortM(bean.msg);
                if (bean.status > 0) {
                    is_collect = 0;
                    ivCollet.setChecked(is_collect == 1);
                }
                break;
            case  GET_SIGN_ACTIVITY_CODE:
                UIUtils.shortM(bean.msg);
                if (bean.status > 0) {
                    BaseQuestStart.getIsCellectSign(that, id);
                }
                break;
        }
        super.nofityUpdate(requestCode, bean);
    }
}
