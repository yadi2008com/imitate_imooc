package com.etc.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
 
import com.etc.bean.MyCourseBean;
import com.etc.util.MyCourseSQLiteDBUtil;

public class MyCourseDaoImpl implements MyCourseDao {

	private MyCourseSQLiteDBUtil courseSQLiteDBUtil = null;
	private SQLiteDatabase sqLiteDatabase = null;

	public MyCourseDaoImpl(Context context) {
		courseSQLiteDBUtil = new MyCourseSQLiteDBUtil(context);
	}

	@Override
	public List<MyCourseBean> fetchMyCourseListByUserId(int userid)   {
		List<MyCourseBean> myCourseBeanList=null;
		String sql = "select * from mycourse where user_id=? ";
		sqLiteDatabase = courseSQLiteDBUtil.getWritableDatabase();
		String[] selectionArgs = { userid + "" };
		Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
		myCourseBeanList = new ArrayList<MyCourseBean>();
		while (cursor.moveToNext()) {
			MyCourseBean myCourseBean = new MyCourseBean();
			myCourseBean.setMycourse_id(cursor.getInt(cursor.getColumnIndex("mycourse_id")));
			myCourseBean.setUser_id(cursor.getInt(cursor.getColumnIndex("user_id")));
			myCourseBean.setMycour_name(cursor.getString(cursor
					.getColumnIndex("mycour_name")));
			myCourseBean.setMycour_img(cursor.getString(cursor
					.getColumnIndex("mycour_img")));
			myCourseBean.setMycour_state(cursor.getString(cursor
					.getColumnIndex("mycour_state")));
			myCourseBean.setCourse_id(cursor.getInt(cursor.getColumnIndex("course_id")));
			myCourseBeanList.add(myCourseBean);
		}
		cursor.close();
		sqLiteDatabase.close(); 
		return myCourseBeanList;
	}
	 
 

}
