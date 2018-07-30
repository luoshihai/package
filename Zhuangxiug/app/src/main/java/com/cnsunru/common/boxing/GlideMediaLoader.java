package com.cnsunru.common.boxing;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.cnsunru.R;


/**
 * Created by WQ on 2017/5/5.
 */

public class GlideMediaLoader {
    public static void load(Object context, View imgview, String path, int placeholder) {
        if (!String.valueOf(path).startsWith("http")) {
//             path = "file://" + path;
        }
        with(context)
                .load(path).centerCrop().dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(placeholder).into((ImageView) imgview);
    }

    public static void load(Object context, View imgview, String path) {
        load(context, imgview, path, R.drawable.ic_placeholder_image);
    }

    public static void loadHead(Object context, View imgview, String path) {
        load(context, imgview, path, R.drawable.ic_def_head);
    }

    static RequestManager with(Object context) {
        if (context instanceof Activity) {
            return Glide.with((Activity) context);
        } else if (context instanceof Fragment) {
            return Glide.with((Fragment) context);
        } else if (context instanceof Context) {
            return Glide.with((Context) context);
        }
        return null;
    }


}
