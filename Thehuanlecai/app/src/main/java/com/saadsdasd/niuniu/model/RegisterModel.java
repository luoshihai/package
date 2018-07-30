package com.saadsdasd.niuniu.model;

/**
 * Created by QYQ on 2017/9/26.
 */

public class RegisterModel {

    /**
     * can_use : 0
     * reason : 昵称已被别人占用，请改过再来
     * status : 0
     */

    private int can_use;
    private String reason;
    private int status;
    private int is_registered;

    public int getIs_registered() {
        return is_registered;
    }

    public void setIs_registered(int is_registered) {
        this.is_registered = is_registered;
    }

    public int getCan_use() {
        return can_use;
    }

    public void setCan_use(int can_use) {
        this.can_use = can_use;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
