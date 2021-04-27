package com.turing.purchase.entity;

import java.io.Serializable;

public class QuoteWithBLOBs extends Quote implements Serializable {
    private String quoAddress;

    private String quoRemark;

    private Long myID;

    public Long getMyID() {
        return myID;
    }

    public void setMyID(Long myID) {
        this.myID = myID;
    }

    private static final long serialVersionUID = 1L;

    public String getQuoAddress() {
        return quoAddress;
    }

    public void setQuoAddress(String quoAddress) {
        this.quoAddress = quoAddress == null ? null : quoAddress.trim();
    }

    public String getQuoRemark() {
        return quoRemark;
    }

    public void setQuoRemark(String quoRemark) {
        this.quoRemark = quoRemark == null ? null : quoRemark.trim();
    }
}