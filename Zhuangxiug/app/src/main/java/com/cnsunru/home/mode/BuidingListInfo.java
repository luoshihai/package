package com.cnsunru.home.mode;

import java.util.List;

/**
 * Created by cnsunrun on 2017-09-18.
 */

public class BuidingListInfo {


        /**
         * count : 2
         * pages : 1
         * page :
         * list : [{"id":"1","title":"万科魅力之城一期1栋","cover":"","acreage":"0.00","price":"0.00","distance":"0.0000"},{"id":"2","title":"万科魅力之城一期1栋","cover":"","acreage":"0.00","price":"0.00","distance":"1.4690"}]
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
             * title : 万科魅力之城一期1栋
             * cover :
             * acreage : 0.00
             * price : 0.00
             * distance : 0.0000
             */

            private String id;
            private String title;
            private String cover;
            private String acreage;
            private String price;
            private String distance;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getAcreage() {
                return acreage;
            }

            public void setAcreage(String acreage) {
                this.acreage = acreage;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }
        }
}
