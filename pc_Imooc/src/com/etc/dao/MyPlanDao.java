package com.etc.dao;

public interface MyPlanDao {

	int join(int plan_id,int user_id) throws Exception;

	int delete(int plan_id,int user_id) throws Exception;

}
