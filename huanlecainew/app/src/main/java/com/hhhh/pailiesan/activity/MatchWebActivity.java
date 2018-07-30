package com.hhhh.pailiesan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.hhhh.pailiesan.R;
import com.hhhh.pailiesan.base.KBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by QYQ on 2017/9/13.
 */

public class MatchWebActivity extends KBaseActivity {
    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.prog)
    ProgressBar progressBar;
    private Intent intent;
    private String id;

    @Override
    protected void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_match_web);
    }

    @Override
    protected void initView() {
        super.initView();
        setTitleName("赛事直播");
        setTitleBack(true, 0);
        intent = getIntent();
        id = intent.getStringExtra("id");
        mWebView.setBackgroundColor(0);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setJavaScriptEnabled(true); // 设置页面支持Javascript
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setSupportZoom(true); // 支持缩放
        mWebView.getSettings().setBuiltInZoomControls(false); // 显示放
        //扩大比例的缩放
        mWebView.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setDefaultTextEncodingName("utf-8");

        mWebView.setWebViewClient(new WebViewClient() {
            // 点击网页中按钮时，在原页面打开
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            // 页面加载完成后执行
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

//            @Override
//            public void onReceivedError(WebView view, int errorCode,
//                                        String description, String failingUrl) {
//                super.onReceivedError(view, errorCode, description, failingUrl);
//            }
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
//                view.loadUrl("https://m.qmcai.com/hd/caipiaoclass/index.html?h5ControlTitle=true&backH5Control=true");//加载本地网页的路径
                view.loadUrl("file:///android_asset/error.html");//加载本地网页的路径
                view.clearHistory();
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                view.loadUrl("file:///android_asset/error.html");//加载本地网页的路径
                view.clearHistory();
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                    //progressBar.setProgress(newProgress);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);//设置加载进度
                }
            }

        });
        mWebView.requestFocus();
        mWebView.loadUrl("http://m.zhuoyicp.com/Bfyc/statistic?matchId=" + id + "&lotId=&param=eyJzaWQiOiIzMTAwMTAwMjAyMyIsInNlaWQiOiI2Q0E5REJFQjM5MUM5Q0U1Q0I1MTBGQjlFRDdBQkE4RSIsInVzZXJJZCI6IjI1ODYzMjQiLCJhcHBWZXJzaW9uIjoiMy4yLjQiLCJmcm9tIjoiaW9zIn0=");
        mWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK
                            && mWebView.canGoBack()) { // 表示按返回键
                        mWebView.goBack(); // 后退
                        return true; // 已处理
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /***
     * 防止WebView加载内存泄漏
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebView.removeAllViews();
        mWebView.destroy();
    }
}
