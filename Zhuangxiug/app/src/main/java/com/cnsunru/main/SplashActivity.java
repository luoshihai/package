package com.cnsunru.main;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cnsunru.R;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.quest.Config;
import com.cnsunru.login.LoginActivity;
import com.sunrun.sunrunframwork.adapter.ImagePagerAdapter;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.utils.AHandler;
import com.sunrun.sunrunframwork.weight.AutoScrollViewPager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;


/**
 * 启动页(引导页)
 */
public class SplashActivity extends LBaseActivity {

    private static final String TAG = "SplashActivity";
    int startNum = 0;
    //开始的时候是2000    改成1s了
    long time = 500;
    static SplashActivity act;
    AHandler.Task task;
    @BindView(R.id.view_pager)
    AutoScrollViewPager viewPager;
    @BindView(R.id.start)
    View start;
    @BindView(R.id.iv_splash)
    ImageView ivSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        act = this;
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        startNum = Integer.parseInt(Config.getConfigInfo(this, Config.START_NUM, "0"));// 启动次数
        Config.putConfigInfo(this, Config.START_NUM, String.valueOf(startNum + 1));// 启动次数加1
//        startNum = 0;
        if (startNum <= 0 && hasStartImg()) {
            try {
                viewPager.setVisibility(View.VISIBLE);
                viewPager.setSlideBorderMode(AutoScrollViewPager.SLIDE_BORDER_MODE_TO_PARENT);
                String[] bgs = getAssets().list(Config.START_IMG);
                for (int i = 0; i < bgs.length; i++) {
                    bgs[i] = Config.START_IMG + "/" + bgs[i];
                }
                viewPager.setAdapter(new ImagePagerAdapter<String>(this, Arrays.asList(bgs)) {
                            @Override
                            protected void loadImage(@NonNull ImageView imageView, @NonNull String s) {
                            }

                            @Override
                            public View getView(int index, View view, ViewGroup container) {
                                View view1 = super.getView(index, view, container);
                                ImageView imageView = (ImageView) view1.findViewById(R.id.img);
                                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                                return view1;
                            }
                        }.setInfiniteLoop(false)
                                .setOnBannerClickListener(new ImagePagerAdapter.OnBannerClickListener() {
                                    @Override
                                    public void onBannerClick(int position, Object t) {
                                        if (viewPager.getAdapter() != null && viewPager.getCurrentItem() != viewPager.getAdapter().getCount() - 1) {
                                            return;
                                        }
//                                        startLogin(null);
                                        startNavigatorActivity(null);
                                    }
                                })
                )
                ;
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {

            AHandler.runTask(task = new AHandler.Task() {
                @Override
                public void update() {
                    startNavigatorActivity(null);
//                    startLogin(null);
                }
            }, time);
        }
    }
    public static SplashActivity getSplash() {
        return act;
    }

    public static void close() {
        if (act != null) {
            if (act.task == null) {
                act.finish();
            }
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        AHandler.cancel(task);
        act = null;
    }

    @Override
    public void loadFaild(int requestCode, BaseBean bean) {
        super.loadFaild(requestCode, bean);
    }

    public void startNavigatorActivity(View view) {
        List<PermissionItem> permissionItems = new ArrayList<>();
        permissionItems.add(new PermissionItem(Manifest.permission.CAMERA, "照相机", R.drawable.permission_ic_camera));
        permissionItems.add(new PermissionItem(Manifest.permission.ACCESS_FINE_LOCATION, "位置信息", R.drawable.permission_ic_location));
        permissionItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "存储空间", R.drawable.permission_ic_storage));
        HiPermission.create(this)
                .permissions(permissionItems)
                .checkMutiPermission(new PermissionCallback() {
                    @Override
                    public void onClose() {

                    }

                    @Override
                    public void onFinish() {
                        if (LoginActivity.isLogin(SplashActivity.this, true)) {
                            startIntent.startNavigatorActivity(that);
                        }
                        finish();
                    }

                    @Override
                    public void onDeny(String permission, int position) {
                    }

                    @Override
                    public void onGuarantee(String permission, int position) {

                    }
                });

    }

    @OnClick({R.id.start})
    public void onClick(View view) {
        if (viewPager.getAdapter() != null &&
                viewPager.getCurrentItem() != viewPager.getAdapter().getCount() - 1) {
            return;
        }
        switch (view.getId()) {
            case R.id.start:
                startNavigatorActivity(null);
                break;
        }

    }

    boolean hasStartImg() {
        try {
            String[] bgs = getAssets().list(Config.START_IMG);
            return bgs != null && bgs.length > 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
