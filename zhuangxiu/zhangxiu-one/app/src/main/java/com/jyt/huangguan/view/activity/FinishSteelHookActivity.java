package com.jyt.huangguan.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.jyt.huangguan.api.BeanCallback;
import com.jyt.huangguan.bean.BaseJson;
import com.jyt.huangguan.bean.ProgressBean;
import com.jyt.huangguan.helper.IntentKey;
import com.jyt.huangguan.model.ProjectDetailModel;
import com.jyt.huangguan.model.impl.ProjectDetailModelImpl;
import com.jyt.huangguan.util.T;
import com.jyt.huangguan.R;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by chenweiqi on 2017/11/6.
 */

public class FinishSteelHookActivity extends BaseActivity {
    @BindView(R.id.btn_submit)
    TextView btnSubmit;

    ProjectDetailModel projectDetailModel;
    ProgressBean progressBean;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_finish_steel_hook;
    }

    @Override
    protected View getContentView() {
        return null;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressBean = getIntent().getParcelableExtra(IntentKey.DATA);
        projectDetailModel = new ProjectDetailModelImpl();
        projectDetailModel.onCreate(getContext());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        projectDetailModel.onDestroy();
    }

    @OnClick(R.id.btn_submit)
    public void onBtnSubmitClicked() {
        new AlertDialog.Builder(getContext()).setMessage("是否确认钢挂已完成？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, int which) {
                projectDetailModel.clickToNextStep(progressBean.getSpeedId(), progressBean.getProjectId(), progressBean.getSpeedCode()+"", new BeanCallback<BaseJson>() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        T.showShort(getContext(),e.getMessage());
                    }

                    @Override
                    public void onResponse(BaseJson response, int id) {
                        if (response.ret){
                            dialog.dismiss();
                            finish();
                        }else{
                            T.showShort(getContext(),response.forUser);
                        }

                    }
                });
                dialog.dismiss();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }
}
