package com.etc.dao;

import java.util.List;

import com.etc.bean.MyCourseBean;

public interface MyCourseDao {

	List<MyCourseBean> fetchMyCourseListByUserId(int userid)  ;

  
}
