package com.cnsunru.budget.mode;

import java.util.List;

/**
 * 房间预算清单详情
 * Created by WQ on 2017/9/26.
 */
public class RoomListDetails {

    /**
     * id : 280
     * calculate_project_info_id : 55
     * house_type : 4
     * house_info : {"house_info":{"length":"4.91","width":"4.91","high":"4.91","acreage":"4.91","perimeter":"4.91"},"door_info":[{"id":1,"title":"4.91","width":"4.91","length":"4.91","product_id":"4.91"}],"window_info":[{"id":1,"title":"4.91","width":"4.91","length":"4.91","product_id":"4.91"},{"id":1,"title":"4.91","width":"4.91","length":"4.91","product_id":"4.91"}],"calculate_info":{"x":4.91,"y":4.91}}
     * house_title : 客厅
     * house_all_money : 36.00
     * house_material_money : 28.00
     * house_people_money : 8.00
     * house_acreage : 0.00
     * is_hid : 0
     * is_del : 0
     */

    public HouseInfoBean house_info;
    /**
     * title : 天棚工程
     * cons_project_id : 120
     * child_list : [{"title":"平面吊顶","id":"8663","hours_type":"5","main_id":"120","cons_project_id":"122","product_id":"0","calculate_formula":"x","main_product_fee":"0.00","second_product_fee":"1.00","people_fee":"1.00","loss_fee":"1.00","level":"0","pid":"121","is_hid":"0","is_del":"0","main_people_fee":"1.00","calculate_money":"4.91","calculate_house_info_id":"55","calculate_house_list_id":"280","status":"1","cons_project_title":""},{"title":"异形吊顶","id":"8664","hours_type":"5","main_id":"120","cons_project_id":"123","product_id":"0","calculate_formula":"y","main_product_fee":"0.00","second_product_fee":"1.00","people_fee":"1.00","loss_fee":"1.00","level":"0","pid":"121","is_hid":"0","is_del":"0","main_people_fee":"1.00","calculate_money":"4.91","calculate_house_info_id":"55","calculate_house_list_id":"280","status":"1","cons_project_title":""},{"title":"石膏板灯槽","id":"8665","hours_type":"5","main_id":"120","cons_project_id":"124","product_id":"0","calculate_formula":"x","main_product_fee":"0.00","second_product_fee":"1.00","people_fee":"1.00","loss_fee":"1.00","level":"0","pid":"120","is_hid":"0","is_del":"0","main_people_fee":"1.00","calculate_money":"4.91","calculate_house_info_id":"55","calculate_house_list_id":"280","status":"1","cons_project_title":""},{"title":"平面吊顶","id":"8679","hours_type":"2","main_id":"120","cons_project_id":"122","product_id":"0","calculate_formula":"x","main_product_fee":"0.00","second_product_fee":"1.00","people_fee":"1.00","loss_fee":"1.00","level":"0","pid":"121","is_hid":"0","is_del":"0","main_people_fee":"1.00","calculate_money":"4.91","calculate_house_info_id":"55","calculate_house_list_id":"280","status":"1","cons_project_title":""},{"title":"异形吊顶","id":"8680","hours_type":"2","main_id":"120","cons_project_id":"123","product_id":"0","calculate_formula":"x","main_product_fee":"0.00","second_product_fee":"1.00","people_fee":"1.00","loss_fee":"1.00","level":"0","pid":"121","is_hid":"0","is_del":"0","main_people_fee":"1.00","calculate_money":"4.91","calculate_house_info_id":"55","calculate_house_list_id":"280","status":"1","cons_project_title":""},{"title":"石膏板灯槽","id":"8681","hours_type":"2","main_id":"120","cons_project_id":"124","product_id":"0","calculate_formula":"x","main_product_fee":"0.00","second_product_fee":"1.00","people_fee":"1.00","loss_fee":"1.00","level":"0","pid":"120","is_hid":"0","is_del":"0","main_people_fee":"1.00","calculate_money":"4.91","calculate_house_info_id":"55","calculate_house_list_id":"280","status":"1","cons_project_title":""},{"title":"平面吊顶","id":"8684","hours_type":"1","main_id":"120","cons_project_id":"122","product_id":"0","calculate_formula":"x","main_product_fee":"0.00","second_product_fee":"0.00","people_fee":"0.00","loss_fee":"0.00","level":"0","pid":"121","is_hid":"0","is_del":"0","main_people_fee":"0.00","calculate_money":"4.91","calculate_house_info_id":"55","calculate_house_list_id":"280","status":"1","cons_project_title":""},{"title":"异形吊顶","id":"8685","hours_type":"1","main_id":"120","cons_project_id":"123","product_id":"0","calculate_formula":"x","main_product_fee":"0.00","second_product_fee":"0.00","people_fee":"0.00","loss_fee":"0.00","level":"0","pid":"121","is_hid":"0","is_del":"0","main_people_fee":"0.00","calculate_money":"4.91","calculate_house_info_id":"55","calculate_house_list_id":"280","status":"1","cons_project_title":""},{"title":"石膏板灯槽","id":"8686","hours_type":"1","main_id":"120","cons_project_id":"124","product_id":"0","calculate_formula":"x","main_product_fee":"0.00","second_product_fee":"0.00","people_fee":"0.00","loss_fee":"0.00","level":"0","pid":"120","is_hid":"0","is_del":"0","main_people_fee":"0.00","calculate_money":"4.91","calculate_house_info_id":"55","calculate_house_list_id":"280","status":"1","cons_project_title":""}]
     */

    public List<ListBean> list;

    public static class HouseInfoBean {
        public String id;
        public String calculate_project_info_id;
        public String house_type;
        public String house_info;
        public String house_title;
        public String house_all_money;
        public String house_material_money;
        public String house_people_money;
        public String house_acreage;
        public String is_hid;
        public String is_del;

    }

    public static class ListBean {
        public String title;
        public String cons_project_id;
        /**
         * title : 平面吊顶
         * id : 8663
         * hours_type : 5
         * main_id : 120
         * cons_project_id : 122
         * product_id : 0
         * calculate_formula : x
         * main_product_fee : 0.00
         * second_product_fee : 1.00
         * people_fee : 1.00
         * loss_fee : 1.00
         * level : 0
         * pid : 121
         * is_hid : 0
         * is_del : 0
         * main_people_fee : 1.00
         * calculate_money : 4.91
         * calculate_house_info_id : 55
         * calculate_house_list_id : 280
         * status : 1
         * cons_project_title :
         */

        public List<AllListBean.FirstListBean.SecondListBean> child_list;

        public static class ChildListBean {
            public String title;
            public String id;
            public String hours_type;
            public String main_id;
            public String cons_project_id;
            public String product_id;
            public String calculate_formula;
            public String main_product_fee;
            public String second_product_fee;
            public String people_fee;
            public String loss_fee;
            public String level;
            public String pid;
            public String is_hid;
            public String is_del;
            public String main_people_fee;
            public String calculate_money;
            public String calculate_house_info_id;
            public String calculate_house_list_id;
            public String status;
            public String cons_project_title;
        }
    }
}
