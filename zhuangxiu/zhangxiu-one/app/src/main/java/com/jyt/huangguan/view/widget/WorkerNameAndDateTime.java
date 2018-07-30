package com.jyt.huangguan.view.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.jyt.huangguan.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 显示 上传员工 上传时间 控件
 * Created by chenweiqi on 2017/10/31.
 */

public class WorkerNameAndDateTime extends FrameLayout {
    @BindView(R.id.text_workerInfo)
    TextView textWorkerInfo;
    @BindView(R.id.text_dateTime)
    TextView textDateTime;

    String workerInfo;
    String time;
    public WorkerNameAndDateTime(@NonNull Context context) {
        this(context, null);
    }

    public WorkerNameAndDateTime(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.widget_worker_name_and_update_time, this, true);
        ButterKnife.bind(this);

        if (workerInfo!=null){
            setWorkerText(workerInfo);
        }

        if (time != null){
            setUpdateTime(time);
        }
    }

    public void setWorkerText(String text){
        if (textWorkerInfo!=null) {
            textWorkerInfo.setText(text);
        }else {
            workerInfo = text;
        }
    }

    public void setUpdateTime(String text){
        if (textDateTime!=null){
            textDateTime.setText(text);
        }else {
            time = text;
        }
    }
}
