package com.etc.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.etc.bean.ChapterBean;
import com.etc.dao.ChapterDao;
import com.etc.dao.ChapterDaoImpl;

public class ChapterBizImpl implements ChapterBiz {
	ChapterDao chapterDao = new ChapterDaoImpl();

	@Override
	public Map<Integer, List> fetchChapterList(int planid) {
		Map<Integer, List> map = new HashMap<Integer, List>();
		try {
			map = chapterDao.fetchChapterList(planid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public int chapterInsert(ChapterBean chapterBean) {
		int result = 0;
		try {
			result = chapterDao.chapterInsert(chapterBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ChapterBean chapterEdit(int id) {
		ChapterBean chapterBean = null;
		try {
			chapterBean = chapterDao.chapterEdit(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chapterBean;
	}

	@Override
	public List<ChapterBean> chapterShow() {
		List<ChapterBean> chapterBeanList = null;
		try {
			chapterBeanList = chapterDao.chapterShow();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chapterBeanList;
	}

	@Override
	public int chapterDelete(int id) {
		int result = 0;
		try {
			result = chapterDao.chapterDelete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int chapterUpdate(ChapterBean chapterBean) {
		int result = 0;
		try {
			result = chapterDao.chapterUpdate(chapterBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int chapterRows() {
		int result = 0;
		try {
			result = chapterDao.chapterRows();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<ChapterBean> chapterPageShow(int pageno) {
		List<ChapterBean> chapterBeanList = null;
		try {
			chapterBeanList = chapterDao.chapterPageShow(pageno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chapterBeanList;
	}
	@Override
	public Map<String, List> fetchChapterListString(int plan_id) {
		Map<String, List> map=null;
		try {
			map=chapterDao.fetchChapterListString(plan_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
