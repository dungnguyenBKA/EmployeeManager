package com.example.qunlnhns.model;

public class PhongBan {
    private String maPb;
    private String tenPb;
    private String diachiPb;
    private int SDT;

    public PhongBan(String maPb, String tenPb, String diachiPb, int SDT) {
        this.maPb = maPb;
        this.tenPb = tenPb;
        this.diachiPb = diachiPb;
        this.SDT = SDT;
    }

    public PhongBan() {
    }

    public String getMaPb() {
        return maPb;
    }

    public void setMaPb(String maPb) {
        this.maPb = maPb;
    }

    public String getTenPb() {
        return tenPb;
    }

    public void setTenPb(String tenPb) {
        this.tenPb = tenPb;
    }

    public String getDiachiPb() {
        return diachiPb;
    }

    public void setDiachiPb(String diachiPb) {
        this.diachiPb = diachiPb;
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }
}
