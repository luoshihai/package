package com.cnsunru.order.mode;

import java.util.List;

/**
 * Created by WQ on 2017/9/20.
 */

public class GoodsDetails {

    /**
     * id : 8
     * title : 塞纳春天瓷砖 全抛釉地砖客厅防滑地板砖 8001 800*800
     * description : 塞纳春天瓷砖 全抛釉地砖客厅防滑地板砖 8001 800*800
     * content : <p>塞纳春天瓷砖 全抛釉地砖客厅防滑地板砖 8001 800*800</p>
     * price : 99.00
     * cost_price : 69.00
     * width : 800.0
     * height : 0.0
     * image : http://www.zhuangxiu.com/Uploads/Product/Image/2017-09-13/59b89ae877aed.jpg
     * is_limited : 0
     * is_explosion : 0
     * limited_price : 0.00
     * explosion_price : 0.00
     * is_custom : 0
     * brand_name : 马可波罗
     * sku_list : [{"id":"4967","product_id":"8","market_price":"199.00","price":"99.00","inventory":"100","spec_value":"2394","image":"http://www.zhuangxiu.com/Uploads/Product/Image/2017-09-13/59b89ae877aed.jpg","limited":"0","limited_price":"0.00","limited_inventory":"0"},{"id":"4968","product_id":"8","market_price":"199.00","price":"99.00","inventory":"100","spec_value":"2395","image":"http://www.zhuangxiu.com/Uploads/Product/Image/2017-09-13/59b89ae877aed.jpg","limited":"0","limited_price":"0.00","limited_inventory":"0"},{"id":"4969","product_id":"8","market_price":"199.00","price":"99.00","inventory":"100","spec_value":"2396","image":"http://www.zhuangxiu.com/Uploads/Product/Image/2017-09-13/59b89ae877aed.jpg","limited":"0","limited_price":"0.00","limited_inventory":"0"}]
     * spec : [{"title":"风格","id":"71","value":[{"id":"2394","spec_id":"71","title":"现代"},{"id":"2395","spec_id":"71","title":"古典"},{"id":"2396","spec_id":"71","title":"简约"}]}]
     */

    public String id;
    public String title;
    public String description;
    public String content;
    public String price;
    public String cost_price;
    public String width;
    public String height;
    public String image;
    public String is_limited;
    public String is_explosion;
    public String limited_price;
    public String explosion_price;
    public String is_custom;
    public String brand_name="无";
    public String category_id;

    /**
     * id : 4967
     * product_id : 8
     * market_price : 199.00
     * price : 99.00
     * inventory : 100
     * spec_value : 2394
     * image : http://www.zhuangxiu.com/Uploads/Product/Image/2017-09-13/59b89ae877aed.jpg
     * limited : 0
     * limited_price : 0.00
     * limited_inventory : 0
     */

    public List<SkuListBean> sku_list;
    /**
     * title : 风格
     * id : 71
     * value : [{"id":"2394","spec_id":"71","title":"现代"},{"id":"2395","spec_id":"71","title":"古典"},{"id":"2396","spec_id":"71","title":"简约"}]
     */

    public List<SpecBean> spec;
    /**
     * title : 未命名工程
     * id : 8
     * house_type : [{"id":"16","house_type":"3","house_title":"次卧","calculate_project_info_id":"8"},{"id":"17","house_type":"5","house_title":"客厅","calculate_project_info_id":"8"},{"id":"18","house_type":"9","house_title":"西厨","calculate_project_info_id":"8"},{"id":"19","house_type":"16","house_title":"阳台2","calculate_project_info_id":"8"},{"id":"20","house_type":"19","house_title":"露台2","calculate_project_info_id":"8"}]
     */

    public List<HouseProjectBean> peoject_list;

    public String getBrand_name() {
        return brand_name==null?"无":brand_name;
    }

    public static class SkuListBean {
        public String id;
        public String product_id;
        public String market_price;
        public String price;
        public String inventory;
        public String spec_value;
        public String image;
        public String limited;
        public String limited_price;
        public String limited_inventory;
    }

    public static class SpecBean {
        public String title;
        public String id;
        /**
         * id : 2394
         * spec_id : 71
         * title : 现代
         */

        public List<ValueBean> value;

        public static class ValueBean {
            public String id;
            public String spec_id;
            public String title;
        }
    }

    public static class HouseProjectBean {
        public String title;
        public String id;
        /**
         * id : 16
         * house_type : 3
         * house_title : 次卧
         * calculate_project_info_id : 8
         */

        public List<HouseTypeBean> house_type;

        public static class HouseTypeBean {
            public String id;
            public String house_type;
            public String house_title;
            public String calculate_project_info_id;
        }
    }
}
