package com.etc.dao;

import java.util.ArrayList;
import java.util.List;

import com.etc.bean.UsersBean;
import com.etc.util.LoginUserDBHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SelectUserInfoDao {
	private LoginUserDBHelper loginUserDBHelper = null;
	private SQLiteDatabase sqlLiteDatabase = null;

	public SelectUserInfoDao(Context context) {
		loginUserDBHelper = new LoginUserDBHelper(context);
	}

	public UsersBean SelectLoginUser() {
		Cursor cursor = null;
		UsersBean usersBean = null;
		try {
			String sql = "select * from userinfo";
			sqlLiteDatabase = loginUserDBHelper.getWritableDatabase();
			String[] selectionArgs = null;
			cursor = sqlLiteDatabase.rawQuery(sql, selectionArgs);
			if (null != cursor) {
				usersBean = new UsersBean();
				while (cursor.moveToNext()) {
					usersBean.setUser_id(cursor.getInt(cursor
							.getColumnIndex("user_id")));
					usersBean.setUsername(cursor.getString(cursor.getColumnIndex("username")));
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
		return usersBean;
	}
}
