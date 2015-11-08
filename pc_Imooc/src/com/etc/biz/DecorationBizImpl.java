package com.etc.biz;

import java.util.List;

import com.etc.bean.DecorationBean;
import com.etc.dao.DecorationDao;
import com.etc.dao.DecorationDaoImpl;

public class DecorationBizImpl implements DecorationBiz {
	DecorationDao decorationdao = new DecorationDaoImpl();

	@Override
	public int decorationInsert(DecorationBean decorationBean) {
		int result = 0;
		try {
			result = decorationdao.decorationInsert(decorationBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public DecorationBean decorationEdit(int id) {
		DecorationBean decorationBean = null;
		try {
			decorationBean = decorationdao.decorationEdit(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decorationBean;
	}

	@Override
	public List<DecorationBean> decorationShow() {
		List<DecorationBean> decorationBeanList = null;
		try {
			decorationBeanList = decorationdao.decorationShow();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decorationBeanList;
	}

	@Override
	public int decorationDelete(int id) {
		int result = 0;
		try {
			result = decorationdao.decorationDelete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int decorationUpdate(DecorationBean decorationBean) {
		int result = 0;
		try {
			result = decorationdao.decorationUpdate(decorationBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int decorationRows() {
		int result=0;
		try {
			result = decorationdao.decorationRows();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<DecorationBean> decorationPageShow(int pageno) {
		List<DecorationBean> decorationBeanList = null;
		try {
			decorationBeanList = decorationdao.decorationPageShow(pageno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decorationBeanList;
	}

}
