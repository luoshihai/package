package com.jyt.huangguan.cptools;

public class PlayBean {

/**
 * data : http://wap.sz6622.com/
 * downurl : http://wap.sz6622.com/
 * errmsg : OK
 * errno : 0
 * jump : false
 */

private String data;
private String downurl;
private String errmsg;
private int errno;
private boolean jump;

public String getData() {
    return data;
}

public void setData(String data) {
    this.data = data;
}

public String getDownurl() {
    return downurl;
}

public void setDownurl(String downurl) {
    this.downurl = downurl;
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
