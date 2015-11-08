package com.etc.biz;

import java.util.List;

import com.etc.bean.DecorationBean;
import com.etc.bean.LanguageBean;
import com.etc.dao.LanguageDao;
import com.etc.dao.LanguageDaoImpl;

public class LanguageBizImpl implements LanguageBiz{

	LanguageDao languageDao =new LanguageDaoImpl();
	@Override
	public int fetchAllLanguageListRows() {
		int rows = 0;
		try {
			rows = languageDao.fetchAllLanguageListRows();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public List<LanguageBean> fetchAllLanguageList(int pageno) {
		List<LanguageBean> languagelist=null;
		try {
			languagelist = languageDao.fetchAllLanguageList(pageno);
					
		} catch (Exception e) {
			e.printStackTrace();
		}

		return languagelist;
	}

	@Override
	public String[] languageFetchAlldecoration(int[] decoration_id, int pageno) {
		String[] decorationList = null;
		try {
			decorationList = languageDao.languageFetchAlldecoration(decoration_id,
					pageno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decorationList;
	}

	@Override
	public int delectLanguageById(int language_id) {
		int rows=0;
		try {
			rows=languageDao.delectLanguageById(language_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public int selectLanguageByNameRows(String language_name) {
		int rows = 0;
		try {
			rows = languageDao.selectLanguageByNameRows(language_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public List<LanguageBean> selectLanguageByName(String language_name,
			int pageno) {
		List<LanguageBean> languagelist=null;
		try {
			languagelist = languageDao.selectLanguageByName(language_name,pageno);
					
		} catch (Exception e) {
			e.printStackTrace();
		}

		return languagelist;
	}

	@Override
	public int addDecoration(String deco_name) {
		int rows=0;
		try {
			rows=languageDao.addDecoration(deco_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public DecorationBean selectDecorationByName(String deco_name) {
		DecorationBean decorationBean=null;
		try {
			decorationBean=languageDao.selectDecorationByName(deco_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decorationBean;
	}

	@Override
	public int addLanguage(String language_name, int decoration_id) {
		int rows=0;
		try {
			rows=languageDao.addLanguage(language_name,decoration_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public int isDeco_nameRepeat(String deco_name) {
		int rows=0;
		try {
			rows=languageDao.isDeco_nameRepeat(deco_name);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public int isLaguageRepeat(String language_name) {
		int rows=0;
		try {
			rows=languageDao.isLaguageRepeat(language_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

}
