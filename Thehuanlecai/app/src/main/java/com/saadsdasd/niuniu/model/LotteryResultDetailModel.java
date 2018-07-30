package com.saadsdasd.niuniu.model;

import java.util.List;

/**
 * Created by QYQ on 2017/9/25.
 */

public class LotteryResultDetailModel {
    /**
     * lotteryId : 33
     * lotteryNumber : 9,3,9
     * issue : 2017241
     * awardmoney : 13967824
     * lotteryName : 排列三
     * ernie_date : 1504618200000
     * prizePool : 0
     * time : 2017-09-05 21:30:00
     * htime : 2017年09月05日(星期二)
     * playid : 33
     * red : ["9","3","9"]
     * blue : [""]
     * award : 9,3,9
     * lotId : 33
     * prizeDate : 2017-09-05
     * testAwardNum : null
     * awardNum : 9,3,9
     * issueId : 2017241
     * rq_time : 2017年09月05日
     */

    private int lotteryId;
    private String lotteryNumber;
    private String issue;
    private int awardmoney;
    private String lotteryName;
    private long ernie_date;
    private int prizePool;
    private String time;
    private String htime;
    private String playid;
    private String award;
    private String lotId;
    private String prizeDate;
    private Object testAwardNum;
    private String awardNum;
    private String issueId;
    private String rq_time;
    private List<String> red;
    private List<String> blue;

    public int getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(int lotteryId) {
        this.lotteryId = lotteryId;
    }

    public String getLotteryNumber() {
        return lotteryNumber;
    }

    public void setLotteryNumber(String lotteryNumber) {
        this.lotteryNumber = lotteryNumber;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public int getAwardmoney() {
        return awardmoney;
    }

    public void setAwardmoney(int awardmoney) {
        this.awardmoney = awardmoney;
    }

    public String getLotteryName() {
        return lotteryName;
    }

    public void setLotteryName(String lotteryName) {
        this.lotteryName = lotteryName;
    }

    public long getErnie_date() {
        return ernie_date;
    }

    public void setErnie_date(long ernie_date) {
        this.ernie_date = ernie_date;
    }

    public int getPrizePool() {
        return prizePool;
    }

    public void setPrizePool(int prizePool) {
        this.prizePool = prizePool;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHtime() {
        return htime;
    }

    public void setHtime(String htime) {
        this.htime = htime;
    }

    public String getPlayid() {
        return playid;
    }

    public void setPlayid(String playid) {
        this.playid = playid;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public String getLotId() {
        return lotId;
    }

    public void setLotId(String lotId) {
        this.lotId = lotId;
    }

    public String getPrizeDate() {
        return prizeDate;
    }

    public void setPrizeDate(String prizeDate) {
        this.prizeDate = prizeDate;
    }

    public Object getTestAwardNum() {
        return testAwardNum;
    }

    public void setTestAwardNum(Object testAwardNum) {
        this.testAwardNum = testAwardNum;
    }

    public String getAwardNum() {
        return awardNum;
    }

    public void setAwardNum(String awardNum) {
        this.awardNum = awardNum;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getRq_time() {
        return rq_time;
    }

    public void setRq_time(String rq_time) {
        this.rq_time = rq_time;
    }

    public List<String> getRed() {
        return red;
    }

    public void setRed(List<String> red) {
        this.red = red;
    }

    public List<String> getBlue() {
        return blue;
    }

    public void setBlue(List<String> blue) {
        this.blue = blue;
    }
}
