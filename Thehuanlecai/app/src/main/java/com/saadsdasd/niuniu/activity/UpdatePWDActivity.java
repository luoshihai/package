package com.saadsdasd.niuniu.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.saadsdasd.niuniu.base.KBaseActivity;
import com.saadsdasd.niuniu.model.UpdatePwd;
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
 * Created by QYQ on 2017/9/22.
 */

public class UpdatePWDActivity extends KBaseActivity {
    @BindView(R.id.edt_old)
    EditText edtOld;
    @BindView(R.id.edt_new1)
    EditText edtNew1;
    @BindView(R.id.edt_new2)
    EditText edtNew2;
    @BindView(R.id.btn_complete)
    Button btnComplete;
    private String error;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    show("修改成功");
                    finish();
                    break;
                case 2:
                    show(error);
                    break;
            }
        }
    };
    @Override
    protected void setRootView() {
        super.setRootView();
        setContentView(R.layout.update_pwd);
    }

    @Override
    protected void initView() {
        super.initView();
        setTitleName("修改密码");
        setTitleBack(true,0);
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(edtOld.getText().toString().trim())&&!TextUtils.isEmpty(edtNew1.getText().toString().trim())
                &&!TextUtils.isEmpty(edtNew2.getText().toString().trim())){
                    if(edtNew1.getText().toString().equals(edtNew2.getText().toString().trim())){
                            update();
                    }else {
                        show("请确保输入的两次新密码一致");
                    }
                }else {
                    show("请完善信息");
                }
            }
        });
    }
    private void update(){
        String url = "http://api.icaipiao123.com/api/v6/user/updateinfo";
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("passwd_old", edtOld.getText().toString().trim())
                .add("passwd_new", edtNew1.getText().toString().trim())
                .build();
        Request request = new Request.Builder()
                .addHeader("sid", (String) UserCacheData.get(UpdatePWDActivity.this,"sid",""))
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
                Gson gson = new Gson();
                UpdatePwd model = gson.fromJson(str,UpdatePwd.class);
                if(model.getStatus()==0){
                    UserCacheData.put(UpdatePWDActivity.this,"password",edtNew1.getText().toString().trim());
                    handler.sendEmptyMessage(1);
                }else {
                    error = model.getMsg();
                    handler.sendEmptyMessage(2);
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
