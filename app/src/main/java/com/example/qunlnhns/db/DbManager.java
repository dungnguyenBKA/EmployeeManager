package com.example.qunlnhns.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbManager extends SQLiteOpenHelper {

    private static final String TAG = "dung_test";
    private static final String DATABASE_NAME = "QuanLyNhanSu";
    private static final int VERSION = 1;

    public DbManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        Log.d(TAG, "DbManager: constructor ");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createLuong = "CREATE TABLE Luong(\n" +
                "HeSoLuong INT PRIMARY KEY,\n" +
                "LuongCoBan INT NOT NULL,\n" +
                "Luong INT NOT NULL\n" +
                ");\n";

        String createChucVu = "CREATE TABLE ChucVu(\n" +
                "MaCV NVARCHAR(10) PRIMARY KEY ,\n" +
                "TenCV NVARCHAR(50) NOT NULL\n" +
                ");";

        String createPhongBan = "CREATE TABLE PhongBan(\n" +
                "MaPB NVARCHAR(10) PRIMARY KEY,\n" +
                "TenPB NVARCHAR(50) NOT NULL,\n" +
                "DiaChi NVARCHAR(50) NOT NULL,\n" +
                "SDT INT NOT NULL\n" +
                ");";

        String createDuAn = "CREATE TABLE DuAn(\n" +
                "MaDA NVARCHAR(10) PRIMARY KEY,\n" +
                "TenDuAn NVARCHAR(50) NOT NULL,\n" +
                "DiaChi NVARCHAR(50)\n" +
                ");";

        String createNhanVien = "CREATE TABLE NhanVien(\n" +
                "MaNV NVARCHAR(10) PRIMARY KEY,\n" +
                "HoTen NVARCHAR(50) NOT NULL,\n" +
                "GioiTinh NVARCHAR(10) NOT NULL,\n" +
                "NgaySinh DATE NOT NULL,\n" +
                "DiaChi NVARCHAR(50) NOT NULL,\n" +
                "SDT INT NOT NULL,\n" +
                "Email NVARCHAR(50) NOT NULL,\n" +
                "HeSoLuong INT NOT NULL,\n" +
                "MaPB NVARCHAR(10) NOT NULL, \n" +
                "MaDA NVARCHAR(10) NOT NULL,\n" +
                "MaCV NVARCHAR(10) NOT NULL,\n" +
                "FOREIGN KEY (HeSoLuong) REFERENCES Luong(HeSoLuong),\n" +
                "FOREIGN KEY(MaPB) REFERENCES PhongBan(MaPB),\n" +
                "FOREIGN KEY(MaDA) REFERENCES DuAn(MaDA),\n" +
                "FOREIGN KEY(MaCV) REFERENCES ChucVu(MaCV)\n" +
                ");";

        db.execSQL(createLuong);
        db.execSQL(createChucVu);
        db.execSQL(createPhongBan);
        db.execSQL(createDuAn);
        db.execSQL(createNhanVien);

        insertChucVu(db);
        insertDuAn(db);
        insertLuong(db);
        insertPhongBan(db);
        insertNhanVien(db);

        Log.d(TAG, "onCreate: database");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    // create database if onCreate isn't called
    public void createDatabase(){
        SQLiteDatabase db = getReadableDatabase();
        db.close();
    }

    /*
    * INSERTING
    * */

    private void insertChucVu(SQLiteDatabase db){

        String add = "INSERT INTO [ChucVu] ([MaCV], [TenCV]) " +
                "VALUES ('CV01', 'Tổng Giám đốc')\n," +
                "('CV02', 'Phó Tổng giám đốc')\n," +
                "('CV03', 'Thư Ký')\n," +
                "('CV04', 'Trưởng phòng')\n," +
                "('CV05', 'Cố vấn cấp cao')\n," +
                "('CV06', 'Nhân viên hành chính')\n," +
                "('CV07', 'Nhân viên kỹ thuật')";

        db.execSQL(add);
    }

    private void insertDuAn(SQLiteDatabase db){

        String add = "INSERT INTO [DuAn] ([MaDA], [TenDuAn], [DiaChi]) " +
                "VALUES " +
                "('DA01', 'Phát triển sản phẩm đồ uống có ga', 'Hà Nội')\n," +
                "('DA02', 'Xây dựng khu nhà tầm trung', 'Hà Nội')\n," +
                "('DA03', 'Phát hành sản phẩm - Điện thoại thông minh', 'Hồ Chí Minh')\n," +
                "('DA04', 'Chuỗi của hàng tiện lợi', 'Bình Dương')";

        db.execSQL(add);
    }

    private void insertLuong(SQLiteDatabase db){

        String add = "INSERT INTO [Luong] ([HeSoLuong], [LuongCoBan], [Luong]) " +
                "VALUES "
                +"(1, 1400000, 1400000),"
                +"(2, 1400000, 2800000),"
                +"(3, 1400000, 4200000),"
                +"(4, 1400000, 5600000),"
                +"(5, 1400000, 7000000),"
                +"(6, 1400000, 8400000),"
                +"(7, 1400000, 7800000),"
                +"(8, 1400000, 11200000)";

        db.execSQL(add);
    }

    private void insertPhongBan(SQLiteDatabase db){

        String add = "INSERT INTO [PhongBan] ([MaPB], [TenPB], [DiaChi], [SDT]) "
                +"VALUES " +
                "('PB01', 'Phòng tổ chức - hành chính', 'Tầng 2, nhà A, Hà Nội', 125478963),"
                +"('PB02', 'Phòng Kinh Tế - Kỹ thuật', 'Tầng 5, nhà B, Hà Nội', 125478963),"
                +"('PB03', 'Phòng Tài Chính - Kế toán', 'Tầng 4, nhà A, Hà Nội', 366464855),"
                +"('PB04', 'Phòng đại diện', 'Tầng 2, nhà B, Hà Nội', 123654789)";

        db.execSQL(add);
    }

    private void insertNhanVien(SQLiteDatabase db){

        String add = "INSERT INTO [NhanVien] ([MaNV], [HoTen], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [Email], [HeSoLuong], [MaPB], [MaDA], [MaCV]) " +
                "VALUES " +
                "('NV01', 'Hoàng Minh Anh', 'Nam', CAST('1990-03-03' AS Date), 'Hà Nội', 123654789, 'ma@gmail.com', 2, 'PB01', 'DA01', 'CV04')\n," +
                "('NV02', 'Nguyễn Thành Long', 'Nam', CAST('1989-12-03' AS Date), 'Hà Nội', 354656556, 'TL@gmail.com', 3, 'PB03', 'DA03', 'CV07')\n," +
                "('NV03', 'Nguyễn Kim Linh', 'Nữ', CAST('1986-03-17' AS Date), 'Thái Bình', 123658974, 'kl@gmail.com', 6, 'PB01', 'DA04', 'CV03')";

        db.execSQL(add);
    }
}
