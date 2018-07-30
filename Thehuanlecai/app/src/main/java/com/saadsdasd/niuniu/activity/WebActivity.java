package com.saadsdasd.niuniu.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.saadsdasd.niuniu.model.ServerSettingObj;
import com.saadsdasd.niuniu.utils.DownloadTask;
import com.saadsdasd.niuniu.utils.Tools;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.grandfortunetech.jlib.WebService.RQCheckUrlAlive;
import com.saadsdasd.niuniu.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;

import qiu.niorgai.StatusBarCompat;

public class WebActivity extends Activity implements View.OnClickListener {
    private Activity mMainActivity;
    private WebView webView;
    ProgressDialog pd;
    ProgressDialog dpd;
    public static String mUrl = ""; //"m.cr0168.bgbet3.com";  //http://m.cx6538.bg866.com";    //http://m.bg567.com";   //http://m.bg567.com/?sn=SP01&device=mobile";    // BG娱乐 | 太阳城娱乐| 银河娱乐场 | 葡京娱乐场

    private TextView txtHome, txtBack, txtNext, txtRefresh;
    private RelativeLayout rl_bar;
    ServerSettingObj serverObj;
    private SharedPreferences preferences;
    private boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //String appId = "mobile.bgh5.ad00.t1603.test";
        serverObj = (ServerSettingObj) getIntent().getSerializableExtra("settingObj");
        //给状态栏设置背景颜色
        if (!TextUtils.isEmpty(serverObj.getBackGroundColor())) {//判断背景颜色
            int backgroundColor = Color.parseColor(serverObj.getBackGroundColor());
            Window window = getWindow();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                ViewGroup decorViewGroup = (ViewGroup) window.getDecorView();
                View statusBarView = new View(window.getContext());
                int statusBarHeight = Tools.getStatusBarHeight(window.getContext());
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, statusBarHeight);
                params.gravity = Gravity.TOP;
                statusBarView.setLayoutParams(params);
                if ("1".equals(serverObj.getFontColor())) {//黑色字体
                    int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                    decorViewGroup.setSystemUiVisibility(option);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        statusBarView.setBackgroundColor(backgroundColor);
                        decorViewGroup.addView(statusBarView);
                        getWindow().setStatusBarColor(Color.TRANSPARENT);
                        getWindow().setNavigationBarColor(Color.TRANSPARENT);
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//大于6.0
                        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    } else {
                        StatusBarCompat.setStatusBarColor(this, backgroundColor);//设置状态栏颜色
                    }
                    FlymeSetStatusBarLightMode(getWindow(), true);//魅族
                    MIUISetStatusBarLightMode(getWindow(), true);//小米
                } else {//白色字体
                    StatusBarCompat.setStatusBarColor(this, backgroundColor);//设置状态栏颜色
                }
            }
        }
        setContentView(R.layout.activity_web);

        mMainActivity = this;
        preferences = getSharedPreferences("preFile", MODE_PRIVATE);
        pd = new ProgressDialog(this);
        pd.setMessage("正在加載，請稍候‧‧‧");

        //check setting from server
        /*String urls = "http://tzxpsm.com/lotto/api";
        JSONObject parameters = new JSONObject();
        try {

            parameters.put("jsonrpc", "2.0");
            parameters.put("id", 1);
            parameters.put("method", "lotto.monitor");
            JSONObject paramsDetail = new JSONObject();
            paramsDetail.put("appid", appId);
            parameters.put("params", paramsDetail);

        }catch (JSONException e){
            e.printStackTrace();
        }*/
        /*JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, urls, parameters, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                //檢查後才做的動作

                try {
                    serverObj = new ServerSettingObj(response.getJSONObject("result"));
                }catch (JSONException e){
                    e.printStackTrace();
                    serverObj = new ServerSettingObj();
                }*/
        //版號不一樣時  需要更新
        if (!preferences.getString("appVersion", "1.0").equals(serverObj.getVersion())) {
            AlertDialog pd = new AlertDialog.Builder(this).create();

            pd.setTitle("偵測到更新程式，開始更新");
            pd.setCancelable(false);

            pd.setButton(ProgressDialog.BUTTON_POSITIVE, getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dpd = new ProgressDialog(WebActivity.this);
                    dpd.setTitle("下載中...請稍候");
                    DownloadTask task = new DownloadTask(WebActivity.this, handler);
                    task.execute(serverObj.getAppStoreUrl(), serverObj.getVersion());
                    dpd.show();
                }
            });
            pd.show();
        }

        //包名
        preferences.edit().putString("appid", serverObj.getAppid()).commit();
        //是否要跳轉URL
        String url = "";
        if (serverObj.isShowWap()) {
            url = serverObj.getWapUrl();
            mUrl = url;

        }

        /*if (serverObj.getFontColor() != null) {

//                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (serverObj.getFontColor().equals("0")) { //白色
                getWindow().getDecorView().setSystemUiVisibility(0);
            } else if (serverObj.getFontColor().equals("1")) { //黑色
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
//                    }
        }

        if (!TextUtils.isEmpty(serverObj.getBackGroundColor())) {
            int backgroundColor = Color.parseColor(serverObj.getBackGroundColor());
            getWindow().setStatusBarColor(backgroundColor);
        }*/

        findViewById(R.id.rl_bar).setVisibility(serverObj.isShowNavBar() ? View.VISIBLE : View.GONE);

        findViewById(R.id.Btn_tspgtoolkit_TransactionViewClose)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("onclick ", "btn url is " + mUrl);
                        findViewById(R.id.Btn_tspgtoolkit_TransactionViewClose).setVisibility(View.INVISIBLE);

                        //RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                        //params.removeRule(RelativeLayout.BELOW);
                        //params.addRule(RelativeLayout.ABOVE,findViewById(R.id.rl_bar).getId());
                        //findViewById(R.id.webview).setLayoutParams(params);

                        webView.loadUrl(mUrl);
                    }
                });


        webView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setUseWideViewPort(true);//将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true);//设置网页自适应屏幕大小 ---这个属性应该是跟上面一个属性一起用
        webSettings.setDisplayZoomControls(true);//隐藏webview缩放按钮
        webSettings.setDomStorageEnabled(true);//Dom Storage（Web Storage）存储机制
        webSettings.setDatabaseEnabled(true);//Web SQL Database 存储机制
        webSettings.setSupportMultipleWindows(true);//支持多窗口
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//设置当一个安全站点企图加载来自一个不安全站点资源时WebView的行为
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSettings.setUserAgentString(webSettings.getUserAgentString() + " mobilebgh5");//android中自带的浏览器Chrome Lite 标识
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过js打开新的窗口
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true); //支持JavaScript


        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onCreateWindow(WebView view, boolean isDialog,
                                          boolean isUserGesture, Message resultMsg) {

                WebView newWebView = new WebView(WebActivity.this);
                //view.addView(newWebView);
                WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
                transport.setWebView(newWebView);
                resultMsg.sendToTarget();

                newWebView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        //view.loadUrl(mUrl);
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                        //browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        browserIntent.setData(Uri.parse(url));
                        startActivity(browserIntent);
                        return true;
                    }
                });
                return true;
            }                });  // 讓alert可正常運作

        //允許webView寫入Cookie
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
        }else{
            CookieManager.getInstance().setAcceptCookie(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (0 != (getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE))
            { webView.setWebContentsDebuggingEnabled(true); }
        }
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("WebView", "shouldOverrideUrlLoading:" + url);
                if(url.startsWith("tel:") || url.startsWith("mailto:"))
                {
                    Log.e("WebView", "detect tel or mailto");
                    return false;
                }
                if(url.startsWith("bgh5://scan") )
                {
                    Log.e("WebView", "openScan");
                    openScan();
                    return true;
                }

//                        setOrientation(url);

                //if(url.indexOf("__open__") > -1)
                //{
                //    openNewWindow(url);
                //    return true;
                //}
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.e("WebView", "onPageFinished");

                if(url.contains("portrait=1") ){
                    Log.e("detect portrait url", "1");
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
                if(url.contains("landscape=1") ){
                    Log.e("detect landscape  url", "1");
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
                if(url.contains("__homeModeA__")) {
                    Log.e("WebView", "homeMode_A Visible");
                    findViewById(R.id.Btn_tspgtoolkit_TransactionViewClose).setVisibility(View.VISIBLE);

                    //RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    //params.addRule(RelativeLayout.BELOW,findViewById(R.id.Btn_tspgtoolkit_TransactionViewClose).getId());
                    //params.addRule(RelativeLayout.ABOVE,findViewById(R.id.rl_bar).getId());
                    //findViewById(R.id.webview).setLayoutParams(params);

                }
                else
                if(url.contains(mUrl)){
                    Log.e("onclick ", "btn url is "+mUrl);
                    findViewById(R.id.Btn_tspgtoolkit_TransactionViewClose).setVisibility(View.INVISIBLE);
                    //RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    //params.removeRule(RelativeLayout.BELOW);
                    //params.addRule(RelativeLayout.ABOVE,findViewById(R.id.rl_bar).getId());
                    //findViewById(R.id.webview).setLayoutParams(params);
                }


                super.onPageFinished(view, url);
                pd.dismiss();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.e("WebView", "onPageStarted url:" + url);
                if(url.contains("__browserModeA__") ){
                    openBrowser(url);
                    view.stopLoading();
                }
                super.onPageStarted(view, url, favicon);
                if(isFirst) {
                    pd.show();
                    isFirst = false;
                }
//                        setOrientation(url);

            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);

                Log.i("WebView onReceivedError", "errorCode:" + errorCode + " description:" + description +" failingUrl:" + failingUrl);
                //view.loadData("找不到網頁", "text/html", "UTF-8");
                webView.loadUrl(mUrl);
                if(errorCode < 0) {

                    //view.loadDataWithBaseURL("", "无法开启网页：" + failingUrl, "text/html", "UTF-8", "");

                    //Intent i = new Intent(Intent.ACTION_VIEW);
                    //i.setData(Uri.parse(failingUrl));
                    //startActivity(i);
                    //Toast.makeText(view.getContext(), "无法开启网页", Toast.LENGTH_LONG).show();
                }

            }

        });

        rl_bar = (RelativeLayout) findViewById(R.id.rl_bar);

        txtHome = (TextView) findViewById(R.id.txt_home);
        txtHome.setOnClickListener(this);

        txtBack = (TextView) findViewById(R.id.txt_back);
        txtBack.setOnClickListener(this);

        txtNext = (TextView) findViewById(R.id.txt_next);
        txtNext.setOnClickListener(this);

        txtRefresh = (TextView) findViewById(R.id.txt_refresh);
        txtRefresh.setOnClickListener(this);


        if (!url.equals("")) {
            //webView.loadUrl("http://hub.gm175888.com/igaming/?gameId=ano&real=1&username=SP0112079575&lang=en&tempToken=0mSXRfESea2T_w1Hwh4gIFCQsMCwUBDY&lobby=http://m.bg1207.com/lobby.html&support=http://m.bg1207.com/support.html&logout=http://m.bg1207.com/logout.html");
            webView.loadUrl(url);
        } else {
            //webView.loadData("<a href=\"bgh5://scan\">open scan</a>", "text/html; charset=utf-8", "UTF-8");
            webView.loadUrl(mUrl);
        }
    }
    /*}, new Response.ErrorListener(){
        @Override
        public void onErrorResponse(VolleyError error) {
            //檢查失敗錯誤處理
        }
    });
    RequestQueue queue = Volley.newRequestQueue(this);
    queue.add(jsObjRequest);*/
    public void setOrientation(String url)
    {
        //強制轉直向
        if(url.indexOf("languageCode=zh-cn") > -1 || url.indexOf("lobbyName=iGamingA4HTML5") > -1)
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        //自動轉向的控制
        if(url.indexOf("orientation=l") > -1 || url.indexOf("gpiops") > -1)
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        Log.i("WebBrowserActivity", "requestCode:" + requestCode + ",resultCode:" + resultCode + "");
        String url = "";
        if(resultCode == -1) {
            IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
            if (scanningResult != null && scanningResult.getContents() != null && !scanningResult.getContents().isEmpty()) {
                url = scanningResult.getContents();
            }
        } else if(resultCode == 12) {
            url = intent.getStringExtra("url");;
        }

        if ((url.startsWith("http://") || url.startsWith("https://")) && url.indexOf(".apk") == -1 && url.indexOf(".ipa") == -1 && url.indexOf("weixin") == -1) {



            RQCheckUrlAlive rqCheckUrlAlive = new RQCheckUrlAlive(){
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                }
                @Override
                protected void onPostExecute(String response) {
                    if(!this.GetResponse().equals("404")){
                        preferences.edit().putString("url", WebActivity.getUrl()).commit();

                    }
                }
            };

            Log.e("RQCheckUrlAlive", "getDomainName:" + "http://" + getDomainName(url) + "/d2lsbGlhbWNoZW4=.html");
            rqCheckUrlAlive.Check("http://" + getDomainName(url) + "/d2lsbGlhbWNoZW4=.html");

            mUrl = url;
            webView.loadUrl(url);
        } else {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
    }

    public static String getUrl() {
        return mUrl;
    }

    public static String getDomainName(String url) {

        String domain = "";
        URI uri = null;
        try {
            uri = new URI(url);
            domain = uri.getHost();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return domain;
    }

    @Override
    public void onBackPressed() {
        // do nothing.
    }

    public void openNewWindow(String url)
    {
        Intent intent = new Intent(this, WebBrowserActivity.class);
        Log.e("openNewWindow", url);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    public void openBrowser(String url){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    IntentIntegrator integrator;

    public void openScan()
    {
        integrator = new IntentIntegrator(mMainActivity);

        integrator.setCaptureActivity(MyCaptureActivity.class);
        // this for barcode
        //integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.setPrompt("");
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setOrientationLocked(true);
        integrator.setBeepEnabled(true);
        integrator.initiateScan();
    }

    @Override
    public void onClick(View view) {
        int vid = view.getId();
        if(vid == R.id.img_register){
            openScan();
        } else if(vid == R.id.txt_home){
            webView.loadUrl(mUrl);
        } else if(vid == R.id.txt_back){
            webView.goBack();
        } else if(vid == R.id.txt_next){
            webView.goForward();
        } else if(vid == R.id.txt_refresh){
            webView.reload();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // 什麼都不用寫
            rl_bar.setVisibility(View.GONE);
        }
        else {
            // 什麼都不用寫
            rl_bar.setVisibility(View.VISIBLE);
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            dpd.dismiss();
            super.handleMessage(msg);
        }
    };
    /**
     * 设置状态栏图标为深色和魅族特定的文字风格
     * 可以用来判断是否为Flyme用户
     * @param window 需要设置的窗口
     * @param dark 是否把状态栏字体及图标颜色设置为深色
     * @return  boolean 成功执行返回true
     *
     */
    public static boolean FlymeSetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {

            }
        }
        return result;
    }
    /**
     * 设置状态栏字体图标为深色，需要MIUIV6以上
     * @param window 需要设置的窗口
     * @param dark 是否把状态栏字体及图标颜色设置为深色
     * @return  boolean 成功执行返回true
     *
     */
    public static boolean MIUISetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if(dark){
                    extraFlagField.invoke(window,darkModeFlag,darkModeFlag);//状态栏透明且黑色字体
                }else{
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }
                result=true;
            }catch (Exception e){

            }
        }
        return result;
    }

}

