package com.etc.biz;

import java.util.List;

import com.etc.bean.SectionBean;
import com.etc.dao.SectionDao;
import com.etc.dao.SectionDaoImpl;

public class SectionBizImpl implements SectionBiz {
	SectionDao sectiondao = new SectionDaoImpl();

	@Override
	public int sectionInsert(SectionBean sectionBean) {
		int result = 0;
		try {
			result = sectiondao.sectionInsert(sectionBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public SectionBean sectionEdit(int id) {
		SectionBean sectionBean = null;
		try {
			sectionBean = sectiondao.sectionEdit(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sectionBean;
	}

	@Override
	public List<SectionBean> sectionShow() {
		List<SectionBean> sectionBeanList = null;
		try {
			sectionBeanList = sectiondao.sectionShow();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sectionBeanList;
	}

	@Override
	public int sectionDelete(int id) {
		int result = 0;
		try {
			result = sectiondao.sectionDelete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int sectionUpdate(SectionBean sectionBean) {
		int result = 0;
		try {
			result = sectiondao.sectionUpdate(sectionBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int sectionRows() {
		int result=0;
		try {
			result = sectiondao.sectionRows();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<SectionBean> sectionPageShow(int pageno) {
		List<SectionBean> sectionBeanList = null;
		try {
			sectionBeanList = sectiondao.sectionPageShow(pageno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sectionBeanList;
	}

}
