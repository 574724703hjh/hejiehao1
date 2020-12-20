package com.life.good.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.HashMap;

/**
 * 用户的数据库表操作工具
 */
public class UserDB {
    /**
     * 数据库名
     */
    public static final String DB_NAME = "user";
    /**
     * 数据库版本
     */
    public static final int VERSION = 1;
    private static UserDB userDB;

    private SQLiteDatabase db;

    private UserDB(Context context) {
        OpenHelper dbHelper = new OpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }
    /**
     * 获取SqliteDB实例
     * @param context
     */
    public synchronized static UserDB getInstance(Context context) {
        if (userDB == null) {
            userDB = new UserDB(context);
        }
        return userDB;
    }
    public ContentValues getContentValues(User user){
        ContentValues cv = new ContentValues();
        cv.put("username", user.getUsername());
        cv.put("userpwd", user.getUserpwd());
        cv.put("email", user.getEmail());
        cv.put("gxqm", user.getGxqm());
        cv.put("imageName", user.getImageName());
        cv.put("mobile", user.getMobile());
        cv.put("age", user.getAge());
        return cv;

    }

    /**
     * 更新用户信息
     * @param user
     */
    public void updataUser(User user){
        if (user != null) {
          db.update("User",getContentValues(user),"id"+ "= ? ", new String[]{user.getId()+""}) ;
        }
    }

    /**
     * 将User实例存储到数据库。
     */
    public int  registerUser(User user) {
        if (user != null) {
            Cursor cursor = db .query("User", null, "username=?", new String[]{user.getUsername().toString()}, null, null, null);
            //cursor长度大于0说明数据已经有了该用户,注册失败
            if (cursor.getCount() > 0) {
                return -1;
            } else {
                try {
                    //插入数据库
                    db.insert("User",null,getContentValues(user));
                } catch (Exception e) {
                    Log.d("错误", e.getMessage().toString());
                }
                return 1;
            }
        }
        else {
            return 0;
        }
    }

    /**
     * 从数据库读取User信息。
     */
    public User loadUser(int id) {
        User user = null;
        Cursor cursor = db .query("User", null, "id"+"="+id, null, null, null, null);
        if(cursor!=null){
            if(cursor.getCount()>0){
                cursor.moveToFirst();
                int id1  = cursor.getInt(cursor.getColumnIndex("id"));
                String username  = cursor.getString(cursor.getColumnIndex("username"));
                String userPwd  = cursor.getString(cursor.getColumnIndex("userpwd"));
                String email  = cursor.getString(cursor.getColumnIndex("email"));
                String imageName  = cursor.getString(cursor.getColumnIndex("imageName"));
                String mobile  = cursor.getString(cursor.getColumnIndex("mobile"));
                String des  = cursor.getString(cursor.getColumnIndex("gxqm"));
                String age  = cursor.getString(cursor.getColumnIndex("age"));
                user = new User(id1,username,userPwd,email,imageName,mobile,age,des);

            }
            cursor.close();
        }
        return user;
    }

    /**
     * 查找用户,验证登录
     * @param pwd
     * @param name
     * @return
     */
    public int login(String pwd,String name){
        HashMap<String,String> hashmap=new HashMap<String,String>();
        //根据用户名查找数据库
        Cursor cursor = db .query("User", null, "username=?", new String[]{name}, null, null, null);
        //大于0说明有这个用户名
        if (cursor.getCount()>0){
            //根据用户名和密码查找数据
            Cursor pwdcursor = db .query("User", null, "userpwd=?"+" and "+"username=?", new String[]{pwd,name
            }, null, null, null);
            if (pwdcursor.getCount()>0){
                //说明有这个用户,且密码对的
                cursor.moveToNext();
                //返回该用户的id
                return cursor.getInt(cursor.getColumnIndex("id"));
            }else {
                //说明密码错误
                return -1;
            }
        }else {
            //说明没有这个用户名
            return -2;
        }


    }
}
