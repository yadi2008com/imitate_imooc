package com.etc.biz;

import java.util.List;
import java.util.Map;

import com.etc.bean.ChapterBean;

public interface ChapterBiz {

	public Map<Integer, List> fetchChapterList(int planid);

	/**
	 * @功能: 添加课程计划
	 * @参数 课程计划对象
	 * @返回值 1添加成功，0添加失败
	 * @作者 张恩桥
	 * */
	int chapterInsert(ChapterBean chapterBean);

	/**
	 * @功能: 根据id查询课程计划信息
	 * @参数 id
	 * @返回值 课程计划对象
	 * @作者 张恩桥
	 * */
	int chapterUpdate(ChapterBean chapterBean);

	/**
	 * @功能: 根据id修改课程计划信息
	 * @参数 课程计划对象
	 * @返回值 1修改成功，0修改失败
	 * @作者 张恩桥
	 * */
	ChapterBean chapterEdit(int id);

	/**
	 * @功能: 查询课程计划信息
	 * @参数 无
	 * @返回值 课程计划list数组
	 * @作者 张恩桥
	 * */
	List<ChapterBean> chapterShow();

	/**
	 * @功能: 删除课程计划信息
	 * @参数 id
	 * @返回值 1删除成功 2删除失败
	 * @作者 张恩桥
	 * */
	int chapterDelete(int id);

	/**
	 * @功能: 获取课程计划的总记录数
	 * @参数 无
	 * @返回值 总记录数
	 * @作者 张恩桥
	 * */
	int chapterRows();

	/**
	 * @功能: 获取课程计划的分页记录
	 * @参数 无
	 * @返回值 分页课程计划信息
	 * @作者 张恩桥
	 * */
	List<ChapterBean> chapterPageShow(int pageno);
	public Map<String, List> fetchChapterListString(int plan_id);
}
