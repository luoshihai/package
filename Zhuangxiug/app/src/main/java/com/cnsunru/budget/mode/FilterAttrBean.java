package com.cnsunru.budget.mode;

import com.sunrun.sunrunframwork.utils.EmptyDeal;

import java.util.Iterator;
import java.util.List;

/**
 * Created by WQ on 2017/9/19.
 */

public class FilterAttrBean {
    /**
     * tilte : 风格
     * child : [{"attr_id":"2","attr_value_id":"4","title":"简约"},{"attr_id":"2","attr_value_id":"5","title":"欧式"},{"attr_id":"2","attr_value_id":"6","title":"现代"}]
     */

    public List<AttrBean> attr;
    /**
     * brand_id : 64
     * brand_name : 马可波罗
     * logo : http://www.zhuangxiu.com/Uploads/Product/Brand/2017-09-07/59b0b2c658429.jpg
     */

    public List<BrandBean> brand;

    public static class AttrBean {
        public String title;
        /**
         * attr_id : 2
         * attr_value_id : 4
         * title : 简约
         */

        public List<ChildBean> child;

        public static class ChildBean {
            public String attr_id;
            public String attr_value_id;
            public String title;

            @Override
            public String toString() {
                return String.valueOf(title);
            }
        }
    }

    public static class BrandBean {
        public String brand_id;
        public String brand_name;
        public String logo;
        @Override
        public String toString() {
            return String.valueOf(brand_name);
        }
    }



    public List<AttrBean> getAttNotEmpty(){
        List<AttrBean> attr = this.attr;
        if(attr !=null){
            Iterator<AttrBean> iterator = attr.iterator();
            while (iterator.hasNext()){
                AttrBean next = iterator.next();
                if(EmptyDeal.isEmpy(next.child))iterator.remove();
            }
        }
        return attr;
    }
//    /**
//     * attr_id : 2
//     * attr_value_id : 6
//     * title : 现代
//     */
//    public String attr_id;
//    public String attr_value_id;
//    public String title;


}
