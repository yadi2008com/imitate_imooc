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

/**
 * 修改用户信息 Servlet implementation class UpdateUsersServlet
 */
@WebServlet("/UpdateUsersServlet")
public class UpdateUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateUsersServlet() {
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
		String idString = request.getParameter("user_id");
		int user_id = Integer.parseInt(idString);

		String username = request.getParameter("username");
		String user_email = request.getParameter("user_email");
		String userpwd = request.getParameter("userpwd");
		String userpwd1 = request.getParameter("userpwd1");
		String user_job = request.getParameter("user_job");
		String user_city = request.getParameter("user_city");
		String user_sex = request.getParameter("user_sex");
		String user_sign = request.getParameter("user_sign");
		String user_image = request.getParameter("user_image");

		UsersBean usersBean = new UsersBean();
		usersBean.setUser_id(user_id);

		usersBean.setUser_city(user_city);
		usersBean.setUser_email(user_email);
		usersBean.setUser_image(user_image);
		usersBean.setUser_job(user_job);
		usersBean.setUser_sex(user_sex);
		usersBean.setUser_sign(user_sign);
		usersBean.setUsername(username);
		usersBean.setUserpwd(userpwd);
		if (user_sex == null || user_sex.trim().equals("")) {
			request.setAttribute("message", "sex");
			request.setAttribute("usersBean", usersBean);
			request.getRequestDispatcher("Admin/Users/edit.jsp").forward(
					request, response);
		} else if (userpwd == null || userpwd.trim().equals("")) {
			request.setAttribute("message1", "userpwd");
			request.setAttribute("usersBean", usersBean);
			request.getRequestDispatcher("Admin/Users/edit.jsp").forward(
					request, response);
		} else {
			if (userpwd1.equals(userpwd)) {
				UsersBiz usersBiz = new UsersBizImpl();
				int result = usersBiz.updateUsersById(usersBean);
				if (result == 1) {

				}
				request.getRequestDispatcher("./UsersFetchALLServlet").forward(
						request, response);

			} else {
				request.setAttribute("message2", "userpwd1");
				request.setAttribute("usersBean", usersBean);
				request.getRequestDispatcher("Admin/Users/edit.jsp").forward(
						request, response);
			}
		}
		// if (user_sex == null || user_sex.trim().equals("")) {
		// request.setAttribute("message", "性别不能为空！");
		// request.getRequestDispatcher("Admin/Users/edit.jsp").forward(
		// request, response);
		// }else if(userpwd == null || userpwd.trim().equals("")){
		// request.setAttribute("message", "密码不能为空！");
		// request.getRequestDispatcher("Admin/Users/edit.jsp").forward(
		// request, response);
		// }else{
		// if(userpwd.equals(userpwd1)){
		// UsersBiz usersBiz = new UsersBizImpl();
		// int result = usersBiz.updateUsersById(usersBean);
		// if(result==1){
		//
		// }
		// request.getRequestDispatcher("./UsersFetchALLServlet").forward(request,
		// response);
		// }else{
		// request.setAttribute("message", "密码不一致！");
		// request.getRequestDispatcher("Admin/Users/edit.jsp").forward(
		// request, response);
		// }
		//
		// }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
