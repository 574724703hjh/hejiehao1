package com.life.good.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class CarHelper extends SQLiteOpenHelper {

    //建表语句
    public static final String CREATE_CAR= "create table car ("
            + "id integer primary key autoincrement, "
            + "userId TEXT, "
            + "goodsId INTEGER, "
            + "goodsNum INTEGER, "
            + "goodsname VARCHAR, "
            + "photo VARCHAR, "
            + "price VARCHAR, "
            + "choosed VARCHAR)";

    public CarHelper(Context context, String name, CursorFactory factory,
                     int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CAR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
