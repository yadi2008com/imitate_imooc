package com.etc.dao;

import java.util.List;

import com.etc.bean.DecorationBean;

public interface DecorationDao {
	/**
	 * @功能: 添加课程计划信息
	 * @参数  课程计划对象
	 * @throws Exception 若出现异常，抛给上层调用者
	 * @返回值  1添加成功，0添加失败
	 * @作者 张恩桥
	 * */
	int decorationInsert(DecorationBean decorationBean) throws Exception;
	/**
	 * @功能: 根据课程计划id获取课程信息
	 * @参数  课程计划对象
	 * @throws Exception 若出现异常，抛给上层调用者
	 * @返回值  课程计划对象
	 * @作者 张恩桥
	 * */
	DecorationBean decorationEdit(int id) throws Exception;
	/**
	 * @功能: 根据课程计划id更新课程信息
	 * @参数  课程计划对象
	 * @throws Exception 若出现异常，抛给上层调用者
	 * @返回值  1修改成功，0修改失败
	 * @作者 张恩桥
	 * */
	int decorationUpdate(DecorationBean decorationBean) throws Exception;
	/**
	 * @功能: 获取全部课程计划信息
	 * @参数  无
	 * @throws Exception 若出现异常，抛给上层调用者
	 * @返回值  课程计划对象list数组
	 * @作者 张恩桥
	 * */
	List<DecorationBean> decorationShow() throws Exception;
	/**
	 * @功能: 根据课程计划id删除课程信息
	 * @参数  课程计划id
	 * @throws Exception 若出现异常，抛给上层调用者
	 * @返回值  1删除成功，0删除失败
	 * @作者 张恩桥
	 * */
	int decorationDelete(int id) throws Exception;
	/**
	 * @功能: 获取课程计划信息的总记录数
	 * @参数  无
	 * @throws Exception 若出现异常，抛给上层调用者
	 * @返回值  总记录数
	 * @作者 张恩桥
	 * */
	int decorationRows() throws Exception;
	/**
	 * @功能: 获取课程计划信息的分页记录
	 * @参数  无
	 * @throws Exception 若出现异常，抛给上层调用者
	 * @返回值  分页记录
	 * @作者 张恩桥
	 * */
	List<DecorationBean> decorationPageShow(int pageno) throws Exception;
}
