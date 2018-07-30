package com.cnsunru.order;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.content.PermissionChecker;

import com.cnsunru.R;
import com.cnsunru.common.base.LBaseActivity;
import com.sunrun.sunrunframwork.uiutils.UIUtils;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import static android.Manifest.permission.CAMERA;

/**
 * 二维码扫描
 * Created by WQ on 2017/5/26.
 */

public class ScanQRActivity extends LBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activit_scan_qr);
        /**
         * 执行扫面Fragment的初始化操作
         */
        CaptureFragment captureFragment = new CaptureFragment();
        // 为二维码扫描界面设置定制化界面
        CodeUtils.setFragmentArgs(captureFragment, R.layout.fragment_scan_qr_camera);
        /**
         * 二维码解析回调函数
         */
        CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
            @Override
            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                Intent resultIntent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
                bundle.putString(CodeUtils.RESULT_STRING, result);
                resultIntent.putExtras(bundle);
                setResult(RESULT_OK, resultIntent);
                finish();

            }

            @Override
            public void onAnalyzeFailed() {
                Intent resultIntent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
                bundle.putString(CodeUtils.RESULT_STRING, "");
                resultIntent.putExtras(bundle);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        };
        captureFragment.setAnalyzeCallback(analyzeCallback);
        try {
            /**
             * 替换我们的扫描控件
             */
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
        }catch (Exception e){
            UIUtils.shortM("摄像头打开失败");
            e.printStackTrace();
        }
    }

    public static boolean _hasPermiss(Context context){
        PackageManager pm = context.getPackageManager();
        boolean permission = (PackageManager.PERMISSION_GRANTED ==
                pm.checkPermission("android.permission.CAMERA",context.getPackageName()));
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
            Camera.Parameters mParameters = mCamera.getParameters(); //针对魅族手机检查相机权限
            mCamera.setParameters(mParameters);
        } catch (Exception e) {
            permission= false;
        }

        if (mCamera != null) {
            try {
                mCamera.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return permission&& PackageManager.PERMISSION_GRANTED== PermissionChecker.checkPermission(context, CAMERA,Process.myPid(),Process.myUid(),context.getPackageName());
    }
}
