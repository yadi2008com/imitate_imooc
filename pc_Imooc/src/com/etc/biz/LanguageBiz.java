package com.etc.biz;

import java.util.List;

import com.etc.bean.DecorationBean;
import com.etc.bean.LanguageBean;

public interface LanguageBiz {

	int fetchAllLanguageListRows();

	List<LanguageBean> fetchAllLanguageList(int pageno);

	String[] languageFetchAlldecoration(int[] decoration_id, int pageno);

	int delectLanguageById(int language_id);

	int selectLanguageByNameRows(String language_name);

	List<LanguageBean> selectLanguageByName(String language_name, int pageno);

	int addDecoration(String deco_name);

	DecorationBean selectDecorationByName(String deco_name);

	int addLanguage(String language_name, int decoration_id);

	int isDeco_nameRepeat(String deco_name);

	int isLaguageRepeat(String language_name);

}
