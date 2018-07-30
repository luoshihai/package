package com.hhhh.pailiesan.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by QYQ on 2017/9/11.
 */

public class TabThreeModel implements Serializable{


    /**
     * allow_comment : 1
     * author_id : 9966450
     * comment_count : 77
     * content : 👍👍👍👍👍👍👍👍👍👍👍差一点
     * device_info :
     * id : 2470220
     * images : ["http://p.zhangkongshidai.cn/20170921/f82bb972bb10a3a04c5bcfa11b0b5451.jpg"]
     * is_check : 0
     * key_name :
     * lottery_key : daletou
     * position : {"country":"None","district":"None","id":195390,"landmarker":"新疆维吾尔自治区昌吉回族自治州昌吉市","latitude":0,"longitude":0,"province":"None","township":"None"}
     * position_id : 195390
     * predict_data :
     * publish_time : 1505967807000
     * score : 0.0
     * share_count : 0
     * sort : 0
     * state : 1
     * thumb_up_count : 269
     * topic_id : 0
     * type : 0
     * up_time : 1505999543000
     * user : {"create_time":1499525058000,"daletou_price":0,"device_id":"2BE5CCF18359CA3K9QVA0U2XWD6TRY6IQABVYDTJK737TH3Q5E85PA96AJDM7KGK","fucai3d_price":0,"icon":"http://p.zhangkongshidai.cn/20170901/0955EF0EAAA78D9A9DB6C8D1CDE6E050.jpg","id":9966450,"install_channel":"None","name":"亚达西","pailie3_price":0,"qilecai_price":0,"reg_ip":"120.69.49.4","shuangseqiu_price":0,"v_tag":0,"vip":{}}
     * win_info :
     */

    private int allow_comment;
    private int author_id;
    private int comment_count;
    private String content;
    private String device_info;
    private int id;
    private int is_check;
    private String key_name;
    private String lottery_key;
    private PositionBean position;
    private int position_id;
    private String predict_data;
    private long publish_time;
    private String score;
    private int share_count;
    private int sort;
    private int state;
    private int thumb_up_count;
    private int topic_id;
    private int type;
    private long up_time;
    private UserBean user;
    private String win_info;
    private List<String> images;

    public int getAllow_comment() {
        return allow_comment;
    }

    public void setAllow_comment(int allow_comment) {
        this.allow_comment = allow_comment;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIs_check() {
        return is_check;
    }

    public void setIs_check(int is_check) {
        this.is_check = is_check;
    }

    public String getKey_name() {
        return key_name;
    }

    public void setKey_name(String key_name) {
        this.key_name = key_name;
    }

    public String getLottery_key() {
        return lottery_key;
    }

    public void setLottery_key(String lottery_key) {
        this.lottery_key = lottery_key;
    }

    public PositionBean getPosition() {
        return position;
    }

    public void setPosition(PositionBean position) {
        this.position = position;
    }

    public int getPosition_id() {
        return position_id;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
    }

    public String getPredict_data() {
        return predict_data;
    }

    public void setPredict_data(String predict_data) {
        this.predict_data = predict_data;
    }

    public long getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(long publish_time) {
        this.publish_time = publish_time;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getShare_count() {
        return share_count;
    }

    public void setShare_count(int share_count) {
        this.share_count = share_count;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
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

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getUp_time() {
        return up_time;
    }

    public void setUp_time(long up_time) {
        this.up_time = up_time;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getWin_info() {
        return win_info;
    }

    public void setWin_info(String win_info) {
        this.win_info = win_info;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public static class PositionBean implements Serializable{
        /**
         * country : None
         * district : None
         * id : 195390
         * landmarker : 新疆维吾尔自治区昌吉回族自治州昌吉市
         * latitude : 0
         * longitude : 0
         * province : None
         * township : None
         */

        private String country;
        private String district;
        private int id;
        private String landmarker;
        private int latitude;
        private int longitude;
        private String province;
        private String township;

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLandmarker() {
            return landmarker;
        }

        public void setLandmarker(String landmarker) {
            this.landmarker = landmarker;
        }

        public int getLatitude() {
            return latitude;
        }

        public void setLatitude(int latitude) {
            this.latitude = latitude;
        }

        public int getLongitude() {
            return longitude;
        }

        public void setLongitude(int longitude) {
            this.longitude = longitude;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getTownship() {
            return township;
        }

        public void setTownship(String township) {
            this.township = township;
        }
    }

    public static class UserBean implements Serializable{
        /**
         * create_time : 1499525058000
         * daletou_price : 0
         * device_id : 2BE5CCF18359CA3K9QVA0U2XWD6TRY6IQABVYDTJK737TH3Q5E85PA96AJDM7KGK
         * fucai3d_price : 0
         * icon : http://p.zhangkongshidai.cn/20170901/0955EF0EAAA78D9A9DB6C8D1CDE6E050.jpg
         * id : 9966450
         * install_channel : None
         * name : 亚达西
         * pailie3_price : 0
         * qilecai_price : 0
         * reg_ip : 120.69.49.4
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
