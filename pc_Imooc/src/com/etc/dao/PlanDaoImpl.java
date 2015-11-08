package com.etc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.etc.bean.CourseBean;
import com.etc.bean.PageBean;
import com.etc.bean.PlanBean;
import com.etc.util.DBUtil;

public class PlanDaoImpl implements PlanDao {
	DBUtil dbUtil = new DBUtil();
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	
	@Override
	public List<PlanBean> fetchAllPlanByPageno(int pageno) throws Exception {
		List<PlanBean> allPlanList = null;
		connection = dbUtil.getConnection();
		int indexrows=(pageno-1)*8;
		String sql = "select * from  plan limit ?,?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, indexrows);
		preparedStatement.setInt(2, 8);
		resultSet = preparedStatement.executeQuery();
		allPlanList = new ArrayList<PlanBean>();// 使用list创建用户信息列表对象
		while (resultSet.next()) {
			PlanBean planBean = new PlanBean();
			planBean.setPlan_id(resultSet.getInt("plan_id"));
			planBean.setPlan_name(resultSet.getString("plan_name"));
			planBean.setPlan_img(resultSet.getString("plan_img"));
			

			allPlanList.add(planBean);// 将登录用户实例对象加入用户信息列表
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return allPlanList;
	
		
	}
	
	@Override
	public int fetchAllPlanRows() throws Exception {
		int rows=0;
		connection=dbUtil.getConnection();
		String sql="select count(*) from plan";
		preparedStatement=connection.prepareStatement(sql);
		resultSet=preparedStatement.executeQuery();
		if(resultSet.next()){
			rows=resultSet.getInt(1);
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;
		
	}
	
	
	@Override
	public List<PlanBean> fetchPlanList(int planid) throws Exception {
		List<PlanBean> planBeanList=null;
		connection = dbUtil.getConnection();
		 
		String sql = "select * from  plan where plan_id=? ";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, planid);
		 
		resultSet = preparedStatement.executeQuery();
		planBeanList = new ArrayList<PlanBean>();// 使用list创建用户信息列表对象
		while (resultSet.next()) {
			PlanBean planBean = new PlanBean();
			planBean.setPlan_content(resultSet.getString("plan_content"));
			planBean.setPlan_id(resultSet.getInt("plan_id"));
			planBean.setPlan_name(resultSet.getString("plan_name"));
			planBean.setPlan_img(resultSet.getString("plan_img"));
			

			planBeanList.add(planBean);// 将登录用户实例对象加入用户信息列表
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return planBeanList;
	}
	@Override
	public int getDecorationCourseRows(int id) throws Exception {
		int result=0;
		try {
			DBUtil dbUtil=new DBUtil();
			connection=dbUtil.getConnection();
			String sql="select count(*) from plan where decoration_id=?";
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				result=resultSet.getInt(1);
			}
			dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		} catch (Exception e) {
		}
		return result;
		
	}
	@Override
	public List<PlanBean> fetchDecCourseInfoList(int pageno, int id)
			throws Exception {
		List<PlanBean> planBeanInfoList = null;
		connection = dbUtil.getConnection();
		int indexRows=(pageno-1)*PageBean.ROWS_PRE_PAGE;
		String sql = "select * from plan where decoration_id=? limit ?,?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		preparedStatement.setInt(2, indexRows);
		preparedStatement.setInt(3, PageBean.ROWS_PRE_PAGE);
		resultSet = preparedStatement.executeQuery();
		planBeanInfoList = new ArrayList<PlanBean>();
		while(resultSet.next()){
			PlanBean planBean = new PlanBean();
			planBean.setPlan_id(resultSet.getInt("plan_id"));
			planBean.setPlan_img(resultSet.getString("plan_img"));
			planBean.setPlan_name(resultSet.getString("plan_name"));
			planBean.setDecoration_id(resultSet.getInt("decoration_id"));
			planBeanInfoList.add(planBean);
		}
		
		dbUtil.closeDBSource(connection,preparedStatement,resultSet);
		return planBeanInfoList;
	}

	@Override
	public int planInsert(PlanBean planBean) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "insert into plan (plan_name,plan_content,plan_img,decoration_id) values (?,?,?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, planBean.getPlan_name());
		preparedStatement.setString(2, planBean.getPlan_content());
		preparedStatement.setString(3, planBean.getPlan_img());
		preparedStatement.setInt(4, planBean.getDecoration_id());
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public PlanBean planEdit(int id) throws Exception {
		PlanBean planBean = null;
		connection = dbUtil.getConnection();
		String sql = "select * from plan where plan_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			planBean = new PlanBean();
			planBean.setPlan_name(resultSet.getString("plan_name"));
			planBean.setPlan_content(resultSet.getString("plan_content"));
			planBean.setPlan_id(resultSet.getInt("plan_id"));
			planBean.setPlan_img(resultSet.getString("plan_img"));
			planBean.setDecoration_id(resultSet.getInt("decoration_id"));
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return planBean;
	}

	@Override
	public List<PlanBean> planShow() throws Exception {
		List<PlanBean> planBeanList = null;
		connection = dbUtil.getConnection();
		String sql = "select * from plan";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		planBeanList = new ArrayList<PlanBean>();
		while (resultSet.next()) {
			PlanBean planBean = new PlanBean();
			planBean.setPlan_id(resultSet.getInt("plan_id"));
			planBean.setPlan_name(resultSet.getString("plan_name"));
			planBean.setPlan_content(resultSet.getString("plan_content"));
			planBeanList.add(planBean);
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return planBeanList;
	}

	@Override
	public int planDelete(int id) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "delete from plan where plan_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;
	}


	
	@Override
	public int planUpdate(PlanBean planBean) throws Exception {
		int result = 0;
		connection = dbUtil.getConnection();
		String sql = "update plan set plan_name=?,plan_content=?,plan_img=?,decoration_id=? where plan_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, planBean.getPlan_name());
		preparedStatement.setString(2, planBean.getPlan_content());
		preparedStatement.setString(3, planBean.getPlan_img());
		preparedStatement.setInt(4, planBean.getDecoration_id());
		preparedStatement.setInt(5, planBean.getPlan_id());
		result = preparedStatement.executeUpdate();
		return result;
	}

	@Override
	public int planRows() throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "select count(*) from plan";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			rows = resultSet.getInt(1);
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public List<PlanBean> planPageShow(int pageno) throws Exception {
		List<PlanBean> planBeanList = null;
		connection = dbUtil.getConnection();
		String sql = " select * from plan limit ?,? ";
		int startIndex = (pageno - 1) * PageBean.ROWS_PRE_PAGE;
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, startIndex);
		preparedStatement.setInt(2, PageBean.ROWS_PRE_PAGE);
		resultSet = preparedStatement.executeQuery();
		planBeanList = new ArrayList<PlanBean>();
		while (resultSet.next()) {
			PlanBean planBean = new PlanBean();
			planBean.setPlan_id(resultSet.getInt("plan_id"));
			planBean.setPlan_name(resultSet.getString("plan_name"));
			planBean.setPlan_content(resultSet.getString("plan_content"));
			planBeanList.add(planBean);
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return planBeanList;
	}


}
