package com.saadsdasd.niuniu.model;

import java.io.Serializable;

/**
 * Created by QYQ on 2017/9/12.
 */

public class SeviceModel implements Serializable {

    /**
     * jsonrpc : 2.0
     * id : 1
     * result : {"appid":"com.zhouwu.demo","showWap":1,"wapUrl":"http://m.fs95676.bg866.com/?app=true","backgroundColor":"#e82e2f","fontColor":"0","version":"1.0","appStoreUrl":"","showNavBar":0}
     */

    private String jsonrpc;
    private int id;
    private ResultBean result;

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable{
        /**
         * appid : com.zhouwu.demo
         * showWap : 1
         * wapUrl : http://m.fs95676.bg866.com/?app=true
         * backgroundColor : #e82e2f
         * fontColor : 0
         * version : 1.0
         * appStoreUrl :
         * showNavBar : 0
         */

        private String appid;
        private int showWap;
        private String wapUrl;
        private String backgroundColor;
        private String fontColor;
        private String version;
        private String appStoreUrl;
        private int showNavBar;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public int getShowWap() {
            return showWap;
        }

        public void setShowWap(int showWap) {
            this.showWap = showWap;
        }

        public String getWapUrl() {
            return wapUrl;
        }

        public void setWapUrl(String wapUrl) {
            this.wapUrl = wapUrl;
        }

        public String getBackgroundColor() {
            return backgroundColor;
        }

        public void setBackgroundColor(String backgroundColor) {
            this.backgroundColor = backgroundColor;
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

        public int getShowNavBar() {
            return showNavBar;
        }

        public void setShowNavBar(int showNavBar) {
            this.showNavBar = showNavBar;
        }
    }
}
