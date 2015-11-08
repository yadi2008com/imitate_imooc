package com.etc.biz;

import java.util.List;

import com.etc.bean.PlanBean;

public interface PlanBiz  {

	
	List<PlanBean> fetchAllPlanByPageno(int pageno);



	int fetchAllPlanRows();

	

	public List<PlanBean> fetchPlanList(int planid);

	
	/**
	 * @功能: 添加课程计划
	 * @参数 课程计划对象
	 * @返回值 1添加成功，0添加失败
	 * @作者 张恩桥
	 * */
	int planInsert(PlanBean planBean);

	int getDecorationPlanRows(int id);

	List<PlanBean> fetchDecPlanInfoList(int pageno, int id);


	

	/**
	 * @功能: 根据id查询课程计划信息
	 * @参数 id
	 * @返回值 课程计划对象
	 * @作者 张恩桥
	 * */
	int planUpdate(PlanBean planBean);

	/**
	 * @功能: 根据id修改课程计划信息
	 * @参数 课程计划对象
	 * @返回值 1修改成功，0修改失败
	 * @作者 张恩桥
	 * */
	PlanBean planEdit(int id);

	/**
	 * @功能: 查询课程计划信息
	 * @参数 无
	 * @返回值 课程计划list数组
	 * @作者 张恩桥
	 * */
	List<PlanBean> planShow();

	/**
	 * @功能: 删除课程计划信息
	 * @参数 id
	 * @返回值 1删除成功 2删除失败
	 * @作者 张恩桥
	 * */
	int planDelete(int id);

	/**
	 * @功能: 获取课程计划的总记录数
	 * @参数 无
	 * @返回值 总记录数
	 * @作者 张恩桥
	 * */
	int planRows();

	/**
	 * @功能: 获取课程计划的分页记录
	 * @参数 无
	 * @返回值 分页课程计划信息
	 * @作者 张恩桥
	 * */
	List<PlanBean> planPageShow(int pageno);
	

	

}
