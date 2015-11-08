package com.etc.biz;

import java.util.List;

import com.etc.bean.MyOpinionBean;

public interface MyOpinionBiz {

	int addOpinion(MyOpinionBean myOpinionBean);

	int getOtherOpinionNumber();

	List<MyOpinionBean> getOtherOpinionInfoByPageno(int pageno);

}
