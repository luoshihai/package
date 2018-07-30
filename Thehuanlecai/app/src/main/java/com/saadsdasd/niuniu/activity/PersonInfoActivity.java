package com.saadsdasd.niuniu.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.saadsdasd.niuniu.R;
import com.saadsdasd.niuniu.base.KBaseActivity;
import com.saadsdasd.niuniu.utils.CameraUtil;
import com.saadsdasd.niuniu.utils.RoundImageView;
import com.saadsdasd.niuniu.utils.UserCacheData;

import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by QYQ on 2017/9/22.
 */

public class PersonInfoActivity extends KBaseActivity {
    @BindView(R.id.rl_headImg)
    RelativeLayout rlHeadImg;
    @BindView(R.id.tv_myName)
    TextView tvMyName;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    @BindView(R.id.tv_myPhone)
    TextView tvMyPhone;
    @BindView(R.id.rl_pwd)
    RelativeLayout rlPwd;
    @BindView(R.id.iv_head1)
    RoundImageView ivHead1;
    // 回调请求码，根据自己的需求定义数值
    private final int REQ_CODE_CAMERA = 1;
    private final int REQ_CODE_PICTURE = 2;
    private final int REQ_CODE_CUT = 3;
    //用来保存拍照时临时存储的uri
    private Uri imgUrl;
    private Intent intent;
    @Override
    protected void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_personinfo);

    }

    @Override
    protected void initView() {
        super.initView();
        tvMyName.setText((String)UserCacheData.get(PersonInfoActivity.this,"name","账号"));
        Uri uri = Uri.parse((String) UserCacheData.get(PersonInfoActivity.this,"photo",""));
        if(!TextUtils.isEmpty((String) UserCacheData.get(PersonInfoActivity.this,"photo",""))){
            ivHead1.setImageBitmap(getBitmapByUri(PersonInfoActivity.this, uri));
        }
        setTitleName("个人信息");
        setTitleBack(true, 0);
        //获取一个临时uri
        imgUrl = CameraUtil.getTempUri();
        rlHeadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                new AlertDialog.Builder(PersonInfoActivity.this)
//                        .setItems(new String[]{"相机", "相册"}, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                if (i == 0) {
//                                    //启动系统照相机
//                                    startActivityForResult(CameraUtil.takePicture(imgUrl), REQ_CODE_CAMERA);
//                                } else {
//                                    startActivityForResult(Intent.createChooser(CameraUtil.selectPhoto(),"选择照片"),REQ_CODE_PICTURE);
//                                }
//                            }
//                        })
//                        .show();

            }
        });
        rlName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(PersonInfoActivity.this,UpdateNameActivity.class);
                startActivity(intent);
            }
        });
        rlPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(PersonInfoActivity.this,UpdatePWDActivity.class);
                startActivity(intent);
            }
        });
    }




    @Override
    protected void getData() {
        super.getData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {

            switch (requestCode) {
                //拍照
                case REQ_CODE_CAMERA:
                    if (data != null) {
                        Uri uri = data.getData();
                        startActivityForResult(
                                CameraUtil.cropPhoto(uri, imgUrl, 150, 150),
                                REQ_CODE_CUT);
                    } else {
                        startActivityForResult(
                                CameraUtil.cropPhoto(imgUrl, imgUrl, 150, 150),
                                REQ_CODE_CUT);
                    }
                    break;
                //相册选择
                case REQ_CODE_PICTURE:
                    if (data != null) {
                        imgUrl = CameraUtil.getTempUri();
                        startActivityForResult(CameraUtil.cropPhoto(data.getData(),
                                imgUrl, 150, 150), REQ_CODE_CUT);
                    }
                    break;
                //裁剪
                case REQ_CODE_CUT:
                    ivHead1.setImageBitmap(getBitmapByUri(PersonInfoActivity.this,imgUrl));
                    UserCacheData.put(PersonInfoActivity.this,"photo",imgUrl.toString());
                    show("头像修改成功");
                default:
                    break;
            }

        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    // CameraUtil获取Bitmap的方法
    public static Bitmap getBitmapByUri(Context context, Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(context.getContentResolver()
                    .openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        tvMyName.setText((String)UserCacheData.get(PersonInfoActivity.this,"name","账号"));
    }
}
