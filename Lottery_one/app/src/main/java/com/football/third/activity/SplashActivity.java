package com.football.third.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.football.third.R;
import com.football.third.application.DSLApplication;


/**
 * Created by dwb on 2018/3/26.
 */

public class SplashActivity extends Activity {
    private ImageView spc_img;
    private static final int MILLI_TIME = 1000;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DSLApplication.getInstance().addActivity(this);
        setContentView(R.layout.special_gride);
        //绑定初始化ButterKnife
//        ButterKnife.bind(this);
        view();
    }

    public void view() {
        spc_img = findViewById(R.id.spc_img);
        go_main();
//        check_app();
/*        new Handler().postDelayed(new Runnable() {
            public void run() {
                    intent=new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
            }
        }, MILLI_TIME);*/
    }

    /**
     * +     * 检测更新
     * +
     */
//    public void check_app() {
//        PackageManager packageManager = getPackageManager();
//        if (NetWorkUtil.checkPackInfo(this, Constant.GO_Package)) {
//            Intent intent = packageManager.getLaunchIntentForPackage(Constant.GO_Package);
//            startActivity(intent);
//            DSLApplication.getInstance().onTerminate();
//        } else {
//            String dateStr = DSLContants.uodate_time;
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            try {
//                Date data1 = format.parse(dateStr);
//                Date now = new Date();
//                if (DateChange.differentDays(data1, now) > 0) {
//                    spc_img.setVisibility(View.VISIBLE);
//                    CustomVersionDialogActivity.customVersionDialogIndex = 2;
//                    CustomVersionDialogActivity.isCustomDownloading = true;
//                    VersionParams.Builder builder = new VersionParams.Builder()
//                            .setRequestUrl(DSLConnections.APP_check)
//                            .setService(DownLoadAppService.class)
//                            .setShowDownloadingDialog(true)
//                            .setCustomDownloadActivityClass(CustomVersionDialogActivity.class);
//                    AllenChecker.startVersionCheck(SplashActivity.this, builder.build());
//                } else {
//                    go_main();
//                }
//            } catch (ParseException e) {
//                go_main();
//            }
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    public void go_main() {
        Intent intent = new Intent(SplashActivity.this, JumpActivity.class);
        startActivity(intent);
        this.finish();
    }
}
