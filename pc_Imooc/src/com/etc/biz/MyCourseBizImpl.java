package com.etc.biz;

import java.util.List;
import java.util.Map;

import com.etc.bean.CourseBean;
import com.etc.bean.MyNotesBean;
import com.etc.bean.PlanBean;
import com.etc.bean.UsersBean;
import com.etc.dao.MyCourseDao;
import com.etc.dao.MyCourseDaoImpl;

public class MyCourseBizImpl implements MyCourseBiz {
	MyCourseDao myCourseDao = new MyCourseDaoImpl();

	@Override
	public List<CourseBean> fetchMyCourseWhereFocusInfo(int user_id, int pageno) {

		List<CourseBean> courseBeanList = null;

		try {
			courseBeanList = myCourseDao.fetchMyCourseWhereFocusInfo(user_id,
					pageno);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return courseBeanList;
	}

	@Override
	public List<PlanBean> fetchMyPlan(int user_id) {
		List<PlanBean> planBeanList = null;

		try {
			planBeanList = myCourseDao.fetchMyPlan(user_id);
		} catch (Exception e) {

		}

		return planBeanList;
	}

	@Override
	public List<MyNotesBean> fetchMynote_contentByUser_idAndCourse_id(
			int user_id) {
		List<MyNotesBean> myNotesBeanList = null;

		try {
			myNotesBeanList = myCourseDao
					.fetchMynote_contentByUser_idAndCourse_id(user_id);

		} catch (Exception e) {
			System.out.println("错误");
		}

		return myNotesBeanList;
	}

	@Override
	public List<CourseBean> fetchMyCourseBystateYx(int user_id, int pageno) {
		List<CourseBean> courseBeanList = null;

		try {
			courseBeanList = myCourseDao
					.fetchMyCourseBystateYx(user_id, pageno);
		} catch (Exception e) {

		}

		return courseBeanList;
	}

	@Override
	public int fetchMyCourseWhereFocusInfoRows(int user_id) {
		int rows = 0;
		try {
			rows = myCourseDao.fetchMyCourseWhereFocusInfoRows(user_id);
		} catch (Exception e) {

		}

		return rows;
	}

	@Override
	public int fetchMyCourseBystateYxRows(int user_id) {
		int rows = 0;
		try {
			rows = myCourseDao.fetchMyCourseBystateYxRows(user_id);
		} catch (Exception e) {

		}

		return rows;
	}

	@Override
	public List<UsersBean> fetchUserByuser_id(int user_id) {
		List<UsersBean> usersBeanList = null;
		try {
			usersBeanList = myCourseDao.fetchUserByuser_id(user_id);
		} catch (Exception e) {

		}

		return usersBeanList;
	}

	@Override
	public MyNotesBean fetchMynoteBymynotes_id(int mynotes_idInt) {
		MyNotesBean myNotesBean = null;
		try {
			myNotesBean = myCourseDao.fetchMynoteBymynotes_id(mynotes_idInt);
		} catch (Exception e) {

		}

		return myNotesBean;
	}

	@Override
	public List<MyNotesBean> fetchMynoteBymycourse_id(int course_idInt) {
		List<MyNotesBean> myNotesBeanlist = null;
		try {
			myNotesBeanlist = myCourseDao
					.fetchMynoteBymycourse_id(course_idInt);
		} catch (Exception e) {

		}

		return myNotesBeanlist;
	}

	@Override
	public List<Map<String, Object>> androidFetchMyNoteListByUser_id(
			int an_user_idInt) {
		List<Map<String, Object>> myNoteList = null;
		try {
			myNoteList = myCourseDao
					.androidFetchMyNoteListByUser_id(an_user_idInt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myNoteList;
	}

	@Override
	public List<MyNotesBean> androidFetchMynoteBymycourse_idAndUser_id(
			int course_idInt, int user_idInt) {
		List<MyNotesBean> myNotesBeanlist = null;
		try {
			myNotesBeanlist = myCourseDao
					.androidFetchMynoteBymycourse_idAndUser_id(course_idInt,
							user_idInt);
		} catch (Exception e) {

		}

		return myNotesBeanlist;
	}

	@Override
	public int deleteMyNotesByMyNoteId(int mynotes_id) {
		int rows = 0;
		try {
			rows = myCourseDao.deleteMyNotesByMyNoteId(mynotes_id);
		} catch (Exception e) {

		}

		return rows;
	}

}
