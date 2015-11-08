package com.etc.biz;

import java.util.ArrayList;
import java.util.List;

import com.etc.bean.MyOpinionBean;
import com.etc.dao.MyOpinionDao;
import com.etc.dao.MyOpinionDaoImpl;

public class MyOpinionBizImpl implements MyOpinionBiz {

	@Override
	public int addOpinion(MyOpinionBean myOpinionBean) {
		int result=0;
		try {
			MyOpinionDao myOpinionDao=new MyOpinionDaoImpl();
			 result= myOpinionDao.addOpinion(myOpinionBean);
			 
		} catch (Exception e) {
		}
		return result;
	}

	@Override
	public int getOtherOpinionNumber() {
		int result=0;
		try {
			MyOpinionDao myOpinionDao=new MyOpinionDaoImpl();
			 result= myOpinionDao.getOtherOpinionNumber();
			 
		} catch (Exception e) {
		}
		return result;
	}

	@Override
	public List<MyOpinionBean> getOtherOpinionInfoByPageno(int pageno) {
		List<MyOpinionBean> otherOpinionList=new ArrayList<MyOpinionBean>();
		MyOpinionDao myOpinionDao=new MyOpinionDaoImpl();
		try {
			otherOpinionList=myOpinionDao.getOtherOpinionInfoByPageno(pageno);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return otherOpinionList;
	}

}
