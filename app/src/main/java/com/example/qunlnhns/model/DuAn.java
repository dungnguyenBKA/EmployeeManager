package com.example.qunlnhns.model;

public class DuAn {
    private String maDA;
    private String tenDA;
    private int quantity;

    public DuAn(String maDA, String tenDA, int quantity) {
        this.maDA = maDA;
        this.tenDA = tenDA;
        this.quantity = quantity;
    }

    public DuAn(){

    }

    public String getMaDA() {
        return maDA;
    }

    public void setMaDA(String maDA) {
        this.maDA = maDA;
    }

    public String getTenDA() {
        return tenDA;
    }

    public void setTenDA(String tenDA) {
        this.tenDA = tenDA;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
