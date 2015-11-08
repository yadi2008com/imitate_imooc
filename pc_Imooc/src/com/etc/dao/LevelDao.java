package com.etc.dao;

import java.util.List;

import com.etc.bean.LevelBean;

public interface LevelDao {

	public List<LevelBean> levelFetchAllList(int pageno) throws Exception;

	public int levelFetchAllListRows() throws Exception;

	public List<LevelBean> selectLevelByUsersName(String leve_name, int pageno) throws Exception;

	public int selectLevelByUsersNameRows(String leve_name) throws Exception;

	public int addlevel(String leve_name) throws Exception;

	public int delectLevelById(int level_id) throws Exception;

	public int updatelevel(int level_id, String leve_name) throws Exception;

	public int isLevelRepeat(String leve_name) throws Exception;

	

}
