package com.zxy.xyz.ztest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zxy.xyz.ztest.biz.City;

import java.util.ArrayList;

/**
 * Created by 51c on 2017/4/24.
 */

public class SqliteDBUtil {
    private final SQLiteDatabase dbW;
    private final SQLiteDatabase dbR;
    private final SqliteDB sqliteDB;
    private Context context;
    private City city;


    public SqliteDBUtil(Context context){
        this.context=context;
        sqliteDB=new SqliteDB(context);
        dbW=sqliteDB.getWritableDatabase();
        dbR=sqliteDB.getReadableDatabase();
    }

    public int insertoDB(String province,String city,String district,String code){
        int result=0;
        ContentValues cValues=new ContentValues();
        cValues.put("province",province);
        cValues.put("city",city);
        cValues.put("district",district);
        if(code.equals("")){
            cValues.put("code", "000000");
        }else {
            cValues.put("code", code);
        }
        dbW.insert("city",null,cValues);
        result=1;
        return result;
    }


    public int deleteDB(String district,String city){
        int result=0;
        String sql;
        String cityLsat=city.substring(city.length()-1);
        if(cityLsat.equals("区")){
           sql="delete from city where district="+district;
            result=1;
        }else{
            sql="delete from city where district="+city;
            result=2;
        }
        dbW.execSQL(sql);
        return result;
    }

    public ArrayList<City> getCityList(){

        ArrayList<City> al=new ArrayList<>();
        Cursor cursor = dbR.query("city",null,null,null,null,null,null);
        if(cursor.moveToFirst()) {

            for (int i = 0; i < cursor.getCount(); i++) {
                  cursor.moveToPosition(i);
                  city=new City();
                String province=cursor.getString(cursor.getColumnIndex("province"));
                  city.setProvince(province);
                String city1=cursor.getString(cursor.getColumnIndex("city"));
                  city.setCity(city1);
                String s=cursor.getString(cursor.getColumnIndex("district"));
                  city.setDistrict(s);
                String code=cursor.getString(cursor.getColumnIndex("code"));
                  city.setCode(code);
                al.add(city);
            }

        }
        return al;
    }

    public void closeDB(){
        dbW.close();
        sqliteDB.close();
    }
}
