package com.etc.dao;

import java.util.List;

import com.etc.bean.CourseBean;

public interface CourseIndexDao {

	List<CourseBean> fetchAllCourseIndex() throws Exception;

	List<CourseBean> androidFetchCourseLimit(int row, int rowAll) throws Exception;

}
