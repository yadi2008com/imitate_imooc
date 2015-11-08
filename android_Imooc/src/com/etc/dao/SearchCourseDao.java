package com.etc.dao;

/**
 * @author 郝宝亮
 * @功能 搜索课程中历史记录的具体方法实现
 */
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.etc.util.SearchCourseSQLiteDBUtil;

public class SearchCourseDao {
	private SearchCourseSQLiteDBUtil courseSQLiteDBUtil = null;
	private SQLiteDatabase sqlLiteDatabase = null;

	public SearchCourseDao(Context context) {
		courseSQLiteDBUtil = new SearchCourseSQLiteDBUtil(context);
	}

	/**
	 * @功能 查找所有的历史记录
	 * @return
	 */
	public List<String> fetchSearchCourseTitle() {
		Cursor cursor = null;
		List<String> course_title_list=null;
		try {
			String sql = "select * from SearchCourse";
			sqlLiteDatabase = courseSQLiteDBUtil.getWritableDatabase();
			String[] selectionArgs = null;
			cursor = sqlLiteDatabase.rawQuery(sql, selectionArgs);
			if(null!=cursor){
				course_title_list = new ArrayList<String>();
				while (cursor.moveToNext()) {
					course_title_list.add(cursor.getString(cursor
							.getColumnIndex("cour_title")));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlLiteDatabase != null) {
				sqlLiteDatabase.close();
			}
			if (cursor != null) {
				cursor.close();
			}
		}
		return course_title_list;
	}

	/**
	 * @功能 插入新的查询记录
	 */
	public void insertSearchCourseTitle(String cour_title) {
		try {
			String sql = "insert into SearchCourse (cour_title) values (?)";
			sqlLiteDatabase = courseSQLiteDBUtil.getWritableDatabase();
			Object[] bindArgs = { cour_title };
			sqlLiteDatabase.execSQL(sql, bindArgs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlLiteDatabase != null) {
				sqlLiteDatabase.close();
			}
		}

	}

	public void deleteSearchCourseTitle() {
		try {
			sqlLiteDatabase = courseSQLiteDBUtil.getWritableDatabase();
			sqlLiteDatabase.delete("SearchCourse", null, null);
			// Object[] bindArgs = null;
			// sqlLiteDatabase.execSQL(sql, bindArgs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlLiteDatabase != null) {
				sqlLiteDatabase.close();
			}
		}
	}
}
