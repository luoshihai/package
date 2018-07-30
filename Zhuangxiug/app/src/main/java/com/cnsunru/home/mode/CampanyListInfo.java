package com.cnsunru.home.mode;

import java.util.List;

/**
 * Created by cnsunrun on 2017-09-19.
 */

public class CampanyListInfo {

        /**
         * count : 1
         * pages : 1
         * page :
         * list : [{"id":"1","company_name":"武汉XXX装饰工程有限公司","image":"","design_num":"0","distance":"0.3920"}]
         */

        private String count;
        private int pages;
        private String page;
        private List<ListBean> list;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1
             * company_name : 武汉XXX装饰工程有限公司
             * image :
             * design_num : 0
             * distance : 0.3920
             */

            private String id;
            private String company_name;
            private String image;
            private String design_num;
            private String distance;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getDesign_num() {
                return design_num;
            }

            public void setDesign_num(String design_num) {
                this.design_num = design_num;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }
        }
}
