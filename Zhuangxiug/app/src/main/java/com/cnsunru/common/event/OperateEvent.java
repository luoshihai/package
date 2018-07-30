package com.cnsunru.common.event;

/**
 * Created by WQ on 2017/9/27.
 */

public class OperateEvent {
    public String action;
    public Object data;

    public <T> T getData() {
        return (T) data;
    }

    public static OperateEvent create(String action, Object data){
        OperateEvent operateEven=new OperateEvent();
        operateEven.action=action;
        operateEven.data=data;
        return operateEven;
    }
}
