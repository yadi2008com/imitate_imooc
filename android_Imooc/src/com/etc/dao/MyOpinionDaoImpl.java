package com.etc.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.etc.bean.MyOpinionBean;
import com.etc.util.MyOpinionSQLiteOpenHelper;

public class MyOpinionDaoImpl implements MyOpinionDao {
private MyOpinionSQLiteOpenHelper helper;
	public MyOpinionDaoImpl(Context context) {
	this.helper = new MyOpinionSQLiteOpenHelper(context);
}
	@Override
	public long insertMyOpinion(MyOpinionBean myOpinionBean) {
		long result=0;
		SQLiteDatabase db=helper.getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put("opin_content", myOpinionBean.getOpin_content());
		values.put("opin_useraddress", myOpinionBean.getOpin_useraddress());
		values.put("opin_date", new Date().toLocaleString());
		result=db.insert("my_opinion", null, values);
		db.close();
		return result;
	}
	@Override
	public List<MyOpinionBean> fetchAllMyOpinion() {
		List<MyOpinionBean> myOpinionBeans=new ArrayList<MyOpinionBean>();
		SQLiteDatabase db=helper.getWritableDatabase();
		Cursor cursor=db.query("my_opinion", null, null, null, null, null, null);
		while (cursor.moveToNext()) {
			MyOpinionBean myOpinionBean=new MyOpinionBean();
			myOpinionBean.setOpin_date(new Date(cursor.getString(cursor.getColumnIndex("opin_date"))) );
			myOpinionBean.setOpin_content(cursor.getString(cursor.getColumnIndex("opin_content")));
			myOpinionBean.setOpin_useraddress(cursor.getString(cursor.getColumnIndex("opin_useraddress")));
			myOpinionBeans.add(myOpinionBean);
		}
		return myOpinionBeans;
	}

}
