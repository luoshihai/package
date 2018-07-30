package com.jyt.huangguan.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jyt.huangguan.api.BeanCallback;
import com.jyt.huangguan.api.Const;
import com.jyt.huangguan.bean.BaseJson;
import com.jyt.huangguan.bean.InfoBean;
import com.jyt.huangguan.helper.IntentHelper;
import com.jyt.huangguan.model.InfoModel;
import com.jyt.huangguan.model.impl.InfoModelmpl;
import com.jyt.huangguan.R;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import okhttp3.Call;

public class InfoActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_progress)
    TextView mTvProgress;
    @BindView(R.id.tv_Btime)
    TextView mTvBtime;
    @BindView(R.id.rl_progress)
    RelativeLayout mRlProgress;
    @BindView(R.id.tv_news)
    TextView mTvNews;
    @BindView(R.id.tv_Ytime)
    TextView mTvYtime;
    @BindView(R.id.rl_news)
    RelativeLayout mRlNews;
    @BindView(R.id.tv_evaluation)
    TextView mTvEvaluation;
    @BindView(R.id.tv_Gtime)
    TextView mTvGtime;
    @BindView(R.id.rl_evaluation)
    RelativeLayout mRlEvaluation;

    private InfoModel mInfoModel;
    private boolean click_b;
    private boolean click_y;
    private boolean click_g;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_info;
    }

    @Override
    protected View getContentView() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initData();
        initListener();

    }

    private void init(){
        setTextTitle("消息");
        mInfoModel = new InfoModelmpl();
    }

    private void initData(){
        //进度
        mInfoModel.getLatOneProgress(new BeanCallback<BaseJson<InfoBean>>() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("@#","P-error: "+e.getMessage());
            }

            @Override
            public void onResponse(BaseJson<InfoBean> response, int id) {
                if (response.ret){
                    click_b=true;
                    InfoBean data = response.data;
                    if (data!=null){
                        mTvProgress.setText("进度已达"+data.getSpeedName() +"，点击查看");
                        mTvBtime.setText(getTime(data.getUpdateDate()));
                    }


                }
            }
        });

        //提示
        mInfoModel.getLastOneHint(new BeanCallback<BaseJson<InfoBean>>() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("@#","H-error: "+e.getMessage());
            }

            @Override
            public void onResponse(BaseJson<InfoBean> response, int id) {
                click_y=true;
                InfoBean data = response.data;
                if (data!=null){
                    mTvNews.setText(data.getBliu()+"有新的消息，点击查看");
                    mTvYtime.setText(getTime(data.getFinishDate()));
                }
            }
        });

        //评价
        mInfoModel.getLastOneEvaluate(new BeanCallback<BaseJson<InfoBean>>() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("@#","E-error: "+e.getMessage());
            }

            @Override
            public void onResponse(BaseJson<InfoBean> response, int id) {
                click_g=true;
                InfoBean data = response.data;
                if (data!=null){
                    mTvEvaluation.setText(data.getProjectName()+"有新的评价，点击查看");
                    switch (Const.getPositionName()) {
                        case "预算员":
                            mTvGtime.setText(getTime(data.getKtobtime()));
                            break;
                        case "设计师":
                            mTvGtime.setText(getTime(data.getKtodtime()));
                            break;
                        case "测量人员":
                            mTvGtime.setText(getTime(data.getKtomtime()));
                            break;
                        case "项目经理":
                            mTvGtime.setText(getTime(data.getKtobtime()));
                            break;
                    }
                }
            }
        });
    }

    private void initListener(){
        mRlProgress.setOnClickListener(this);
        mRlNews.setOnClickListener(this);
        mRlEvaluation.setOnClickListener(this);
    }

    public  String getTime(String time){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd \n HH:mm");
        return format.format(Long.parseLong(time));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
           case R.id.rl_progress:
               if (click_b){
                   IntentHelper.OpenInfoDetailActivity(InfoActivity.this,0);
               }
                break;

            case R.id.rl_news:
                if (click_y){
                    IntentHelper.OpenInfoDetailActivity(InfoActivity.this,1);
                }
                break;

            case R.id.rl_evaluation:
                if (click_g){
                    IntentHelper.OpenInfoDetailActivity(InfoActivity.this,2);
                }
                break;

            default:
                break;
        }
    }


}
