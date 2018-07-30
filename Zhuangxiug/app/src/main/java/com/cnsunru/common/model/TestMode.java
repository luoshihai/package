package com.cnsunru.common.model;

import java.util.List;

/**
 * Created by WQ on 2017/9/21.
 */

public class TestMode {

    /**
     * save_path : http://test.cnsunrun.com/wuye/Uploads/Banner/59bf31ceb2b1d.png
     * url :
     */

    public List<AdBean> ad;
    /**
     * id : 7
     * title : 灌者为王
     * icon : http://test.cnsunrun.com/wuye/Uploads/Forum/Forum/599ab56116b19.png
     */

    public List<ForumBean> forum;
    /**
     * id : 136
     * forum_id : 5
     * member_id : 19
     * member_nickname : 123
     * title : 其实我很美丽
     * content : 测试测试测试测试测试测试测试测试测试测试测试测试
     * views : 8
     * replies : 0
     * likes : 0
     * lastpost_time : 昨天13:04
     * add_time : 昨天13:04
     * image_list : []
     * forum_title : 贴图自拍
     * avatar : http://test.cnsunrun.com/wuye/Uploads/Headimg/000/00/00/H_19_M.jpg?time=1505988247
     */

    public List<ListBean> list;

    public static class AdBean {
        public String save_path;
        public String url;
    }

    public static class ForumBean {
        public String id;
        public String title;
        public String icon;
    }

    public static class ListBean {
        public String id;
        public String forum_id;
        public String member_id;
        public String member_nickname;
        public String title;
        public String content;
        public String views;
        public String replies;
        public String likes;
        public String lastpost_time;
        public String add_time;
        public String forum_title;
        public String avatar;
        public List<?> image_list;
    }
}
