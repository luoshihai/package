package com.cnsunru.user;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.config.BoxingCropOption;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing.model.entity.impl.ImageMedia;
import com.bilibili.boxing.utils.BoxingFileHelper;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.bumptech.glide.Glide;
import com.cnsunru.R;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.boxing.BoxingHelper;
import com.cnsunru.common.boxing.GlideMediaLoader;
import com.cnsunru.common.model.LoginInfo;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.common.quest.Config;
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.http.utils.JsonDeal;
import com.sunrun.sunrunframwork.uiutils.UIUtils;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.cnsunru.common.quest.BaseQuestConfig.EDIT_DATA_CODE;

/**
 * 资料设置
 */
public class EditUserInfoActivity extends LBaseActivity {

    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.imgUserHead)
    CircleImageView imgUserHead;
    @BindView(R.id.setting_iv_photo_container)
    RelativeLayout settingIvPhotoContainer;
    @BindView(R.id.setting_iv_go)
    ImageView settingIvGo;
    @BindView(R.id.editNickname)
    EditText editNickname;
    @BindView(R.id.editSingle)
    EditText editSingle;
    File headImgFile;
    LoginInfo loginInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_userinfo);
        titleBar.setRightAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIUtils.showLoadDialog(that,"保存中..");
                BaseQuestStart.editData(that,editNickname.getText().toString(),headImgFile);
            }
        });
        loginInfo = Config.getLoginInfo();
        editNickname.setText(loginInfo.getNickname());
        editSingle.setText(loginInfo.getDescription());
        GlideMediaLoader.loadHead(this,imgUserHead,loginInfo.getAvatar());
    }

    public void nofityUpdate(int requestCode,BaseBean bean){
        switch(requestCode){
            case EDIT_DATA_CODE:
                if(bean.status==1){
                    JSONObject jobj= JsonDeal.createJsonObj(bean.toString());
                    loginInfo.setNickname(jobj.optString("nickname"));
                    loginInfo.setAvatar(jobj.optString("avatar"));
                    Config.putLoginInfo(loginInfo);
                    finish();
                }else {
                    UIUtils.shortM(bean);
                }
                break;
        }
        super.nofityUpdate(requestCode,bean);
    }

    @OnClick(R.id.setting_iv_photo_container)
    public void onClick() {
        //修改头像
        BoxingConfig singleCropImgConfig = BoxingHelper.headImgConfig(getApplication());
        Boxing.of(singleCropImgConfig).withIntent(this, BoxingActivity.class).start(this, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ArrayList<BaseMedia> medias = Boxing.getResult(data);
        //这里一定要做非空的判断
        if (medias != null && medias.size() > 0) {
            BaseMedia baseMedia = medias.get(0);
            String path;
            if (baseMedia instanceof ImageMedia) {
                path = ((ImageMedia) baseMedia).getThumbnailPath();
            } else {
                path = baseMedia.getPath();
            }

            headImgFile = new File(path);
            String headImageFilePath = headImgFile.getAbsolutePath();
            Bitmap bitmap = BitmapFactory.decodeFile(headImageFilePath);
            Glide.clear(imgUserHead);//这个地方如果不清楚的话  设置的bitmap无效
            imgUserHead.setImageBitmap(bitmap);
        }
    }
}
