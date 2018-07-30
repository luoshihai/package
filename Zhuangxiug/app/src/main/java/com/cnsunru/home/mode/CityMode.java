package com.cnsunru.home.mode;

import com.sunrun.sunrunframwork.view.sidebar.SortModel;

/**
 * Created by WQ on 2017/9/13.
 */

public class CityMode extends SortModel {
    /**
     * id : 1
     * pid : 0
     * title : 北京
     */

    private String id;
    private String pid;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }


}
