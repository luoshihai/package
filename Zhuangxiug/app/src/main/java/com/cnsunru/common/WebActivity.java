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

import com.cnsunru.R;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.sunrun.sunrunframwork.uiutils.UIUtils;

import butterknife.BindView;

/**
 * Created by cnsunrun on 2017/4/14.
 * <p>
 * Web界面
 */

public class WebActivity extends LBaseActivity {

    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.title_bar)
    TitleBar mTitleLayout;
    private String url;
    private String title;
    private WebViewClientBase webViewClient = new WebViewClientBase();

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_web);
        Intent intent = getIntent();
        if (intent != null) {
            parseIntent(intent);
        }
        mTitleLayout.setTitle(title);
        mTitleLayout.setLeftAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setUpWebView();
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
                        .Builder(WebActivity.this)
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
        if (TextUtils.isEmpty(url)) {
            finish();
        }
    }

}
