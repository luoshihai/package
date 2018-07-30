package com.saadsdasd.niuniu.utils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;

/**
 * Created by QYQ on 2017/9/22.
 */

public class CameraUtil {
    // CameraUtil的方法
    public static final String FILE_PATH = Environment.getExternalStorageDirectory().toString()+"/YULE/";
    public static Uri getTempUri() {
        String fileName = System.currentTimeMillis() + ".jpg";
        File out = new File(FILE_PATH);
        if (!out.exists()) {
            out.mkdirs();
        }
        out = new File(FILE_PATH, fileName);
        return Uri.fromFile(out);
    }
    public static Intent takePicture(Uri uri) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        return intent;
    }
    // CameraUtil的裁剪方法
    public static Intent cropPhoto(Uri uri, Uri cropUri, int outputX, int outputY) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");//可裁剪
        intent.putExtra("aspectX", 1); //裁剪的宽比例
        intent.putExtra("aspectY", 1);  //裁剪的高比例
        intent.putExtra("outputX", outputX); //裁剪的宽度
        intent.putExtra("outputY", outputY);  //裁剪的高度
        intent.putExtra("scale", true); //支持缩放
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropUri);  //将裁剪的结果输出到指定的Uri
        intent.putExtra("return-data", true); //若为true则表示返回数据
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());//裁剪成的图片的格式
        intent.putExtra("noFaceDetection", true);  //启用人脸识别
        return intent;
    }
    // CameraUtil选择相册的方法
    public static Intent selectPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        return intent;
    }

}
