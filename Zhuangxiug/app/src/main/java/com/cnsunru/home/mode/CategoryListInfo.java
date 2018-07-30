package com.cnsunru.home.mode;

import java.util.List;

/**
 * Created by cnsunrun on 2017-09-18.
 */

public class CategoryListInfo {
    private List<CategoryListInfo> data;

    public CategoryListInfo(String id, String title, String pid) {
            this.id = id;
            this.title = title;
            this.pid = pid;
        }

        /**
         * id : 12
         * title : 选材攻略
         * pid : 0
         */

        private String id;
        private String title;
        private String pid;

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

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

    public List<CategoryListInfo> getData() {
        return data;
    }

    public void setData(List<CategoryListInfo> data) {
        this.data = data;
    }
}
