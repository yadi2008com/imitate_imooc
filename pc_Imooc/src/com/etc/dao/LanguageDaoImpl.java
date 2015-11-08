package com.etc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.etc.bean.DecorationBean;
import com.etc.bean.LanguageBean;
import com.etc.bean.PageBean;
import com.etc.util.DBUtil;

public class LanguageDaoImpl implements LanguageDao{

	DBUtil dbUtil = new DBUtil();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	@Override
	
	public int fetchAllLanguageListRows() throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "select count(*) from language";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			rows = resultSet.getInt(1);
		}
		return rows;
	}

	//查询语言列表
	@Override
	public List<LanguageBean> fetchAllLanguageList(int pageno) throws Exception {
		List<LanguageBean> languagelist = null;
		connection = dbUtil.getConnection();
		String sql = "select * from language limit ?,?";
		int startIndex = (pageno - 1) * PageBean.ROWS_PRE_PAGE;

		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, startIndex);
		preparedStatement.setInt(2, PageBean.ROWS_PRE_PAGE);

		resultSet = preparedStatement.executeQuery();
		languagelist = new ArrayList<LanguageBean>();
		while (resultSet.next()) {
			LanguageBean languageBean= new LanguageBean();
			languageBean.setLanguage_id(resultSet.getInt("language_id"));
			languageBean.setLang_name(resultSet.getString("lang_name"));
			languageBean.setDecoration_id(resultSet.getInt("decoration_id"));
			languagelist.add(languageBean);
		}
		return languagelist;
	}

	@Override
	public String[] languageFetchAlldecoration(int[] decoration_id, int pageno)
			throws Exception {
		String[] decorationList = new String[decoration_id.length];
		connection = dbUtil.getConnection();
		for (int x = 0; x < decoration_id.length; x++) {
			String sql = "select deco_name from decoration where decoration_id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, decoration_id[x]);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String deco_name = null;
				deco_name = resultSet.getString("deco_name");
				decorationList[x] = deco_name;
			}
		}
		return decorationList;
	}

	@Override
	public int delectLanguageById(int language_id) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "delete from language where language_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, language_id);
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public int selectLanguageByNameRows(String language_name) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "select count(*) from language where lang_name=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, language_name);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			rows = resultSet.getInt(1);
		}
		return rows;
	}

	@Override
	public List<LanguageBean> selectLanguageByName(String language_name,
			int pageno) throws Exception {
        
		List<LanguageBean> languagelist = null;
		connection = dbUtil.getConnection();
		String sql = "select * from language where lang_name=?";
	
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, language_name);

		resultSet = preparedStatement.executeQuery();
		
		languagelist = new ArrayList<LanguageBean>();
		while (resultSet.next()) {
			LanguageBean languageBean= new LanguageBean();
			languageBean.setLanguage_id(resultSet.getInt("language_id"));
			languageBean.setLang_name(resultSet.getString("lang_name"));
			languageBean.setDecoration_id(resultSet.getInt("decoration_id"));
			languagelist.add(languageBean);
			System.out.println(languageBean.getLang_name()+"sadfas");
		}
		
		return languagelist;
	}

	//添加方向
	@Override
	public int addDecoration(String deco_name) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql="insert into decoration (deco_name) values (?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, deco_name);
		rows=preparedStatement.executeUpdate();
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	//查询方向表
	public DecorationBean selectDecorationByName(String deco_name)
			throws Exception {
		DecorationBean decorationBean=null;
		connection=dbUtil.getConnection();
		String sql="select * from decoration where deco_name=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, deco_name);
		resultSet = preparedStatement.executeQuery();
		if(resultSet.next()){
			decorationBean=new DecorationBean();
			decorationBean.setDecoration_id(resultSet.getInt("decoration_id"));
			decorationBean.setDeco_name(resultSet.getString("deco_name"));
		}
		
		return decorationBean;
	}

	@Override
	public int addLanguage(String language_name, int decoration_id)
			throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql="insert into language (lang_name,decoration_id) values (?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, language_name);
		preparedStatement.setInt(2, decoration_id);
		rows=preparedStatement.executeUpdate();
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;
	}

	//查询方向是否重名
	@Override
	public int isDeco_nameRepeat(String deco_name) throws Exception {
		int rows=0;
		connection=dbUtil.getConnection();
		String sql="select * from decoration where deco_name = ?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, deco_name);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			rows = 1;
		}
		return rows;
	}

	@Override
	public int isLaguageRepeat(String language_name) throws Exception {
		int rows=0;
		connection=dbUtil.getConnection();
		String sql="select * from language where lang_name = ?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, language_name);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			rows = 1;
		}
		return rows;
	}

	

}
