package com.cnsunru.common.util;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cnsunru.R;
import com.cnsunru.common.MyActivityWebActivity;
import com.sunrun.sunrunframwork.uiutils.UIUtils;

/**
 * Created by WQ on 2017/8/31.
 */

public class WebViewTool {

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    public static WebSettings webviewDefaultConfig(WebView webView){
        webView.setBackgroundColor(0);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBlockNetworkImage(true);
        webView.setWebViewClient(new WebViewClientBase());
        webView.requestFocus(View.FOCUS_DOWN);
        webSettings.setDefaultTextEncodingName("UTF-8");
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsAlert(WebView webView, String url, String message, final JsResult result) {

                AlertDialog.Builder dialog = new AlertDialog
                        .Builder(webView.getContext())
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
        return webSettings;
    }

    private static class WebViewClientBase extends WebViewClient {

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
}
