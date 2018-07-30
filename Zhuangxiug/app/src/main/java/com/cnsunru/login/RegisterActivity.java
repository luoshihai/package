package com.cnsunru.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.cnsunru.R;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.common.quest.Config;
import com.cnsunru.common.widget.RoundButton;
import com.cnsunru.common.widget.edittext.EditView;
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.cnsunru.login.mode.RegistInfo;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.uiutils.UIUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.cnsunru.common.quest.BaseQuestConfig.QUEST_USER_REGIST_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.QUEST_VERIFICATION_CODE;

/**
 * 注册
 */
public class RegisterActivity extends LBaseActivity {

    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.editPhone)
    EditView editPhone;
    @BindView(R.id.editCode)
    EditView editCode;
    @BindView(R.id.btnGetCode)
    RoundButton btnGetCode;
    @BindView(R.id.editPassword)
    EditView editPassword;
    CaptchaStateMode captchaStateMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        captchaStateMode=new CaptchaStateMode(this,btnGetCode);
        titleBar.setRightAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R.id.btnGetCode, R.id.submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnGetCode:
                String mobileNum = editPhone.getText().toString();
                if (TextUtils.isEmpty(mobileNum) || "".equals(mobileNum)) {
                    UIUtils.shortM("请输入手机号码");
                } else {
                    BaseQuestStart.getVerificationCodeUrl(that,mobileNum);
                }
                break;
            case R.id.submit:
                String mobileNumber = editPhone.getText().toString();
                String mVerticalCodeNumber = editCode.getText().toString();
                String mPasswordNumber = editPassword.getText().toString();
                if (TextUtils.isEmpty(mobileNumber) || "".equals(mobileNumber)) {
                    UIUtils.shortM("手机号码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(mVerticalCodeNumber)||"".equals(mVerticalCodeNumber)) {
                    UIUtils.shortM("验证码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(mPasswordNumber) ||"".equals(mPasswordNumber)) {
                    UIUtils.shortM("密码不能为空");
                    return;
                }
                BaseQuestStart.register(that,mobileNumber,mVerticalCodeNumber,mPasswordNumber);
                break;
        }
    }

    @Override
    public void nofityUpdate(int requestCode, BaseBean bean) {
        switch (requestCode) {
            case QUEST_VERIFICATION_CODE:
                if (bean.status >0) {
                    UIUtils.shortM(bean.Data().toString());
                    captchaStateMode.beginCountDown();
                } else {
                    UIUtils.shortM(bean.msg);
                }
                break;
            case QUEST_USER_REGIST_CODE:
                if (bean.status > 0) {
                    RegistInfo data = bean.Data();
                    //普通用户返回结果
                    Intent intent = new Intent();
                    intent.putExtra(Config.ACCOUNT,data.getMobile());
                    Config.putConfigInfo(this, Config.ACCOUNT, data.getMobile());
                    setResult(RESULT_OK, intent);
                    UIUtils.shortM("注册成功");
                    captchaStateMode.stopCountDown();
                    finish();
                } else {
                    UIUtils.shortM(bean.msg);
                }
                break;
        }
        super.nofityUpdate(requestCode, bean);
    }

    @Override
    public void loadFaild(int requestCode, BaseBean bean) {
        if(QUEST_VERIFICATION_CODE==requestCode)
        captchaStateMode.stopCountDown();
        super.loadFaild(requestCode, bean);
    }
}
