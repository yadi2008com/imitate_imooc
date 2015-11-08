package com.etc.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.UsersBean;
import com.etc.biz.UsersBiz;
import com.etc.biz.UsersBizImpl;
import com.mysql.fabric.Response;

/**
 * 后台添加用户 Servlet implementation class AddUsersServlet
 */
@WebServlet("/AddUsersServlet")
public class AddUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUsersServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		try {
			String nick = request.getParameter("username");
			String email = request.getParameter("user_email");
			String userpwd = request.getParameter("userpwd");
			String userpwd1 = request.getParameter("userpwd1");
			String user_job = request.getParameter("user_job");
			String user_city = request.getParameter("user_city");
			String user_sex = request.getParameter("user_sex");
			String user_sign = request.getParameter("user_sign");
			String user_image = request.getParameter("user_image");

			UsersBean usersBean = new UsersBean();
			usersBean.setUser_city(user_city);
			usersBean.setUser_email(email);
			usersBean.setUser_image(user_image);
			usersBean.setUser_job(user_job);
			usersBean.setUser_sex(user_sex);
			usersBean.setUser_sign(user_sign);
			usersBean.setUsername(nick);
			usersBean.setUserpwd(userpwd);

			if (nick == null || nick.trim().equals("")) {
				request.setAttribute("message1", "usernamenotnull");
				request.setAttribute("usersBean", usersBean);
				request.getRequestDispatcher("Admin/Users/add.jsp").forward(
						request, response);
			} else if (user_sex == null || user_sex.trim().equals("")) {
				request.setAttribute("message2", "性别不能为空！");
				request.setAttribute("usersBean", usersBean);
				request.getRequestDispatcher("Admin/Users/add.jsp").forward(
						request, response);
			} else if (email == null || email.trim().equals("")) {
				request.setAttribute("message3", "邮箱不能为空！");
				request.setAttribute("usersBean", usersBean);
				request.getRequestDispatcher("Admin/Users/add.jsp").forward(
						request, response);

			} else if (userpwd == null || userpwd.trim().equals("")) {
				request.setAttribute("message4", "密码不能为空！");
				request.setAttribute("usersBean", usersBean);
				request.getRequestDispatcher("Admin/Users/add.jsp").forward(
						request, response);
			} else {
				UsersBiz usersBiz = new UsersBizImpl();
				int result = usersBiz.fetchUsername(nick);
				if (result == 0) {
						int result1=usersBiz.fetchEmail(email);
						if(result1==0){
							if (userpwd1.equals(userpwd)) {
								int rows = usersBiz.addUsers(usersBean);
								if (rows == 1) {

								}

								request.getRequestDispatcher("./UsersFetchALLServlet")
										.forward(request, response);
							} else {
								request.setAttribute("message5", "密码不一致！");
								request.setAttribute("usersBean", usersBean);
								request.getRequestDispatcher("Admin/Users/add.jsp")
										.forward(request, response);
							}
							
						}else{
							request.setAttribute("message6", "邮箱已注册！");
							request.setAttribute("usersBean", usersBean);
							request.getRequestDispatcher("Admin/Users/add.jsp")
									.forward(request, response);
						}
					

				} else {
					request.setAttribute("message7", "昵称已存在！");
					request.setAttribute("usersBean", usersBean);
					request.getRequestDispatcher("Admin/Users/add.jsp")
							.forward(request, response);
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
