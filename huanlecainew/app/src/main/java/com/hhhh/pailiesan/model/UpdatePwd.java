package com.hhhh.pailiesan.model;

/**
 * Created by QYQ on 2017/9/22.
 */

public class UpdatePwd {

    /**
     * status : 11111
     * msg : 用户名和密码不匹配
     */

    private int status;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
