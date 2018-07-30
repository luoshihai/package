package com.qqq.asdas.view.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.qqq.asdas.presenter.NewsDetailPresenter;
import com.qqq.asdas.view.NewsDetailView;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceActivity;
import com.qqq.asdas.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsDetailActivity extends MvpLceActivity<WebView, Object, NewsDetailView, NewsDetailPresenter>
        implements NewsDetailView {

    public static final String URL = "URL";
    @BindView(R.id.contentView)
    WebView contentView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String url;


    @BindView(R.id.TextView)
    TextView mTextView;
    @BindView(R.id.imageView)
    ImageView mImager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        toolbar.setTitle("足球资讯");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        if (intent == null || (url = intent.getStringExtra(URL)) == null) {
            showError(new Exception("没有文章链接"), false);
        } else {
            presenter.initWebView();
            loadData(url);
        }

    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @NonNull
    @Override
    public NewsDetailPresenter createPresenter() {
        return new NewsDetailPresenter();
    }

    @Override
    public void setData(Object data) {
    }

    @Override
    public void loadData(boolean pullToRefresh) {
    }

    private void loadData(String url) {
        showLoading(false);
        mTextView.setVisibility(View.GONE);
        mImager.setVisibility(View.GONE);
        if (isNetworkAvailable(getApplication())) {
            presenter.loadData(url);
        }else {
            presenter.getView().getWebView().setVisibility(View.GONE);
            mTextView.setVisibility(View.VISIBLE);
            mImager.setVisibility(View.VISIBLE);
        }

    }
    /**
     * 检测当的网络（WLAN、3G/2G）状态
     * @param context Context
     * @return true 表示网络可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected())
            {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED)
                {
                    // 当前所连接的网络可用
                    return true;
                }

            }
        }
        return false;
    }

    @Override
    public WebView getWebView() {
        return contentView;
    }

    @Override
    public void onBackPressed() {
        if (!presenter.canGoBack()) {
            super.onBackPressed();
        }
    }

    @OnClick({R.id.imageView})
    public void chick(View view){
        switch (view.getId()) {
            case R.id.imageView :
                if (TextUtils.isEmpty(url)) {
                    return;
                }
                loadData(url);
                break;
            default:
                return;
        }
    }
}
