package com.saadsdasd.niuniu.model;

/**
 * Created by QYQ on 2017/9/21.
 */

public class LoginModel {

    /**
     * data : {"coupon_amount":0,"has_password":true,"profile":{"fans_count":0,"follow_count":0,"phone":"13823269614","ups":0,"user_id":10943190},"sid":"f62a6d01f885c763236c5bc136b5a4aacebe0a1f84c380b712170ebf3644b710","token":"28175fdd-add5-4903-bcca-04dc34952508","user":{"create_time":1505992473,"daletou_price":0,"device_id":"B940CBF4EBC7BA2BX3NR2IIN6ZIGJT5XT9W6P924J2M9H9TZ264F6S2HIYNMT689","fucai3d_price":0,"icon":"https://p.zhangkongshidai.cn/usericon_default.jpg","id":10943190,"install_channel":"ssq","name":"sndroifd","pailie3_price":0,"qilecai_price":0,"reg_ip":"183.14.29.120","shuangseqiu_price":0,"v_tag":0},"vip":null}
     * status : 0
     */

    private DataBean data;
    private String status;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * coupon_amount : 0
         * has_password : true
         * profile : {"fans_count":0,"follow_count":0,"phone":"13823269614","ups":0,"user_id":10943190}
         * sid : f62a6d01f885c763236c5bc136b5a4aacebe0a1f84c380b712170ebf3644b710
         * token : 28175fdd-add5-4903-bcca-04dc34952508
         * user : {"create_time":1505992473,"daletou_price":0,"device_id":"B940CBF4EBC7BA2BX3NR2IIN6ZIGJT5XT9W6P924J2M9H9TZ264F6S2HIYNMT689","fucai3d_price":0,"icon":"https://p.zhangkongshidai.cn/usericon_default.jpg","id":10943190,"install_channel":"ssq","name":"sndroifd","pailie3_price":0,"qilecai_price":0,"reg_ip":"183.14.29.120","shuangseqiu_price":0,"v_tag":0}
         * vip : null
         */

        private int coupon_amount;
        private boolean has_password;
        private ProfileBean profile;
        private String sid;
        private String token;
        private UserBean user;
        private Object vip;

        public int getCoupon_amount() {
            return coupon_amount;
        }

        public void setCoupon_amount(int coupon_amount) {
            this.coupon_amount = coupon_amount;
        }

        public boolean isHas_password() {
            return has_password;
        }

        public void setHas_password(boolean has_password) {
            this.has_password = has_password;
        }

        public ProfileBean getProfile() {
            return profile;
        }

        public void setProfile(ProfileBean profile) {
            this.profile = profile;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public Object getVip() {
            return vip;
        }

        public void setVip(Object vip) {
            this.vip = vip;
        }

        public static class ProfileBean {
            /**
             * fans_count : 0
             * follow_count : 0
             * phone : 13823269614
             * ups : 0
             * user_id : 10943190
             */

            private int fans_count;
            private int follow_count;
            private String phone;
            private int ups;
            private int user_id;

            public int getFans_count() {
                return fans_count;
            }

            public void setFans_count(int fans_count) {
                this.fans_count = fans_count;
            }

            public int getFollow_count() {
                return follow_count;
            }

            public void setFollow_count(int follow_count) {
                this.follow_count = follow_count;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public int getUps() {
                return ups;
            }

            public void setUps(int ups) {
                this.ups = ups;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }
        }

        public static class UserBean {
            /**
             * create_time : 1505992473
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
             */

            private int create_time;
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

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
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
        }
    }
}
