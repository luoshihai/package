package com.cnsunru.user.mode;

/**
 * Created by WQ on 2017/10/14.
 */

public class MyProjectBean {

    /**
     * "id": "388", 订单ID
     * "member_id": "69",//用户ID
     * "project_all_money": "39912.76", 订单总价格
     * "material_all_money": "20512.90",
     * "people_all_money": "19400.78",
     * "area_id": "1710",
     * "acreage": "70.20",
     * "house_pattern": "["3","2","21","2"]",
     * "title": "新加的项目", 订单名称
     * "status": "1",
     * "is_hid": "0",
     * "is_del": "0",
     * "add_time": "2017-10-14 16:34:13", 订单时间
     * "is_need_calculate": "1",  是否需要重新计算
     * "is_pay": "0",  是否支付  0 ： 未支付  1： 已支付
     * "order_no": "2017101454985349",  订单号
     * "change_num": "0"   订单产品修改数量
     */

    public String id;
    public String member_id;
    public String project_all_money;
    public String material_all_money;
    public String people_all_money;
    public String area_id;
    public String acreage;
    public String title;
    public String status;
    public String is_hid;
    public String is_del;
    public String add_time;
    public String is_need_calculate;
    public int is_pay;
    public String order_no;
    public String change_num;

    public boolean isNeedCalculate() {
        return "1".equals(is_need_calculate);
    }
}
