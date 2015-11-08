package com.etc.biz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.etc.bean.CourseBean;
import com.etc.bean.DecorationBean;
import com.etc.bean.LanguageBean;
import com.etc.bean.LevelBean;
import com.etc.dao.ChapterDao;
import com.etc.dao.ChapterDaoImpl;
import com.etc.dao.CourseDao;
import com.etc.dao.CourseDaoImpl;

public class CourseBizImpl implements CourseBiz {
	CourseDao courseDao = new CourseDaoImpl();
	ChapterBiz chapterBiz = new ChapterBizImpl();

	/**
	 * @功能: 得到课程方向列表
	 * @返回值 list集合
	 * @作者 陈雅迪
	 * */
	@Override
	public List<DecorationBean> fetchAllDecorationInfoList() {
		// 创建堆内存空间并赋值为空
		List<DecorationBean> decorationInfoList = null;
		try {
			decorationInfoList = courseDao.fetchAllDecorationInfoList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decorationInfoList;
	}

	/**
	 * @功能: 列出所有课程
	 * @返回值 list集合
	 * @作者 陈雅迪
	 * */
	@Override
	public List<CourseBean> fetchAllCourseInfoList(int pageno) {
		// 创建堆内存空间并赋值为空
		List<CourseBean> courseBeanInfoList = null;
		try {
			courseBeanInfoList = courseDao.fetchAllCourseInfoList(pageno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseBeanInfoList;

	}

	/**
	 * @功能: 得到课程难度列表
	 * @返回值 list集合
	 * @作者 陈雅迪
	 * */
	@Override
	public List<LevelBean> fetchAllLevelBeanInfoList() {
		// 创建堆内存空间并赋值为空
		List<LevelBean> levelBeanInfoList = null;
		try {
			levelBeanInfoList = courseDao.fetchAllLevelBeanInfoList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return levelBeanInfoList;
	}

	/**
	 * @功能: 列出课程语言列表
	 * @返回值 list集合
	 * @作者 陈雅迪
	 * */
	@Override
	public List<LanguageBean> fetchAllLanguageInfoList() {
		// 创建堆内存空间并赋值为空
		List<LanguageBean> languageBeanInfoList = null;
		try {
			languageBeanInfoList = courseDao.fetchAllLanguageInfoList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return languageBeanInfoList;
	}

	/**
	 * @功能: 得到课程表的行数
	 * @返回值 int 课程表的行数
	 * @作者 陈雅迪
	 * */
	@Override
	public int getCourseInfoRows() {
		int result = 0;
		try {
			result = courseDao.getCourseInfoRows();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @功能:得到课程表的行数
	 * @参数：方向id
	 * @返回值 int 课程表的行数
	 * @作者 陈雅迪
	 * */
	@Override
	public int getDecorationCourseRows(int id) {
		int result = 0;
		try {
			result = courseDao.getDecorationCourseRows(id);
		} catch (Exception e) {
			e.printStackTrace();
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
	public List<CourseBean> fetchAllCourseInfoList(int pageno, int id) {
		// 创建堆内存空间并赋值为空
		List<CourseBean> courseBeanInfoList = null;
		try {
			courseBeanInfoList = courseDao.fetchAllCourseInfoList(pageno, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseBeanInfoList;
	}

	/**
	 * @功能:得到课程表的行数
	 * @参数：语言种类id
	 * @返回值 int 课程表的行数
	 * @作者 陈雅迪
	 * */
	@Override
	public int getLanguageCourseRows(int id) {
		int result = 0;
		try {
			result = courseDao.getLanguageCourseRows(id);
		} catch (Exception e) {
			e.printStackTrace();
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
	public List<CourseBean> fetchAllLanCourseInfoList(int pageno, int id) {
		// 创建堆内存空间并赋值为空
		List<CourseBean> courseBeanInfoList = null;
		try {
			courseBeanInfoList = courseDao
					.fetchAllLanCourseInfoList(pageno, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseBeanInfoList;
	}

	/**
	 * @功能:按难度系数得到课程表的行数
	 * @参数：水平种类id
	 * @返回值 int 课程表的行数
	 * @作者 陈雅迪
	 * */
	@Override
	public int getLevelCourseRows(int id) {
		int result = 0;
		try {
			result = courseDao.getLevelCourseRows(id);
		} catch (Exception e) {
			e.printStackTrace();
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
	public List<CourseBean> fetchAllLevelCourseInfoList(int pageno, int id) {
		// 创建堆内存空间并赋值为空
		List<CourseBean> courseBeanInfoList = null;
		try {
			courseBeanInfoList = courseDao.fetchAllLevelCourseInfoList(pageno,
					id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseBeanInfoList;
	}

	/**
	 * @功能:按时间顺序得到课程表的行数，最新
	 * @返回值 int 课程表的行数
	 * @作者 陈雅迪
	 * */
	@Override
	public int getDateCourseRows() {
		int result = 0;
		try {
			result = courseDao.getDateCourseRows();
		} catch (Exception e) {
			e.printStackTrace();
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
	public List<CourseBean> fetchAllDateCourseInfoList(int pageno) {
		// 创建堆内存空间并赋值为空
		List<CourseBean> courseBeanInfoList = null;
		try {
			courseBeanInfoList = courseDao.fetchAllDateCourseInfoList(pageno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseBeanInfoList;
	}

	/**
	 * @功能:按点击量顺序得到课程表的行数，最热
	 * @返回值 int 课程表的行数
	 * @作者 陈雅迪
	 * */
	@Override
	public int getHotCourseRows() {
		int result = 0;
		try {
			result = courseDao.getHotCourseRows();
		} catch (Exception e) {
			e.printStackTrace();
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
	public List<CourseBean> fetchAllHotCourseInfoList(int pageno) {
		// 创建堆内存空间并赋值为空
		List<CourseBean> courseBeanInfoList = null;
		try {
			courseBeanInfoList = courseDao.fetchAllHotCourseInfoList(pageno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseBeanInfoList;
	}

	@Override
	public List<CourseBean> fetchAllDecCourseInfoList(int pageno, int id) {
		List<CourseBean> courseBeanInfoList = null;
		try {
			courseBeanInfoList = courseDao
					.fetchAllDecCourseInfoList(pageno, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseBeanInfoList;

	}

	@Override
	public List<CourseBean> fetchAllCourseInfo() {
		List<CourseBean> courseBeans = new ArrayList<CourseBean>();
		CourseDao courseDao = new CourseDaoImpl();
		try {
			courseBeans = courseDao.fetchAllCourseInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseBeans;
	}

	@Override
	public int fetchCourseInfoRows() {
		int rows = 0;
		CourseDao courseDao = new CourseDaoImpl();
		try {
			rows = courseDao.fetchCourseInfoRows();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	// 以下为魏泽锟编写
	@Override
	public List<CourseBean> fetchCourseInfoByPageno(int pageno) {
		List<CourseBean> courseBeans = new ArrayList<CourseBean>();
		CourseDao courseDao = new CourseDaoImpl();
		try {
			courseBeans = courseDao.fetchCourseInfoByPageno(pageno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseBeans;
	}

	@Override
	public CourseBean fetchCourseinfoByName(String courseName) {
		CourseBean courseBean = null;
		CourseDao courseDao = new CourseDaoImpl();
		try {
			courseBean = courseDao.fetchCourseinfoByName(courseName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseBean;
	}

	@Override
	public int addCourseinfo(CourseBean courseBean) {
		int result = 0;
		CourseDao courseDao = new CourseDaoImpl();
		try {
			result = courseDao.addCourseinfo(courseBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int deleteCourseinfoById(int id) {
		int result = 0;
		CourseDao courseDao = new CourseDaoImpl();
		try {
			result = courseDao.deleteCourseinfoById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public CourseBean fetchCourseinfoById(int course_id) {
		CourseBean courseBean = null;
		CourseDao courseDao = new CourseDaoImpl();
		try {
			courseBean = courseDao.fetchCourseinfoById(course_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseBean;
	}

	@Override
	public int courseToPlan(int course_id) {
		int plan_id = 0;
		try {
			plan_id = courseDao.courseToPlan(course_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plan_id;
	}

	@Override
	public Map<Integer, List> planDetail(int plan_id) {
		Map<Integer, List> map = null;
		try {
			map = chapterBiz.fetchChapterList(plan_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
