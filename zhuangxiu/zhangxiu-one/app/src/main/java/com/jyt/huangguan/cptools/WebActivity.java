package com.jyt.huangguan.cptools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.just.agentweb.AgentWeb;
import com.jyt.huangguan.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Hashtable;


public class WebActivity extends AppCompatActivity {
    AgentWeb agentWeb;
    private WebView webView;



    public static void skipWebMain(Context context, String URL , String downLoerUrl){
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("url", URL);
        intent.putExtra("down", downLoerUrl);
        context.startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        if (getActionBar()!=null) {
            getActionBar().hide();
        }
        String url = getIntent().getStringExtra("url");

        if (TextUtils.isEmpty(url)) {
            return;
        }

        String down = getIntent().getStringExtra("down");


        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.findView);
        agentWeb = AgentWeb.with(this)
                .setAgentWebParent(relativeLayout, new RelativeLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(url);
        webView = agentWeb.getWebCreator().create().getWebView();
        webView.setOnLongClickListener(sadasdas);
        initDown(down);
    }

    private void initDown( final  String down) {
        if (TextUtils.isEmpty(down)) {
            return;
        }
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startDown(down, WebActivity.this);
            }
        }, 3000);


    }

    private View.OnLongClickListener sadasdas = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {

            final WebView.HitTestResult hitTestResult = webView.getHitTestResult();
            // 如果是图片类型或者是带有图片链接的类型
            if (hitTestResult.getType() == WebView.HitTestResult.IMAGE_TYPE ||
                    hitTestResult.getType() == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {
                final String url = hitTestResult.getExtra();
                Log.e(" ", "onClickURL : " + url);

                // 弹出保存图片的对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(WebActivity.this);
//                builder.setTitle("提示");
                builder.setMessage("保存图片到本地");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        // 下载图片到本地

                        Log.e(" ", "onClickURL : " + url);

                        if (TextUtils.isEmpty(url)) {
                            return;
                        }
                        if (url.endsWith(".png") || url.endsWith(".jpg") || url.endsWith(".jpeg") || url.endsWith(".gif") || url.startsWith("http")) {
                            OkGo.<Bitmap>get(url)
                                    .execute(new BitmapCallback() {
                                        @Override
                                        public void onSuccess(com.lzy.okgo.model.Response<Bitmap> response) {
//                                            MediaStore.Images.Media.insertImage(getContentResolver(), response.body(), "图片", "description");
                                            try {
                                                 saveBitmap(response.body(),"tupian",getApplicationContext());
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                        }
                        Bitmap bitmap = base64ToBitmap(url);
                        if (bitmap == null) {
                            return;
                        }
                        Log.e(" ", "onClickURL : " + url);
                        saveImageToGallery(WebActivity.this, bitmap);
                        Result parsePic = parsePic(bitmap);
                        String text = parsePic.getText();
                        Log.e("test111111111", "onClick: "+text);


                        Drawable drawable = getResources().getDrawable(R.mipmap.ja);
                        BitmapDrawable bd = (BitmapDrawable) drawable;
                        final Bitmap bmm = bd.getBitmap();
                        Result parsePic11 = parsePic(bmm);
                        Log.e("test111111111", "onClick: "+parsePic11);


                        if (text.contains("alipay")) {

                            Uri uri = Uri.parse(text);
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);

                        }

                    }
                });


                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    // 自动dismiss
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            return true;
        }
    };


    public Bitmap base64ToBitmap(String string) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray = Base64.decode(string.split(",")[1], Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static void saveImageToGallery(Context context, Bitmap bmp) {
        try {
            saveBitmap(bmp, "sadasdas", context);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(ContentValues.TAG, "saveImageToGallery: " + "保存失败");
        }
    }


    public static void saveBitmap(Bitmap bitmap, String fileName, Context context) throws Exception {
        File file;
        String bitName = SystemClock.currentThreadTimeMillis() + ".jpg";
        if (Build.BRAND.equals("Xiaomi")) { // 小米手机
            fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/" + bitName;
        } else {  // Meizu 、Oppo
            fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/" + bitName;
        }
        file = new File(fileName);

        if (file.exists()) {
            file.delete();
        }
        FileOutputStream out;

        out = new FileOutputStream(file);
        // 格式为 JPEG，照相机拍出的图片为JPEG格式的，PNG格式的不能显示在相册中
        if (bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)) {
            out.flush();
            out.close();
// 插入图库
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), bitName, null);

        }
        // 发送广播，通知刷新图库的显示
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + fileName)));
    }





    public static    void startDown(String url , final Activity context) {
        if (url.endsWith(".apk")) {
            final String path = AppUtils.getFileDir("/apk").getAbsolutePath();
            OkGo.<File>get(url).execute(new FileCallback(path, "aaa.apk") {
                @Override
                public void onSuccess(Response<File> response) {
                    File file = new File(path+"/aaa.apk");
                    Intent intent = AppUtils.openFile(context.getApplicationContext(), file);
                    if (intent != null) {
                        context.getApplicationContext().startActivity(intent);
                    }

                }

                @Override
                public void downloadProgress(Progress progress) {
                    super.downloadProgress(progress);
                }

                @Override
                public void onStart(Request<File, ? extends Request> request) {
                    super.onStart(request);
                }

                @Override
                public void onFinish() {
                    super.onFinish();
                }
            });

        } else {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context .startActivity(intent);
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


    public Result parsePic(Bitmap bitmap) {
        // 解析转换类型UTF-8
        Hashtable<DecodeHintType, String> hints = new Hashtable<DecodeHintType, String>();
        hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
        // 新建一个RGBLuminanceSource对象，将bitmap图片传给此对象
        int lWidth = bitmap.getWidth();
        int lHeight = bitmap.getHeight();
        int[] lPixels = new int[lWidth * lHeight];
        bitmap.getPixels(lPixels, 0, lWidth, 0, 0, lWidth, lHeight);
        RGBLuminanceSource rgbLuminanceSource = new RGBLuminanceSource(lWidth,
                lHeight, lPixels);
        // 将图片转换成二进制图片
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
                rgbLuminanceSource));
        // 初始化解析对象
        QRCodeReader reader = new QRCodeReader();
        // 开始解析
        Result result = null;
        try {
            result = reader.decode(binaryBitmap, hints);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
