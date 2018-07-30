package com.cnsunru.budget.mode;

import java.util.List;

/**
 * Created by cnsunrun on 2017-09-22.
 */

public class DefaltCalculateInfo {

        /**
         * id : 35
         * project_all_money : 180.00
         * house_pattern : {"5":1,"4":2,"3":2,"2":1,"1":1}
         * material_all_money : 120.00
         * people_all_money : 60.00
         * area_id : 1701
         * acreage : 80.00
         * title : 工程量清单
         * status : 0
         * area_txt :
         * hours_type_txt : 几室几厅几卫几阳台
         * list : [{"id":"132","calculate_project_info_id":"35","house_type":"5","house_title":"卧室","house_all_money":"44.00","house_material_money":"22.00","house_people_money":"22.00"},{"id":"133","calculate_project_info_id":"35","house_type":"2","house_title":"卫生间","house_all_money":"12.00","house_material_money":"6.00","house_people_money":"6.00"},{"id":"134","calculate_project_info_id":"35","house_type":"1","house_title":"阳台","house_all_money":"0.00","house_material_money":"0.00","house_people_money":"0.00"},{"id":"135","calculate_project_info_id":"35","house_type":"3","house_title":"厨房","house_all_money":"26.00","house_material_money":"18.00","house_people_money":"8.00"},{"id":"136","calculate_project_info_id":"35","house_type":"3","house_title":"厨房","house_all_money":"26.00","house_material_money":"18.00","house_people_money":"8.00"},{"id":"137","calculate_project_info_id":"35","house_type":"4","house_title":"客厅","house_all_money":"36.00","house_material_money":"28.00","house_people_money":"8.00"},{"id":"138","calculate_project_info_id":"35","house_type":"4","house_title":"客厅","house_all_money":"36.00","house_material_money":"28.00","house_people_money":"8.00"}]
         */

        private String id;
        private String project_all_money;
        private String house_pattern;
        private String material_all_money;
        private String people_all_money;
        private String area_id;
        private String acreage;
        private String title;
        private String status;
        private String area_txt;
        private String hours_type_txt;
        private List<ListBean> list;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProject_all_money() {
            return project_all_money;
        }

        public void setProject_all_money(String project_all_money) {
            this.project_all_money = project_all_money;
        }

        public String getHouse_pattern() {
            return house_pattern;
        }

        public void setHouse_pattern(String house_pattern) {
            this.house_pattern = house_pattern;
        }

        public String getMaterial_all_money() {
            return material_all_money;
        }

        public void setMaterial_all_money(String material_all_money) {
            this.material_all_money = material_all_money;
        }

        public String getPeople_all_money() {
            return people_all_money;
        }

        public void setPeople_all_money(String people_all_money) {
            this.people_all_money = people_all_money;
        }

        public String getArea_id() {
            return area_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public String getAcreage() {
            return acreage;
        }

        public void setAcreage(String acreage) {
            this.acreage = acreage;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getArea_txt() {
            return area_txt;
        }

        public void setArea_txt(String area_txt) {
            this.area_txt = area_txt;
        }

        public String getHours_type_txt() {
            return hours_type_txt;
        }

        public void setHours_type_txt(String hours_type_txt) {
            this.hours_type_txt = hours_type_txt;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 132
             * calculate_project_info_id : 35
             * house_type : 5
             * house_title : 卧室
             * house_all_money : 44.00
             * house_material_money : 22.00
             * house_people_money : 22.00
             */

            private String id;
            private String calculate_project_info_id;
            private String house_type;
            private String house_title;
            private String house_all_money;
            private String house_material_money;
            private String house_people_money;
            private String house_acreage;

            public String getHouse_acreage() {
                return house_acreage;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCalculate_project_info_id() {
                return calculate_project_info_id;
            }

            public void setCalculate_project_info_id(String calculate_project_info_id) {
                this.calculate_project_info_id = calculate_project_info_id;
            }

            public String getHouse_type() {
                return house_type;
            }

            public void setHouse_type(String house_type) {
                this.house_type = house_type;
            }

            public String getHouse_title() {
                return house_title;
            }

            public void setHouse_title(String house_title) {
                this.house_title = house_title;
            }

            public String getHouse_all_money() {
                return house_all_money;
            }

            public void setHouse_all_money(String house_all_money) {
                this.house_all_money = house_all_money;
            }

            public String getHouse_material_money() {
                return house_material_money;
            }

            public void setHouse_material_money(String house_material_money) {
                this.house_material_money = house_material_money;
            }

            public String getHouse_people_money() {
                return house_people_money;
            }

            public void setHouse_people_money(String house_people_money) {
                this.house_people_money = house_people_money;
            }
        }
}
