package com.gzsll.hupu.bean;

/**
 * Created by sll on 2016/3/10.
 */
public class UserResult {
    public String news_comment_url;
    public String nickname_set_url;

    public int is_self;
    public String news_comment_count;


    public String bbs_msg_url;
    public String bbs_post_url;
    public String nickname;
    public String header;
    public String reg_time_str;
    public int gender;
    public String location_str;
    public String school;
    public String bbs_msg_count;
    public String bbs_post_count;

    public UserResult(String bbs_msg_url, String bbs_post_url, String nickname, String header, String reg_time_str, int gender, String location_str, String school, String bbs_msg_count, String bbs_post_count) {
        this.bbs_msg_url = bbs_msg_url;
        this.bbs_post_url = bbs_post_url;
        this.nickname = nickname;
        this.header = header;
        this.reg_time_str = reg_time_str;
        this.gender = gender;
        this.location_str = location_str;
        this.school = school;
        this.bbs_msg_count = bbs_msg_count;
        this.bbs_post_count = bbs_post_count;
    }
}
