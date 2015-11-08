package com.etc.biz;

import java.util.List;

import com.etc.bean.CourseBean;
import com.etc.bean.Mydiscuss;
import com.etc.dao.MydiscussDao;
import com.etc.dao.MydiscussDaoImpl;

/**
 * @功能 我的评论中的业务处理的实现
 * @作者 郝宝亮
 *
 */
public class MydiscussBizImpl implements MydiscussBiz {
	MydiscussDao mydiscussDao = new MydiscussDaoImpl();

	/**
	 * @作者 郝宝亮
	 * @功能 查询所有我的评论
	 * @返回值 mydiscusseList
	 */
	@Override
	public List<Mydiscuss> mydiscussFetchAllList(int pageno) {
		List<Mydiscuss> mydiscusseList = null;
		try {
			mydiscusseList = mydiscussDao.mydiscussFetchAllList(pageno);
		} catch (Exception e) {
			e.printStackTrace();
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
			int pageno) {
		List<Mydiscuss> mydiscusseList = null;
		try {
			mydiscusseList = mydiscussDao.mydiscussFetchList(user_id,
					course_id, pageno);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mydiscusseList;
	}

	/**
	 * @作者 郝宝亮
	 * @功能 通过评论ID查找评论
	 * @返回值 mydiscuss
	 */
	@Override
	public Mydiscuss fetchMydiscussByMydiscussId(int mydiscuss_id) {
		Mydiscuss mydiscuss = null;
		try {
			mydiscuss = mydiscussDao.fetchMydiscussByMydiscussId(mydiscuss_id);
		} catch (Exception e) {
			e.printStackTrace();
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
			int disc_praise) {
		int row = 0;
		try {
			row = mydiscussDao.updateMydiscuss(mydiscuss_id, disc_content,
					disc_praise);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}

	/**
	 * @作者 郝宝亮
	 * @功能 删除评论
	 * @返回值 row
	 */
	@Override
	public int deleteMydiscussByMydiscuss_id(int mydiscuss_id) {
		int row = 0;
		try {
			row = mydiscussDao.deleteMydiscussByMydiscuss_id(mydiscuss_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}

	/**
	 * @作者 郝宝亮
	 * @功能 查询评论里显示的用户昵称
	 * @返回值 usernameList
	 */
	@Override
	public String[] mydiscussFetchAllUsername(int[] userID, int pageno) {
		String[] usernameList = null;
		try {
			usernameList = mydiscussDao.mydiscussFetchAllUsername(userID,
					pageno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usernameList;
	}

	/**
	 * @作者 郝宝亮
	 * @功能 查询评论里显示的课程名
	 * @返回值 cour_titleList
	 */
	@Override
	public String[] mydiscussFetchAllCourTitle(int[] courseID, int pageno) {
		String[] cour_titleList = null;
		try {
			cour_titleList = mydiscussDao.mydiscussFetchAllCourTitle(courseID,
					pageno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cour_titleList;
	}

	/**
	 * @作者 郝宝亮
	 * @功能 查找所有评论列表的行数
	 * @返回值 rows
	 */
	@Override
	public int fetchMydiscussListRows() {
		int rows = 0;
		try {
			rows = mydiscussDao.fetchMydiscussListRows();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	/**
	 * @作者 郝宝亮
	 * @功能 查找不同条件评论列表的行数
	 * @返回值 rows
	 */
	@Override
	public int fetchMydiscussRows(int user_id, int course_id) {
		int rows = 0;
		try {
			rows = mydiscussDao.fetchMydiscussRows(user_id, course_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	/**
	 * @作者 郝宝亮
	 * @功能 通过用户名查找用户ID
	 * @返回值 user_id
	 */
	@Override
	public int fetchUserIdByUsername(String username) {
		int user_id = 0;
		try {
			user_id = mydiscussDao.fetchUserIdByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user_id;
	}

	/**
	 * @作者 郝宝亮
	 * @功能 通过课程名查找课程ID
	 * @返回值 course_id
	 */
	@Override
	public int fetchCourseIdByCourTitle(String cour_title) {
		int course_id = 0;
		try {
			course_id = mydiscussDao.fetchCourseIdByCourTitle(cour_title);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return course_id;
	}

	/**
	 * @作者 郝宝亮
	 * @功能 全局搜索
	 * @返回值 courseList
	 */
	@Override
	public List<CourseBean> selectCourseList(String cour_title, int pageno) {
		List<CourseBean> courseList = null;
		try {
			courseList = mydiscussDao.selectCourseList(cour_title, pageno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseList;
	}

	/**
	 * @作者 郝宝亮
	 * @功能 全局搜索的行数
	 * @返回值 rows
	 */
	@Override
	public int fetchSelectCourseListRows(String cour_title) {
		int rows = 0;
		try {
			rows = mydiscussDao.fetchSelectCourseListRows(cour_title);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public List<CourseBean> json_selectCourseList(String cour_title) {
		List<CourseBean> courseList = null;
		try {
			courseList = mydiscussDao.json_selectCourseList(cour_title);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseList;
	}
}
