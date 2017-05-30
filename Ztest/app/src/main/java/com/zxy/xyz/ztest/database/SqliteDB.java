package com.zxy.xyz.ztest.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 51c on 2017/4/24.
 */

public class SqliteDB extends SQLiteOpenHelper{
    private static String name="citydb";
    private static int version=1;
    public SqliteDB(Context context){
        super(context, name, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table city(province varchar(64),city varchar(64),district varchar(64),code varchar(64))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
