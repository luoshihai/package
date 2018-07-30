package com.cnsunru.budget.mode;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.annotations.SerializedName;
import com.sunrun.sunrunframwork.utils.EmptyDeal;

import java.util.ArrayList;
import java.util.List;

/**
 * 【预算】查看全部清单
 * Created by WQ on 2017/9/25.
 */

public class AllListBean {

    /**
     * id : 35
     * house_type : 5
     * house_title : 卧室
     * house_all_money : 816.00
     * first_list : [[{"title":"天棚工程","id":"445","cons_project_id":"104","calculate_house_list_id":"35","second_list":[[{"title":"平面吊顶","id":"447","hours_type":"5","main_id":"104","cons_project_id":"106","product_id":"0","calculate_formula":"x","main_product_fee":"0.00","second_product_fee":"100.00","people_fee":"100.00","loss_fee":"100.00","level":"0","pid":"105","is_hid":"0","is_del":"0","main_people_fee":"100.00","calculate_money":"0.00","calculate_house_info_id":"22","calculate_house_list_id":"35","status":"1"},{"title":"异形吊顶","id":"448","hours_type":"5","main_id":"104","cons_project_id":"107","product_id":"0","calculate_formula":"x","main_product_fee":"0.00","second_product_fee":"100.00","people_fee":"100.00","loss_fee":"100.00","level":"0","pid":"105","is_hid":"0","is_del":"0","main_people_fee":"100.00","calculate_money":"0.00","calculate_house_info_id":"22","calculate_house_list_id":"35","status":"1"}]]},{"title":"墙面工程","id":"449","cons_project_id":"109","calculate_house_list_id":"35","second_list":[[{"title":"墙顶面基层处理","id":"450","hours_type":"5","main_id":"109","cons_project_id":"110","product_id":"0","calculate_formula":"y","main_product_fee":"0.00","second_product_fee":"1.00","people_fee":"1.00","loss_fee":"1.00","level":"0","pid":"109","is_hid":"0","is_del":"0","main_people_fee":"1.00","calculate_money":"0.00","calculate_house_info_id":"22","calculate_house_list_id":"35","status":"1"},{"title":"粉刷石膏打底","id":"451","hours_type":"5","main_id":"109","cons_project_id":"111","product_id":"0","calculate_formula":"y","main_product_fee":"0.00","second_product_fee":"1.00","people_fee":"1.00","loss_fee":"1.00","level":"0","pid":"109","is_hid":"0","is_del":"0","main_people_fee":"1.00","calculate_money":"0.00","calculate_house_info_id":"22","calculate_house_list_id":"35","status":"1"},{"title":"墙顶面浅色乳胶漆","id":"452","hours_type":"5","main_id":"109","cons_project_id":"112","product_id":"0","calculate_formula":"y","main_product_fee":"0.00","second_product_fee":"1.00","people_fee":"1.00","loss_fee":"1.00","level":"0","pid":"109","is_hid":"0","is_del":"0","main_people_fee":"1.00","calculate_money":"0.00","calculate_house_info_id":"22","calculate_house_list_id":"35","status":"1"},{"title":"实木地脚线","id":"453","hours_type":"5","main_id":"109","cons_project_id":"113","product_id":"0","calculate_formula":"y","main_product_fee":"0.00","second_product_fee":"1.00","people_fee":"1.00","loss_fee":"1.00","level":"0","pid":"109","is_hid":"0","is_del":"0","main_people_fee":"1.00","calculate_money":"0.00","calculate_house_info_id":"22","calculate_house_list_id":"35","status":"1"}]]}]]
     */

    public String id;
    public String house_type;
    public String house_title;
    public String house_all_money;
    public String house_acreage;
    public HouseInfoBean house_info;
    /**
     * title : 天棚工程
     * id : 445
     * cons_project_id : 104
     * calculate_house_list_id : 35
     * second_list : [[{"title":"平面吊顶","id":"447","hours_type":"5","main_id":"104","cons_project_id":"106","product_id":"0","calculate_formula":"x","main_product_fee":"0.00","second_product_fee":"100.00","people_fee":"100.00","loss_fee":"100.00","level":"0","pid":"105","is_hid":"0","is_del":"0","main_people_fee":"100.00","calculate_money":"0.00","calculate_house_info_id":"22","calculate_house_list_id":"35","status":"1"},{"title":"异形吊顶","id":"448","hours_type":"5","main_id":"104","cons_project_id":"107","product_id":"0","calculate_formula":"x","main_product_fee":"0.00","second_product_fee":"100.00","people_fee":"100.00","loss_fee":"100.00","level":"0","pid":"105","is_hid":"0","is_del":"0","main_people_fee":"100.00","calculate_money":"0.00","calculate_house_info_id":"22","calculate_house_list_id":"35","status":"1"}]]
     */
    private List<FirstListBean> first_list;
    private List<FirstListBean> list;

    public List<FirstListBean> getFirst_list() {
        return first_list==null?list:first_list;
    }

    public static class FirstListBean implements MultiItemEntity {
        public AllListBean parentInfo;
        public String title;
        public String id;
        public String cons_project_id;
        public String calculate_house_list_id;

        /**
         * title : 平面吊顶
         * id : 447
         * hours_type : 5
         * main_id : 104
         * cons_project_id : 106
         * product_id : 0
         * calculate_formula : x
         * main_product_fee : 0.00
         * second_product_fee : 100.00
         * people_fee : 100.00
         * loss_fee : 100.00
         * level : 0
         * pid : 105
         * is_hid : 0
         * is_del : 0
         * main_people_fee : 100.00
         * calculate_money : 0.00
         * calculate_house_info_id : 22
         * calculate_house_list_id : 35
         * status : 1
         */
        private List<SecondListBean> second_list;
        private List<SecondListBean> child_list;

        public List<SecondListBean> getSecond_list() {
            return second_list==null?child_list:second_list;
        }

        @Override
        public int getItemType() {
            return 0;
        }

        public static class SecondListBean implements MultiItemEntity {
            public FirstListBean parent;
            public String title;
            public String id;
            public String hours_type;
            public String main_id;
            public String cons_project_id;
            public String product_id;
            public String calculate_formula;
            public String calculate_num;

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
            public String list_unit;
            public String icon;
            @Override
            public int getItemType() {
                return 0;
            }
        }
    }
    public static class HouseInfoBean {
        public String id;
        public String calculate_project_info_id;
        public String house_type;
//        public String house_info;
        public String house_title;
        public String house_all_money;
        public String house_material_money;
        public String house_people_money;
        public String house_acreage;
        public String is_hid;
        public String is_del;



    }
    /**
     * (整理数据层次)
     * @param allListBeans
     * @return
     */
    public static List<MultiItemEntity> converList(List<AllListBean> allListBeans) {
        ArrayList mData = new ArrayList<>();
        if(allListBeans!=null)
        for (int i = 0, len = allListBeans.size(); i < len; i++) {
            AllListBean allListBean = allListBeans.get(i);
            List<AllListBean.FirstListBean> first_list = allListBean.getFirst_list();
            if (!EmptyDeal.isEmpy(first_list)) {
                first_list.get(0).parentInfo = allListBean;//设置父级的信息,只给第一个设置,作为一级标题信息
                mData.addAll(first_list);//添加到列表集合里边
            }
        }
        return mData;
    }
}
