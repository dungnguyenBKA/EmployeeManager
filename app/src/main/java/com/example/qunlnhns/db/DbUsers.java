package com.example.qunlnhns.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DbUsers extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "User";
    private static final String TABLE_NAME = "UserAndPass";

    public DbUsers(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " +
                TABLE_NAME+
                "( " +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Username TEXT NOT NULL," +
                "Password TEXT NOT NULL" +
                ")";

        db.execSQL(createUserTable);

        insertUser(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void createDatabase(){
        SQLiteDatabase db = getReadableDatabase();
        db.close();
    }

    public void insertUser(SQLiteDatabase db){
        String add = "INSERT INTO " +
                TABLE_NAME +
                " (Username, Password) " +
                " VALUES ('admin', 'admin')";

        db.execSQL(add);
    }

    public boolean checkLoginSuccess(String username, String password){
        String query = "SELECT * FROM " + TABLE_NAME+
            " WHERE Username LIKE '" + username + "' AND "+
            " Password LIKE '" + password + "'";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        int user_count = cursor.getCount();

        cursor.close();
        db.close();

        return (user_count==1);
    }
}
