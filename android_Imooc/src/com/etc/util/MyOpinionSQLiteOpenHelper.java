package com.etc.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpinionSQLiteOpenHelper extends SQLiteOpenHelper {

	public MyOpinionSQLiteOpenHelper(Context context) {
		super(context, "Opinion.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table my_opinion (opinion_id integer primary key autoincrement, opin_useraddress varchar(20), opin_content varchar(20), opin_replycontent varcahr(20), opin_date varchar(20)) ");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
