package com.mobileapplicationsclass.pykandlxm.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/9/24 0024.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {
    //    public static final String CREATE_BOOK = "create table fine（"
//            + "id integer primary key autoincrement,"
//            + "source text,"
//            + "title varchar(50),"
//            + "icon varchar(120),"
//            + "url varchar(120))";
//    public static final String CREATE_BOOK = "create table fine(_id integer primary key autoincrement," +
//            "source text," +
//            "title varchar(50)" +
//            "icon varchar(120)" +
//            "url varchar(120))";


    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS fine(_id integer primary key autoincrement,source text,title text,icon text,url text, wechat_id text not null unique )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
