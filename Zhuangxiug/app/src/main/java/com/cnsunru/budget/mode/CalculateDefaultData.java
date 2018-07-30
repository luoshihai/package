package com.cnsunru.budget.mode;

import java.util.List;

/**
 * Created by cnsunrun on 2017-09-20.
 */

public class CalculateDefaultData {

    public List<String> hours_type;
    /**
         * hours_type_txt : 几室几厅几卫几阳台
         * acreage : 80
         * area_txt : 武汉
         */


        private String hours_type_txt;
        private String acreage;
        private String area_txt;

        public String getHours_type_txt() {
            return hours_type_txt;
        }

        public void setHours_type_txt(String hours_type_txt) {
            this.hours_type_txt = hours_type_txt;
        }

        public String getAcreage() {
            return acreage;
        }

        public void setAcreage(String acreage) {
            this.acreage = acreage;
        }

        public String getArea_txt() {
            return area_txt;
        }

        public void setArea_txt(String area_txt) {
            this.area_txt = area_txt;
        }
}
