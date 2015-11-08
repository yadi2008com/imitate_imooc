package com.etc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.etc.util.DBUtil;

public class MyPlanDaoImpl implements MyPlanDao{
	DBUtil dbUtil = new DBUtil();
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	@Override
	public int join(int plan_id,int user_id) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "insert into myplan (plan_id,user_id) values (?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1,plan_id);
		preparedStatement.setInt(2,user_id);
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;
	}
	@Override
	 public int delete(int plan_id,int user_id) throws Exception {
		int rows=0;
		connection = dbUtil.getConnection();
		String sql = "delete from  myplan  where plan_id=? and user_id=? ";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1,plan_id);
		preparedStatement.setInt(2,user_id);
		
		rows = preparedStatement.executeUpdate();
		
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;
	}

}
