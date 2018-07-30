package com.saadsdasd.niuniu.model;

/**
 * Created by QYQ on 2017/9/25.
 */

public class MyCommentModel {

    /**
     * comment : {"author_id":10943190,"content":"55","id":11138660,"message_id":2481266,"publish_time":1506303471000,"reply_to":"","reply_to_comment_id":0,"user":{"create_time":1505992473000,"daletou_price":0,"device_id":"B940CBF4EBC7BA2BX3NR2IIN6ZIGJT5XT9W6P924J2M9H9TZ264F6S2HIYNMT689","fucai3d_price":0,"icon":"https://p.zhangkongshidai.cn/usericon_default.jpg","id":10943190,"install_channel":"ssq","name":"sndroifd","pailie3_price":0,"qilecai_price":0,"reg_ip":"183.14.29.120","shuangseqiu_price":0,"v_tag":0,"vip":{}}}
     * status : 0
     */

    private CommentBean comment;
    private String status;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CommentBean getComment() {
        return comment;
    }

    public void setComment(CommentBean comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class CommentBean {
        /**
         * author_id : 10943190
         * content : 55
         * id : 11138660
         * message_id : 2481266
         * publish_time : 1506303471000
         * reply_to :
         * reply_to_comment_id : 0
         * user : {"create_time":1505992473000,"daletou_price":0,"device_id":"B940CBF4EBC7BA2BX3NR2IIN6ZIGJT5XT9W6P924J2M9H9TZ264F6S2HIYNMT689","fucai3d_price":0,"icon":"https://p.zhangkongshidai.cn/usericon_default.jpg","id":10943190,"install_channel":"ssq","name":"sndroifd","pailie3_price":0,"qilecai_price":0,"reg_ip":"183.14.29.120","shuangseqiu_price":0,"v_tag":0,"vip":{}}
         */

        private int author_id;
        private String content;
        private int id;
        private int message_id;
        private long publish_time;
        private String reply_to;
        private int reply_to_comment_id;
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

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * create_time : 1505992473000
             * daletou_price : 0
             * device_id : B940CBF4EBC7BA2BX3NR2IIN6ZIGJT5XT9W6P924J2M9H9TZ264F6S2HIYNMT689
             * fucai3d_price : 0
             * icon : https://p.zhangkongshidai.cn/usericon_default.jpg
             * id : 10943190
             * install_channel : ssq
             * name : sndroifd
             * pailie3_price : 0
             * qilecai_price : 0
             * reg_ip : 183.14.29.120
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
}
