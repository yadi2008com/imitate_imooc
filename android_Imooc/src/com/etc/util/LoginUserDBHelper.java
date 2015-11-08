package com.etc.util;
 
 import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
     /**
      * 建立一个数据库帮助类
      */
 public class LoginUserDBHelper extends SQLiteOpenHelper {
     //userinfo.db-->数据库名
     public LoginUserDBHelper(Context context) {
         super(context, "userinfo.db", null, 1);
     }
     
     /**
      * 在userinfo.db数据库下创建一个userinfo表存储下载信息
      */
     @Override
     public void onCreate(SQLiteDatabase db) {
    	 db.execSQL("create table userinfo(user_id integer PRIMARY KEY AUTOINCREMENT, " +
 				"user_email varchar(50), username varchar(50), userpwd varchar(50), " +
 				"user_job varchar(50), user_city varchar(50), user_sex varchar(10), " +
 				"user_sign varchar(50), user_image varchar(50))");
     }
     @Override
     public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 
     }
 
 }