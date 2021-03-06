package com.etc.dao;

import java.util.List;

import com.etc.bean.CourseBean;
import com.etc.bean.Mydiscuss;

/**
 * @功能 封装我的评论中的数据处理
 * @作者 郝宝亮
 *
 */
public interface MydiscussDao {
	/**
	 * @作者 郝宝亮
	 * @功能 查询所有我的评论
	 * @返回值 mydiscusseList
	 */
	public List<Mydiscuss> mydiscussFetchAllList(int pageno) throws Exception;

	/**
	 * @作者 郝宝亮
	 * @功能 通过不同条件查询评论
	 * @返回值 mydiscusseList
	 */
	public List<Mydiscuss> mydiscussFetchList(int user_id, int course_id,
			int pageno) throws Exception;

	/**
	 * @作者 郝宝亮
	 * @功能 通过评论ID查找评论
	 * @返回值 mydiscuss
	 */
	public Mydiscuss fetchMydiscussByMydiscussId(int mydiscuss_id)
			throws Exception;

	/**
	 * @作者 郝宝亮
	 * @功能 修改评论内容和点赞数量
	 * @返回值 row
	 */
	public int updateMydiscuss(int mydiscuss_id, String disc_content,
			int disc_praise) throws Exception;

	/**
	 * @作者 郝宝亮
	 * @功能 删除评论
	 * @返回值 row
	 */
	public int deleteMydiscussByMydiscuss_id(int mydiscuss_id) throws Exception;

	/**
	 * @作者 郝宝亮
	 * @功能 查询评论里显示的用户昵称
	 * @返回值 usernameList
	 */
	public String[] mydiscussFetchAllUsername(int[] userID, int pageno)
			throws Exception;

	/**
	 * @作者 郝宝亮
	 * @功能 查询评论里显示的用户课程名
	 * @返回值 cour_titleList
	 */
	public String[] mydiscussFetchAllCourTitle(int[] courseID, int pageno)
			throws Exception;

	/**
	 * @作者 郝宝亮
	 * @功能 查找所有评论列表的行数
	 * @返回值 rows
	 */
	public int fetchMydiscussListRows() throws Exception;

	/**
	 * @作者 郝宝亮
	 * @功能 查找不同条件评论列表的行数
	 * @返回值 rows
	 */
	public int fetchMydiscussRows(int user_id, int course_id) throws Exception;

	/**
	 * @作者 郝宝亮
	 * @功能 通过用户名查找用户ID
	 * @返回值 user_id
	 */
	public int fetchUserIdByUsername(String username) throws Exception;

	/**
	 * @作者 郝宝亮
	 * @功能 通过课程名查找课程ID
	 * @返回值 course_id
	 */
	public int fetchCourseIdByCourTitle(String cour_title) throws Exception;

	/**
	 * @作者 郝宝亮
	 * @功能 全局搜索
	 * @返回值 courseList
	 */
	public List<CourseBean> selectCourseList(String cour_title, int pageno)
			throws Exception;

	/**
	 * @作者 郝宝亮
	 * @功能 全局搜索的行数
	 * @返回值 rows
	 */
	public int fetchSelectCourseListRows(String cour_title) throws Exception;

	public List<CourseBean> json_selectCourseList(String cour_title) throws Exception;

}
