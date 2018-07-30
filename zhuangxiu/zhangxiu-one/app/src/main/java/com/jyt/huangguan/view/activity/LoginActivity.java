package com.jyt.huangguan.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jyt.huangguan.api.Const;
import com.jyt.huangguan.bean.UserBean;
import com.jyt.huangguan.model.LoginModel;
import com.jyt.huangguan.util.BaseUtil;
import com.jyt.huangguan.util.FinishActivityManager;
import com.jyt.huangguan.util.MD5Util;
import com.jyt.huangguan.view.widget.LoadingDialog;
import com.jyt.huangguan.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author LinWei on 2017/10/30 14:06
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.et__tel)
    EditText mEtTel;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.btn_submit)
    Button mBtnSubmit;
    private LoginModel mLoginModel;
    private LoadingDialog mLoadingDialog;
    private boolean isClick =true;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected View getContentView() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HideActionBar();
        mLoginModel=new LoginModel();
        mLoadingDialog = new LoadingDialog(this);
        Const.createFileMkdirs();
    }

    @OnClick(R.id.btn_submit)
    public void ToLogin(){
        String tel=mEtTel.getText().toString().trim();
        String pwd=mEtPwd.getText().toString().trim();
        if (TextUtils.isEmpty(tel) || TextUtils.isEmpty(pwd)){
            BaseUtil.makeText("参数不全");
            return;
        }
        if (!"123456".equals(tel) || !"123456".equals(pwd)) {
            Toast.makeText(this, "账号密码不正确", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isClick){
            return;
        }
        isClick =false;
        mLoadingDialog.show();

//        //内部人员
        mLoginModel.ToLogin(tel, MD5Util.encrypt(pwd), new LoginModel.LoginResultListener() {
            @Override
            public void Result(boolean isSuccess, UserBean user) {
                isClick =true;
                mLoadingDialog.dismiss();
                if (isSuccess){
                    Const.KeepLoginState(user.getDepartmentId(),user.getNickName(),user.getPositionId(),user.getTokenSession(),user.getUserId(),user.getDepartmentName(),user.getStationName(),user.getTel());
                    startActivity(new Intent(LoginActivity.this,ContentActivity.class));
                    finish();
                    BaseUtil.makeText("登录成功");
                }
            }
        });

//        店主
//        mLoginModel.ToShopLogin(tel, MD5Util.encrypt(pwd), new LoginModel.LoginResultListener() {
//            @Override
//            public void Result(boolean isSuccess, UserBean user) {
//                if (isSuccess){
//                    Const.KeepLoginStateShop(user.getShopkeeperName(),user.getShopkeeperId(),user.getToken());
//                    Intent intent = new Intent(LoginActivity.this,ShopActivity.class);
//                    intent.putExtra(IntentKey.PROJECTID,user.getProjectId());
//                    intent.putExtra(IntentKey.SHOPNAME,user.getProjectName());
//                    BaseUtil.setSpString(Const.PROJECTNAME,user.getProjectName());
//                    BaseUtil.setSpString(Const.PROJECTID,user.getProjectId());
//                    startActivity(intent);
//                    finish();
//                    BaseUtil.makeText("登录成功");
//                }
//            }
//        });

        //品牌方
//        mLoginModel.ToBrandLogin(tel, MD5Util.encrypt(pwd), new LoginModel.LoginResultListener() {
//            @Override
//            public void Result(boolean isSuccess, UserBean user) {
//                if (isSuccess){
//                    Const.KeepLoginStateBrand(user.getNickName(),user.getBrandId(),user.getToken(),user.getTel());
//                    startActivity(new Intent(LoginActivity.this,ContentActivity.class));
//                    finish();
//                    BaseUtil.makeText("登录成功");
//                }
//            }
//        });

    }


    /**
     * 双击退出
     */
    private long mPressedTime = 0;
    @Override
    public void onBackPressed() {
        long mNowTime = System.currentTimeMillis();//获取第一次按键时间
        if((mNowTime - mPressedTime) > 2000){//比较两次按键时间差
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mPressedTime = mNowTime;
        }
        else{//退出程序
            FinishActivityManager.getManager().finishAllActivity();
        }
    }

}
