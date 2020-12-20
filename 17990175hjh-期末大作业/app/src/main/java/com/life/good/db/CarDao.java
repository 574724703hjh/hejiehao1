package com.life.good.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.life.good.bean.Good;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户的数据库表操作工具
 */
public class CarDao {
    public static final String DB_NAME = "car";

    public static final int VERSION = 1;

    private static CarDao sGoodsDao;

    private SQLiteDatabase db;

    private CarDao(Context context) {
        CarHelper dbHelper = new CarHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public synchronized static CarDao getInstance(Context context) {
        if (sGoodsDao == null) {
            sGoodsDao = new CarDao(context);
        }
        return sGoodsDao;
    }

    public ContentValues getContentValues(Car car){
        ContentValues cv = new ContentValues();
        cv.put("userId", car.getUserId());
        cv.put("goodsNum", car.getGoodsNum());
        cv.put("price", car.getPrice());
        cv.put("goodsname", car.getGoodsname());
        cv.put("photo", car.getGoodimg());
        cv.put("choosed", car.getChoosed());
        return cv;
    }



    public List<Car> searchGoods (){
        ArrayList<Car> orderList = new ArrayList<>();
        Cursor cursor = db.query("car", null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String userId = cursor.getString(cursor.getColumnIndex("userId"));
                int goodsNum = cursor.getInt(cursor.getColumnIndex("goodsNum"));
                String goodsname = cursor.getString(cursor.getColumnIndex("goodsname"));
                String price = cursor.getString(cursor.getColumnIndex("price"));
                String photo = cursor.getString(cursor.getColumnIndex("photo"));
                String choosed = cursor.getString(cursor.getColumnIndex("choosed"));
                Car order = new Car( id,  userId,  goodsNum,  goodsname,  price,photo,choosed);
                orderList.add(order);
            }
            cursor.close();
        }
        return orderList;
    }




    public void delete(Car car) {
        db.delete("car","id"+ "= ? ", new String[]{car.getId()+""});
    }
    public void update(Car car){
        if (car != null) {
            db.update("car",getContentValues(car),"id"+ "= ? ", new String[]{car.getId()+""}) ;
        }
    }


    public void insert(String userId, Good goods, int num) {
        if (goods != null) {
            Cursor cursor = db .query("car", null, "goodsname=?", new String[]{goods.getName()+""}, null, null, null);
            //cursor长度大于0说明购物车已经有这个商品,注册失败
            if (cursor.getCount() > 0) {
                cursor.moveToNext();
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                int goodsNum = cursor.getInt(cursor.getColumnIndex("goodsNum"));
                Car car =  new  Car( userId+"", num+goodsNum, goods.getName(), goods.getPrice()+"", goods.getImg(),"0") ;
                db.update("car",getContentValues(car),"id"+ "= ? ", new String[]{id+""});
            } else {
                try {
                    //插入数据库
                    Car car =  new  Car( userId+"", num, goods.getName(), goods.getPrice()+"", goods.getImg(),"0") ;
                    db.insert("car",null,getContentValues(car));
                } catch (Exception e) {
                    Log.d("错误", e.getMessage().toString());
                }
            }
        }

    }


}
