package com.etc.biz;

import java.util.ArrayList;
import java.util.List;

import com.etc.bean.CourseBean;
import com.etc.bean.PageBean;
import com.etc.bean.PlanBean;
import com.etc.dao.CourseDao;
import com.etc.dao.CourseDaoImpl;
import com.etc.dao.PlanDao;
import com.etc.dao.PlanDaoImpl;

public class PlanBizImpl  implements PlanBiz{
	PlanDao planDao=new PlanDaoImpl();
	

	

	@Override
	public List<PlanBean> fetchAllPlanByPageno(int pageno) {
		PlanDao planDao=new PlanDaoImpl();
		List<PlanBean> allPlanList=null;
		try {
			allPlanList=planDao.fetchAllPlanByPageno(pageno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allPlanList;
	}

	

	@Override
	public int fetchAllPlanRows() {
		int rows=0;
		try {
			rows=planDao.fetchAllPlanRows();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rows;
	}

	







	@Override
	public List<PlanBean> fetchPlanList(int planid) {
		List<PlanBean> planBeanList=null;
		try {
			planBeanList=planDao.fetchPlanList(planid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return planBeanList;
	}

	@Override
	public int getDecorationPlanRows(int id) {
		int result=0;
		try {
			result=planDao.getDecorationCourseRows(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<PlanBean> fetchDecPlanInfoList(int pageno, int id) {
		 List<PlanBean> planBeanInfoList = null;
		 try {
			 planBeanInfoList = planDao. fetchDecCourseInfoList(pageno,  id) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return planBeanInfoList;
	}

  @Override
	public int planInsert(PlanBean planBean) {
		int result = 0;
		try {
			result = planDao.planInsert(planBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	
	
	@Override
	public List<PlanBean> planShow() {
		List<PlanBean> planBeanList = null;
		try {
			planBeanList = planDao.planShow();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return planBeanList;
	}
	@Override
	public PlanBean planEdit(int id) {
		PlanBean planBean = null;
		try {
			planBean = planDao.planEdit(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return planBean;
	}

	@Override
	public int planDelete(int id) {
		int result = 0;
		try {
			result = planDao.planDelete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int planUpdate(PlanBean planBean) {
		int result = 0;
		try {
			result = planDao.planUpdate(planBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int planRows() {
		int result=0;
		try {
			result = planDao.planRows();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<PlanBean> planPageShow(int pageno) {
		List<PlanBean> planBeanList = null;
		try {
			planBeanList = planDao.planPageShow(pageno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return planBeanList;
	}
}
