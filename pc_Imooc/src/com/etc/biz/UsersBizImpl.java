package com.etc.biz;

import java.util.ArrayList;
import java.util.List;

import com.etc.bean.UsersBean;
import com.etc.dao.UsersDao;
import com.etc.dao.UsersDaoImpl;

public class UsersBizImpl implements UsersBiz {

	UsersDao usersdao = new UsersDaoImpl();

	@Override
	public int addUsers(UsersBean usersBean) {
		int rows = 0;
		try {
			rows = usersdao.addUsers(usersBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public UsersBean userLogin(UsersBean userBean) {
		UsersBean usersBean2 = null;
		try {
			usersBean2 = usersdao.userLogin(userBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usersBean2;
	}

	@Override
	public List<UsersBean> usersFetchAllList(int pageon) {
		List<UsersBean> userslist = null;
		try {
			userslist = usersdao.usersFetchAllList(pageon);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userslist;
	}

	@Override
	public int delectUserById(int user_id) {
		int i = 0;
		try {
			i = usersdao.delectUserById(user_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;

	}

	@Override
	public int updateUsersById(UsersBean usersBean) {
		int i = 0;
		try {
			i = usersdao.updateUsersById(usersBean);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return i;
	}

	@Override
	public UsersBean fetchSelectUserByName(String username) {
		UsersBean usersBean = new UsersBean();
		try {
			usersBean = usersdao.fetchSelectUserByName(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usersBean;
	}

	@Override
	public List<UsersBean> selectUserByUsersName(String username, int pageno) {
		List<UsersBean> userslist = null;
		try {
			userslist = usersdao.selectUserByUsersName(username, pageno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userslist;
	}

	@Override
	public boolean checkEmail(String email) {
		Boolean flag = false;
		try {
			flag = usersdao.checkEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public int fetchEmail(String email) {
		int rows = 0;
		try {
			rows = usersdao.fetchEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public boolean checkPassword(String password) {
		Boolean flag = false;
		try {
			flag = usersdao.checkPassword(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean checkNick(String nick) {
		Boolean flag = false;
		try {
			flag = usersdao.checkNick(nick);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public int fetchUsername(String nick) {
		int rows = 0;
		try {
			rows = usersdao.fetchUsername(nick);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public int usersFetchAllListRows() {
		int rows = 0;
		try {
			rows = usersdao.usersFetchAllListRows();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public int selectUserByUsersNameRows(String username) {
		int rows = 0;
		try {
			rows = usersdao.selectUserByUsersNameRows(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public boolean setPersonalFileById(UsersBean usersBean) {
		// TODO Auto-generated method stub
		boolean flag = false;
		int rows;
		try {
			rows = usersdao.setPersonalFileById(usersBean);
			if (rows >= 1) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean setPassWordById(String userpassword, int id) {
		// TODO Auto-generated method stub
		boolean flag = false;
		int rows;
		try {
			rows = usersdao.setPassWordById(userpassword, id);
			if (rows >= 1) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean matchPassWordById(String userpassword, int id) {
		// TODO Auto-generated method stub
		boolean flag = false;
		int rows;
		try {
			rows = usersdao.matchPassWordById(userpassword, id);
			// System.out.println(rows);
			if (rows >= 1) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean setAvatorById(String fileField, int id) {
		// TODO Auto-generated method stub
		boolean flag = false;
		int rows;
		try {
			rows = usersdao.setAvatorById(fileField, id);
			// System.out.println(rows);
			if (rows >= 1) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public String fetchUseravatorById(int id) {
		// TODO Auto-generated method stub
		String Useravator = "";
		try {
			Useravator = usersdao.fetchUseravatorById(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Useravator;
	}

	@Override
	public UsersBean fetchUserBeanById(int id) throws Exception {
		// TODO Auto-generated method stub
		UsersBean usersBean = new UsersBean();
		try {
			usersBean = usersdao.fetchUserBeanById(id);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return usersBean;
	}

	@Override
	public Boolean UpdateUserOccupationById(int id, String Occupation) {
		// TODO Auto-generated method stub
		UsersBean usersBean = new UsersBean();
		Boolean flag = false;
		int rows = 0;
		try {
			rows = usersdao.UpdateUserOccupationById(id, Occupation);
			if (rows > 0) {
				flag = true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public Boolean UpdateUserAutographById(int id, String autograph) {
		// TODO Auto-generated method stub
		UsersBean usersBean = new UsersBean();
		Boolean flag = false;
		int rows = 0;
		try {
			rows = usersdao.UpdateUserAutographById(id, autograph);
			if (rows > 0) {
				flag = true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public Boolean UpdateUserNicknameById(int id, String nickname) {
		// TODO Auto-generated method stub
		UsersBean usersBean = new UsersBean();
		Boolean flag = false;
		int rows = 0;
		try {
			rows = usersdao.UpdateUserNicknameById(id, nickname);
			if (rows > 0) {
				flag = true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public Boolean UpdateUserSexById(int id, String sex) {
		// TODO Auto-generated method stub
		UsersBean usersBean = new UsersBean();
		Boolean flag = false;
		int rows = 0;
		try {
			rows = usersdao.UpdateUserSexById(id, sex);
			if (rows > 0) {
				flag = true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public void UpdateUserRegionById(int id, String stringRegion) {
		// TODO Auto-generated method stub
		UsersBean usersBean = new UsersBean();
		Boolean flag = false;
		int rows = 0;
		try {
			rows = usersdao.UpdateUserRegionById(id, stringRegion);
			if (rows > 0) {
				flag = true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
	}

}
