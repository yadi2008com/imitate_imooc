package com.etc.biz;

import java.util.List;

import com.etc.bean.SectionBean;

public interface SectionBiz {
	/**
	 * @功能: 添加课程计划
	 * @参数 课程计划对象
	 * @返回值 1添加成功，0添加失败
	 * @作者 张恩桥
	 * */
	int sectionInsert(SectionBean sectionBean);

	/**
	 * @功能: 根据id查询课程计划信息
	 * @参数 id
	 * @返回值 课程计划对象
	 * @作者 张恩桥
	 * */
	int sectionUpdate(SectionBean sectionBean);

	/**
	 * @功能: 根据id修改课程计划信息
	 * @参数 课程计划对象
	 * @返回值 1修改成功，0修改失败
	 * @作者 张恩桥
	 * */
	SectionBean sectionEdit(int id);

	/**
	 * @功能: 查询课程计划信息
	 * @参数 无
	 * @返回值 课程计划list数组
	 * @作者 张恩桥
	 * */
	List<SectionBean> sectionShow();

	/**
	 * @功能: 删除课程计划信息
	 * @参数 id
	 * @返回值 1删除成功 2删除失败
	 * @作者 张恩桥
	 * */
	int sectionDelete(int id);

	/**
	 * @功能: 获取课程计划的总记录数
	 * @参数 无
	 * @返回值 总记录数
	 * @作者 张恩桥
	 * */
	int sectionRows();

	/**
	 * @功能: 获取课程计划的分页记录
	 * @参数 无
	 * @返回值 分页课程计划信息
	 * @作者 张恩桥
	 * */
	List<SectionBean> sectionPageShow(int pageno);

}
