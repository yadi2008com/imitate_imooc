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

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (email == null || email.trim().equals("")) {
			request.setAttribute("message1", "邮箱不能为空！");
			request.getRequestDispatcher("Admin/admin_login.jsp")
					.forward(request, response);
		} else if (password == null || password.trim().equals("")) {
			request.setAttribute("temp_email", email);
			request.setAttribute("message2", "密码不能为空！");
			request.getRequestDispatcher("Admin/admin_login.jsp")
					.forward(request, response);
		} else {
			UsersBean userBean = new UsersBean();
			userBean.setUser_email(email);
			userBean.setUserpwd(password);
			
			UsersBiz usersBiz = new UsersBizImpl();
			UsersBean userBean2 = new UsersBean();
			
			userBean2 = usersBiz.userLogin(userBean);
			
			if(userBean2 != null){
				request.getSession().setAttribute("user_id", userBean2.getUser_id());
				request.getRequestDispatcher("/Admin/index.jsp").forward(
						request, response);
			}else{
				request.setAttribute("message2", "登录邮箱或密码不存在，请重新登录！");
				request.getRequestDispatcher("./Admin/admin_login.jsp").forward(request,
						response);
			}
			
		}
	}

}
