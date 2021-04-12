package com.turing.purchase.entity;

import java.io.Serializable;

public class ContractDetailWithBLOBs extends ContractDetail implements Serializable {
    private String address;

    private String wrapRequire;

    private String factory;

    private static final long serialVersionUID = 1L;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getWrapRequire() {
        return wrapRequire;
    }

    public void setWrapRequire(String wrapRequire) {
        this.wrapRequire = wrapRequire == null ? null : wrapRequire.trim();
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory == null ? null : factory.trim();
    }
}