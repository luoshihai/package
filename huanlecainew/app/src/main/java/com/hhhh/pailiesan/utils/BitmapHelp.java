package com.hhhh.pailiesan.utils;

import android.content.Context;
import android.graphics.Bitmap;

import com.lidroid.xutils.BitmapUtils;


/***
 *
 */
public class BitmapHelp {

    private BitmapHelp() {
    }

    private static BitmapUtils bitmapUtils;

    private static BitmapUtils bitmapUtils1;


    /**
     * BitmapUtils不是单例的 根据需要重载多个获取实例的方法
     *
     * @return
     */
    public static BitmapUtils getBitmapUtils(Context context) {
        if (bitmapUtils == null) {
            bitmapUtils = new BitmapUtils(context);
            bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);
        }

        return bitmapUtils;
    }

    /***
     * 有默认图片的
     *
     * @param imageRes
     * @return
     */
    public static BitmapUtils getBitmapUtils(Context context, int imageRes) {
        if (bitmapUtils1 == null) {
            bitmapUtils1 = new BitmapUtils(context);
        }
        bitmapUtils1.configDefaultLoadingImage(imageRes);
        bitmapUtils1.configDefaultLoadFailedImage(imageRes);
        bitmapUtils1.configDefaultBitmapConfig(Bitmap.Config.RGB_565);
        return bitmapUtils1;
    }

}
