package com.etc.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyCourseSQLiteDBUtil extends SQLiteOpenHelper{

	private static final int VERSION = 1;//�������ݿ�汾
	private static final String DBNAME = "userinfo2.db";//�������ݿ���

	
	public MyCourseSQLiteDBUtil(Context context) {
		super(context, DBNAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase  sqLiteDatabase) {
		String sql="create table mycourse (mycourse_id integer primary key,user_id integer(50),course_id integer(50),mycour_name varchar(50) not null,mycour_img varchar(50) not null,mycour_state varchar(50) )";
		sqLiteDatabase.execSQL(sql);
		
		sql="insert into mycourse (mycourse_id,user_id,course_id,mycour_name,mycour_img,mycour_state) values (?,?,?,?,?,?)";
		Object [] bindArgs={1,1,1,"C语言","图片","关注"};
		 
		sqLiteDatabase.execSQL(sql, bindArgs);
		 
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}
