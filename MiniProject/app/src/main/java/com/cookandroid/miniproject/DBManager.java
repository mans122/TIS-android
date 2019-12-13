package com.cookandroid.miniproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper {
    public final static String MY_CARDB_NAME = "Diary";
    public final static String MY_CARDB_TABLE = "Diary";
    public final static int MY_CARDB_VERSION = 1;

    private static volatile DBManager dbManager;


    public static DBManager getInstance(Context context){
        if(dbManager == null){
            synchronized (DBManager.class){
                if(dbManager==null){
                    dbManager = new DBManager(context);
                }
            }
        }
        return dbManager;
    }

    private DBManager(Context context){
        super(context,MY_CARDB_NAME,null,MY_CARDB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL("create table Diary(Title char(30) primary key, Date char(10), Weather char(10), Content char(2000))");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Table 초기화 코드
        db.execSQL("drop table if exists Diary");
        onCreate(db);
    }
}

