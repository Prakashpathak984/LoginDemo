package com.example.logindemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class dbManager extends SQLiteOpenHelper {
    private static final String dbname = "userInfo.db", tblname = "usersRecord", col_4 = "username", col_5 = "password";
    Cursor cursor;

    public dbManager(Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry = "create table usersRecord(id integer primary key autoincrement,name text,age integer, username text, password text)";
        db.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usersRecord");
        onCreate(db);
    }

    public String addUser(String p1, String p2, String p3, String p4) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", p1);
        cv.put("age", p2);
        cv.put("username", p3);
        cv.put("password", p4);

        long res = db.insert("usersRecord", null, cv);

        if (res == -1)
            return "Failed";
        else
            return "Successfully Registered";

    }


    public Boolean validateUser(String p1, String p2) {
        boolean res = false;

        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM " + dbManager.tblname + " where " + dbManager.col_4 + "=? AND " + dbManager.col_5 + "=?", new String[]{p1, p2});

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToNext();
                res = true;
            }

        }

        return res;
    }


    public Boolean validate(String name, String password) {
        boolean result = false;


        if (name.isEmpty() || password.isEmpty()) {
            result = false;
        } else {
            result = true;
        }

        return result;
    }

    public String viewData(String username, String password) {
//        String str;
        SQLiteDatabase db = this.getReadableDatabase();
//        str = String.valueOf(
//                db.rawQuery("SELECT name FROM " + dbManager.tblname + " WHERE " + dbManager.col_4 + "=? AND " + dbManager.col_5 + "=?", new String[]{username, password}));

//        String str_new = db.rawQuery("SELECT * FROM " + dbManager.tblname, new String[]{username, password}).toString();

        String dbName = dbManager.dbname;
        Log.d("D_NAME", dbName);

        return dbName;

    }
}
