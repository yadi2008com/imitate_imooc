package com.etc.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONSerializer;

import com.etc.bean.UsersBean;
import com.etc.biz.UsersBiz;
import com.etc.biz.UsersBizImpl;

@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
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
		String type = request.getParameter("type");
		UsersBiz usersBiz = new UsersBizImpl();
		if ("json".equals(type)) {
			PrintWriter writer = response.getWriter();
			String getUsername = request.getParameter("getUsername");
			String getEmail = request.getParameter("getEmail");
			String getPassword = request.getParameter("getPassword");
			int rows = usersBiz.fetchEmail(getEmail);
			int rows2 = usersBiz.fetchUsername(getUsername);
			Map<String, String> map = new HashMap<String, String>();
			if (rows2 == 1) {
				map.put("result", "1");
				String RegisterInfo = JSONSerializer.toJSON(map).toString();
				writer.println(RegisterInfo);
				writer.flush();
				writer.close();
			} else if (rows == 1) {
				map.put("result", "2");
				String RegisterInfo = JSONSerializer.toJSON(map).toString();
				writer.println(RegisterInfo);
				writer.flush();
				writer.close();
			} else {
				UsersBean usersBean = new UsersBean();
				usersBean.setUser_email(getEmail);
				usersBean.setUserpwd(getPassword);
				usersBean.setUsername(getUsername);
				usersBiz.addUsers(usersBean);

				map.put("result", "3");
				String RegisterInfo = JSONSerializer.toJSON(map).toString();
				writer.println(RegisterInfo);
				writer.flush();
				writer.close();
			}

		} else {
			// pc
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String nick = request.getParameter("nick");
			String input = request.getParameter("verify");

			if (email == null || email.trim().equals("")) {
				request.setAttribute("message1", "邮箱不能为空！");
				request.getRequestDispatcher("Home/user_register.jsp").forward(
						request, response);
			} else if (password == null || password.trim().equals("")) {
				request.setAttribute("message2", "密码不能为空！");
				request.getRequestDispatcher("Home/user_register.jsp").forward(
						request, response);
			} else if (nick == null || nick.trim().equals("")) {
				request.setAttribute("message3", "昵称不能为空！");
				request.getRequestDispatcher("Home/user_register.jsp").forward(
						request, response);
			} else if (input == null || input.trim().equals("")) {
				request.setAttribute("message4", "验证码不能为空！");
				request.getRequestDispatcher("Home/user_register.jsp").forward(
						request, response);
			} else {
				boolean flag = usersBiz.checkEmail(email);
				int rows = usersBiz.fetchEmail(email);
				boolean flag2 = usersBiz.checkPassword(password);
				boolean flag3 = usersBiz.checkNick(nick);
				int rows3 = usersBiz.fetchUsername(nick);
				String rand = (String) request.getSession()
						.getAttribute("rand");

				if (flag == false) {
					request.setAttribute("message1", "邮箱格式不正确！");
					request.getRequestDispatcher("Home/user_register.jsp")
							.forward(request, response);
				} else if (rows == 1) {
					request.setAttribute("message1", "该邮箱已注册！");
					request.getRequestDispatcher("Home/user_register.jsp")
							.forward(request, response);
				} else if (flag2 == false) {
					request.setAttribute("message2", "密码格式不正确");
					request.getRequestDispatcher("Home/user_register.jsp")
							.forward(request, response);
				} else if (flag3 == false) {
					request.setAttribute("message3", "昵称格式不正确");
					request.getRequestDispatcher("Home/user_register.jsp")
							.forward(request, response);
				} else if (rows3 == 1) {
					request.setAttribute("message3", "该昵称已注册！");
					request.getRequestDispatcher("Home/user_register.jsp")
							.forward(request, response);
				} else if (!rand.equals(input)) {
					request.setAttribute("message4", "验证码错误！");
					request.getRequestDispatcher("Home/user_register.jsp")
							.forward(request, response);
				} else {
					UsersBean usersBean = new UsersBean();
					usersBean.setUser_email(email);
					usersBean.setUserpwd(password);
					usersBean.setUsername(nick);
					usersBiz.addUsers(usersBean);
					request.getRequestDispatcher("Home/user_login.jsp")
							.forward(request, response);
				}
			}
		}

	}
}
