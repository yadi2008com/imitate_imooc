package com.etc.servlet.course;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.DecorationBean;
import com.etc.biz.LanguageBiz;
import com.etc.biz.LanguageBizImpl;
import com.etc.biz.LevelBiz;
import com.etc.biz.LevelBizImpl;

/**
 * 添加语言 Servlet implementation class LanguageAddServlet
 */
@WebServlet("/LanguageAddServlet")
public class LanguageAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LanguageAddServlet() {
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
		String language_name = request.getParameter("language_name");
		String deco_name = request.getParameter("deco_name");
		LanguageBiz languageBiz = new LanguageBizImpl();
		if (language_name == null || language_name.trim().equals("")) {
			request.setAttribute("message1", "notnull");
			request.getRequestDispatcher("Admin/language/add.jsp").forward(
					request, response);

		} else if (deco_name == null || deco_name.trim().equals("")) {
			request.setAttribute("message2", "notnull");
			request.getRequestDispatcher("Admin/language/add.jsp").forward(
					request, response);
		} else {
			int isLaguageRepeat = languageBiz.isLaguageRepeat(language_name);
			if (isLaguageRepeat == 1) {
				request.setAttribute("message3", "notnull");
				request.getRequestDispatcher("Admin/language/add.jsp").forward(
						request, response);
			} else {
				int rows1 = languageBiz.isDeco_nameRepeat(deco_name);
				if (rows1 == 1) {

				} else {

					int rows = languageBiz.addDecoration(deco_name);
					if (rows == 1) {

					}
				}
				DecorationBean decorationBean = new DecorationBean();
				decorationBean = languageBiz.selectDecorationByName(deco_name);
				if (decorationBean != null) {
					int decoration_id = decorationBean.getDecoration_id();
					int result = languageBiz.addLanguage(language_name,
							decoration_id);
					if (result == 1) {
						request.getRequestDispatcher(
								"./LanguageFetchAllServlet").forward(request,
								response);
					}

				}
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
