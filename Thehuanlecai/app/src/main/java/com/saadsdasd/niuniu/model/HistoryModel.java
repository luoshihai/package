package com.saadsdasd.niuniu.model;

import java.io.Serializable;

/**
 * Created by QYQ on 2017/9/19.
 */

public class HistoryModel implements Serializable{

    /**
     * result : 08,14,16,18,21,23|16
     * time : 2017-09-17
     * prizepool : 436050760
     * term : 2017109
     * type : 双色球
     * sale : 349302018
     */

    private String result;
    private String time;
    private String prizepool;
    private String term;
    private String type;
    private String sale;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrizepool() {
        return prizepool;
    }

    public void setPrizepool(String prizepool) {
        this.prizepool = prizepool;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }
}
