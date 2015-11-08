package com.etc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.etc.bean.LevelBean;
import com.etc.bean.PageBean;
import com.etc.util.DBUtil;

public class LevelDaoImpl implements LevelDao{
	DBUtil dbUtil = new DBUtil();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	@Override
	//等级列表
	public List<LevelBean> levelFetchAllList(int pageno) throws Exception {
		List<LevelBean> levellist=null;
		connection=dbUtil.getConnection();
		String sql="select * from level limit ?,?";
		int startIndex = (pageno - 1) * PageBean.ROWS_PRE_PAGE;
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setInt(1, startIndex);
		preparedStatement.setInt(2, PageBean.ROWS_PRE_PAGE);
		resultSet = preparedStatement.executeQuery();
		levellist=new ArrayList<LevelBean>();
		while(resultSet.next()){
			LevelBean levelBean=new LevelBean();
			levelBean.setLevel_id(resultSet.getInt("level_id"));
			levelBean.setLeve_name(resultSet.getString("leve_name"));
			levellist.add(levelBean);
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return levellist;
	}

	@Override
	public int levelFetchAllListRows() throws Exception {
		int rows=0;
		connection=dbUtil.getConnection();
		String sql="select count(*) from level";
		preparedStatement=connection.prepareStatement(sql);
		resultSet=preparedStatement.executeQuery();
		if(resultSet.next()){
			rows = resultSet.getInt(1);
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	//查询等级
	public List<LevelBean> selectLevelByUsersName(String leve_name, int pageno)
			throws Exception {
		List<LevelBean> levellist=null;
		connection=dbUtil.getConnection();
		String sql="select * from level where leve_name=? limit ?,?";
		int startIndex = (pageno - 1) * PageBean.ROWS_PRE_PAGE;
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, leve_name);
		preparedStatement.setInt(2, startIndex);
		preparedStatement.setInt(3, PageBean.ROWS_PRE_PAGE);
		resultSet = preparedStatement.executeQuery();
		levellist=new ArrayList<LevelBean>();
		while(resultSet.next()){
			LevelBean levelBean=new LevelBean();
			levelBean.setLevel_id(resultSet.getInt("level_id"));
			levelBean.setLeve_name(resultSet.getString("leve_name"));
			levellist.add(levelBean);
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return levellist;
	}

	@Override
	public int selectLevelByUsersNameRows(String leve_name) throws Exception {
		int rows=0;
		connection=dbUtil.getConnection();
		String sql="select count(*) from level where leve_name=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, leve_name);
		resultSet=preparedStatement.executeQuery();
		if(resultSet.next()){
			rows = resultSet.getInt(1);
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	//添加等级
	public int addlevel(String leve_name) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql="insert into level (leve_name) values (?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, leve_name);
		rows=preparedStatement.executeUpdate();
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	//删除等级
	public int delectLevelById(int level_id) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "delete from level where level_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, level_id);
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	//修改等级
	public int updatelevel(int level_id, String leve_name) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql="update level set leve_name=? where level_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, leve_name);
		preparedStatement.setInt(2, level_id);
		rows=preparedStatement.executeUpdate();
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;
	}

	//查询等级是否重名
	@Override
	public int isLevelRepeat(String leve_name) throws Exception {
		int rows=0;
		connection=dbUtil.getConnection();
		String sql="select * from level where leve_name = ?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, leve_name);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			rows = 1;
		}
		return rows;
	}

}
