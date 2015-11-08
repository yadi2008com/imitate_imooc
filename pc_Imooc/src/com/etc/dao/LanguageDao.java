package com.etc.dao;

import java.util.List;

import com.etc.bean.DecorationBean;
import com.etc.bean.LanguageBean;

public interface LanguageDao {

	int fetchAllLanguageListRows() throws Exception;

	List<LanguageBean> fetchAllLanguageList(int pageno) throws Exception;

	String[] languageFetchAlldecoration(int[] decoration_id, int pageno) throws Exception;

	int delectLanguageById(int language_id) throws Exception;

	int selectLanguageByNameRows(String language_name) throws Exception;

	List<LanguageBean> selectLanguageByName(String language_name, int pageno) throws Exception;

	int addDecoration(String deco_name) throws Exception;

	DecorationBean selectDecorationByName(String deco_name) throws Exception;

	int addLanguage(String language_name, int decoration_id) throws Exception;

	int isDeco_nameRepeat(String deco_name) throws Exception;

	int isLaguageRepeat(String language_name) throws Exception;

}
