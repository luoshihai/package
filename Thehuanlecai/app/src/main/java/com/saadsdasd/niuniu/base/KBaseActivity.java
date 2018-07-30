package com.saadsdasd.niuniu.base;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.saadsdasd.niuniu.R;

import butterknife.ButterKnife;

/**
 * Created by QYQ on 2017/9/13.
 */

public class KBaseActivity extends AppCompatActivity implements View.OnClickListener{
    /*Toolbar*/
    private Toolbar toolBar;
    /*是否第一次加载返回*/
    private boolean title_back_first = true;
    /*是否是返回(有可能是代表别的功能)*/
    private boolean is_title_back = true;
    /*返回*/
    private ImageView titleBack;
    /*标题名称*/
    private TextView titleName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setRootView();
        ButterKnife.bind(this);
        initToolbar();
        initView();
        getData();
    }
    protected void setRootView(){}
    protected void initView(){}
    protected void getData(){}
    protected <T extends View>T getView(int resourcesId){
        return (T) findViewById(resourcesId);
    }
    /*初始化toolbar*/
    private void initToolbar(){
        toolBar = getView(R.id.toolbar);
        toolBar.setTitle("");
        toolBar.setTitleTextColor(Color.WHITE);
        titleName = getView(R.id.title_name);
    }
    /**
     * 设置返回
     * @param back :是否返回：是-->返回，不是则设置其他图标
     * @param resourcesId :图标id,返回时随意设置，不使用
     * */
    protected void setTitleBack(final boolean back,int resourcesId){
        is_title_back = back;
        if (title_back_first || titleBack == null){
            titleBack= getView(R.id.title_back);
            titleBack.setOnClickListener(this);
            title_back_first = false;
        }
        titleBack.setVisibility(View.VISIBLE);
        if (!back){
            titleBack.setImageResource(resourcesId);
        }
    }
    /**
     * 设置title
     * @param title ：title
     * */
    protected void setTitleName(String title){
        titleName.setText(title);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.title_back && is_title_back){
            onBackPressed();
        }
    }
    protected void show(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
