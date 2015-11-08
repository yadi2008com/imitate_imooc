package com.etc.biz;


import java.util.List;
import java.util.Map;

import com.etc.bean.CourseBean;
import com.etc.bean.DecorationBean;
import com.etc.bean.LanguageBean;
import com.etc.bean.LevelBean;


public interface CourseBiz {
	/**
	 * @功能: 得到课程表的行数
	 * @返回值  int 课程表的行数
	 * @作者 陈雅迪
	 * */
	public int getCourseInfoRows();
	/**
	 * @功能: 得到课程方向列表
	 * @返回值  list集合
	 * @作者 陈雅迪
	 * */
	public List<DecorationBean> fetchAllDecorationInfoList();
	/**
	 * @功能: 列出所有课程
	 * @返回值  list集合
	 * @作者 陈雅迪
	 * */
	public List<CourseBean> fetchAllCourseInfoList(int pageno);
	/**
	 * @功能: 得到课程难度列表
	 * @返回值  list集合
	 * @作者 陈雅迪
	 * */
	public List<LevelBean> fetchAllLevelBeanInfoList();
	/**
	 * @功能: 列出课程语言列表
	 * @返回值  list集合
	 * @作者 陈雅迪
	 * */
	public List<LanguageBean> fetchAllLanguageInfoList();
	/**
	 * @功能:得到课程表的行数
	 * @参数：方向id
	 * @返回值  int 课程表的行数
	 * @作者 陈雅迪
	 * */
	public int getDecorationCourseRows(int id);
	/**
	 * @功能: 列出所有课程按方向列出
	 * @参数：方向id，页面数量
	 * @返回值  list集合
	 * @作者 陈雅迪
	 * */
	public List<CourseBean> fetchAllCourseInfoList(int pageno, int id);
	/**
	 * @功能:得到课程表的行数
	 * @参数：语言种类id
	 * @返回值  int 课程表的行数
	 * @作者 陈雅迪
	 * */
	public int getLanguageCourseRows(int id);
	/**
	 * @功能: 列出以语言种类列出课程
	 * @参数：语言种类id，页面数量
	 * @返回值  list集合
	 * @作者 陈雅迪
	 * */
	public List<CourseBean> fetchAllLanCourseInfoList(int pageno, int id);
	/**
	 * @功能:按难度系数得到课程表的行数
	 * @参数：水平种类id
	 * @返回值  int 课程表的行数
	 * @作者 陈雅迪
	 * */
	public int getLevelCourseRows(int id);
	/**
	 * @功能: 列出以难度系数列出课程
	 * @参数：语言种类id，页面数量
	 * @返回值  list集合
	 * @作者 陈雅迪
	 * */
	public List<CourseBean> fetchAllLevelCourseInfoList(int pageno, int id);
	/**
	 * @功能:按时间顺序得到课程表的行数，最新
	 * @返回值  int 课程表的行数
	 * @作者 陈雅迪
	 * */
	public int getDateCourseRows();
	/**
	 * @功能: 列出以时间先后列出课程，最新
	 * @参数： 页面数量
	 * @返回值  list集合
	 * @作者 陈雅迪
	 * */
	public List<CourseBean> fetchAllDateCourseInfoList(int pageno);
	/**
	 * @功能:按点击量顺序得到课程表的行数，最热
	 * @返回值  int 课程表的行数
	 * @作者 陈雅迪
	 * */
	public int getHotCourseRows();
	/**
	 * @功能: 列出以点击量列出课程，最热
	 * @参数： 页面数量
	 * @返回值  list集合
	 * @作者 陈雅迪
	 * */
	public List<CourseBean> fetchAllHotCourseInfoList(int pageno);
	

	int fetchCourseInfoRows();

	List<CourseBean> fetchCourseInfoByPageno(int pageno);
	/**
	 * @功能: 列出该方向的所有课程
	 * @参数： 页面数量
	 * @返回值  list集合
	 * @作者 陈雅迪
	 * */
	public List<CourseBean> fetchAllDecCourseInfoList(int pageno, int id);
	
	List<CourseBean> fetchAllCourseInfo();

    CourseBean fetchCourseinfoByName(String courseName);

	int addCourseinfo(CourseBean courseBean);

	int deleteCourseinfoById(int id);

	CourseBean fetchCourseinfoById(int course_id);
	int courseToPlan(int course_id);

	Map<Integer, List> planDetail(int plan_id);

}
