package com.mobileapplicationsclass.pykandlxm.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/24 0024.
 */
public class SQLiteDao {
    private MyDatabaseHelper myDatabaseHelper;

    public SQLiteDao(Context context) {
        myDatabaseHelper = new MyDatabaseHelper(context, "fine.db", null, 1);
    }

    public void insert(FineEntity fineEntity) {// 插入记录
        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();// 取得数据库操作
        ContentValues values = new ContentValues();
        values.put("source", fineEntity.getSource());
        values.put("title", fineEntity.getTitle());
        values.put("icon", fineEntity.getIcon());
        values.put("url", fineEntity.getUrl());
        values.put("wechat_id", fineEntity.getWechat_id());
        long id = db.insert("fine", null, values);
        fineEntity.setId(id);
        db.close();
    }


    public int delete(long id) {// 删除纪录
        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();// 取得数据库操作
        int count = db.delete("fine", "_id=?", new String[]{id + ""});
        db.close();
        return count;
    }


    public List<FineEntity> queryAll() {
        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
        Cursor c = db.query("fine", null, null, null, null, null, "_id DESC");
        List<FineEntity> list = new ArrayList<FineEntity>();

        while (c.moveToNext()) {
            long id = c.getLong(c.getColumnIndex("_id"));

            String wechat_id = c.getString(0);
            String source = c.getString(1);
            String title = c.getString(2);
            String icon = c.getString(3);
            String url = c.getString(0);

            list.add(new FineEntity(id, wechat_id, source, title, icon, url));
        }
        c.close();
        db.close();
        return list;
    }
}