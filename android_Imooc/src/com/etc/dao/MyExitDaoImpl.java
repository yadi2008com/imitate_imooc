package com.etc.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.etc.util.LoginUserDBHelper;

public class MyExitDaoImpl implements MyExitDao {
private LoginUserDBHelper helper;

public MyExitDaoImpl(Context context) {
	super();
	this.helper =new LoginUserDBHelper(context);
}

@Override
public int deleteCurrentUserInfo(){
	int result=0;
	SQLiteDatabase db=helper.getWritableDatabase();
	result=db.delete("userinfo", null, null);
	return result;
	
}

}
