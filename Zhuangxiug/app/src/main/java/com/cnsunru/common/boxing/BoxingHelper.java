package com.cnsunru.common.boxing;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.config.BoxingCropOption;
import com.bilibili.boxing.utils.BoxingFileHelper;
import com.cnsunru.R;

import java.util.Locale;

/**
 * Created by WQ on 2017/9/18.
 */

public class BoxingHelper {
    public static BoxingConfig headImgConfig(Context context){
        //获取文件夹的路径  如果没有的这个路径的话就创建一个默认的路径
        String cachePath = BoxingFileHelper.getCacheDir(context);
        if (TextUtils.isEmpty(cachePath)) {
            Toast.makeText(context, R.string.storage_deny, Toast.LENGTH_SHORT).show();
            return new BoxingConfig(BoxingConfig.Mode.SINGLE_IMG);
        }
        Uri destUri = new Uri.Builder()
                .scheme("file")
                .appendPath(cachePath)
                .appendPath(String.format(Locale.US, "%s.jpg", System.currentTimeMillis()))
                .build();
        //这里设置的config的mode是sngle_img  就是单选图片的模式  支持相机
        BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.SINGLE_IMG)
                .needGif()
                .withCropOption(new BoxingCropOption(destUri).withMaxResultSize(200, 200).aspectRatio(1, 1));
        return singleCropImgConfig;
    }
}
