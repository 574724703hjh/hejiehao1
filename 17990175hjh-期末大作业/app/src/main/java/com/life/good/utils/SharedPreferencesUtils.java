package com.life.good.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {
	
	private static final String SP_NAME = "sp";
	private static SharedPreferences sp;
	

	
	public static void saveInt(Context context,String key,int value){
		sp = context.getSharedPreferences(SP_NAME,0);
		sp.edit().putInt(key, value).commit();
	}
	public static void saveString(Context context,String key,String value){
		sp = context.getSharedPreferences(SP_NAME,0);
		sp.edit().putString(key, value).commit();
	}
	public static String getString(Context context,String key,String value){
		if(sp==null){
			sp = context.getSharedPreferences(SP_NAME,0);
		}
		return sp.getString(key,value);
	}
	public static int getInt(Context context,String key,int value){
		if(sp==null){
			 sp = context.getSharedPreferences(SP_NAME,0);
		}
		return sp.getInt(key, value);
	}
	


	
}
