package com.saadsdasd.niuniu.model;

/**
 * Created by QYQ on 2017/9/14.
 */

public class NewModel {

    /**
     * url : http://mapi.159cai.com/discovery/news/football/2017/0914/29962.html
     * title : 周四竞彩：蓝鹰客场展翅翱翔
     * pubdate : 09-14
     * image : http://mapi.159cai.com/uploads/allimg/170914/2-1F914100113D9-lp.jpg
     * contents : 周四014 欧联H组第1轮 贝尔格莱德红星VS巴特 比赛时间：2017-09-15 03:05 地点：红星球场 天气：17晴 贝尔格莱德红星近况： 贝尔格莱德
     */

    private String url;
    private String title;
    private String pubdate;
    private String image;
    private String contents;
    private String shorttitle;

    public String getShorttitle() {
        return shorttitle;
    }

    public void setShorttitle(String shorttitle) {
        this.shorttitle = shorttitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
