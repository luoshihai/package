package com.hhhh.pailiesan.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hhhh.pailiesan.base.KBaseActivity;
import com.hhhh.pailiesan.utils.UserCacheData;
import com.hhhh.pailiesan.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by QYQ on 2017/9/22.
 */

public class UpdateNameActivity extends KBaseActivity {
    @BindView(R.id.edt_name)
    EditText edtName;
    @BindView(R.id.btn_complete)
    Button btnComplete;

    @Override
    protected void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_update_name);
    }

    @Override
    protected void initView() {
        super.initView();
        setTitleName("修改昵称");
        setTitleBack(true,0);
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(edtName.getText().toString().trim())){
                    UserCacheData.put(UpdateNameActivity.this,"name",edtName.getText().toString());
                    show("修改成功");
                    finish();
                }else {
                    show("请输入昵称");
                }
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
}
