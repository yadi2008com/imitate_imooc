package com.etc.dao;

import java.sql.SQLException;
import java.util.List;

import com.etc.bean.UsersBean;

public interface UsersDao {

	public int addUsers(UsersBean usersBean) throws Exception;

	public UsersBean userLogin(UsersBean userBean) throws Exception;

	public List<UsersBean> usersFetchAllList(int pageon) throws Exception;

	public Boolean checkEmail(String email) throws Exception;

	public int fetchEmail(String email) throws Exception;

	public Boolean checkPassword(String password) throws Exception;

	public int delectUserById(int user_id) throws Exception;

	public int updateUsersById(UsersBean usersBean) throws Exception;

	public UsersBean fetchSelectUserByName(String username) throws Exception;

	public List<UsersBean> selectUserByUsersName(String username,int pageno) throws Exception;

	public Boolean checkNick(String nick) throws Exception;

	public int usersFetchAllListRows() throws Exception;

	public int fetchUsername(String nick) throws Exception;

	public int selectUserByUsersNameRows(String username) throws Exception;
	
	public int setPersonalFileById(UsersBean usersBean) throws SQLException, Exception;
	
	public int setPassWordById(String userpassword,int id) throws Exception;
	
	public int matchPassWordById(String userpassword,int id) throws Exception;
	
	public String queryMailById(String oldmail,int id) throws Exception;
	
	public int setAvatorById(String fileField,int id) throws Exception;
	
	public String fetchUseravatorById(int id) throws Exception;
	
	public UsersBean fetchUserBeanById(int id) throws Exception;
	
	public int UpdateUserOccupationById(int id,String Occupation) throws Exception;
	
	public int UpdateUserAutographById(int id,String autograph) throws Exception;
	
	public int UpdateUserNicknameById(int id,String nickname) throws Exception;

	public int UpdateUserSexById(int id, String sex) throws Exception;

	public int UpdateUserRegionById(int id, String stringRegion) throws Exception;
	

}
