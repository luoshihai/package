package com.hhhh.pailiesan.model;

import java.io.Serializable;

/**
 * Created by tgw on 2017/8/7.
 */

public class LotteryModel implements Serializable{


    /**
     * result : 08,14,16,18,21,23|16
     * prizepool : 436050760
     * nums : 4,96,795,47610,1019017,7049837
     * name : ssq
     * termNo : 2017109
     * ver : 1
     * grades :
     * date : 2017-09-17
     * type : 双色球
     * sale : 349302018
     */

    private String result;
    private String prizepool;
    private String nums;
    private String name;
    private String termNo;
    private String ver;
    private String grades;
    private String date;
    private String type;
    private String sale;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTermNo() {
        return termNo;
    }

    public void setTermNo(String termNo) {
        this.termNo = termNo;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrizepool(String prizepool) {
        this.prizepool = prizepool;
    }

    public String getPrizepool() {
        return prizepool;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }
}

