package com.qqq.asdas.view;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;
import com.qqq.asdas.DQDApi.model.Schedule.Schedule;

/**
 * Created by chenxin.
 * Date: 2017/5/9.
 * Time: 22:17.
 */

public interface ScheduleView extends MvpLceView<Schedule> {
    void setData2(Schedule schedule,int currentRound);
    void showToast(String content);
}
