package com.cnsunru.common.model;

import com.sunrun.sunrunframwork.utils.EmptyDeal;

/**
 * Created by cnsunrun on 2017/5/26.
 * <p>
 * 登录模型类
 */

public class LoginInfo {
    /**
     * id : 59
     * member_id : 59
     * description :
     * type : 1
     * mobile : 15271946331
     * balance : 0.00
     * avatar :
     * nickname : 15271****31
     */

    private String id;
    private String member_id;
    private String description;
    private String type;
    private String mobile;
    private String balance;
    private String avatar;
    private String nickname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isValid() {
        return !EmptyDeal.isEmpy(getId()) && !EmptyDeal.isEmpy(id);
    }

}
