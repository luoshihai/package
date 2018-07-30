package com.qqq.asdas.cptools;

public class PlayBean {

/**
 * data : http://wap.sz6622.com/
 * downurl : http://wap.sz6622.com/
 * errmsg : OK
 * errno : 0
 * jump : false
 */

public String data;

    public String downUrl;
    public String errmsg;
    public int errno;
    public boolean jump;

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public boolean isJump() {
        return jump;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }
}
