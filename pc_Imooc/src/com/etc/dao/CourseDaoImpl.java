package com.etc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.etc.bean.CourseBean;
import com.etc.bean.DecorationBean;
import com.etc.bean.LanguageBean;
import com.etc.bean.LevelBean;
import com.etc.bean.PageBean;
import com.etc.util.DBUtil;

public class CourseDaoImpl implements CourseDao {
	DBUtil dbUtil = new DBUtil();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	@Override
	/**
	 * @功能: 得到课程表的行数
	 * @返回值  int 课程表的行数
	 * @作者 陈雅迪
	 * */
	public int getCourseInfoRows() throws Exception {
		int result = 0;
		try {
			DBUtil dbUtil = new DBUtil();
			connection = dbUtil.getConnection();
			String sql = "select count(*) from course";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = resultSet.getInt(1);
			}
			dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * @功能: 得到课程方向列表
	 * @返回值 list集合
	 * @作者 陈雅迪
	 * */
	@Override
	public List<DecorationBean> fetchAllDecorationInfoList() throws Exception {
		List<DecorationBean> decorationInfoList = null;
		connection = dbUtil.getConnection();
		String sql = "select * from decoration";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		decorationInfoList = new ArrayList<DecorationBean>();
		while (resultSet.next()) {
			DecorationBean decorationBean = new DecorationBean();
			decorationBean.setDecoration_id(resultSet.getInt("decoration_id"));
			decorationBean.setDeco_name(resultSet.getString("deco_name"));
			decorationInfoList.add(decorationBean);
		}

		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return decorationInfoList;
	}

	/**
	 * @功能: 按课程筛选返回list集合
	 * @返回值 list集合
	 * @作者 陈雅迪
	 * */

	@Override
	public List<CourseBean> fetchAllCourseInfoList(int pageno) throws Exception {
		List<CourseBean> courseBeanInfoList = null;
		connection = dbUtil.getConnection();
		int indexRows = (pageno - 1) * PageBean.ROWS_PRE_PAGE;
		String sql = "select * from course limit ?,?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, indexRows);
		preparedStatement.setInt(2, PageBean.ROWS_PRE_PAGE);
		resultSet = preparedStatement.executeQuery();
		courseBeanInfoList = new ArrayList<CourseBean>();
		while (resultSet.next()) {
			CourseBean courseBean = new CourseBean();
			courseBean.setCourse_id(resultSet.getInt("course_id"));
			courseBean.setCour_title(resultSet.getString("cour_title"));
			courseBean.setCour_image(resultSet.getString("cour_image"));
			courseBean.setCour_url(resultSet.getString("cour_url"));
			courseBean.setCour_duration(resultSet.getString("cour_duration"));
			courseBean.setCour_hot(resultSet.getInt("cour_hot"));
			courseBean.setCour_date(resultSet.getDate("cour_date"));
			courseBean.setCour_source(resultSet.getString("cour_source"));
			courseBean.setCour_content(resultSet.getString("cour_content"));
			courseBean.setCour_language(resultSet.getString("cour_language"));
			courseBean.setCour_teacher(resultSet.getString("cour_teacher"));
			courseBean.setLanguage_id(resultSet.getInt("language_id"));
			courseBean.setDecoration_id(resultSet.getInt("decoration_id"));
			courseBean.setLevel_name(resultSet.getString("level_name"));
			courseBeanInfoList.add(courseBean);
		}

		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return courseBeanInfoList;
	}

	/**
	 * @功能: 得到课程难度列表
	 * @返回值 list集合
	 * @作者 陈雅迪
	 * */

	@Override
	public List<LevelBean> fetchAllLevelBeanInfoList() throws Exception {
		List<LevelBean> levelBeanInfoList = null;
		connection = dbUtil.getConnection();
		String sql = "select * from level";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		levelBeanInfoList = new ArrayList<LevelBean>();
		while (resultSet.next()) {
			LevelBean levelBean = new LevelBean();
			levelBean.setLevel_id(resultSet.getInt(1));
			levelBean.setLeve_name(resultSet.getString(2));
			levelBeanInfoList.add(levelBean);
		}

		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return levelBeanInfoList;
	}

	/**
	 * @功能: 列出课程语言列表
	 * @返回值 list集合
	 * @作者 陈雅迪
	 * */
	@Override
	public List<LanguageBean> fetchAllLanguageInfoList() throws Exception {
		List<LanguageBean> languageInfoList = null;
		connection = dbUtil.getConnection();
		String sql = "select * from language";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		languageInfoList = new ArrayList<LanguageBean>();
		while (resultSet.next()) {
			LanguageBean languageBean = new LanguageBean();
			languageBean.setLanguage_id(resultSet.getInt(1));
			languageBean.setLang_name(resultSet.getString(2));
			languageBean.setDecoration_id(resultSet.getInt(3));
			languageInfoList.add(languageBean);
		}

		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return languageInfoList;
	}

	/**
	 * @功能:得到课程表的行数
	 * @参数：方向id
	 * @返回值 int 课程表的行数
	 * @作者 陈雅迪
	 * */
	@Override
	public int getDecorationCourseRows(int id) throws Exception {
		int result = 0;
		try {
			DBUtil dbUtil = new DBUtil();
			connection = dbUtil.getConnection();
			String sql = "select count(*) from course where decoration_id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = resultSet.getInt(1);
			}
			dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * @功能: 列出所有课程按方向列出
	 * @参数：方向id，页面数量
	 * @返回值 list集合
	 * @作者 陈雅迪
	 * */
	@Override
	public List<CourseBean> fetchAllCourseInfoList(int pageno, int id)
			throws Exception {
		List<CourseBean> courseBeanInfoList = null;
		connection = dbUtil.getConnection();

		int indexRows = (pageno - 1) * PageBean.ROWS_PRE_PAGE;
		String sql = "select * from course where decoration_id=? limit ?,?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		preparedStatement.setInt(2, indexRows);
		preparedStatement.setInt(3, PageBean.ROWS_PRE_PAGE);

		resultSet = preparedStatement.executeQuery();
		courseBeanInfoList = new ArrayList<CourseBean>();
		while (resultSet.next()) {
			CourseBean courseBean = new CourseBean();
			courseBean.setCourse_id(resultSet.getInt("course_id"));
			courseBean.setCour_title(resultSet.getString("cour_title"));
			courseBean.setCour_image(resultSet.getString("cour_image"));
			courseBean.setCour_url(resultSet.getString("cour_url"));
			courseBean.setCour_duration(resultSet.getString("cour_duration"));
			courseBean.setCour_hot(resultSet.getInt("cour_hot"));
			courseBean.setCour_date(resultSet.getDate("cour_date"));
			courseBean.setCour_source(resultSet.getString("cour_source"));
			courseBean.setCour_content(resultSet.getString("cour_content"));
			courseBean.setCour_language(resultSet.getString("cour_language"));
			courseBean.setCour_teacher(resultSet.getString("cour_teacher"));
			courseBean.setLanguage_id(resultSet.getInt("language_id"));
			courseBean.setDecoration_id(resultSet.getInt("decoration_id"));
			courseBean.setLevel_name(resultSet.getString("level_name"));
			courseBeanInfoList.add(courseBean);
		}

		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return courseBeanInfoList;
	}

	/**
	 * @功能:得到课程表的行数
	 * @参数：语言种类id
	 * @返回值 int 课程表的行数
	 * @作者 陈雅迪
	 * */
	@Override
	public int getLanguageCourseRows(int id) throws Exception {
		int result = 0;
		try {
			DBUtil dbUtil = new DBUtil();
			connection = dbUtil.getConnection();
			String sql = "select count(*) from course where  language_id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = resultSet.getInt(1);
			}
			dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * @功能: 列出以语言种类列出课程
	 * @参数：语言种类id，页面数量
	 * @返回值 list集合
	 * @作者 陈雅迪
	 * */
	@Override
	public List<CourseBean> fetchAllLanCourseInfoList(int pageno, int id)
			throws Exception {
		List<CourseBean> courseBeanInfoList = null;
		connection = dbUtil.getConnection();

		int indexRows = (pageno - 1) * PageBean.ROWS_PRE_PAGE;
		String sql = "select * from course where  language_id=?  limit ?,?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		preparedStatement.setInt(2, indexRows);
		preparedStatement.setInt(3, PageBean.ROWS_PRE_PAGE);

		resultSet = preparedStatement.executeQuery();
		courseBeanInfoList = new ArrayList<CourseBean>();
		while (resultSet.next()) {
			CourseBean courseBean = new CourseBean();
			courseBean.setCourse_id(resultSet.getInt("course_id"));
			courseBean.setCour_title(resultSet.getString("cour_title"));
			courseBean.setCour_image(resultSet.getString("cour_image"));
			courseBean.setCour_url(resultSet.getString("cour_url"));
			courseBean.setCour_duration(resultSet.getString("cour_duration"));
			courseBean.setCour_hot(resultSet.getInt("cour_hot"));
			courseBean.setCour_date(resultSet.getDate("cour_date"));
			courseBean.setCour_source(resultSet.getString("cour_source"));
			courseBean.setCour_content(resultSet.getString("cour_content"));
			courseBean.setCour_language(resultSet.getString("cour_language"));
			courseBean.setCour_teacher(resultSet.getString("cour_teacher"));
			courseBean.setLanguage_id(resultSet.getInt("language_id"));
			courseBean.setDecoration_id(resultSet.getInt("decoration_id"));
			courseBean.setLevel_name(resultSet.getString("level_name"));
			courseBeanInfoList.add(courseBean);
		}

		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return courseBeanInfoList;
	}

	/**
	 * @功能:按难度系数得到课程表的行数
	 * @参数：水平种类id
	 * @返回值 int 课程表的行数
	 * @作者 陈雅迪
	 * */
	@Override
	public int getLevelCourseRows(int id) throws Exception {
		int result = 0;
		try {
			DBUtil dbUtil = new DBUtil();
			connection = dbUtil.getConnection();
			String sql = "select count(*) from level where level_id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = resultSet.getInt(1);
			}
			dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * @功能: 列出以难度系数列出课程
	 * @参数：语言种类id，页面数量
	 * @返回值 list集合
	 * @作者 陈雅迪
	 * */
	@Override
	public List<CourseBean> fetchAllLevelCourseInfoList(int pageno, int id)
			throws Exception {
		List<CourseBean> courseBeanInfoList = null;
		connection = dbUtil.getConnection();
		int indexRows = (pageno - 1) * PageBean.ROWS_PRE_PAGE;
		String sql = "select * from course where level_name=(select leve_name from level where level_id=?) limit ?,?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		preparedStatement.setInt(2, indexRows);
		preparedStatement.setInt(3, PageBean.ROWS_PRE_PAGE);
		resultSet = preparedStatement.executeQuery();
		courseBeanInfoList = new ArrayList<CourseBean>();
		while (resultSet.next()) {
			CourseBean courseBean = new CourseBean();
			courseBean.setCourse_id(resultSet.getInt("course_id"));
			courseBean.setCour_title(resultSet.getString("cour_title"));
			courseBean.setCour_image(resultSet.getString("cour_image"));
			courseBean.setCour_url(resultSet.getString("cour_url"));
			courseBean.setCour_duration(resultSet.getString("cour_duration"));
			courseBean.setCour_hot(resultSet.getInt("cour_hot"));
			courseBean.setCour_date(resultSet.getDate("cour_date"));
			courseBean.setCour_source(resultSet.getString("cour_source"));
			courseBean.setCour_content(resultSet.getString("cour_content"));
			courseBean.setCour_language(resultSet.getString("cour_language"));
			courseBean.setCour_teacher(resultSet.getString("cour_teacher"));
			courseBean.setLanguage_id(resultSet.getInt("language_id"));
			courseBean.setDecoration_id(resultSet.getInt("decoration_id"));
			courseBean.setLevel_name(resultSet.getString("level_name"));
			courseBeanInfoList.add(courseBean);
		}

		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return courseBeanInfoList;
	}

	/**
	 * @功能:按时间顺序得到课程表的行数，最新
	 * @返回值 int 课程表的行数
	 * @作者 陈雅迪
	 * */
	@Override
	public int getDateCourseRows() throws Exception {
		int result = 0;
		try {
			DBUtil dbUtil = new DBUtil();
			connection = dbUtil.getConnection();
			String sql = "select count(*) from course order by cour_date desc";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = resultSet.getInt(1);
			}
			dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * @功能: 列出以时间先后列出课程，最新
	 * @参数： 页面数量
	 * @返回值 list集合
	 * @作者 陈雅迪
	 * */
	@Override
	public List<CourseBean> fetchAllDateCourseInfoList(int pageno)
			throws Exception {
		List<CourseBean> courseBeanInfoList = null;
		connection = dbUtil.getConnection();
		int indexRows = (pageno - 1) * PageBean.ROWS_PRE_PAGE;
		String sql = "select * from course order by cour_date desc limit ?,?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, indexRows);
		preparedStatement.setInt(2, PageBean.ROWS_PRE_PAGE);
		resultSet = preparedStatement.executeQuery();
		courseBeanInfoList = new ArrayList<CourseBean>();
		while (resultSet.next()) {
			CourseBean courseBean = new CourseBean();
			courseBean.setCourse_id(resultSet.getInt("course_id"));
			courseBean.setCour_title(resultSet.getString("cour_title"));
			courseBean.setCour_image(resultSet.getString("cour_image"));
			courseBean.setCour_url(resultSet.getString("cour_url"));
			courseBean.setCour_duration(resultSet.getString("cour_duration"));
			courseBean.setCour_hot(resultSet.getInt("cour_hot"));
			courseBean.setCour_date(resultSet.getDate("cour_date"));
			courseBean.setCour_source(resultSet.getString("cour_source"));
			courseBean.setCour_content(resultSet.getString("cour_content"));
			courseBean.setCour_language(resultSet.getString("cour_language"));
			courseBean.setCour_teacher(resultSet.getString("cour_teacher"));
			courseBean.setLanguage_id(resultSet.getInt("language_id"));
			courseBean.setDecoration_id(resultSet.getInt("decoration_id"));
			courseBean.setLevel_name(resultSet.getString("level_name"));
			courseBeanInfoList.add(courseBean);
		}

		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return courseBeanInfoList;
	}

	/**
	 * @功能:按点击量顺序得到课程表的行数，最热
	 * @返回值 int 课程表的行数
	 * @作者 陈雅迪
	 * */
	@Override
	public int getHotCourseRows() throws Exception {
		int result = 0;
		try {
			DBUtil dbUtil = new DBUtil();
			connection = dbUtil.getConnection();
			String sql = "select count(*) from course order by cour_hot desc";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = resultSet.getInt(1);
			}
			dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * @功能: 列出以点击量列出课程，最热
	 * @参数： 页面数量
	 * @返回值 list集合
	 * @作者 陈雅迪
	 * */
	@Override
	public List<CourseBean> fetchAllHotCourseInfoList(int pageno)
			throws Exception {
		List<CourseBean> courseBeanInfoList = null;
		connection = dbUtil.getConnection();
		int indexRows = (pageno - 1) * PageBean.ROWS_PRE_PAGE;
		String sql = "select * from course order by cour_hot desc limit ?,?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, indexRows);
		preparedStatement.setInt(2, PageBean.ROWS_PRE_PAGE);
		resultSet = preparedStatement.executeQuery();
		courseBeanInfoList = new ArrayList<CourseBean>();
		while (resultSet.next()) {
			CourseBean courseBean = new CourseBean();
			courseBean.setCourse_id(resultSet.getInt("course_id"));
			courseBean.setCour_title(resultSet.getString("cour_title"));
			courseBean.setCour_image(resultSet.getString("cour_image"));
			courseBean.setCour_url(resultSet.getString("cour_url"));
			courseBean.setCour_duration(resultSet.getString("cour_duration"));
			courseBean.setCour_hot(resultSet.getInt("cour_hot"));
			courseBean.setCour_date(resultSet.getDate("cour_date"));
			courseBean.setCour_source(resultSet.getString("cour_source"));
			courseBean.setCour_content(resultSet.getString("cour_content"));
			courseBean.setCour_language(resultSet.getString("cour_language"));
			courseBean.setCour_teacher(resultSet.getString("cour_teacher"));
			courseBean.setLanguage_id(resultSet.getInt("language_id"));
			courseBean.setDecoration_id(resultSet.getInt("decoration_id"));
			courseBean.setLevel_name(resultSet.getString("level_name"));
			courseBeanInfoList.add(courseBean);
		}

		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return courseBeanInfoList;
	}

	@Override
	public List<CourseBean> fetchAllDecCourseInfoList(int pageno, int id)
			throws Exception {
		List<CourseBean> courseBeanInfoList = null;
		connection = dbUtil.getConnection();
		int indexRows = (pageno - 1) * PageBean.ROWS_PRE_PAGE;
		String sql = "select * from course where decoration_id=? limit ?,?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		preparedStatement.setInt(2, indexRows);
		preparedStatement.setInt(3, PageBean.ROWS_PRE_PAGE);
		resultSet = preparedStatement.executeQuery();
		courseBeanInfoList = new ArrayList<CourseBean>();
		while (resultSet.next()) {
			CourseBean courseBean = new CourseBean();
			courseBean.setCourse_id(resultSet.getInt("course_id"));
			courseBean.setCour_title(resultSet.getString("cour_title"));
			courseBean.setCour_image(resultSet.getString("cour_image"));
			courseBean.setCour_url(resultSet.getString("cour_url"));
			courseBean.setCour_duration(resultSet.getString("cour_duration"));
			courseBean.setCour_hot(resultSet.getInt("cour_hot"));
			courseBean.setCour_date(resultSet.getDate("cour_date"));
			courseBean.setCour_source(resultSet.getString("cour_source"));
			courseBean.setCour_content(resultSet.getString("cour_content"));
			courseBean.setCour_language(resultSet.getString("cour_language"));
			courseBean.setCour_teacher(resultSet.getString("cour_teacher"));
			courseBean.setLanguage_id(resultSet.getInt("language_id"));
			courseBean.setDecoration_id(resultSet.getInt("decoration_id"));
			courseBean.setLevel_name(resultSet.getString("level_name"));
			courseBeanInfoList.add(courseBean);
		}

		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return courseBeanInfoList;
	}

	// 魏泽锟编写
	@Override
	public java.util.List<com.etc.bean.CourseBean> fetchAllCourseInfo()
			throws Exception {
		List<CourseBean> courseBeans = new ArrayList<CourseBean>();
		DBUtil dbUtil = new DBUtil();
		connection = dbUtil.getConnection();
		String sql = "select * from course";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			CourseBean courseBean = new CourseBean();
			courseBean.setCour_content(resultSet.getString("cour_content"));
			courseBean.setCour_date(resultSet.getDate("cour_date"));
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
	public int fetchCourseInfoRows() throws Exception {
		int rows = 0;
		DBUtil dbUtil = new DBUtil();
		connection = dbUtil.getConnection();
		String sql = "select count(*) from course";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			rows = resultSet.getInt(1);
		}
		return rows;
	}

	@Override
	public List<CourseBean> fetchCourseInfoByPageno(int pageno)
			throws Exception {
		List<CourseBean> courseBeans = new ArrayList<CourseBean>();
		DBUtil dbUtil = new DBUtil();
		connection = dbUtil.getConnection();
		int index = (pageno - 1) * PageBean.ROWS_PRE_PAGE;

		String sql = "select * from course limit ?,?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, index);
		preparedStatement.setInt(2, PageBean.ROWS_PRE_PAGE);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			CourseBean courseBean = new CourseBean();
			courseBean.setCour_content(resultSet.getString("cour_content"));
			courseBean.setCour_date(resultSet.getDate("cour_date"));
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
	public CourseBean fetchCourseinfoByName(String courseName) throws Exception {
		CourseBean courseBean = null;
		DBUtil dbUtil = new DBUtil();
		connection = dbUtil.getConnection();

		String sql = "select * from course where cour_title=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, courseName);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			courseBean = new CourseBean();
			courseBean.setCour_content(resultSet.getString("cour_content"));
			courseBean.setCour_date(resultSet.getDate("cour_date"));
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
		}
		return courseBean;
	}

	@Override
	public int addCourseinfo(CourseBean courseBean) throws Exception {
		int result = 0;
		DBUtil dbUtil = new DBUtil();
		connection = dbUtil.getConnection();

		String sql = "insert into course (cour_title,cour_image,"
				+ "cour_url,cour_duration,"
				+ "cour_hot,cour_date,"
				+ "cour_source,cour_content,"
				+ "cour_language,cour_teacher,"
				+ "language_id,decoration_id,level_name) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, courseBean.getCour_title());
		preparedStatement.setString(2, courseBean.getCour_image());
		preparedStatement.setString(3, courseBean.getCour_url());

		preparedStatement.setString(4, courseBean.getCour_duration());
		preparedStatement.setInt(5, courseBean.getCour_hot());
		java.sql.Date tempdate = new java.sql.Date(courseBean.getCour_date()
				.getTime());
		preparedStatement.setDate(6, tempdate);
		preparedStatement.setString(7, courseBean.getCour_source());
		preparedStatement.setString(8, courseBean.getCour_content());
		preparedStatement.setString(9, courseBean.getCour_language());
		preparedStatement.setString(10, courseBean.getCour_teacher());
		preparedStatement.setInt(11, courseBean.getLanguage_id());

		preparedStatement.setInt(12, courseBean.getDecoration_id());
		preparedStatement.setString(13, courseBean.getLevel_name());
		result = preparedStatement.executeUpdate();
		return result;
	}

	@Override
	public int deleteCourseinfoById(int id) throws Exception {
		int rows = 0;
		DBUtil dbUtil = new DBUtil();
		connection = dbUtil.getConnection();
		String sql = "delete from course where course_id=? ";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public CourseBean fetchCourseinfoById(int course_id) throws Exception {
		CourseBean courseBean = null;
		DBUtil dbUtil = new DBUtil();
		connection = dbUtil.getConnection();

		String sql = "select * from course where course_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, course_id);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			courseBean = new CourseBean();

			courseBean.setCour_content(resultSet.getString("cour_content"));
			Date date = new java.util.Date(resultSet.getDate("cour_date")
					.getTime());
			courseBean.setCour_date(date);

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
		}
		return courseBean;
	}

	@Override
	public int courseToPlan(int id) throws Exception {
		int plan_id = 0;
		DBUtil dbUtil = new DBUtil();
		connection = dbUtil.getConnection();
		String sql = "select plan.plan_id from course,plan,chapter,section where course.section_id=section.section_id and section.chap_id=chapter.chapter_id and plan.plan_id=chapter.plan_id and course.course_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			plan_id = resultSet.getInt(1);
		}
		return plan_id;
	}

}
