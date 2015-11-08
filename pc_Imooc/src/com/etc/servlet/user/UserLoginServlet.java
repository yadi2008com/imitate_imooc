package com.etc.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONSerializer;

import com.etc.bean.UsersBean;
import com.etc.biz.UsersBiz;
import com.etc.biz.UsersBizImpl;

@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		UsersBiz usersBiz = new UsersBizImpl();
		String type = request.getParameter("type");
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if ("json".equals(type)) {
			PrintWriter writer = response.getWriter();
			String getEmail = request.getParameter("getEmail");
			String getPassword = request.getParameter("getPassword");
			UsersBean usersBean3 = new UsersBean();
			usersBean3.setUser_email(getEmail);
			usersBean3.setUserpwd(getPassword);

			UsersBean userBean4 = new UsersBean();

			userBean4 = usersBiz.userLogin(usersBean3);
			if (userBean4 != null) {
				String loginInfo = JSONSerializer.toJSON(userBean4).toString();
				writer.println(loginInfo);
			}
			writer.flush();
			writer.close();
		} else {
			// pc
			
			if (email == null || email.trim().equals("")) {
				request.setAttribute("message1", "邮箱不能为空！");
				request.getRequestDispatcher("Home/user_login.jsp").forward(
						request, response);
			} else if (password == null || password.trim().equals("")) {
				request.setAttribute("temp_email", email);
				request.setAttribute("message2", "密码不能为空！");
				request.getRequestDispatcher("Home/user_login.jsp").forward(
						request, response);
			} else {
				UsersBean userBean = new UsersBean();
				userBean.setUser_email(email);
				userBean.setUserpwd(password);

				UsersBean userBean2 = new UsersBean();

				userBean2 = usersBiz.userLogin(userBean);
				if (userBean2 != null) {
					request.getSession().setAttribute("user_id",
							userBean2.getUser_id());
					request.getSession().setAttribute("user_image",
							userBean2.getUser_image());
					request.getRequestDispatcher("./MyCourseServlet").forward(
							request, response);
				} else {
					request.setAttribute("message2", "登录邮箱或密码不存在，请重新登录！");
					request.getRequestDispatcher("./Home/user_login.jsp")
							.forward(request, response);
				}

			}
		}

	}

}
