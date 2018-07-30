package com.saadsdasd.niuniu.model;

import java.util.List;

/**
 * Created by QYQ on 2017/9/15.
 */

public class LotteryFinalModel {

    /**
     * id : 2004
     * lotId : 33
     * issue : 2017250
     * award : 8,5,6
     * sale : 14330346
     * pool : 0
     * prizeDate : 2017-09-14 21:30:00
     * lotName : 排列三
     * reList : [{"awards":"直选","winningNote":13304,"single_Note_Bonus":1040},{"awards":"组选3","winningNote":0,"single_Note_Bonus":346},{"awards":"组选6","winningNote":19129,"single_Note_Bonus":173}]
     * prizeDetail : ["直选_1040_13304","组选3_346_0","组选6_173_19129"]
     */

    private String id;
    private String lotId;
    private String issue;
    private String award;
    private int sale;
    private int pool;
    private String prizeDate;
    private String lotName;
    private List<ReListBean> reList;
    private List<String> prizeDetail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLotId() {
        return lotId;
    }

    public void setLotId(String lotId) {
        this.lotId = lotId;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public int getPool() {
        return pool;
    }

    public void setPool(int pool) {
        this.pool = pool;
    }

    public String getPrizeDate() {
        return prizeDate;
    }

    public void setPrizeDate(String prizeDate) {
        this.prizeDate = prizeDate;
    }

    public String getLotName() {
        return lotName;
    }

    public void setLotName(String lotName) {
        this.lotName = lotName;
    }

    public List<ReListBean> getReList() {
        return reList;
    }

    public void setReList(List<ReListBean> reList) {
        this.reList = reList;
    }

    public List<String> getPrizeDetail() {
        return prizeDetail;
    }

    public void setPrizeDetail(List<String> prizeDetail) {
        this.prizeDetail = prizeDetail;
    }

    public static class ReListBean {
        /**
         * awards : 直选
         * winningNote : 13304
         * single_Note_Bonus : 1040
         */

        private String awards;
        private int winningNote;
        private int single_Note_Bonus;

        public String getAwards() {
            return awards;
        }

        public void setAwards(String awards) {
            this.awards = awards;
        }

        public int getWinningNote() {
            return winningNote;
        }

        public void setWinningNote(int winningNote) {
            this.winningNote = winningNote;
        }

        public int getSingle_Note_Bonus() {
            return single_Note_Bonus;
        }

        public void setSingle_Note_Bonus(int single_Note_Bonus) {
            this.single_Note_Bonus = single_Note_Bonus;
        }
    }
}
