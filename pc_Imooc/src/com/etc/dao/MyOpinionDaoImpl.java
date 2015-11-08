package com.etc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.etc.bean.CourseBean;
import com.etc.bean.MyOpinionBean;
import com.etc.bean.PageBean;
import com.etc.util.DBUtil;

public class MyOpinionDaoImpl implements MyOpinionDao {
	DBUtil dbUtil = new DBUtil();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	@Override
	public int addOpinion(MyOpinionBean myOpinionBean) throws Exception {
		int result = 0;
		DBUtil dbUtil = new DBUtil();
		connection = dbUtil.getConnection();

		String sql = "insert into opinion(opin_useraddress,opin_content,opin_date) values(?,?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, myOpinionBean.getOpin_useraddress());
		preparedStatement.setString(2, myOpinionBean.getOpin_content());
		java.sql.Date tempdate = new java.sql.Date(new Date().getTime());
		preparedStatement.setDate(3, tempdate);
		result = preparedStatement.executeUpdate();
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return result;
	}

	@Override
	public int getOtherOpinionNumber() throws Exception {
		int result = 0;
		DBUtil dbUtil = new DBUtil();
		connection = dbUtil.getConnection();

		String sql = "select count(*) from opinion";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			result = resultSet.getInt(1);
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return result;
	}

	@Override
	public List<MyOpinionBean> getOtherOpinionInfoByPageno(int pageno)
			throws Exception {
		List<MyOpinionBean> otherOpinionList = null;
		connection = dbUtil.getConnection();
		int indexRows = (pageno - 1) * PageBean.ROWS_PRE_PAGE;
		String sql = "select * from opinion limit ?,?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, indexRows);
		preparedStatement.setInt(2, PageBean.ROWS_PRE_PAGE);
		resultSet = preparedStatement.executeQuery();
		otherOpinionList = new ArrayList<MyOpinionBean>();
		while (resultSet.next()) {
			MyOpinionBean myOpinionBean = new MyOpinionBean();
			myOpinionBean.setOpinion_id(resultSet.getInt(1));
			myOpinionBean.setOpin_content(resultSet.getString(3));
			myOpinionBean.setOpin_replycontent(resultSet.getString(4));
			myOpinionBean.setOpin_useraddress(resultSet.getString(2));
			myOpinionBean.setOpin_date(resultSet.getDate(5));
			otherOpinionList.add(myOpinionBean);
		}

		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return otherOpinionList;
	}

}
