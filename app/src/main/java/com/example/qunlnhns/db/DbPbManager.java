package com.example.qunlnhns.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.qunlnhns.model.PhongBan;

import java.util.ArrayList;

public class DbPbManager {

    private static String PB_TABLE = "PhongBan";

    private static DbPbManager dbPbManager;

    private static DbManager dbManager;

    private DbPbManager(Context context){
        dbManager = new DbManager(context);
    }

    public static DbPbManager getInstance(Context context){
        if(dbPbManager == null){
            dbPbManager = new DbPbManager(context);
        }

        return dbPbManager;
    }

    public ArrayList<PhongBan> getAllPhongBan(){
        SQLiteDatabase sqLiteDatabase =dbManager.getReadableDatabase();

        ArrayList<PhongBan> phongBanArrayList = new ArrayList<>();

        String getQuery ="SELECT * FROM "+PB_TABLE;

        Cursor cursor = sqLiteDatabase.rawQuery(getQuery, null);

        if(cursor.moveToFirst()){
            do{
                PhongBan phongBan = new PhongBan();

                phongBan.setMaPb(cursor.getString(0));
                phongBan.setTenPb(cursor.getString(1));
                phongBan.setDiachiPb(cursor.getString(2));
                phongBan.setSDT(Integer.valueOf( cursor.getString(3) ));

                phongBanArrayList.add(phongBan);
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        sqLiteDatabase.close();

        return phongBanArrayList;
    }

    public ArrayList<PhongBan> getSearchPhongBan(String search){
        SQLiteDatabase sqLiteDatabase = dbManager.getReadableDatabase();

        ArrayList<PhongBan> phongBanArrayList = new ArrayList<>();

        String getQuery ="SELECT * FROM PhongBan WHERE "+
                "MaPB LIKE '%"+ search +"%'"
                + " OR TenPB LIKE '%"+ search + "%'";

        Cursor cursor = sqLiteDatabase.rawQuery(getQuery, null);

        if(cursor.moveToFirst()){
            do{
                PhongBan phongBan = new PhongBan();

                phongBan.setMaPb(cursor.getString(0));
                phongBan.setTenPb(cursor.getString(1));
                phongBan.setDiachiPb(cursor.getString(2));
                phongBan.setSDT(Integer.valueOf( cursor.getString(3) ));

                phongBanArrayList.add(phongBan);
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        sqLiteDatabase.close();

        return phongBanArrayList;
    }

    public void addRow(PhongBan phongBan){
        SQLiteDatabase sqLiteDatabase = dbManager.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("MaPB", phongBan.getMaPb());
        contentValues.put("TenPB", phongBan.getTenPb());
        contentValues.put("DiaChi", phongBan.getDiachiPb());
        contentValues.put("SDT", phongBan.getSDT());

        sqLiteDatabase.insert(PB_TABLE, null, contentValues);
    }

    public boolean isPBValid(String maPB){
        int count;

        String getAllQuery = "SELECT * FROM PhongBan \n" +
                " WHERE maPB LIKE '" + maPB + "'";

        SQLiteDatabase db = dbManager.getReadableDatabase();
        Cursor cursor = db.rawQuery(getAllQuery, null);

        count = cursor.getCount();

        cursor.close();
        db.close();

        return count==1;
    }

    public boolean isCVValid(String maCV){
        int count;

        String getAllQuery = "SELECT * FROM ChucVu \n" +
                " WHERE maCV LIKE '" + maCV + "'";

        SQLiteDatabase db = dbManager.getReadableDatabase();
        Cursor cursor = db.rawQuery(getAllQuery, null);

        count = cursor.getCount();

        cursor.close();
        db.close();

        return count==1;
    }

    public ArrayList<String> getStringsCV(){
        ArrayList<String> strings = new ArrayList<>();

        String get = "SELECT * FROM ChucVu";

        SQLiteDatabase db = dbManager.getReadableDatabase();
        Cursor cursor = db.rawQuery(get, null);

        if(cursor.moveToFirst()){
            do{
                strings.add( cursor.getString(0) );
            }
            while(cursor.moveToNext());
        }


        cursor.close();
        db.close();

        return strings;
    }

    public ArrayList<String> getStringsPB(){
        ArrayList<String> strings = new ArrayList<>();

        String get = "SELECT * FROM PhongBan";

        SQLiteDatabase db = dbManager.getReadableDatabase();
        Cursor cursor = db.rawQuery(get, null);

        if(cursor.moveToFirst()){
            do{
                strings.add( cursor.getString(0) );
            }
            while(cursor.moveToNext());
        }


        cursor.close();
        db.close();

        return strings;
    }

    public ArrayList<String> getStringsDA(){
        ArrayList<String> strings = new ArrayList<>();

        String get = "SELECT * FROM DuAn";

        SQLiteDatabase db = dbManager.getReadableDatabase();
        Cursor cursor = db.rawQuery(get, null);

        if(cursor.moveToFirst()){
            do{
                strings.add( cursor.getString(0) );
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return strings;
    }
}
