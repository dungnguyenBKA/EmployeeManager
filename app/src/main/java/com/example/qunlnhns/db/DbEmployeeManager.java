package com.example.qunlnhns.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.qunlnhns.model.NhanVien;

import java.util.ArrayList;

/*
* Lớp này quản lý thêm sửa xóa nhân viên, gọi instance để đảm bảo duy nhất 1 dbManager được khởi tạo
* Singleton pattern
*/


public class DbEmployeeManager {

    private static final String NHANVIEN_TABLE = "NhanVien";

    private static DbManager dbManager;
    private static DbEmployeeManager dbEmployeeManager;

    private DbEmployeeManager(Context context) {
        dbManager = new DbManager(context);
    }

    public static DbEmployeeManager getInstance(Context context){
        if(dbManager == null){
            dbEmployeeManager = new DbEmployeeManager(context);
        }

        return dbEmployeeManager;
    }

    public ArrayList<NhanVien> getAllNhanVien(){

        ArrayList<NhanVien> nhanVienList = new ArrayList<>();

        String getAllQuery = "SELECT * FROM NhanVien \n" +
                "INNER JOIN DuAn, ChucVu, Luong, PhongBan \n" +
                "ON NhanVien.mada = DuAn.mada \n" +
                "AND NhanVien.macv = ChucVu.MaCV \n" +
                "AND NhanVien.mapb = PhongBan.mapb \n" +
                "AND NhanVien.hesoluong = Luong.HeSoLuong";

        SQLiteDatabase db = dbManager.getReadableDatabase();
        Cursor cursor = db.rawQuery(getAllQuery, null);

        if (cursor.moveToFirst()) {
            do {
                NhanVien nhanVien= new NhanVien();

                nhanVien.setMaNV(cursor.getString(0));
                nhanVien.setHoTen(cursor.getString(1));
                nhanVien.setGioiTinh(cursor.getString(2));
                nhanVien.setNgaySinh(cursor.getString(3));
                nhanVien.setDiaChi(cursor.getString(4));
                nhanVien.setSDT(Integer.valueOf(cursor.getString(5)));
                nhanVien.setEmail(cursor.getString(6));
                nhanVien.setHeSoLuong(Integer.valueOf(cursor.getString(7)));
                nhanVien.setPhongBan(cursor.getString(20));
                nhanVien.setChucVu(cursor.getString(15));
                nhanVien.setLuong(Integer.valueOf(cursor.getString(18)));
                nhanVien.setDuAn(cursor.getString(12));
                nhanVien.setMaDA(cursor.getString(9));
                nhanVien.setMaCV(cursor.getString(10));
                nhanVien.setMaPB(cursor.getString(8));
                nhanVienList.add(nhanVien);
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return nhanVienList;
    }

    public ArrayList<NhanVien> getNhanVien(String searchNhanVien){


        ArrayList<NhanVien> nhanVienList = new ArrayList<>();

        String getAllQuery = "SELECT * FROM NhanVien \n" +
                "INNER JOIN DuAn, ChucVu, Luong, PhongBan \n" +
                "ON NhanVien.mada = DuAn.mada \n" +
                "AND NhanVien.macv = ChucVu.MaCV \n" +
                "AND NhanVien.mapb = PhongBan.mapb \n" +
                "AND NhanVien.hesoluong = Luong.HeSoLuong"+
                " WHERE NhanVien.HoTen LIKE "+ "\"%" + searchNhanVien + "%\"";

        SQLiteDatabase db = dbManager.getReadableDatabase();
        Cursor cursor = db.rawQuery(getAllQuery, null);

        if (cursor.moveToFirst()) {
            do {
                NhanVien nhanVien= new NhanVien();
                nhanVien.setMaNV(cursor.getString(0));

                nhanVien.setHoTen(cursor.getString(1));
                nhanVien.setGioiTinh(cursor.getString(2));
                nhanVien.setNgaySinh(cursor.getString(3));
                nhanVien.setDiaChi(cursor.getString(4));
                nhanVien.setSDT(Integer.valueOf(cursor.getString(5)));
                nhanVien.setEmail(cursor.getString(6));
                nhanVien.setHeSoLuong(Integer.valueOf(cursor.getString(7)));
                nhanVien.setPhongBan(cursor.getString(20));
                nhanVien.setChucVu(cursor.getString(15));
                nhanVien.setLuong(Integer.valueOf(cursor.getString(18)));
                nhanVien.setDuAn(cursor.getString(12));
                nhanVien.setMaDA(cursor.getString(9));
                nhanVien.setMaCV(cursor.getString(10));
                nhanVien.setMaPB(cursor.getString(8));

                nhanVienList.add(nhanVien);
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return nhanVienList;
    }

    public void modifyRow(NhanVien newNhanVien, String maNV){
        SQLiteDatabase sqLiteDatabase = dbManager.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("HoTen",newNhanVien.getHoTen());
        cv.put("GioiTinh",newNhanVien.getGioiTinh());
        cv.put("NgaySinh",newNhanVien.getNgaySinh());
        cv.put("DiaChi",newNhanVien.getDiaChi());
        cv.put("SDT",newNhanVien.getSDT());
        cv.put("Email",newNhanVien.getEmail());
        cv.put("HeSoLuong",newNhanVien.getHeSoLuong());
        cv.put("MaPB",newNhanVien.getMaPB());
        cv.put("MaDA",newNhanVien.getMaDA());
        cv.put("MaCV",newNhanVien.getMaCV());
        cv.put("MaNV", newNhanVien.getMaNV());

        sqLiteDatabase.update(NHANVIEN_TABLE, cv, "MaNV LIKE '" + maNV+"'",null);
    }

    public void addEmployee(NhanVien nhanVien){
        SQLiteDatabase sqLiteDatabase = dbManager.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("MaNV",nhanVien.getMaNV());
        cv.put("HoTen",nhanVien.getHoTen());
        cv.put("GioiTinh",nhanVien.getGioiTinh());
        cv.put("NgaySinh",nhanVien.getNgaySinh());
        cv.put("DiaChi",nhanVien.getDiaChi());
        cv.put("SDT",nhanVien.getSDT());
        cv.put("Email",nhanVien.getEmail());
        cv.put("HeSoLuong",nhanVien.getHeSoLuong());
        cv.put("MaPB",nhanVien.getMaPB());
        cv.put("MaDA",nhanVien.getMaDA());
        cv.put("MaCV",nhanVien.getMaCV());
        cv.put("MaNV", nhanVien.getMaNV());

        sqLiteDatabase.insert(NHANVIEN_TABLE, null, cv);
    }


    public void deleteEmployee(String maNV){
        SQLiteDatabase sqLiteDatabase = dbManager.getWritableDatabase();

        sqLiteDatabase.delete(NHANVIEN_TABLE, "MaNV LIKE '"+maNV +"'", null);
    }

    public int getCountEmployeeEachProject(String maDA){
        int count = 0;

        String getAllQuery = "SELECT * FROM NhanVien \n" +
                " WHERE NhanVien.MaDA LIKE '" + maDA + "'";

        SQLiteDatabase db = dbManager.getReadableDatabase();
        Cursor cursor = db.rawQuery(getAllQuery, null);

        count = cursor.getCount();

        cursor.close();
        db.close();

        return count;
    }

    public boolean isDupEmployee(String maNV){
        int count = 0;

        String getAllQuery = "SELECT * FROM NhanVien \n" +
                " WHERE NhanVien.maNV LIKE '" + maNV + "'";

        SQLiteDatabase db = dbManager.getReadableDatabase();
        Cursor cursor = db.rawQuery(getAllQuery, null);

        count = cursor.getCount();

        cursor.close();
        db.close();

        return count!=0;
    }
}
