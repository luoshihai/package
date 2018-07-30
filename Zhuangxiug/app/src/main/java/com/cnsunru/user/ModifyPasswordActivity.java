package com.cnsunru.user;

import android.os.Bundle;
import android.widget.Button;

import com.cnsunru.R;
import com.cnsunru.common.base.LBaseActivity;
import com.cnsunru.common.quest.BaseQuestStart;
import com.cnsunru.common.widget.edittext.EditView;
import com.cnsunru.common.widget.titlebar.TitleBar;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.uiutils.UIUtils;
import com.sunrun.sunrunframwork.utils.formVerify.FormatException;
import com.sunrun.sunrunframwork.utils.formVerify.VerifyUtil;
import com.sunrun.sunrunframwork.utils.formVerify.VerifyerSet;
import com.sunrun.sunrunframwork.utils.formVerify.VerifyerSet.EmptyVerifyer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.cnsunru.common.quest.BaseQuestConfig.EDIT_DATA_CODE;
import static com.cnsunru.common.quest.BaseQuestConfig.EDIT_PWD_CODE;

/**
 * 密码修改
 */
public class ModifyPasswordActivity extends LBaseActivity {

    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.editOldPwd)
    EditView editOldPwd;
    @BindView(R.id.editNewPwd)
    EditView editNewPwd;
    @BindView(R.id.editConfirmNewPwd)
    EditView editConfirmNewPwd;
    @BindView(R.id.submit)
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pwd);
    }

    public void nofityUpdate(int requestCode, BaseBean bean) {
        switch (requestCode) {
            case EDIT_PWD_CODE:
                UIUtils.shortM(bean);
                if (bean.status == 1) {
                    finish();
                }
                break;
        }
        super.nofityUpdate(requestCode, bean);
    }

    @OnClick(R.id.submit)
    public void onClick() {
        try {
            VerifyUtil.verify(editOldPwd.EditText(), new EmptyVerifyer("旧密码不能为空"));
            VerifyUtil.verify(editNewPwd.EditText(), new EmptyVerifyer("新密码不能为空"));
            VerifyUtil.verify(editConfirmNewPwd.EditText(), new EmptyVerifyer("确认密码不能为空"));
            VerifyUtil.verify(new VerifyerSet.ConfirmPasswordVerifyer("两次输入密码不一致"),editNewPwd.EditText(),editConfirmNewPwd.EditText());
            UIUtils.showLoadDialog(that,"修改中...");
            BaseQuestStart.edit_pwd(this, editOldPwd.EditText(), editNewPwd.EditText(), editConfirmNewPwd.EditText());
        } catch (FormatException e) {
            UIUtils.shortM(e.getLocalizedMessage());
        }
    }
}
