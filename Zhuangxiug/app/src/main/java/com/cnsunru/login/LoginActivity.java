package com.cnsunru.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnsunru.R;
import com.cnsunru.common.CommonIntent;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.model.LoginInfo;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.common.quest.Config;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.uiutils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.cnsunru.common.quest.BaseQuestConfig.QUEST_USER_LOGIN_CODE;
import static com.sunrun.sunrunframwork.uiutils.UIUtils.shortM;

/**
 * 登录
 */
public class LoginActivity extends LBaseActivity {

    @BindView(R.id.edit_mobile)
    EditText editMobile;
    @BindView(R.id.edit_assword)
    EditText editAssword;
    @BindView(R.id.btn_forgetfwd)
    TextView btnForgetfwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.txtThirdPartLoginTips)
    TextView txtThirdPartLoginTips;
//    @BindView(R.id.btnWXLogin)
//    ImageView btnWXLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initTitleBar();
        initLoginButton();
        initWxLoginButton();
    }

    private void initWxLoginButton() {
//        findViewById(R.id.btnWXLogin).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    @OnClick(R.id.btn_register)
    public void btnRegister() {
        startIntent.startRegisterActivity(that);
    }

    @OnClick(R.id.btn_forgetfwd)
    public void btnForgetPwd() {
        startIntent.startForgetPasswordActivity(that);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 100) {
            if (data != null) {
                editMobile.setText(data.getStringExtra(Config.ACCOUNT));
            }
        }
    }

    private void initLoginButton() {
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobileNumber = editMobile.getText().toString();
                String passwordContent = editAssword.getText().toString();
                if (TextUtils.isEmpty(mobileNumber) || "".equals(mobileNumber)) {
                    UIUtils.shortM( "请输入手机号码");
                    return;
                }
                if (TextUtils.isEmpty(passwordContent) || "".equals(passwordContent)) {
                    UIUtils.shortM( "请输入密码");
                    return;
                }
                UIUtils.showLoadDialog(that,"登录中...");
                BaseQuestStart.login(that, mobileNumber, passwordContent);
            }
        });
    }


    /**
     * 检查登录状态
     *
     * @param bean
     */
    void checkLoginStatus(BaseBean bean) {
        if (bean != null) {
            shortM("登录成功");
            LoginInfo info = bean.Data();
//            ACache.get(that).put(CacheConfig.TOKEN, info.getR_token());
//            ACache.get(that).put(CacheConfig.IS_OPEN, info.getIs_open());
//            ACache.get(that).put(CacheConfig.TYPE, info.getType());
//            ACache.get(that).put(CacheConfig.ID, info.getId());
//            RongIMHelper.connect(info.getR_token());
            Config.putLoginInfo(info);
//            String isExit = ACache.get(that).getAsString(ConfigEvent.EXIT);
                CommonIntent.startNavigatorActivity(this);
            finish();
        } else {
            UIUtils.shortM("登录失败");
        }
    }

    /**
     * 判断是否登录
     *
     * @param act
     * @param turnLogin 是否判断完毕后跳转到登录页面
     * @return
     */
    public static boolean isLogin(Context act, boolean turnLogin) {
        if (Config.getLoginInfo().isValid()) {
            return true;
        }
        if (turnLogin) {
            Intent intent = new Intent(act, LoginActivity.class);
            intent.putExtra("isReLogin", true);
            act.startActivity(intent);
        }
        return false;
    }

    @Override
    public void nofityUpdate(int requestCode, BaseBean bean) {
        switch (requestCode) {
            case QUEST_USER_LOGIN_CODE:
                if (bean.status > 0) {
                    checkLoginStatus(bean);
                } else {
                    UIUtils.shortM( bean.msg);
                }
                break;
        }
        super.nofityUpdate(requestCode, bean);
    }

    private void initTitleBar() {
//        TitleBar titleBar = (TitleBar) findViewById(R.id.titleBar);
//        titleBar.setRightAction(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
//            }
//        });
    }

}
