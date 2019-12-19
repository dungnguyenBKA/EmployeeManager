package com.example.qunlnhns.model;

import java.io.Serializable;

public class NhanVien implements Serializable {
    private String MaNV;
    private String HoTen;
    private String GioiTinh;
    private String NgaySinh;
    private String DiaChi;
    private int SDT;
    private String Email;

    /* foreigh key */
    private String MaPB;
    private String MaCV;
    private String MaDA;
    private int HeSoLuong;

    private String ChucVu;
    private String PhongBan;
    private String DuAn;
    private int Luong;

    public NhanVien(String maNV, String hoTen, String gioiTinh, String ngaySinh, String diaChi, int SDT, String email, String maPB, String maCV, String maDA, int heSoLuong) {
        MaNV = maNV;
        HoTen = hoTen;
        GioiTinh = gioiTinh;
        NgaySinh = ngaySinh;
        DiaChi = diaChi;
        this.SDT = SDT;
        Email = email;
        MaPB = maPB;
        MaCV = maCV;
        MaDA = maDA;
        HeSoLuong = heSoLuong;
    }

    public NhanVien() {
    }

    public String getMaPB() {
        return MaPB;
    }

    public void setMaPB(String maPB) {
        MaPB = maPB;
    }

    public String getMaCV() {
        return MaCV;
    }

    public void setMaCV(String maCV) {
        MaCV = maCV;
    }

    public String getMaDA() {
        return MaDA;
    }

    public void setMaDA(String maDA) {
        MaDA = maDA;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        MaNV = maNV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getHeSoLuong() {
        return HeSoLuong;
    }

    public void setHeSoLuong(int heSoLuong) {
        HeSoLuong = heSoLuong;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String chucVu) {
        ChucVu = chucVu;
    }

    public String getPhongBan() {
        return PhongBan;
    }

    public void setPhongBan(String phongBan) {
        PhongBan = phongBan;
    }

    public String getDuAn() {
        return DuAn;
    }

    public void setDuAn(String duAn) {
        DuAn = duAn;
    }

    public int getLuong() {
        return Luong;
    }

    public void setLuong(int luong) {
        Luong = luong;
    }
}
