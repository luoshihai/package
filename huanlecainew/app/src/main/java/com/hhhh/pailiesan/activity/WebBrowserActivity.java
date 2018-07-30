package com.hhhh.pailiesan.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.hhhh.pailiesan.R;


public class WebBrowserActivity extends Activity implements View.OnClickListener {
    private Activity webBrowserActivity;
    private WebView webView;
    private String mUrl;
    private ImageView img_back;
    private SharedPreferences preferences;

    @SuppressLint({"NewApi", "SetJavaScriptEnabled"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_browser);
        webBrowserActivity = this;

        //this.setTitle("");
        //ActionBar actionBar = getActionBar();
        //actionBar.setIcon(R.mipmap.icon);

        Intent intent = this.getIntent();
        mUrl = intent.getStringExtra("url");


        /*
        webView = (WebView) findViewById(R.id.webview);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        //webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(mUrl);

        //webView.setWebChromeClient(new WebChromeClient(this));
        //webView.setWebViewClient(new WebViewClient(this));
        //webView.clearCache(true);
        //webView.clearHistory();
        //webView.getSettings().setJavaScriptEnabled(true);
        //webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        */

        img_back = (ImageView) findViewById(R.id.img_back);
        img_back.setOnClickListener(this);

        webView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDisplayZoomControls(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);


        webSettings.setJavaScriptEnabled(true); //支持JavaScript
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
        }

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("WebView2", "shouldOverrideUrlLoading:" + url);
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.e("WebView2", "onPageFinished");
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.e("WebView2", "onPageStarted");
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);

                Log.i("WebView onReceivedError", "errorCode:" + errorCode + " description:" + description + " failingUrl:" + failingUrl);
                webView.loadUrl(mUrl);
            }
        });
        Log.e("Open Url", mUrl);
        webView.loadUrl(mUrl);
    }


    @Override
    public void onBackPressed() {
    }


    @Override
    public void onClick(View view) {
        finish();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // 什麼都不用寫
        } else {
            // 什麼都不用寫
        }
    }
}
