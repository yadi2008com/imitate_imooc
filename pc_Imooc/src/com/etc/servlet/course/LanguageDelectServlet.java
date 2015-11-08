package com.etc.servlet.course;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.biz.LanguageBiz;
import com.etc.biz.LanguageBizImpl;
import com.etc.biz.LevelBiz;
import com.etc.biz.LevelBizImpl;

/**
 * Servlet implementation class LanguageDelectServlet
 */
@WebServlet("/LanguageDelectServlet")
public class LanguageDelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LanguageDelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String idString = request.getParameter("language_id");
		int language_id = Integer.parseInt(idString);
		LanguageBiz languageBiz = new LanguageBizImpl();
		int result = languageBiz.delectLanguageById(language_id);
		if (result == 1) {

		}
		request.getRequestDispatcher("./LanguageFetchAllServlet").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
