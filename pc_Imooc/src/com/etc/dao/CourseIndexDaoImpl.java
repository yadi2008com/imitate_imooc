package com.etc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.etc.bean.CourseBean;
import com.etc.util.DBUtil;

public class CourseIndexDaoImpl implements CourseIndexDao {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	@Override
	public List<CourseBean> fetchAllCourseIndex() throws Exception {
		List<CourseBean> courseBeans = new ArrayList<CourseBean>();
		DBUtil dbUtil = new DBUtil();
		connection = dbUtil.getConnection();
		String sql = "select * from course ";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			CourseBean courseBean = new CourseBean();
			// json关于时间里类型的处理
			Date date = null;
			if (resultSet.getDate("cour_date") != null) {
				date = new java.util.Date(resultSet.getDate("cour_date")
						.getTime());
			}
			courseBean.setCour_date(date);

			courseBean.setCour_content(resultSet.getString("cour_content"));
			courseBean.setCour_duration(resultSet.getString("cour_duration"));
			courseBean.setCour_hot(resultSet.getInt("cour_hot"));
			courseBean.setCour_image(resultSet.getString("cour_image"));
			courseBean.setCour_language(resultSet.getString("cour_language"));
			courseBean.setCour_source(resultSet.getString("cour_source"));
			courseBean.setCour_teacher(resultSet.getString("cour_teacher"));
			courseBean.setCour_title(resultSet.getString("cour_title"));
			courseBean.setCourse_id(resultSet.getInt("course_id"));
			courseBean.setDecoration_id(resultSet.getInt("decoration_id"));
			courseBean.setCour_url(resultSet.getString("cour_url"));
			courseBean.setLevel_name(resultSet.getString("level_name"));
			courseBean.setLanguage_id(resultSet.getInt("language_id"));
			courseBeans.add(courseBean);
		}
		return courseBeans;
	}

	@Override
	public List<CourseBean> androidFetchCourseLimit(int row, int rowAll)
			throws Exception {
		List<CourseBean> courseBeans = new ArrayList<CourseBean>();
		DBUtil dbUtil = new DBUtil();
		connection = dbUtil.getConnection();
		String sql = "select * from course limit ?,?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, row);
		preparedStatement.setInt(2, rowAll);

		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			CourseBean courseBean = new CourseBean();
			// json关于时间里类型的处理
			Date date = null;
			if (resultSet.getDate("cour_date") != null) {
				date = new java.util.Date(resultSet.getDate("cour_date")
						.getTime());
			}
			courseBean.setCour_date(date);

			courseBean.setCour_content(resultSet.getString("cour_content"));
			courseBean.setCour_duration(resultSet.getString("cour_duration"));
			courseBean.setCour_hot(resultSet.getInt("cour_hot"));
			courseBean.setCour_image(resultSet.getString("cour_image"));
			courseBean.setCour_language(resultSet.getString("cour_language"));
			courseBean.setCour_source(resultSet.getString("cour_source"));
			courseBean.setCour_teacher(resultSet.getString("cour_teacher"));
			courseBean.setCour_title(resultSet.getString("cour_title"));
			courseBean.setCourse_id(resultSet.getInt("course_id"));
			courseBean.setDecoration_id(resultSet.getInt("decoration_id"));
			courseBean.setCour_url(resultSet.getString("cour_url"));
			courseBean.setLevel_name(resultSet.getString("level_name"));
			courseBean.setLanguage_id(resultSet.getInt("language_id"));
			courseBeans.add(courseBean);
		}
		return courseBeans;
	}
}
