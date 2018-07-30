package com.cnsunru.home.mode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.cnsunru.common.base.LBaseFragment;
import com.cnsunru.common.intent.IntentPoxy;
import com.cnsunru.common.intent.StartIntent;
import com.cnsunru.common.util.Tool;
import com.sunrun.sunrunframwork.uibase.BaseFragment;
import com.sunrun.sunrunframwork.uiutils.UIUtils;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import static com.cnsunru.common.intent.StartIntent.SCAN_QR_REQUEST;

/**
 * Created by WQ on 2017/10/18.
 */

public class QRScannerMode {
    /* 处理二维码扫描结果
              */
    public static void dealResult(Activity activity, int requestCode, int resultCode, Intent data) {
        //处理扫描结果（在界面上显示）
        if (requestCode == SCAN_QR_REQUEST && data != null && data.getExtras() != null) {
            Bundle bundle = data.getExtras();
            if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                String result = bundle.getString(CodeUtils.RESULT_STRING);
                if(Tool.isNumber(result)) {
                    IntentPoxy.getProxyInstance().startGoodsDetailsActivity(activity, result);
                }else {
                    UIUtils.shortM("二维码内容识别失败");
                }
//                    Toast.makeText(that, "解析结果:" + result, Toast.LENGTH_LONG).show();
            } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                UIUtils.shortM("解析二维码失败");
            }
        }
    }
}
