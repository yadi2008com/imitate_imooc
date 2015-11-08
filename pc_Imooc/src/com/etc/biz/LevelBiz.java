package com.etc.biz;

import java.util.List;

import com.etc.bean.LevelBean;

public interface LevelBiz {

	public List<LevelBean> levelFetchAllList(int pageno);

	public int levelFetchAllListRows();

	public int selectLevelByUsersNameRows(String leve_name);

	public List<LevelBean> selectLevelByUsersName(String leve_name, int pageno);

	public int addlevel(String leve_name);

	public int delectLevelById(int level_id);

	public int updatelevel(int level_id, String leve_name);

	public int isLevelRepeat(String leve_name);

}
