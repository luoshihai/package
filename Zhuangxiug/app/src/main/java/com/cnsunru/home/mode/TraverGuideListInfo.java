package com.cnsunru.home.mode;

import java.util.List;

/**
 * Created by cnsunrun on 2017-09-18.
 */

public class TraverGuideListInfo {


        /**
         * count : 1
         * pages : 1
         * page :
         * list : [{"id":"9","title":"卖房反悔被索赔155万余元 毁约要承担责任的","description":"","cover":"http://www.zhuangxiu.com/Uploads/News/59b8ed551cb9d.png","category_id":"12","views":"0","add_time":"2016-11-08 11:43:32","category":"选材攻略"}]
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
             * id : 9
             * title : 卖房反悔被索赔155万余元 毁约要承担责任的
             * description :
             * cover : http://www.zhuangxiu.com/Uploads/News/59b8ed551cb9d.png
             * category_id : 12
             * views : 0
             * add_time : 2016-11-08 11:43:32
             * category : 选材攻略
             */

            private String id;
            private String title;
            private String description;
            private String cover;
            private String category_id;
            private String views;
            private String add_time;
            private String category;

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

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getViews() {
                return views;
            }

            public void setViews(String views) {
                this.views = views;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }
        }
}
