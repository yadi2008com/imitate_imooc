package com.etc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.etc.bean.CourseBean;
import com.etc.bean.MyNotesBean;
import com.etc.bean.PageBean;
import com.etc.bean.PlanBean;
import com.etc.bean.UsersBean;
import com.etc.util.DBUtil;

public class MyCourseDaoImpl implements MyCourseDao {
	DBUtil dbUtil = new DBUtil();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	/**
	 * @throws Exception
	 * @作者 许建皓
	 * @功能 查询已关注的课程
	 * @返回值 courseBeanList
	 */

	@Override
	public List<CourseBean> fetchMyCourseWhereFocusInfo(int user_id, int pageno)
			throws Exception {
		connection = dbUtil.getConnection();
		String sql = "SELECT * FROM course where course_id in (SELECT course_id from mycourse WHERE user_id=? and mycour_focus=1) limit ?,?";
		preparedStatement = connection.prepareStatement(sql);
		int startIndex = (pageno - 1) * PageBean.ROWS_PRE_PAGE;

		preparedStatement.setInt(1, user_id);
		preparedStatement.setInt(2, startIndex);
		preparedStatement.setInt(3, 9);
		resultSet = preparedStatement.executeQuery();
		List<CourseBean> courseBeanList = new ArrayList<CourseBean>();
		while (resultSet.next()) {
			CourseBean courseBean = new CourseBean();
			courseBean.setCourse_id(resultSet.getInt("course_id"));
			courseBean.setCour_title(resultSet.getString("cour_title"));
			courseBean.setCour_image(resultSet.getString("cour_image"));
			courseBean.setCour_url(resultSet.getString("cour_url"));
			courseBean.setCour_duration(resultSet.getString("cour_duration"));
			courseBean.setCour_hot(resultSet.getInt("cour_hot"));
			Date date = new java.util.Date(resultSet.getDate("cour_date")
					.getTime());
			courseBean.setCour_date(date);

			courseBean.setCour_source(resultSet.getString("cour_source"));
			courseBean.setCour_content(resultSet.getString("cour_content"));
			courseBean.setCour_language(resultSet.getString("cour_language"));
			courseBean.setCour_teacher(resultSet.getString("cour_teacher"));
			courseBean.setLanguage_id(resultSet.getInt("language_id"));
			courseBean.setDecoration_id(resultSet.getInt("decoration_id"));
			courseBean.setLevel_name(resultSet.getString("level_name"));
			courseBeanList.add(courseBean);
		}

		return courseBeanList;
	}

	/**
	 * @throws Exception
	 * @作者 许建皓
	 * @功能 查询计划的课程
	 * @返回值 courseBeanList
	 */
	@Override
	public List<PlanBean> fetchMyPlan(int user_id) throws Exception {
		connection = dbUtil.getConnection();
		String sql = "select * from plan where plan_id in (select plan_id from myplan where user_id=?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, user_id);
		resultSet = preparedStatement.executeQuery();
		List<PlanBean> planBeanList = new ArrayList<PlanBean>();
		while (resultSet.next()) {
			PlanBean planBean = new PlanBean();
			planBean.setPlan_id(resultSet.getInt("plan_id"));
			planBean.setPlan_name(resultSet.getString("plan_name"));
			planBean.setPlan_content(resultSet.getString("plan_content"));
			planBean.setPlan_img(resultSet.getString("plan_img"));
			planBeanList.add(planBean);

		}

		return planBeanList;
	}

	@Override
	public List<MyNotesBean> fetchMynote_contentByUser_idAndCourse_id(
			int user_id) throws Exception {
		connection = dbUtil.getConnection();
		List<MyNotesBean> myNotesBeanList = new ArrayList<MyNotesBean>();
		String sql = "SELECT * from mynotes WHERE user_id =?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, user_id);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			MyNotesBean myNotesBean = new MyNotesBean();
			myNotesBean.setMynotes_id(resultSet.getInt("mynotes_id"));
			myNotesBean.setUser_id(resultSet.getInt("user_id"));
			myNotesBean.setCourse_id(resultSet.getInt("course_id"));
			myNotesBean
					.setMynote_content(resultSet.getString("mynote_content"));
			myNotesBeanList.add(myNotesBean);
		}

		return myNotesBeanList;
	}

	@Override
	public List<CourseBean> fetchMyCourseBystateYx(int user_id, int pageno)
			throws Exception {
		connection = dbUtil.getConnection();
		List<CourseBean> courseBeanList = new ArrayList<CourseBean>();
		String sql = "SELECT * FROM course where course_id in (SELECT course_id from mycourse WHERE user_id=? and mycour_state=1) limit ?,?";
		preparedStatement = connection.prepareStatement(sql);
		int startIndex = (pageno - 1) * PageBean.ROWS_PRE_PAGE;
		preparedStatement.setInt(1, user_id);
		preparedStatement.setInt(2, startIndex);
		preparedStatement.setInt(3, 9);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			CourseBean courseBean = new CourseBean();
			courseBean.setCourse_id(resultSet.getInt("course_id"));
			courseBean.setCour_title(resultSet.getString("cour_title"));
			courseBean.setCour_image(resultSet.getString("cour_image"));
			courseBean.setCour_url(resultSet.getString("cour_url"));
			courseBean.setCour_duration(resultSet.getString("cour_duration"));
			courseBean.setCour_hot(resultSet.getInt("cour_hot"));
			Date date = new java.util.Date(resultSet.getDate("cour_date")
					.getTime());
			courseBean.setCour_date(date);
			courseBean.setCour_source(resultSet.getString("cour_source"));
			courseBean.setCour_content(resultSet.getString("cour_content"));
			courseBean.setCour_language(resultSet.getString("cour_language"));
			courseBean.setCour_teacher(resultSet.getString("cour_teacher"));
			courseBean.setLanguage_id(resultSet.getInt("language_id"));
			courseBean.setDecoration_id(resultSet.getInt("decoration_id"));
			courseBean.setLevel_name(resultSet.getString("level_name"));
			courseBeanList.add(courseBean);
		}

		return courseBeanList;
	}

	@Override
	public int fetchMyCourseWhereFocusInfoRows(int user_id) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "select count(*) FROM course where course_id in (SELECT course_id from mycourse WHERE user_id=? and mycour_focus=1)";

		preparedStatement = connection.prepareStatement(sql);

		preparedStatement.setInt(1, user_id);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			rows = resultSet.getInt(1);
		}

		return rows;
	}

	@Override
	public int fetchMyCourseBystateYxRows(int user_id) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "select count(*) FROM course where course_id in (SELECT course_id from mycourse WHERE user_id=? and  mycour_state=1)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, user_id);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			rows = resultSet.getInt(1);

		}
		return rows;
	}

	@Override
	public List<UsersBean> fetchUserByuser_id(int user_id) throws Exception {
		connection = dbUtil.getConnection();
		List<UsersBean> usersBeanList = new ArrayList<UsersBean>();
		String sql = "SELECT * FROM users WHERE user_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, user_id);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			UsersBean usersBean = new UsersBean();
			usersBean.setUser_id(resultSet.getInt("user_id"));
			usersBean.setUsername(resultSet.getString("username"));
			usersBean.setUser_job(resultSet.getString("user_job"));
			usersBean.setUser_city(resultSet.getString("user_city"));
			usersBean.setUser_email(resultSet.getString("user_email"));
			usersBean.setUser_image(resultSet.getString("user_image"));
			usersBean.setUser_sex(resultSet.getString("user_sex"));
			usersBean.setUser_sign(resultSet.getString("user_sign"));
			usersBean.setUserpwd(resultSet.getString("userpwd"));
			usersBeanList.add(usersBean);

		}

		return usersBeanList;
	}

	@Override
	public MyNotesBean fetchMynoteBymynotes_id(int mynotes_idInt)
			throws Exception {
		connection = dbUtil.getConnection();
		MyNotesBean myNotesBean = new MyNotesBean();
		String sql = "SELECT * from mynotes WHERE mynotes_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, mynotes_idInt);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			myNotesBean.setMynotes_id(resultSet.getInt("mynotes_id"));
			myNotesBean.setUser_id(resultSet.getInt("user_id"));
			myNotesBean.setCourse_id(resultSet.getInt("course_id"));
			myNotesBean
					.setMynote_content(resultSet.getString("mynote_content"));

		}

		return myNotesBean;
	}

	@Override
	public List<MyNotesBean> fetchMynoteBymycourse_id(int course_idInt)
			throws Exception {
		connection = dbUtil.getConnection();
		List<MyNotesBean> myNotesBeanList = new ArrayList<MyNotesBean>();
		String sql = "select * from mynotes where course_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, course_idInt);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			MyNotesBean myNotesBean = new MyNotesBean();
			myNotesBean.setMynotes_id(resultSet.getInt("mynotes_id"));
			myNotesBean.setUser_id(resultSet.getInt("user_id"));
			myNotesBean.setCourse_id(resultSet.getInt("course_id"));
			myNotesBean
					.setMynote_content(resultSet.getString("mynote_content"));
			myNotesBeanList.add(myNotesBean);

		}
		return myNotesBeanList;
	}

	@Override
	public List<Map<String, Object>> androidFetchMyNoteListByUser_id(
			int an_user_idInt) throws Exception {
		connection = dbUtil.getConnection();
		List<Map<String, Object>> myNoteList = new ArrayList<Map<String, Object>>();
		String sql = "select course.cour_date,course.course_id,cour_image,cour_title,count(mynotes_id) from course,mynotes where user_id=? and mynotes.course_id=course.course_id GROUP BY cour_title;";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, an_user_idInt);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			Date date = new java.util.Date(resultSet
					.getDate("course.cour_date").getTime());
			int year = date.getYear() + 1900;
			int month = date.getMonth() + 1;
			int day = date.getDate();
			String dateString = "" + year + month + day;
			map.put("cour_date", dateString);
			map.put("course_id", resultSet.getInt("course.course_id"));
			map.put("cour_image", resultSet.getString("cour_image"));
			map.put("cour_title", resultSet.getString("cour_title"));
			map.put("count(mynotes_id)", resultSet.getInt("count(mynotes_id)"));
			myNoteList.add(map);
		}
		return myNoteList;
	}

	@Override
	public List<MyNotesBean> androidFetchMynoteBymycourse_idAndUser_id(
			int course_idInt, int user_idInt) throws Exception {
		connection = dbUtil.getConnection();
		List<MyNotesBean> myNotesBeanList = new ArrayList<MyNotesBean>();
		String sql = "select * from mynotes where course_id=? and user_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, course_idInt);
		preparedStatement.setInt(2, user_idInt);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			MyNotesBean myNotesBean = new MyNotesBean();
			myNotesBean.setMynotes_id(resultSet.getInt("mynotes_id"));
			myNotesBean.setUser_id(resultSet.getInt("user_id"));
			myNotesBean.setCourse_id(resultSet.getInt("course_id"));
			myNotesBean
					.setMynote_content(resultSet.getString("mynote_content"));
			myNotesBeanList.add(myNotesBean);

		}
		return myNotesBeanList;
	}

	@Override
	public int deleteMyNotesByMyNoteId(int mynotes_id) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "delete from mynotes where mynotes_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, mynotes_id);
		rows = preparedStatement.executeUpdate();
		return rows;
	}

}
