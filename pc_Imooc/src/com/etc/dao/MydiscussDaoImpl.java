package com.etc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.etc.bean.CourseBean;
import com.etc.bean.Mydiscuss;
import com.etc.bean.PageBean;
import com.etc.util.DBUtil;

/**
 * @功能 我的评论中的数据处理的实现
 * @作者 郝宝亮
 *
 */
public class MydiscussDaoImpl implements MydiscussDao {
	DBUtil dbUtil = new DBUtil();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	/**
	 * @作者 郝宝亮
	 * @功能 查询所有我的评论
	 * @返回值 mydiscusseList
	 */
	@Override
	public List<Mydiscuss> mydiscussFetchAllList(int pageno) throws Exception {
		List<Mydiscuss> mydiscusseList = null;
		connection = dbUtil.getConnection();
		String sql = "select * from mydiscuss limit ?,?";
		int startIndex = (pageno - 1) * PageBean.ROWS_PRE_PAGE;

		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, startIndex);
		preparedStatement.setInt(2, PageBean.ROWS_PRE_PAGE);

		resultSet = preparedStatement.executeQuery();
		mydiscusseList = new ArrayList<Mydiscuss>();
		while (resultSet.next()) {
			Mydiscuss mydiscuss = new Mydiscuss();
			mydiscuss.setMydiscuss_id(resultSet.getInt("mydiscuss_id"));
			mydiscuss.setUser_id(resultSet.getInt("user_id"));
			mydiscuss.setCourse_id(resultSet.getInt("course_id"));
			mydiscuss.setDisc_content(resultSet.getString("disc_content"));
			mydiscuss.setDisc_date(resultSet.getDate("disc_date"));
			mydiscuss.setDisc_praise(resultSet.getInt("disc_praise"));

			mydiscusseList.add(mydiscuss);
		}
		return mydiscusseList;
	}

	/**
	 * @作者 郝宝亮
	 * @功能 通过不同条件查询评论
	 * @返回值 mydiscusseList
	 */
	@Override
	public List<Mydiscuss> mydiscussFetchList(int user_id, int course_id,
			int pageno) throws Exception {
		List<Mydiscuss> mydiscusseList = null;
		String sql = null;
		connection = dbUtil.getConnection();

		int startIndex = (pageno - 1) * PageBean.ROWS_PRE_PAGE;
		if (user_id == 0 && course_id != 0) {// 课程ID
			sql = "select * from mydiscuss where course_id=? limit ?,?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, course_id);
			preparedStatement.setInt(2, startIndex);
			preparedStatement.setInt(3, PageBean.ROWS_PRE_PAGE);
		} else if (user_id != 0 && course_id == 0) {// 用户ID
			sql = "select * from mydiscuss where user_id=? limit ?,?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, user_id);
			preparedStatement.setInt(2, startIndex);
			preparedStatement.setInt(3, PageBean.ROWS_PRE_PAGE);
		} else {// 都查找或者都为空
			sql = "select * from mydiscuss where user_id=? and course_id=? limit ?,?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, user_id);
			preparedStatement.setInt(2, course_id);
			preparedStatement.setInt(3, startIndex);
			preparedStatement.setInt(4, PageBean.ROWS_PRE_PAGE);
		}
		resultSet = preparedStatement.executeQuery();
		mydiscusseList = new ArrayList<Mydiscuss>();
		while (resultSet.next()) {
			Mydiscuss mydiscuss = new Mydiscuss();
			mydiscuss.setMydiscuss_id(resultSet.getInt("mydiscuss_id"));
			mydiscuss.setUser_id(resultSet.getInt("user_id"));
			mydiscuss.setCourse_id(resultSet.getInt("course_id"));
			mydiscuss.setDisc_content(resultSet.getString("disc_content"));
			mydiscuss.setDisc_date(resultSet.getDate("disc_date"));
			mydiscuss.setDisc_praise(resultSet.getInt("disc_praise"));

			mydiscusseList.add(mydiscuss);
		}
		return mydiscusseList;
	}

	/**
	 * @作者 郝宝亮
	 * @功能 通过评论ID查找评论
	 * @返回值 mydiscuss
	 */
	@Override
	public Mydiscuss fetchMydiscussByMydiscussId(int mydiscuss_id)
			throws Exception {
		Mydiscuss mydiscuss = null;

		connection = dbUtil.getConnection();
		String sql = "select * from mydiscuss where mydiscuss_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, mydiscuss_id);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			mydiscuss = new Mydiscuss();
			mydiscuss.setMydiscuss_id(resultSet.getInt("mydiscuss_id"));
			mydiscuss.setUser_id(resultSet.getInt("user_id"));
			mydiscuss.setCourse_id(resultSet.getInt("course_id"));
			mydiscuss.setDisc_content(resultSet.getString("disc_content"));
			mydiscuss.setDisc_date(resultSet.getDate("disc_date"));
			mydiscuss.setDisc_praise(resultSet.getInt("disc_praise"));
		}
		return mydiscuss;
	}

	/**
	 * @作者 郝宝亮
	 * @功能 修改评论内容和点赞数量
	 * @返回值 row
	 */
	@Override
	public int updateMydiscuss(int mydiscuss_id, String disc_content,
			int disc_praise) throws Exception {
		int row = 0;
		connection = dbUtil.getConnection();
		String sql = "update mydiscuss set disc_content=?,disc_praise=? where mydiscuss_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, disc_content);
		preparedStatement.setInt(2, disc_praise);
		preparedStatement.setInt(3, mydiscuss_id);
		row = preparedStatement.executeUpdate();
		return row;
	}

	/**
	 * @作者 郝宝亮
	 * @功能 删除评论
	 * @返回值 row
	 */
	@Override
	public int deleteMydiscussByMydiscuss_id(int mydiscuss_id) throws Exception {
		int row = 0;
		connection = dbUtil.getConnection();
		String sql = "delete from mydiscuss where mydiscuss_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, mydiscuss_id);
		row = preparedStatement.executeUpdate();
		return row;
	}

	/**
	 * @作者 郝宝亮
	 * @功能 查询评论里显示的用户昵称
	 * @返回值 usernameList
	 */
	@Override
	public String[] mydiscussFetchAllUsername(int[] userID, int pageno)
			throws Exception {
		String[] usernameList = new String[userID.length];
		connection = dbUtil.getConnection();
		for (int x = 0; x < userID.length; x++) {
			String sql = "select username from users where user_id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userID[x]);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String username = null;
				username = resultSet.getString("username");
				usernameList[x] = username;
			}
		}
		return usernameList;
	}

	/**
	 * @作者 郝宝亮
	 * @功能 查询评论里显示的用户课程名
	 * @返回值 cour_titleList
	 */
	@Override
	public String[] mydiscussFetchAllCourTitle(int[] courseID, int pageno)
			throws Exception {
		String[] cour_titleList = new String[courseID.length];
		connection = dbUtil.getConnection();
		for (int x = 0; x < courseID.length; x++) {
			String sql = "select cour_title from course where course_id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, courseID[x]);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String cour_title = null;
				cour_title = resultSet.getString("cour_title");
				cour_titleList[x] = cour_title;
			}
		}
		return cour_titleList;
	}

	/**
	 * @作者 郝宝亮
	 * @功能 查找所有评论列表的行数
	 * @返回值 rows
	 */
	@Override
	public int fetchMydiscussListRows() throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "select count(*) from mydiscuss";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			rows = resultSet.getInt(1);
		}
		return rows;
	}

	/**
	 * @作者 郝宝亮
	 * @功能 查找不同条件评论列表的行数
	 * @返回值 rows
	 */
	@Override
	public int fetchMydiscussRows(int user_id, int course_id) throws Exception {
		int rows = 0;
		String sql = null;
		connection = dbUtil.getConnection();
		if (user_id == 0 && course_id != 0) {// 课程ID
			sql = "select count(*) from mydiscuss where course_id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, course_id);
		} else if (user_id != 0 && course_id == 0) {// 用户ID
			sql = "select count(*) from mydiscuss where user_id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, user_id);
		} else {// 都查找或者都为空
			sql = "select count(*) from mydiscuss where user_id=? and course_id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, user_id);
			preparedStatement.setInt(2, course_id);
		}
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			rows = resultSet.getInt(1);
		}
		return rows;
	}

	/**
	 * @作者 郝宝亮
	 * @功能 通过用户名查找用户ID
	 * @返回值 user_id
	 */
	@Override
	public int fetchUserIdByUsername(String username) throws Exception {
		int user_id = 0;
		connection = dbUtil.getConnection();
		String sql = "select user_id from users where username=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, username);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			user_id = resultSet.getInt(1);
		}
		return user_id;
	}

	/**
	 * @作者 郝宝亮
	 * @功能 通过课程名查找课程ID
	 * @返回值 course_id
	 */
	@Override
	public int fetchCourseIdByCourTitle(String cour_title) throws Exception {
		int course_id = 0;
		connection = dbUtil.getConnection();
		String sql = "select course_id from course where cour_title=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, cour_title);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			course_id = resultSet.getInt(1);
		}
		return course_id;
	}

	/**
	 * @作者 郝宝亮
	 * @功能 全局搜索
	 * @返回值 courseList
	 */
	@Override
	public List<CourseBean> selectCourseList(String cour_title, int pageno)
			throws Exception {
		List<CourseBean> courseList = new ArrayList<CourseBean>();
		connection = dbUtil.getConnection();
		int indexRows=(pageno-1)*PageBean.ROWS_PRE_PAGE;
		String sql = "select * from course where cour_title like ? limit ?,?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, "%" + cour_title + "%");
		preparedStatement.setInt(2, indexRows);
		preparedStatement.setInt(3, PageBean.ROWS_PRE_PAGE);
		resultSet = preparedStatement.executeQuery();
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
			courseList.add(courseBean);
		}
		return courseList;
	}
	/**
	 * @作者 郝宝亮
	 * @功能 全局搜索的行数
	 * @返回值 rows
	 */
	@Override
	public int fetchSelectCourseListRows(String cour_title) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "select count(*) from course where cour_title like ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, "%" + cour_title + "%");
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			rows = resultSet.getInt(1);
		}
		return rows;
	}

	@Override
	public List<CourseBean> json_selectCourseList(String cour_title)
			throws Exception {
		List<CourseBean> courseList = new ArrayList<CourseBean>();
		connection = dbUtil.getConnection();
		String sql = "select * from course where cour_title like ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, "%" + cour_title + "%");
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			CourseBean courseBean = new CourseBean();
			Date date= new java.util.Date(resultSet.getDate("cour_date").getTime());
			courseBean.setCour_date(date);
			
			courseBean.setCourse_id(resultSet.getInt("course_id"));
			courseBean.setCour_title(resultSet.getString("cour_title"));
			courseBean.setCour_image(resultSet.getString("cour_image"));
			courseBean.setCour_url(resultSet.getString("cour_url"));
			courseBean.setCour_duration(resultSet.getString("cour_duration"));
			courseBean.setCour_hot(resultSet.getInt("cour_hot"));
			courseBean.setCour_source(resultSet.getString("cour_source"));
			courseBean.setCour_content(resultSet.getString("cour_content"));
			courseBean.setCour_language(resultSet.getString("cour_language"));
			courseBean.setCour_teacher(resultSet.getString("cour_teacher"));
			courseBean.setLanguage_id(resultSet.getInt("language_id"));
			courseBean.setDecoration_id(resultSet.getInt("decoration_id"));
			courseBean.setLevel_name(resultSet.getString("level_name"));
			courseList.add(courseBean);
		}
		return courseList;
	}

}
