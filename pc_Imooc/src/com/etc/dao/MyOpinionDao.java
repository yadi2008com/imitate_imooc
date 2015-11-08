package com.etc.dao;

import java.util.List;

import com.etc.bean.MyOpinionBean;

public interface MyOpinionDao {

	int addOpinion(MyOpinionBean myOpinionBean) throws Exception;

	int getOtherOpinionNumber() throws Exception;

	List<MyOpinionBean> getOtherOpinionInfoByPageno(int pageno) throws Exception;

}
