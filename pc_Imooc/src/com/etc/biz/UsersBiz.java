package com.etc.biz;

import java.util.List;

import com.etc.bean.UsersBean;

public interface UsersBiz {

	public int addUsers(UsersBean usersBean);

	public UsersBean userLogin(UsersBean userBean);

	public List<UsersBean> usersFetchAllList(int pageon);

	public boolean checkEmail(String email);

	public int fetchEmail(String email);

	public boolean checkPassword(String password);

	public int delectUserById(int user_id);


	public int updateUsersById(UsersBean usersBean);

	public UsersBean fetchSelectUserByName(String username);

	public List<UsersBean> selectUserByUsersName(String username,int pageno);

	public boolean checkNick(String nick);

	public int fetchUsername(String nick);

	public int usersFetchAllListRows();

	public int selectUserByUsersNameRows(String username);
	
	public boolean setPersonalFileById(UsersBean usersBean);
	
	public boolean setPassWordById(String userpassword,int id);
	
	public boolean matchPassWordById(String userpassword,int id);
	
	public boolean setAvatorById(String fileField,int id);
	
	public String fetchUseravatorById(int id);
	
	public UsersBean fetchUserBeanById(int id) throws Exception;
	
	public Boolean UpdateUserOccupationById(int id,String Occupation);
	
	public Boolean UpdateUserAutographById(int id,String autograph);
	
	public Boolean UpdateUserNicknameById(int id,String nickname);

	public Boolean UpdateUserSexById(int intid, String Sex);

	public void UpdateUserRegionById(int intid, String stringRegion);

}
