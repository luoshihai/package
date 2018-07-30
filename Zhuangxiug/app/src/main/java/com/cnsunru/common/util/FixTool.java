package com.cnsunru.common.util;

import com.sunrun.sunrunframwork.uiutils.UIUtils;

/**
 * 修复一些bug
 * Created by WQ on 2017/10/13.
 */

public class FixTool {
    /**
     * 修复因为Dialog窗口泄露产生的异常,这里简单捕获下
     */
    public static void cancelLoadDialog(){
        try {
            UIUtils.cancelLoadDialog();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
