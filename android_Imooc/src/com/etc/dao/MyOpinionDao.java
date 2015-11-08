package com.etc.dao;

import java.util.List;

import com.etc.bean.MyOpinionBean;

public interface MyOpinionDao {

	long insertMyOpinion(MyOpinionBean myOpinionBean);

	List<MyOpinionBean> fetchAllMyOpinion();

}
