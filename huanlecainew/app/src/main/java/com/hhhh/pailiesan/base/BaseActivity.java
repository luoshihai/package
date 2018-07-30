package com.hhhh.pailiesan.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhhh.pailiesan.R;


/**
 * Created by QYQ on 2017/9/8.
 */

public class BaseActivity extends AppCompatActivity implements View.OnClickListener{
    /*Toolbar*/
    private Toolbar toolBar;
    /*返回*/
    private ImageView titleBack;
    /*标题名称*/
    private TextView titleName;
    private boolean isBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();

    }
    protected <T extends View>T getView(int resourcesId){
        return (T) findViewById(resourcesId);
    }/*初始化toolbar*/
    private void initToolbar(){
        toolBar = getView(R.id.toolbar);
        toolBar.setTitle("");
        toolBar.setTitleTextColor(Color.WHITE);
        titleName = getView(R.id.title_name);
        titleBack= getView(R.id.title_back);
    }
    /**
     * 设置返回
     * @param back :是否返回：是-->返回，不是则设置其他图标
     *
     * */
    protected void showBack(final boolean back){
        isBack = back;
        if (titleBack != null){
            titleBack.setOnClickListener(this);
        }
        titleBack.setVisibility(View.VISIBLE);
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
        if (v.getId() == R.id.title_back){
            onBackPressed();
        }
    }


}
