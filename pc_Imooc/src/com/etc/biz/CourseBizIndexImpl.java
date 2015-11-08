package com.etc.biz;

import java.util.List;

import com.etc.bean.CourseBean;
import com.etc.dao.CourseDao;
import com.etc.dao.CourseDaoImpl;
import com.etc.dao.CourseIndexDao;
import com.etc.dao.CourseIndexDaoImpl;

public class CourseBizIndexImpl implements CourseBizIndex {

	@Override
	public List<CourseBean> fetchAllCourseByHot() {
		List<CourseBean> courseBeans = null;
		CourseIndexDao courseIndexDao=new CourseIndexDaoImpl();
		try {
			courseBeans=courseIndexDao.fetchAllCourseIndex();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courseBeans;
	}

	@Override
	public List<CourseBean> androidFetchCourseLimit(int row, int rowAll) {
		List<CourseBean> courseBeans = null;
		CourseIndexDao courseIndexDao = new CourseIndexDaoImpl();
		try {
			courseBeans = courseIndexDao.androidFetchCourseLimit(row, rowAll);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseBeans;

	}

}
