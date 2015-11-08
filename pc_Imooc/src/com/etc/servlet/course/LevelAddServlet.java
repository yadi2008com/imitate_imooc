package com.etc.servlet.course;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.LevelBean;
import com.etc.biz.LevelBiz;
import com.etc.biz.LevelBizImpl;

/**
 * 添加等级 Servlet implementation class AddLevelServlet
 */
@WebServlet("/LevelAddServlet")
public class LevelAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LevelAddServlet() {
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
		String leve_name = request.getParameter("leve_name");
		LevelBiz levelBiz = new LevelBizImpl();
		if (leve_name == null || leve_name.trim().equals("")) {
			request.setAttribute("message1", "notnull");
			request.getRequestDispatcher("Admin/level/add.jsp").forward(
					request, response);
		} else {
			int result = levelBiz.isLevelRepeat(leve_name);
			if (result == 1) {
				request.setAttribute("message2", "notnull");
				request.getRequestDispatcher("Admin/level/add.jsp").forward(
						request, response);
			} else {
				int rows = levelBiz.addlevel(leve_name);
				if (rows == 1) {

				}
				request.getRequestDispatcher("./LevelFetchAllServlet").forward(
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
		doGet(request, response);

	}

}
