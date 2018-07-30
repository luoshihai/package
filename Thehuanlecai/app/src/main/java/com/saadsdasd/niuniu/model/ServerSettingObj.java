package com.saadsdasd.niuniu.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by raykon.lin on 2017/5/26.
 */

public class ServerSettingObj implements Serializable{
    private String appid;
    private boolean showWap;
    private String wapUrl;
    private String backGroundColor;
    private String fontColor;
    private String version;
    private String appStoreUrl;
    private boolean showNavBar;

    public ServerSettingObj() {
    }
    public ServerSettingObj(JSONObject obj) throws JSONException{
        setAppid(obj.getString("appid"));
        setShowWap(obj.getInt("showWap") == 1);
        setWapUrl(obj.getString("wapUrl"));
        setBackGroundColor(obj.getString("backgroundColor"));
        setFontColor(obj.getString("fontColor"));
        setVersion(obj.getString("version"));
        setAppStoreUrl(obj.getString("appStoreUrl"));
        setShowNavBar(obj.getInt("showNavBar") == 1);
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public boolean isShowWap() {
        return showWap;
    }

    public void setShowWap(boolean showWap) {
        this.showWap = showWap;
    }

    public String getWapUrl() {
        return wapUrl;
    }

    public void setWapUrl(String wapUrl) {
        this.wapUrl = wapUrl;
    }

    public String getBackGroundColor() {
        return backGroundColor;
    }

    public void setBackGroundColor(String backGroundColor) {
        this.backGroundColor = backGroundColor;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAppStoreUrl() {
        return appStoreUrl;
    }

    public void setAppStoreUrl(String appStoreUrl) {
        this.appStoreUrl = appStoreUrl;
    }

    public boolean isShowNavBar() {
        return showNavBar;
    }

    public void setShowNavBar(boolean showNavBar) {
        this.showNavBar = showNavBar;
    }
}
