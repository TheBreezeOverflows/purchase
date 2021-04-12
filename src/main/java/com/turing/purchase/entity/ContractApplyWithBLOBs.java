package com.turing.purchase.entity;

import java.io.Serializable;

public class ContractApplyWithBLOBs extends ContractApply implements Serializable {
    private String planOpinion;

    private String leadOpinion;

    private static final long serialVersionUID = 1L;

    public String getPlanOpinion() {
        return planOpinion;
    }

    public void setPlanOpinion(String planOpinion) {
        this.planOpinion = planOpinion == null ? null : planOpinion.trim();
    }

    public String getLeadOpinion() {
        return leadOpinion;
    }

    public void setLeadOpinion(String leadOpinion) {
        this.leadOpinion = leadOpinion == null ? null : leadOpinion.trim();
    }
}