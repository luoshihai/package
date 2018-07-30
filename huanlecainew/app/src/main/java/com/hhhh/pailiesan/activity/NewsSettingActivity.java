package com.hhhh.pailiesan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.hhhh.pailiesan.base.KBaseActivity;
import com.hhhh.pailiesan.utils.UserCacheData;
import com.hhhh.pailiesan.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by QYQ on 2017/9/25.
 */

public class NewsSettingActivity extends KBaseActivity {
    @BindView(R.id.iv_kaiguan)
    ImageView ivKaiguan;
    @BindView(R.id.iv_shenying)
    ImageView ivShenying;
    @BindView(R.id.iv_zhendong)
    ImageView ivZhendong;
    private int open = 0;
    private int sound = 0;
    private int shoke = 0;
    @Override
    protected void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_news_setting);
    }

    @Override
    protected void initView() {
        super.initView();
        setTitleName("推送设置");
        setTitleBack(true,0);
        if((Boolean) UserCacheData.get(this,"openClose",true)){
            ivKaiguan.setImageResource(R.mipmap.kai);
        }else {
            ivKaiguan.setImageResource(R.mipmap.guan);
        }
        if((Boolean) UserCacheData.get(this,"shengyin",true)){
            ivShenying.setImageResource(R.mipmap.kai);
        }else {
            ivShenying.setImageResource(R.mipmap.guan);
        }
        if((Boolean) UserCacheData.get(this,"zhendong",true)){
            ivZhendong.setImageResource(R.mipmap.kai);
        }else {
            ivZhendong.setImageResource(R.mipmap.guan);
        }
    }

    @Override
    protected void getData() {
        super.getData();
        ivKaiguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open++;
                if(open%2==0){
                    ivKaiguan.setImageResource(R.mipmap.kai);
                    UserCacheData.put(NewsSettingActivity.this,"openClose",true);
                }else {
                    ivKaiguan.setImageResource(R.mipmap.guan);
                    UserCacheData.put(NewsSettingActivity.this,"openClose",false);
                }
            }
        });
        ivShenying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound++;
                if(sound%2==0){
                    ivShenying.setImageResource(R.mipmap.kai);
                    UserCacheData.put(NewsSettingActivity.this,"shengyin",true);
                }else {
                    ivShenying.setImageResource(R.mipmap.guan);
                    UserCacheData.put(NewsSettingActivity.this,"shengyin",false);
                }
            }
        });
        ivZhendong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shoke++;
                if(shoke%2==0){
                    ivZhendong.setImageResource(R.mipmap.kai);
                    UserCacheData.put(NewsSettingActivity.this,"zhendong",true);
                }else {
                    ivZhendong.setImageResource(R.mipmap.guan);
                    UserCacheData.put(NewsSettingActivity.this,"zhendong",false);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
