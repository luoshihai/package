package com.jyt.huangguan.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jyt.huangguan.api.Const;
import com.jyt.huangguan.util.BaseUtil;
import com.jyt.huangguan.util.FinishActivityManager;
import com.jyt.huangguan.view.widget.FreeDialog;
import com.jyt.huangguan.view.widget.JumpItem;
import com.jyt.huangguan.R;

import butterknife.BindView;

public class PersonInfoActivity extends BaseActivity {


    @BindView(R.id.jt_name)
    JumpItem mJtName;
    @BindView(R.id.jt_phone)
    JumpItem mJtPhone;
    @BindView(R.id.jt_department)
    JumpItem mJtDepartment;
    @BindView(R.id.jt_position)
    JumpItem mJtPosition;
    @BindView(R.id.btn_logout)
    Button mBtnLogout;
    private FreeDialog mDialog;
    private TextView TvSubmit;
    private TextView TvCancel;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_info;
    }

    @Override
    protected View getContentView() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTextTitle("个人资料");
        String AA = Const.getUserName().replaceAll("测试", "");
        mJtName.setMsg(AA);
        mJtPhone.setMsg(Const.getTel());
        if (Const.getPositionName()!=null || TextUtils.isEmpty(Const.getPositionName())){
            mJtPosition.setMsg(Const.getPositionName());
        }
        if (Const.getDepartmentName()!=null){
            mJtDepartment.setMsg(Const.getDepartmentName());
        }



        mBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (!mDialog.isShowing()){
                   mDialog.show();
               }

            }
        });
        initDialog();

    }
    private void initDialog(){
        mDialog = new FreeDialog(this, R.layout.dialog_input);
        TvSubmit = (TextView) mDialog.getView().findViewById(R.id.tv_submit);
        TvCancel = (TextView) mDialog.getView().findViewById(R.id.tv_cancel);
        TvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                BaseUtil.makeText("已退出登录");
                FinishActivityManager.getManager().finishAllActivity();
                Const.Logout(getContext());

            }
        });
        TvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
    }


}
