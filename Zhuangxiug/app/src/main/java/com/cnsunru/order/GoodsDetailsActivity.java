package com.cnsunru.order;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;

import com.cnsunru.R;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.util.FixTool;
import com.cnsunru.common.util.WebViewTool;
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.cnsunru.order.fragment.AddGoodsDialogFragment;
import com.cnsunru.user.mode.GoMyProjectMode;
import com.sunrun.sunrunframwork.uiutils.UIUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.cnsunru.budget.fragment.RoomCustomInfoFragment.EDIT_ROOM_TYPE;
import static com.cnsunru.common.quest.BaseQuestConfig.PRODUCT_INFO;

/**
 * Created by WQ on 2017/8/31.
 * @Describe 商品详情
 */

public class GoodsDetailsActivity extends LBaseActivity {
    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.webContent)
    WebView webContent;
    @BindView(R.id.btnGoProject)
    ImageView btnGoProject;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    String id;
    public static  boolean isSelectMaterial=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
        id=getIntent().getStringExtra("id");
        WebViewTool.webviewDefaultConfig(webContent);
        webContent.setWebViewClient(new WebViewClientBase());
        webContent.setWebChromeClient(new WebChromeClient());
        webContent.loadUrl(String.format(PRODUCT_INFO,id));
        new GoMyProjectMode(btnGoProject);
        if(isSelectMaterial){
            int edit_room_type = getSession().getInt(EDIT_ROOM_TYPE);////判断编辑类型 1 添加门 2 添加窗 3 材料替换
            if(edit_room_type==3){
                btnAdd.setText("替换材料");
            }
        }
    }

    @OnClick({R.id.btnAdd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
//                if(isSelectMaterial){
//
//                }else {
                    AddGoodsDialogFragment.showFragment(getSupportFragmentManager(), id);
//                }
                break;
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
            UIUtils.showLoadDialog(that);
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
            FixTool.cancelLoadDialog();
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
            String errorHtml = "<html><body><h2>加载失败</h2></body></html>";
            view.loadDataWithBaseURL(null, errorHtml, "text/html", "UTF-8", null);
            FixTool.cancelLoadDialog();
        }
    }
}
