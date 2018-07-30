package com.saadsdasd.niuniu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.saadsdasd.niuniu.base.KBaseActivity;
import com.saadsdasd.niuniu.model.LoginModel;
import com.saadsdasd.niuniu.utils.UserCacheData;
import com.google.gson.Gson;
import com.saadsdasd.niuniu.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by QYQ on 2017/9/21.
 */

public class LoginActivity extends KBaseActivity {
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_pwsd)
    EditText editPwsd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;

    private Intent intent;
    private String error;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    show(error);
                    break;
            }
        }
    };
    @Override
    protected void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initView() {
        super.initView();
        setTitleName("登录");
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(editPhone.getText().toString())&&!TextUtils.isEmpty(editPwsd.getText().toString())){
                    login();
                }else {
                    show("请输入手机号或密码");
                }
            }
        });
        btnRegister.setVisibility(View.GONE);
    }
    private void login(){
        String url = "http://api.icaipiao123.com/api/v6/user/phonelogin";
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("passwd", editPwsd.getText().toString().trim())
                .add("phone", editPhone.getText().toString().trim())
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Log.e("登录",str);
                Gson gson = new Gson();
                LoginModel date = gson.fromJson(str,LoginModel.class);
                if("0".equals(date.getStatus())){//登录成功
                    UserCacheData.put(LoginActivity.this,"phone",editPhone.getText().toString());
                    UserCacheData.put(LoginActivity.this,"password",editPwsd.getText().toString());
                    UserCacheData.put(LoginActivity.this,"sid",date.getData().getSid());
                    UserCacheData.put(LoginActivity.this,"isLogin",true);
                    intent = new Intent(LoginActivity.this,MyActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    error = date.getMsg();
                    handler.sendEmptyMessage(1);
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
