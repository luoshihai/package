package com.hhhh.pailiesan.model;

/**
 * Created by QYQ on 2017/9/22.
 */

public class CommentModel {


    /**
     * author_id : 10925504
     * content : 只有一次，真的，开奖后我看到的号码好像是一样的，有点小失落。没买的又怎样
     * id : 11109664
     * message_id : 2473250
     * publish_time : 1506064888000
     * reply_to :
     * reply_to_comment_id : 0
     * state : 1
     * thumb_up_count : 0
     * thumb_up_last_time : null
     * user : {"create_time":1505858990000,"daletou_price":0,"device_id":"BFD150A1CE980A5Y455CV592NRQN145NKLNFGMBFQ2NU42USXEHGS7Z7S4FVF7YX","fucai3d_price":0,"icon":"https://p.zhangkongshidai.cn/avatar/170920/82aabd9e761b1524d1156508ba664b7713027724.jpg","id":10925504,"install_channel":"ssq","name":"释然39131e","pailie3_price":0,"qilecai_price":0,"reg_ip":"117.136.40.49","shuangseqiu_price":0,"v_tag":0,"vip":{}}
     */

    private int author_id;
    private String content;
    private int id;
    private int message_id;
    private long publish_time;
    private String reply_to;
    private int reply_to_comment_id;
    private int state;
    private int thumb_up_count;
    private Object thumb_up_last_time;
    private UserBean user;

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public long getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(long publish_time) {
        this.publish_time = publish_time;
    }

    public String getReply_to() {
        return reply_to;
    }

    public void setReply_to(String reply_to) {
        this.reply_to = reply_to;
    }

    public int getReply_to_comment_id() {
        return reply_to_comment_id;
    }

    public void setReply_to_comment_id(int reply_to_comment_id) {
        this.reply_to_comment_id = reply_to_comment_id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getThumb_up_count() {
        return thumb_up_count;
    }

    public void setThumb_up_count(int thumb_up_count) {
        this.thumb_up_count = thumb_up_count;
    }

    public Object getThumb_up_last_time() {
        return thumb_up_last_time;
    }

    public void setThumb_up_last_time(Object thumb_up_last_time) {
        this.thumb_up_last_time = thumb_up_last_time;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * create_time : 1505858990000
         * daletou_price : 0
         * device_id : BFD150A1CE980A5Y455CV592NRQN145NKLNFGMBFQ2NU42USXEHGS7Z7S4FVF7YX
         * fucai3d_price : 0
         * icon : https://p.zhangkongshidai.cn/avatar/170920/82aabd9e761b1524d1156508ba664b7713027724.jpg
         * id : 10925504
         * install_channel : ssq
         * name : 释然39131e
         * pailie3_price : 0
         * qilecai_price : 0
         * reg_ip : 117.136.40.49
         * shuangseqiu_price : 0
         * v_tag : 0
         * vip : {}
         */

        private long create_time;
        private int daletou_price;
        private String device_id;
        private int fucai3d_price;
        private String icon;
        private int id;
        private String install_channel;
        private String name;
        private int pailie3_price;
        private int qilecai_price;
        private String reg_ip;
        private int shuangseqiu_price;
        private int v_tag;
        private VipBean vip;

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public int getDaletou_price() {
            return daletou_price;
        }

        public void setDaletou_price(int daletou_price) {
            this.daletou_price = daletou_price;
        }

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public int getFucai3d_price() {
            return fucai3d_price;
        }

        public void setFucai3d_price(int fucai3d_price) {
            this.fucai3d_price = fucai3d_price;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getInstall_channel() {
            return install_channel;
        }

        public void setInstall_channel(String install_channel) {
            this.install_channel = install_channel;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPailie3_price() {
            return pailie3_price;
        }

        public void setPailie3_price(int pailie3_price) {
            this.pailie3_price = pailie3_price;
        }

        public int getQilecai_price() {
            return qilecai_price;
        }

        public void setQilecai_price(int qilecai_price) {
            this.qilecai_price = qilecai_price;
        }

        public String getReg_ip() {
            return reg_ip;
        }

        public void setReg_ip(String reg_ip) {
            this.reg_ip = reg_ip;
        }

        public int getShuangseqiu_price() {
            return shuangseqiu_price;
        }

        public void setShuangseqiu_price(int shuangseqiu_price) {
            this.shuangseqiu_price = shuangseqiu_price;
        }

        public int getV_tag() {
            return v_tag;
        }

        public void setV_tag(int v_tag) {
            this.v_tag = v_tag;
        }

        public VipBean getVip() {
            return vip;
        }

        public void setVip(VipBean vip) {
            this.vip = vip;
        }

        public static class VipBean {
        }
    }
}
