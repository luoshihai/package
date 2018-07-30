package com.jyt.huangguan.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jyt.huangguan.cptools.CpTools;
import com.jyt.huangguan.model.PersonModel;
import com.jyt.huangguan.R;

import butterknife.BindView;

public class AboutUsActivity extends BaseActivity {

    @BindView(R.id.tv_msg)
    TextView mTvMsg;

    private PersonModel mPersonModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected View getContentView() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("关于我们");
        mPersonModel = new PersonModel();

        mPersonModel.getAboutUs(new PersonModel.OngetAboutUsListener() {
            @Override
            public void Result(boolean isSuccess, String data) {
                if (isSuccess){
                    String all = data.replaceAll(CpTools.需要替换的内容, CpTools.替换成什么);
                    mTvMsg.setText(all);

                }
            }
        });

    }
}
