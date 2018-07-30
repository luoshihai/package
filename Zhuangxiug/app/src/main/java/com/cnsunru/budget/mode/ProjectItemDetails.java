package com.cnsunru.budget.mode;

import java.util.List;

/**
 * 工程详情
 * Created by WQ on 2017/9/25.
 */

public class ProjectItemDetails {

    /**
     * id : 446
     * title : 石膏板灯槽
     * choose_list : [{"id":"447","title":"平面吊顶","status":"1"},{"id":"448","title":"异形吊顶","status":"1"}]
     */

    public ChooseInfoBean choose_info;
    /**
     * id : 1
     * title : 地膜
     * standard : 1000*1000mm
     * brand : 星星
     * price : ￥100
     */

    public List<ProductListBean> product_list;

    public static class ChooseInfoBean {
        public String id;
        public String title;
        /**
         * id : 447
         * title : 平面吊顶
         * status : 1
         */

        public List<ChooseListBean> choose_list;

        public static class ChooseListBean {
            public String id;
            public String title;
            public String status;
        }
    }

    public static class ProductListBean {
        public String id;
        public String title;
        public String standard;
        public String brand;
        public String price;
    }
}
