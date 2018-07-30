package com.cnsunru.common.event;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by cnsunrun on 2017/7/14.
 * <p>
 * EventBus模型类
 */

public class MessageEvent {
    private String type;
    private String content;
    private String title;
    private String id;
    private LinkedHashSet<String> house_patternTreeSet;
    private LinkedHashSet<String> titles;

    public LinkedHashSet<String> getTitles() {
        return titles;
    }

    public void setTitles(LinkedHashSet<String> titles) {
        this.titles = titles;
    }

    public void setHouse_patternTreeSet(LinkedHashSet<String> house_patternTreeSet) {
        this.house_patternTreeSet = house_patternTreeSet;
    }

    public LinkedHashSet<String> getHouse_patternTreeSet() {
        return house_patternTreeSet;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MessageEvent(String action, String content) {
        this.type = action;
        this.content = content;
    }

    public MessageEvent(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public MessageEvent() {
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
