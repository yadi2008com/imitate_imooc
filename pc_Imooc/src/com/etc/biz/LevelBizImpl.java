package com.etc.biz;

import java.util.List;

import com.etc.bean.LevelBean;
import com.etc.dao.LevelDao;
import com.etc.dao.LevelDaoImpl;

public class LevelBizImpl implements LevelBiz{

	LevelDao  leveldao=new LevelDaoImpl();
	@Override
	public List<LevelBean> levelFetchAllList(int pageno) {
		List<LevelBean> levellist = null;
		try {
			levellist = leveldao.levelFetchAllList(pageno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return levellist;
		
	}

	@Override
	public int levelFetchAllListRows() {
		int rows=0;
		try {
			rows = leveldao.levelFetchAllListRows();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public int selectLevelByUsersNameRows(String leve_name) {
		int rows=0;
		try {
			rows = leveldao.selectLevelByUsersNameRows(leve_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public List<LevelBean> selectLevelByUsersName(String leve_name, int pageno) {
		List<LevelBean> levellist = null;
		try {
			levellist = leveldao.selectLevelByUsersName(leve_name,pageno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return levellist;
	}

	@Override
	public int addlevel(String leve_name) {
		int rows=0;
		try {
			rows=leveldao.addlevel(leve_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public int delectLevelById(int level_id) {
		int rows=0;
		try {
			rows=leveldao.delectLevelById(level_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rows;
	}

	@Override
	public int updatelevel(int level_id, String leve_name) {
		int rows=0;
		try {
			rows=leveldao.updatelevel(level_id,leve_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public int isLevelRepeat(String leve_name) {
		int rows=0;
		try {
			rows=leveldao.isLevelRepeat(leve_name);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rows;
	}

}
