package com.life.good.utils;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static Drawable getDrawable(Context context,String name){
        int resID =context. getResources().getIdentifier(name, "drawable", context.getPackageName());
        Drawable image =context. getResources().getDrawable(resID);
        return image;
    }

    /**
     * 判断是否为手机号码
     * */
    public static boolean isPhoneNumberValid(String phoneNumber) {
        String regExp = "^(1)[0-9]{10}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }
    /**
     * 判断是否为邮箱
     * */
    public static boolean isMailboxValid(String mailBox) {
        Pattern p = Pattern.compile("^([\\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$");
        Matcher m = p.matcher(mailBox);
        return m.matches();
    }

}
