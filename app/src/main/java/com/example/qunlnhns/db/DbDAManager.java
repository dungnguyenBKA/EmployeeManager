package com.example.qunlnhns.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.qunlnhns.model.DuAn;

import java.util.ArrayList;

public class DbDAManager {

    private static String DUAN_TABLE = "DuAn";

    private static DbDAManager dbDAManager;

    private static DbManager dbManager;

    private DbDAManager(Context context){
        dbManager = new DbManager(context);
    }

    public static DbDAManager getInstance(Context context){
        if(dbDAManager == null){
            dbDAManager = new DbDAManager(context);
        }

        return dbDAManager;
    }

    public ArrayList<DuAn> getAllDuAn(){
        SQLiteDatabase sqLiteDatabase =dbManager.getReadableDatabase();

        ArrayList<DuAn> duAnArrayList = new ArrayList<>();

        String getQuery ="SELECT * FROM DuAn";

        Cursor cursor = sqLiteDatabase.rawQuery(getQuery, null);

        if(cursor.moveToFirst()){
            do{
                DuAn duAn = new DuAn();
                duAn.setMaDA(cursor.getString(0));
                duAn.setTenDA(cursor.getString(1));

                duAnArrayList.add(duAn);
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        sqLiteDatabase.close();

        return duAnArrayList;
    }

    public ArrayList<DuAn> getSearchDuAn(String search){
        SQLiteDatabase sqLiteDatabase = dbManager.getReadableDatabase();

        ArrayList<DuAn> duAnArrayList = new ArrayList<>();

        String getQuery ="SELECT * FROM DuAn WHERE "+
                "maDA LIKE '%"+ search +"%'"
                + " OR TenDuAn LIKE '%"+ search + "%'";

        Cursor cursor = sqLiteDatabase.rawQuery(getQuery, null);

        if(cursor.moveToFirst()){
            do{
                DuAn duAn = new DuAn();
                duAn.setMaDA(cursor.getString(0));
                duAn.setTenDA(cursor.getString(1));

                duAnArrayList.add(duAn);
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        sqLiteDatabase.close();

        return duAnArrayList;
    }

    public void deleteRow(String maDA){
        SQLiteDatabase sqLiteDatabase = dbManager.getWritableDatabase();

        sqLiteDatabase.delete(DUAN_TABLE, " maDA LIKE '"+maDA+"'", null);
        sqLiteDatabase.close();
    }

    public void addRow(DuAn duAn){
        SQLiteDatabase sqLiteDatabase = dbManager.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("MaDA",duAn.getMaDA());
        contentValues.put("TenDuAn", duAn.getTenDA());
        contentValues.put("DiaChi", "");

        sqLiteDatabase.insert(DUAN_TABLE, null, contentValues);

    }

    public boolean isDAValid(String maDA){
        int count;

        String getAllQuery = "SELECT * FROM DuAn \n" +
                " WHERE maDA LIKE '" + maDA + "'";

        SQLiteDatabase db = dbManager.getReadableDatabase();
        Cursor cursor = db.rawQuery(getAllQuery, null);

        count = cursor.getCount();

        cursor.close();
        db.close();

        return count==1;
    }

}
