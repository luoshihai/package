package com.cnsunru.budget.mode;

import com.cnsunru.common.adapter.Selectable;

/**
 * Created by WQ on 2017/10/18.
 */

public class WorkTypeBean implements Selectable.SelectableEntity {
    String  title;
    String  id;
    public WorkTypeBean(String title) {
        this.title = title;
    }

    public WorkTypeBean() {
    }

    @Override
    public int getUniqueCode() {
        return String.valueOf(id).hashCode();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
