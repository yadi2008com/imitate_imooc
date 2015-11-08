package com.etc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.etc.bean.PageBean;
import com.etc.bean.UsersBean;
import com.etc.util.DBUtil;


public class UsersDaoImpl implements UsersDao {

	DBUtil dbUtil = new DBUtil();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	@Override
	public int addUsers(UsersBean usersBean) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "insert into users (user_email,username,userpwd,user_job,user_city,user_sex,user_sign,user_image) values (?,?,?,?,?,?,?,?)";

		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(2, usersBean.getUsername());
		preparedStatement.setString(1, usersBean.getUser_email());
		preparedStatement.setString(3, usersBean.getUserpwd());
		preparedStatement.setString(4, usersBean.getUser_job());
		preparedStatement.setString(5, usersBean.getUser_city());
		preparedStatement.setString(6, usersBean.getUser_sex());
		preparedStatement.setString(7, usersBean.getUser_sign());
		preparedStatement.setString(8, usersBean.getUser_image());

		rows = preparedStatement.executeUpdate();

		dbUtil.closeDBSource(connection, preparedStatement, resultSet);

		return rows;
	}

	@Override
	public UsersBean userLogin(UsersBean userBean) throws Exception {
		UsersBean usersBean2 = null;

		connection = dbUtil.getConnection();

		String sql = "select * from users where user_email = ? and userpwd = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, userBean.getUser_email());
		preparedStatement.setString(2, userBean.getUserpwd());

		resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			usersBean2 = new UsersBean();
			usersBean2.setUser_id(resultSet.getInt("user_id"));
			usersBean2.setUser_email(resultSet.getString("user_email"));
			usersBean2.setUsername(resultSet.getString("username"));
			usersBean2.setUserpwd(resultSet.getString("userpwd"));
			usersBean2.setUser_job(resultSet.getString("user_job"));
			usersBean2.setUser_city(resultSet.getString("user_city"));
			usersBean2.setUser_sex(resultSet.getString("user_sex"));
			usersBean2.setUser_sign(resultSet.getString("user_sign"));
			usersBean2.setUser_image(resultSet.getString("user_image"));
		}

		dbUtil.closeDBSource(connection, preparedStatement, resultSet);

		return usersBean2;
	}

	/**
	 * @作者 赵晓鑫
	 * @功能 查询所有用户
	 * @返回值 usersList
	 */
	@Override
	public List<UsersBean> usersFetchAllList(int pageon) throws Exception {
		List<UsersBean> userslist = null;
		connection = dbUtil.getConnection();
		String sql="select * from users limit ?,?";
		int startIndex = (pageon - 1) * PageBean.ROWS_PRE_PAGE;
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, startIndex);
		preparedStatement.setInt(2, PageBean.ROWS_PRE_PAGE);
		resultSet = preparedStatement.executeQuery();
		userslist = new ArrayList<UsersBean>();
		while (resultSet.next()) {

			UsersBean usersBean = new UsersBean();
			usersBean.setUser_id(resultSet.getInt("user_id"));
			usersBean.setUsername(resultSet.getString("username"));
			usersBean.setUser_job(resultSet.getString("user_job"));
			usersBean.setUser_city(resultSet.getString("user_city"));

			userslist.add(usersBean);
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);

		return userslist;
	}

	@Override
	public Boolean checkEmail(String email) throws Exception {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	@Override
	public int fetchEmail(String email) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "select * from users where user_email = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, email);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			rows = 1;
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public Boolean checkPassword(String password) throws Exception {
		boolean flag = false;
		try {
			String check = "^\\s*[^\\s\u4e00-\u9fa5]{6,16}\\s*$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(password);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	@Override
	public int delectUserById(int user_id) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "delete from users where user_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, user_id);
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;

	}

	/**
	 * 修改用户信息
	 */
	@Override
	public int updateUsersById(UsersBean usersBean) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();

		String sql = "update users set  username=?,user_email=?,userpwd=?,user_job=?,user_city=?,user_sex=?,user_sign=?,user_image=? where user_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, usersBean.getUsername());
		preparedStatement.setString(2, usersBean.getUser_email());
		preparedStatement.setString(3, usersBean.getUserpwd());
		preparedStatement.setString(4, usersBean.getUser_job());
		preparedStatement.setString(5, usersBean.getUser_city());
		preparedStatement.setString(6, usersBean.getUser_sex());
		preparedStatement.setString(7, usersBean.getUser_sign());
		preparedStatement.setString(8, usersBean.getUser_image());
		preparedStatement.setInt(9, usersBean.getUser_id());

		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;
	}

	/**
	 * 按名称查询
	 * 返回 userbean
	 */
	
	@Override
	public UsersBean fetchSelectUserByName(String username) throws Exception {
		UsersBean usersBean = null;
		connection = dbUtil.getConnection();
		String sql = "select * from users where username=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, username);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			usersBean = new UsersBean();
			usersBean.setUser_id(resultSet.getInt("user_id"));
			usersBean.setUsername(resultSet.getString("username"));
			usersBean.setUser_job(resultSet.getString("user_job"));
			usersBean.setUser_city(resultSet.getString("user_city"));
			usersBean.setUser_email(resultSet.getString("user_email"));
			usersBean.setUser_image(resultSet.getString("user_image"));
			usersBean.setUser_sex(resultSet.getString("user_sex"));
			usersBean.setUser_sign(resultSet.getString("user_sign"));
			usersBean.setUserpwd(resultSet.getString("userpwd"));

		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return usersBean;
	}

	/**
	 * 按昵称查询 
	 * 返回 list
	 */
	@Override
	public List<UsersBean> selectUserByUsersName(String username,int pageno)
			throws Exception {
		List<UsersBean> userslist = null;
		connection = dbUtil.getConnection();
		String sql="select * from users where username=? limit ?,?";
		preparedStatement = connection.prepareStatement(sql);
		int startIndex = (pageno - 1) * PageBean.ROWS_PRE_PAGE;
		preparedStatement.setString(1, username);
		preparedStatement.setInt(2, startIndex);
		preparedStatement.setInt(3, PageBean.ROWS_PRE_PAGE);
		resultSet = preparedStatement.executeQuery();
		userslist = new ArrayList<UsersBean>();
		while (resultSet.next()) {
			UsersBean usersBean = new UsersBean();
			usersBean.setUser_id(resultSet.getInt("user_id"));
			usersBean.setUsername(resultSet.getString("username"));
			usersBean.setUser_job(resultSet.getString("user_job"));
			usersBean.setUser_city(resultSet.getString("user_city"));
			userslist.add(usersBean);
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return userslist;
	}

	@Override
	public Boolean checkNick(String nick) throws Exception {
		boolean flag = false;
		try {
			String check = "^[a-zA-Z0-9_]{3,16}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(nick);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	/**
	 * 获得users的rows
	 */
	@Override
	public int usersFetchAllListRows() throws Exception {
		int rows=0;
		connection=dbUtil.getConnection();
		String sql="select count(*) from users";
		preparedStatement=connection.prepareStatement(sql);
		resultSet=preparedStatement.executeQuery();
		if(resultSet.next()){
			rows = resultSet.getInt(1);
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;
	}

	/**
	 * 按昵称查询rows
	 */
	@Override
	public int selectUserByUsersNameRows(String username) throws Exception {
		int rows=0;
		connection=dbUtil.getConnection();
		String sql="select count(*) from users where username=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, username);
		resultSet=preparedStatement.executeQuery();
		if(resultSet.next()){
			rows = resultSet.getInt(1);
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;
	}

	
	

	@Override
	public int fetchUsername(String nick) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "select * from users where username = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, nick);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			rows = 1;
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public int setPersonalFileById(UsersBean usersBean) throws Exception {
		// TODO Auto-generated method stub		
		int rows;
		connection = dbUtil.getConnection();
		String sql = "UPDATE users SET username=?,user_job=?,user_city=?,user_sex=?,user_sign=? WHERE user_id=?;";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1,usersBean.getUsername());
		preparedStatement.setString(2,usersBean.getUser_job());
		preparedStatement.setString(3,usersBean.getUser_city());
		preparedStatement.setString(4,usersBean.getUser_sex());
		preparedStatement.setString(5, usersBean.getUser_sign());
		preparedStatement.setInt(6,usersBean.getUser_id());
		rows= preparedStatement.executeUpdate();	
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public int setPassWordById(String userpassword,int id) throws Exception {
		// TODO Auto-generated method stub
		int rows;
		connection = dbUtil.getConnection();
		String sql = "UPDATE users SET userpwd=? WHERE user_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1,userpassword);
		preparedStatement.setInt(2,id);
		rows= preparedStatement.executeUpdate();			
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);	
		return rows;
	}

	@Override
	public int matchPassWordById(String userpassword, int id) throws Exception {
		// TODO Auto-generated method stub
		ResultSet res;
		int rows=0;
		connection = dbUtil.getConnection();
		String sql = "select * from users where userpwd=? and user_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1,userpassword);
		preparedStatement.setInt(2,id);
		res=preparedStatement.executeQuery();
		while(res.next()){
			rows=++rows;
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);	
		return rows;		
	}

	@Override
	public String queryMailById(String oldmail, int id) throws Exception {
		// TODO Auto-generated method stub
		ResultSet res;		
		String mailstr="";
		connection = dbUtil.getConnection();
		String sql = "select * from users where user_email=? and user_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1,oldmail);
		preparedStatement.setInt(2,id);
		res=preparedStatement.executeQuery();	
		while(res.next()){
			mailstr=oldmail;		    		
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);	
		return mailstr;		
	}

	@Override
	public int setAvatorById(String fileField, int id) throws Exception {
		// TODO Auto-generated method stub
		int rows;
		connection = dbUtil.getConnection();
		String sql ="UPDATE users SET user_image=? WHERE user_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1,fileField);
		preparedStatement.setInt(2,id);
		rows= preparedStatement.executeUpdate();			
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);	
		return rows;
	}

	@Override
	public String fetchUseravatorById(int id) throws Exception {
		// TODO Auto-generated method stub
		String a="";
		connection=dbUtil.getConnection();
		String sql="SELECT user_image FROM users WHERE user_id=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);		
		resultSet=preparedStatement.executeQuery();
		while(resultSet.next()){
			a=resultSet.getString("user_image");
		}
		return a;
	}

	@Override
	public UsersBean fetchUserBeanById(int id) throws Exception {
		// TODO Auto-generated method stub
		connection=dbUtil.getConnection();
		String sql="SELECT * FROM users WHERE user_id=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		resultSet=preparedStatement.executeQuery();
		UsersBean usersBean=new UsersBean();
		while(resultSet.next()){					
			usersBean.setUser_email(resultSet.getString("user_email"));
			usersBean.setUser_image(resultSet.getString("user_image"));
			usersBean.setUser_job(resultSet.getString("user_job"));
			usersBean.setUser_sex(resultSet.getString("user_sex"));
			usersBean.setUser_city(resultSet.getString("user_city"));
			usersBean.setUser_sign(resultSet.getString("user_sign"));
			usersBean.setUsername(resultSet.getString("username"));
			usersBean.setUserpwd(resultSet.getString("userpwd"));
		}
		
		return usersBean;
	}

	@Override
	public int UpdateUserOccupationById(int id, String Occupation) throws Exception {
		// TODO Auto-generated method stub
		int rows=0;
		connection=dbUtil.getConnection();
		String sql="UPDATE users SET user_job=? WHERE user_id=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, Occupation);
		preparedStatement.setInt(2, id);
		rows= preparedStatement.executeUpdate();			
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);		    
		return rows;
	}

	@Override
	public int UpdateUserAutographById(int id, String autograph) throws Exception {
		// TODO Auto-generated method stub
		int rows=0;
		connection=dbUtil.getConnection();
		String sql="UPDATE users SET user_sign=? WHERE user_id=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, autograph);
		preparedStatement.setInt(2, id);
		rows= preparedStatement.executeUpdate();			
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);		    
		return rows;		
	}

	@Override
	public int UpdateUserNicknameById(int id, String nickname)
			throws Exception {
		// TODO Auto-generated method stub
		int rows=0;
		connection=dbUtil.getConnection();
		String sql="UPDATE users SET username=? WHERE user_id=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, nickname);
		preparedStatement.setInt(2, id);
		rows= preparedStatement.executeUpdate();			
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);		    
		return rows;
	}

	@Override
	public int UpdateUserSexById(int id, String sex) throws Exception {
		// TODO Auto-generated method stub
		int rows=0;
		connection=dbUtil.getConnection();
		String sql="UPDATE users SET user_sex=? WHERE user_id=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, sex);
		preparedStatement.setInt(2, id);
		rows= preparedStatement.executeUpdate();			
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);		    
		return rows;
	}

	@Override
	public int UpdateUserRegionById(int id, String stringRegion) throws Exception {
		// TODO Auto-generated method stub
		int rows=0;
		connection=dbUtil.getConnection();
		String sql="UPDATE users SET user_city=? WHERE user_id=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, stringRegion);
		preparedStatement.setInt(2, id);
		rows= preparedStatement.executeUpdate();			
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);		    
		return rows;
	}
	

}
