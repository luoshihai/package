package com.cnsunru.common.model;

import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.http.utils.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 分页内容
 * Created by WQ on 2017/9/18.
 */

public class PageBean <T> {

    /**
     * count : 1
     * pages : 1
     * page :
     */

    public String count;
    public int pages;
    public String page;

    public List<T> list;
    /**
     * time : 10:00
     * time_now : 1505962953
     * time_end : 1505973600
     */

    public String time;
    public long time_now;
    public long time_end;


    Calendar calendar=null;
    public  String getLimteTime(){
        if(calendar==null){
            calendar= Calendar.getInstance();
            {
                calendar.set(Calendar.HOUR_OF_DAY,0);
                calendar.set(Calendar.MINUTE,0);
                calendar.set(Calendar.SECOND,0);
            }
            calendar.add(Calendar.MILLISECOND, (int) ((time_end-time_now)*1000));
        }
        calendar.add(Calendar.SECOND,-1);

        return DateUtil.getStringByFormat(calendar.getTimeInMillis(),"HH:mm:ss");
     }
    /**
     * 读取PageBean 中的list , 空安全
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> List<T> getList(BaseBean bean){
        PageBean <T>  pageBean=  bean.Data();
        if(pageBean==null){
            return new ArrayList<>();
        }
        return pageBean.list;
    }
}
