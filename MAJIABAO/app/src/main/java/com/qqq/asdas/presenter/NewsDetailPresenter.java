package com.qqq.asdas.presenter;

import android.os.Build;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.qqq.asdas.view.activities.NewsDetailActivity;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.qqq.asdas.view.NewsDetailView;

/**
 * Created by chenxin.
 * Date: 2017/5/10.
 * Time: 12:59.
 */

public class NewsDetailPresenter extends MvpBasePresenter<NewsDetailView> {


//            presenter.getView().getWebView().setVisibility(View.GONE);
//            mTextView.setVisibility(View.VISIBLE);
//            mImager.setVisibility(View.VISIBLE);

    public void initWebView() {
        if (isViewAttached()) {
            WebSettings settings = getView().getWebView().getSettings();
            settings.setBlockNetworkImage(false);
            settings.setJavaScriptEnabled(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            }
            getView().getWebView().setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    if (url.startsWith("dongqiudi:")) {
                        String articleId = url.replaceAll("\\D", "");
                        StringBuilder sb = new StringBuilder("https://api.dongqiudi.com/article/");
                        sb.append(articleId);
                        sb.append(".html?from=tab_56");
                        String newsUrl = sb.toString();
                        Log.e("TEST" , NewsDetailActivity.isNetworkAvailable( view.getContext())+ " ");
                        Log.e("TEST" , newsUrl);
                        if (NewsDetailActivity.isNetworkAvailable( view.getContext())) {
                            getView().getWebView().loadUrl(newsUrl);
                        }
                    }
                    return false;
                }


                @Override
                public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {

                    super.onReceivedError(view, request, error);
                }
            });
            getView().getWebView().setWebChromeClient(
                    new WebChromeClient() {
                        @Override
                        public void onProgressChanged(WebView view, int newProgress) {
                            super.onProgressChanged(view, newProgress);
                            if (newProgress > 40) {
                                if (isViewAttached())
                                    getView().showContent();
                            }
                        }
                    }
            );
        }
    }

    public void loadData(String url) {
        if (isViewAttached()) {
            getView().getWebView().loadUrl(url);
        }
    }

    public boolean canGoBack() {
        if (isViewAttached() && getView().getWebView().canGoBack()) {
            getView().getWebView().goBack();
            return true;
        } else {
            return false;
        }

    }
}
