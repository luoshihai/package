package com.hhhh.pailiesan.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hhhh.pailiesan.base.KBaseActivity;
import com.hhhh.pailiesan.model.RegisterModel;
import com.hhhh.pailiesan.utils.PhoneUtils;
import com.google.gson.Gson;
import com.hhhh.pailiesan.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by QYQ on 2017/9/21.
 */

public class RegisterActivity extends KBaseActivity {
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.edit_pwd)
    EditText editPwd;
    @BindView(R.id.btn_register)
    Button btnRegister;
    private String phone,name,password,error;
    @Override
    protected void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void initView() {
        super.initView();
        setTitleName("注册");
        setTitleBack(true,0);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(editName.getText().toString().trim())&&!TextUtils.isEmpty(editPhone.getText().toString().trim())&&
                        !TextUtils.isEmpty(editPwd.getText().toString().trim())){
                    if(PhoneUtils.isPhone(editPhone.getText().toString().trim())){
                        show("此昵称已被占用，请重新输入");
                    }else {
                        show("请输入正确的手机号码");
                    }
                }else {
                    show("请完善资料");
                }
            }
        });

    }
    private void checkPhone(String Phone){
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://api.icaipiao123.com/api/v6/user/phoneisregistered?phone="+Phone).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {//回调的方法执行在子线程。
                    final RegisterModel model = new Gson().fromJson(response.toString(),RegisterModel.class);
                    if(model.getIs_registered()==0){//号码可以使用

                    }else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                show("该号码已被注册或格式错误");
                            }
                        });
                    }
                }
            }
        });
    }
    private void checkName(String Name){
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://api.icaipiao123.com/api/v6/user/nicknamecanuse?name="+Name).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {//回调的方法执行在子线程。
                    final RegisterModel model = new Gson().fromJson(response.toString(),RegisterModel.class);
                    if(model.getCan_use()==1){//昵称可以使用

                    }else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                show(model.getReason());
                            }
                        });
                    }
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
