package com.etc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @功能：封装数据库连接的类
 * @作者：郝宝亮
 *
 */
public class DBUtil {
	/**
	 * @功能：数据库连接的方法
	 * @return
	 * @throws Exception
	 */
	public Connection getConnection() throws Exception {
		Connection connection = null;
		String user = "root";
		String password = "";
        String url = "jdbc:mysql://localhost:3306/immoc?useUnicode=true&characterEncoding=utf8";
		String className = "com.mysql.jdbc.Driver";
		Class.forName(className);
		connection = DriverManager.getConnection(url, user, password);
		return connection;
	}

	/**
	 * @功能：数据库关闭的方法
	 * @param connection
	 * @param preparedStatement
	 * @param resultSet
	 * @throws Exception
	 */
	public void closeDBSource(Connection connection,
			PreparedStatement preparedStatement, ResultSet resultSet)
			throws Exception {
		if (resultSet != null) {
			resultSet.close();
		}
		if (preparedStatement != null) {
			preparedStatement.close();
		}
		if (connection != null) {
			connection.close();
		}
	}
}
