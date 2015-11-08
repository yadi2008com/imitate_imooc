package com.etc.biz;

import com.etc.dao.MyPlanDao;
import com.etc.dao.MyPlanDaoImpl;

public class MyPlanBizImpl implements MyPlanBiz {
   MyPlanDao myPlanDao=new MyPlanDaoImpl() ;

	@Override
	public int join(int plan_id,int user_id) {
		
		int result=0;
		try {
			result=myPlanDao.join(plan_id,user_id);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(int plan_id,int user_id) {
		int result=0;
		try {
			result=myPlanDao.delete(plan_id,user_id);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
