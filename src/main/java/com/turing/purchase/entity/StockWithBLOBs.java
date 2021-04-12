package com.turing.purchase.entity;

import java.io.Serializable;

public class StockWithBLOBs extends Stock implements Serializable {
    private String remark;

    private String leadOpinion;

    private static final long serialVersionUID = 1L;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getLeadOpinion() {
        return leadOpinion;
    }

    public void setLeadOpinion(String leadOpinion) {
        this.leadOpinion = leadOpinion == null ? null : leadOpinion.trim();
    }
}