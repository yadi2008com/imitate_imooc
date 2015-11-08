package com.etc.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SearchCourseSQLiteDBUtil extends SQLiteOpenHelper {

	private static final int VERSION = 1;
	private static final String DBNAME = "SearchCourse.db";

	public SearchCourseSQLiteDBUtil(Context context) {
		super(context, DBNAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		String sql = "create table SearchCourse(course_id integer primary key,cour_title varchar(50) not null)";
		sqLiteDatabase.execSQL(sql);

	}

	@Override
	public void onUpgrade(SQLiteDatabase dv, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

}
