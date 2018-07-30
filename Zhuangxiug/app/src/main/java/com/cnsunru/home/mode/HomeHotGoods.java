package com.cnsunru.home.mode;

import java.util.List;

/**
 * Created by WQ on 2017/9/18.
 */

public class HomeHotGoods {

    /**
     * id : 7
     * title : 威利斯瓷砖 厨房墙砖卫生间阳台地砖美式田园仿古砖地板砖300X300 1㎡
     * image : http://www.zhuangxiu.com/Uploads/Product/Image/2017-09-11/59b5e23838527.jpg
     * description : 威利斯瓷砖 厨房墙砖卫生间阳台地砖美式田园仿古砖地板砖300X300 1㎡
     * market_price : 99.00
     * price : 71.00
     * brand_id : 64
     * category_id : 104
     * sub_category_id : 105
     * explosion_price : 69.00
     * explosion_inventory : 10
     * is_explosion : 1
     * brand_name : 马可波罗
     */

    public String id;
    public String title;
    public String image;
    public String description;
    public String market_price;
    public String price;
    public String brand_id;
    public String category_id;
    public String sub_category_id;
    public String explosion_price;
    public String explosion_inventory;
    public String is_explosion;
    public String brand_name;
    public String limited_price;

    public String getPrice(){
        String []prices={limited_price,explosion_price,price};
        for (String price : prices) {
            if(price!=null)return price;
        }
        return null;
    }

    public static class ListGoods{
    public     List<HomeHotGoods>list;
    }
}
