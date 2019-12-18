package com.cookandroid.miniproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager2 extends SQLiteOpenHelper {
    public final static String MY_CARDB_NAME2 = "Idlist";
    public final static String MY_CARDB_TABLE2 = "Idlist";
    public final static int MY_CARDB_VERSION2 = 1;

    private static volatile DBManager2 dbManager;


    public static DBManager2 getInstance(Context context){
        if(dbManager == null){
            synchronized (DBManager2.class){
                if(dbManager==null){
                    dbManager = new DBManager2(context);
                }
            }
        }
        return dbManager;
    }

    private DBManager2(Context context){
        super(context,MY_CARDB_NAME2,null,MY_CARDB_VERSION2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL("create table Idlist(Site char(30) primary key, Id char(30), Pwd char(30), Comment char(100))");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Table 초기화 코드
        db.execSQL("drop table if exists Idlist");
        onCreate(db);
    }
}

