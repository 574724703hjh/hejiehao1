package com.life.good.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 用户的数据表
 */
public class OpenHelper extends SQLiteOpenHelper {
    //建表语句
    public static final String CREATE_USER = "create table User ("
            + "id integer primary key autoincrement, "
            + "username VARCHAR, "
            + "userpwd VARCHAR, "
            + "email VARCHAR, "
            + "imageName VARCHAR, "
            + "gxqm VARCHAR, "
            + "mobile VARCHAR, "
            + "age VARCHAR)";

    public OpenHelper(Context context, String name, CursorFactory factory,
                      int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(CREATE_USER);//创建用户表
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}
