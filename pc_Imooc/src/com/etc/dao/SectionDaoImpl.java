package com.etc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.etc.bean.SectionBean;
import com.etc.bean.PageBean;
import com.etc.util.DBUtil;

public class SectionDaoImpl implements SectionDao {
	DBUtil dbUtil = new DBUtil();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	@Override
	public int sectionInsert(SectionBean sectionBean) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "insert into section (sect_name,chap_id) values (?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, sectionBean.getSect_name());
		preparedStatement.setInt(2, sectionBean.getChap_id());
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public SectionBean sectionEdit(int id) throws Exception {
		SectionBean sectionBean = null;
		connection = dbUtil.getConnection();
		String sql = "select * from section where section_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			sectionBean = new SectionBean();
			sectionBean.setSection_id(resultSet.getInt("section_id"));
			sectionBean.setSect_name(resultSet.getString("sect_name"));
			sectionBean.setChap_id(resultSet.getInt("chap_id"));
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return sectionBean;
	}

	@Override
	public List<SectionBean> sectionShow() throws Exception {
		List<SectionBean> sectionBeanList = null;
		connection = dbUtil.getConnection();
		String sql = "select * from section";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		sectionBeanList = new ArrayList<SectionBean>();
		while (resultSet.next()) {
			SectionBean sectionBean = new SectionBean();
			sectionBean.setSection_id(resultSet.getInt("section_id"));
			sectionBean.setChap_id(resultSet.getInt("chap_id"));
			sectionBean.setSect_name(resultSet.getString("sect_name"));
			sectionBeanList.add(sectionBean);
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return sectionBeanList;
	}

	@Override
	public int sectionDelete(int id) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "delete from section where section_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public int sectionUpdate(SectionBean sectionBean) throws Exception {
		int result = 0;
		connection = dbUtil.getConnection();
		String sql = "update section set sect_name=?,chap_id=? where section_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, sectionBean.getSect_name());
		preparedStatement.setInt(2, sectionBean.getChap_id());
		preparedStatement.setInt(3, sectionBean.getSection_id());
		result = preparedStatement.executeUpdate();
		return result;
	}

	@Override
	public int sectionRows() throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "select count(*) from section";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			rows = resultSet.getInt(1);
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public List<SectionBean> sectionPageShow(int pageno) throws Exception {
		List<SectionBean> sectionBeanList = null;
		connection = dbUtil.getConnection();
		String sql = " select section.*, chapter.chap_name from section,chapter where section.chap_id=chapter.chapter_id order by chapter.chapter_id   limit ?,? ";
		int startIndex = (pageno - 1) * PageBean.ROWS_PRE_PAGE;
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, startIndex);
		preparedStatement.setInt(2, PageBean.ROWS_PRE_PAGE);
		resultSet = preparedStatement.executeQuery();
		sectionBeanList = new ArrayList<SectionBean>();
		while (resultSet.next()) {
			SectionBean sectionBean = new SectionBean();
			sectionBean.setSection_id(resultSet.getInt("section_id"));
			sectionBean.setSect_name(resultSet.getString("sect_name"));
			sectionBean.setChap_id(resultSet.getInt("chap_id"));
			sectionBean.setChap_name(resultSet.getString("chap_name"));
			sectionBeanList.add(sectionBean);
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return sectionBeanList;
	}


}
