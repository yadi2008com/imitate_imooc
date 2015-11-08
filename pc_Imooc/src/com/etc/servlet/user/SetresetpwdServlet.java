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
import com.etc.dao.UsersDao;
import com.etc.dao.UsersDaoImpl;

/**
 * Servlet implementation class SetresetpwdServlet
 */
@WebServlet("/SetresetpwdServlet")
public class SetresetpwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SetresetpwdServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int id =2; // (int) request.getSession().getAttribute("user_id");
		boolean flag1 = false;
		boolean flag2 = false;
		String oldpwd = request.getParameter("oldpwd");
		String newpass = request.getParameter("newpass");
		String confirm = request.getParameter("confirm");
		UsersBiz usersBiz = new UsersBizImpl();
		System.out.println(oldpwd);

		if (oldpwd == null || oldpwd.trim().equals("")) {
			request.setAttribute("oldmsg", "当前密码不能为空!");
			request.getRequestDispatcher("Home/setresetpwd.jsp").forward(
					request, response);
		} else {
			flag1 = usersBiz.matchPassWordById(oldpwd, id);			
			if (flag1) {
				if (newpass == null || newpass.trim().equals("")) {
					request.setAttribute("newmsg", "新密码不能为空!");
					request.getRequestDispatcher("Home/setresetpwd.jsp")
							.forward(request, response);
				}
				if (confirm == null || confirm.trim().equals("")) {
					request.setAttribute("confirmmsg", "请确认!");
					request.getRequestDispatcher("Home/setresetpwd.jsp")
							.forward(request, response);
				}
				if (confirm.equals(newpass)) {
					try {
						flag2 = usersBiz.setPassWordById(confirm, id);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (flag2) {
						request.setAttribute("endmsg", "密码修改成功!");
					} else {
						request.setAttribute("endmsg", "密码修改失败!");
					}
					request.getRequestDispatcher("Home/setresetpwd.jsp")
							.forward(request, response);
				} else {
					request.setAttribute("endmsg", "两次输入不匹配!");
					request.getRequestDispatcher("Home/setresetpwd.jsp")
							.forward(request, response);
				}

			} else {
				request.setAttribute("oldmsg", "旧密码错误!");
				request.getRequestDispatcher("Home/setresetpwd.jsp").forward(
						request, response);
			}

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
