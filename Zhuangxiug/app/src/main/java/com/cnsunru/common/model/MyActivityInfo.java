package com.cnsunru.common.model;

import java.util.List;

/**
 * Created by cnsunrun on 2017-09-18.
 */

public class MyActivityInfo {

        /**
         * count : 1
         * pages : 1
         * page :
         * list : [{"id":"1","title":"最新活动","start_date":"2017-08-28","end_date":"2017-09-29","price":"10000.00","cover":"http://192.168.1.123/zhuangxiuapp/code/Uploads/Ativity/59b8edc2b4af1.png"}]
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
             * title : 最新活动
             * start_date : 2017-08-28
             * end_date : 2017-09-29
             * price : 10000.00
             * cover : http://192.168.1.123/zhuangxiuapp/code/Uploads/Ativity/59b8edc2b4af1.png
             */

            private String id;
            private String pid;
            private String title;
            private String start_date;
            private String end_date;
            private String price;
            private String cover;
            private int is_sign;

            public int getIs_sign() {
                return is_sign;
            }

            public String getId() {
                return id==null?pid:id;
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

            public String getStart_date() {
                return start_date;
            }

            public void setStart_date(String start_date) {
                this.start_date = start_date;
            }

            public String getEnd_date() {
                return end_date;
            }

            public void setEnd_date(String end_date) {
                this.end_date = end_date;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }
        }
}
