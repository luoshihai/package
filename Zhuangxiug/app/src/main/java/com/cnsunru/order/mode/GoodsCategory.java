package com.cnsunru.order.mode;

import java.util.List;

/**
 * Created by WQ on 2017/9/18.
 */

public class GoodsCategory {

    /**
     * title : 墙地面材料
     * image :
     * pid : 0
     * id : 104
     * _child : [{"title":"木材","image":"","pid":"104","id":"106"},{"title":"瓷砖","image":"","pid":"104","id":"105"}]
     */

    public String title;
    public String image;
    public String pid;
    public String id;
    /**
     * title : 木材
     * image :
     * pid : 104
     * id : 106
     */

    public List<ChildBean> _child;

    public static class ChildBean {
        public String title;
        public String image;
        public String pid;
        public String id;
    }
}
