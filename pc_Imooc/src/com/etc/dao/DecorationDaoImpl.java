package com.etc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.etc.bean.DecorationBean;
import com.etc.bean.PageBean;
import com.etc.util.DBUtil;

public class DecorationDaoImpl implements DecorationDao {

	DBUtil dbUtil = new DBUtil();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	@Override
	public int decorationInsert(DecorationBean decorationBean) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "insert into decoration (deco_name) values (?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, decorationBean.getDeco_name());
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public DecorationBean decorationEdit(int id) throws Exception {
		DecorationBean decorationBean = null;
		connection = dbUtil.getConnection();
		String sql = "select * from decoration where decoration_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			decorationBean = new DecorationBean();
			decorationBean.setDeco_name(resultSet.getString("deco_name"));
			decorationBean.setDecoration_id(resultSet.getInt("decoration_id"));
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return decorationBean;
	}

	@Override
	public List<DecorationBean> decorationShow() throws Exception {
		List<DecorationBean> decorationBeanList = null;
		connection = dbUtil.getConnection();
		String sql = "select * from decoration";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		decorationBeanList = new ArrayList<DecorationBean>();
		while (resultSet.next()) {
			DecorationBean decorationBean = new DecorationBean();
			decorationBean.setDecoration_id(resultSet.getInt("decoration_id"));
			decorationBean.setDeco_name(resultSet.getString("deco_name"));
			decorationBeanList.add(decorationBean);
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return decorationBeanList;
	}

	@Override
	public int decorationDelete(int id) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "delete from decoration where decoration_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public int decorationUpdate(DecorationBean decorationBean) throws Exception {
		int result = 0;
		connection = dbUtil.getConnection();
		String sql = "update decoration set deco_name=? where decoration_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, decorationBean.getDeco_name());
		preparedStatement.setInt(2, decorationBean.getDecoration_id());
		result = preparedStatement.executeUpdate();
		return result;
	}

	@Override
	public int decorationRows() throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "select count(*) from decoration";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			rows = resultSet.getInt(1);
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public List<DecorationBean> decorationPageShow(int pageno) throws Exception {
		List<DecorationBean> decorationBeanList = null;
		connection = dbUtil.getConnection();
		String sql = " select * from decoration limit ?,? ";
		int startIndex = (pageno - 1) * PageBean.ROWS_PRE_PAGE;
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, startIndex);
		preparedStatement.setInt(2, PageBean.ROWS_PRE_PAGE);
		resultSet = preparedStatement.executeQuery();
		decorationBeanList = new ArrayList<DecorationBean>();
		while (resultSet.next()) {
			DecorationBean decorationBean = new DecorationBean();
			decorationBean.setDecoration_id(resultSet.getInt("decoration_id"));
			decorationBean.setDeco_name(resultSet.getString("deco_name"));
			decorationBeanList.add(decorationBean);
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return decorationBeanList;
	}


}
