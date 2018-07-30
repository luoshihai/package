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
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.cnsunru.home.mode.BuildingCollectInfo;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.uiutils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.cnsunru.common.quest.BaseQuestConfig.GET_ADD_COLLECT_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.GET_DEL_COLLECT_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.MY_BUILDINGSITE_COLLECT_SUBCREIBE_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.MY_BUILDINGSITE_SUBSCREIBE_CODE;

/**
 * Created by cnsunrun on 2017/4/14.
 * <p>
 * Web界面
 */

public class WorkSiteListWebActivity extends LBaseActivity {

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
    private WebViewClientBase webViewClient = new WebViewClientBase();
    private String id;
    private int is_collect;
    private int is_sign;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_worksite_web);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent != null) {
            parseIntent(intent);
        }
        initData();
        mTitleLayout.setTitle(title);
        mTitleLayout.setLeftAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setUpWebView();
    }

    private void initData() {
        BaseQuestStart.buildingCollect(that, id);
    }

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    private void setUpWebView() {
        mWebView.setBackgroundColor(0);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBlockNetworkImage(true);
        mWebView.setWebViewClient(webViewClient);
        mWebView.requestFocus(View.FOCUS_DOWN);
        mWebView.getSettings().setDefaultTextEncodingName("UTF-8");
        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {

                AlertDialog.Builder dialog = new AlertDialog
                        .Builder(WorkSiteListWebActivity.this)
                        .setTitle(R.string.app_name)
                        .setMessage(message)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                result.confirm();
                            }
                        });

                dialog.setCancelable(false);
                dialog.create();
                dialog.show();
                return true;
            }
        });
        //网页的url
        mWebView.loadUrl(url);
    }

    @OnClick({R.id.collet_cantainer,R.id.iv_collet, R.id.tv_atonce})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.collet_cantainer:
                if (is_collect == 0) {
                    //为收藏  点击了就是请求收藏的接口
                    BaseQuestStart.addCollect(that, id, 2);
                } else {
                    //已经收藏 点击了之后就是请求取消收藏的接口
                    BaseQuestStart.delCollect(that, new String[]{id}, 2);
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
                    //预约工地
                    BaseQuestStart.joinBuildingSite(that, id);
                }
                break;
        }
    }

    public void setData2BottomView(BuildingCollectInfo data) {
        if (data != null) {
            is_collect = data.getIs_collect();
            is_sign = data.getIs_subscribe();
            ivCollet.setChecked(is_collect == 1);
            tvAtonce.setEnabled(is_sign != 1);
        }
    }

    private class WebViewClientBase extends WebViewClient {

        /**
         * 加载开始的监听
         *
         * @param view
         * @param url
         * @param favicon
         */
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }


        /**
         * 加载完成的监听
         *
         * @param view
         * @param url
         */
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            view.getSettings().setBlockNetworkImage(false);
            UIUtils.cancelLoadDialog();
            view.loadUrl("javascript:setFontSize()");
        }

        /**
         * 加载失败的监听
         *
         * @param view
         * @param errorCode
         * @param description
         * @param failingUrl
         */
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            String errorHtml = "<html><body><h2>网页加载失败</h2></body></html>";
            view.loadDataWithBaseURL(null, errorHtml, "text/html", "UTF-8", null);
            UIUtils.cancelLoadDialog();
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
            case MY_BUILDINGSITE_COLLECT_SUBCREIBE_CODE:
                if (bean.status > 0) {
                    BuildingCollectInfo data = bean.Data();
                    setData2BottomView(data);
                }
                break;
            case GET_ADD_COLLECT_CODE:
                UIUtils.shortM(bean.msg);
                if (bean.status > 0) {
                    is_collect = 1;
                }
                ivCollet.setChecked(is_collect == 1);
                break;
            case GET_DEL_COLLECT_CODE:
                UIUtils.shortM(bean.msg);
                if (bean.status > 0) {
                    is_collect = 0;
                }
                ivCollet.setChecked(is_collect == 1);
                break;
            case MY_BUILDINGSITE_SUBSCREIBE_CODE:
                UIUtils.shortM(bean.msg);
                if (bean.status > 0) {
                    BaseQuestStart.buildingCollect(that, id);
                }
                break;
        }
        super.nofityUpdate(requestCode, bean);
    }
}
