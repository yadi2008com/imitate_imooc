package com.etc.dao;

import java.util.List;
import java.util.Map;

import com.etc.bean.CourseBean;
import com.etc.bean.MyNotesBean;
import com.etc.bean.PlanBean;
import com.etc.bean.UsersBean;

public interface MyCourseDao {
	/**
	 * @throws Exception
	 * @作者 许建皓
	 * @功能 查询已关注的课程
	 * @返回值 courseBeanList
	 */
	List<CourseBean> fetchMyCourseWhereFocusInfo(int user_id, int pageno)
			throws Exception;

	/**
	 * @throws Exception
	 * @作者 许建皓
	 * @功能 查询计划的课程
	 * @返回值 courseBeanList
	 */
	List<PlanBean> fetchMyPlan(int user_id) throws Exception;

	/**
	 * @功能：根据用户Id和所学课程的Id查找笔记的内容
	 * @作者：许建皓
	 * 
	 */
	List<MyNotesBean> fetchMynote_contentByUser_idAndCourse_id(int user_id)
			throws Exception;

	/**
	 * @功能：查找已学课程
	 * @作者：许建皓
	 * @返回值 List<CourseBean>
	 */
	List<CourseBean> fetchMyCourseBystateYx(int user_id, int pageno)
			throws Exception;

	/**
	 * @功能：查找已关注课程行数
	 * @作者：许建皓
	 * @返回值 int
	 */
	int fetchMyCourseWhereFocusInfoRows(int user_id) throws Exception;

	/**
	 * @功能：查找已学课程行数
	 * @作者：许建皓
	 * @返回值 int
	 */
	int fetchMyCourseBystateYxRows(int user_id) throws Exception;

	List<UsersBean> fetchUserByuser_id(int user_id) throws Exception;

	/**
	 * @功能：根据我的笔记ID查询我的笔记
	 * @作者：许建皓
	 * @返回值 MyNotesBean
	 */
	MyNotesBean fetchMynoteBymynotes_id(int mynotes_idInt) throws Exception;

	/**
	 * @功能：根据课程ID查询我的笔记
	 * @作者：许建皓
	 * @返回值 MyNotesBean
	 */
	List<MyNotesBean> fetchMynoteBymycourse_id(int course_idInt)
			throws Exception;

	/**
	 * @功能：android获取我的笔记列表
	 * @作者：许建皓
	 * @返回值 cour_title,count(note_id),cour_image,cour_id
	 */
	List<Map<String, Object>> androidFetchMyNoteListByUser_id(int an_user_idInt)
			throws Exception;

	/**
	 * @功能：android获取我的笔记详情通过course_id和user_id
	 * @作者：许建皓
	 * @返回值 MyNotesBeanList
	 */
	List<MyNotesBean> androidFetchMynoteBymycourse_idAndUser_id(
			int course_idInt, int user_idInt) throws Exception;

	/**
	 * @功能：android删除我的笔记详情通过mynote_id
	 * @作者：许建皓
	 * @返回值 MyNotesBeanList
	 */
	int deleteMyNotesByMyNoteId(int mynotes_id) throws Exception;
}
