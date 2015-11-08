package com.etc.biz;

import java.util.List;

import com.etc.bean.CourseBean;

public interface CourseBizIndex {

	List<CourseBean> fetchAllCourseByHot();

	List<CourseBean> androidFetchCourseLimit(int row, int rowAll);

}
