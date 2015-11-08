package com.etc.servlet.course;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.LanguageBean;
import com.etc.bean.PageBean;
import com.etc.biz.LanguageBiz;
import com.etc.biz.LanguageBizImpl;
import com.etc.util.PageUtil;

/**
 * Servlet implementation class LanguageSelectByName
 */
@WebServlet("/LanguageSelectByName")
public class LanguageSelectByName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LanguageSelectByName() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		LanguageBiz languageBiz= new LanguageBizImpl();
		
		String language_name=request.getParameter("language_name");
		PageUtil pageUtil = new PageUtil();
		System.out.println(language_name);
		
		String pagenoString = request.getParameter("pageno");
		int rows = languageBiz.selectLanguageByNameRows(language_name);// 只有行数不一样
		PageBean pageBean = pageUtil.getPageBean(pagenoString, rows);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("rows", rows);
		request.setAttribute("i", 2);
		int pageno = pageBean.getPageno();
		
		List<LanguageBean> languagelist=languageBiz.selectLanguageByName(language_name,pageno);
		if(languagelist != null && !languagelist.isEmpty()){
			int[] decoration_id = new int[languagelist.size()];
			for (int x = 0; x < languagelist.size(); x++) {
				LanguageBean languageBean = languagelist.get(x);
				decoration_id[x] = languageBean.getDecoration_id();
				
			}
			String[] decorationList = languageBiz.languageFetchAlldecoration(
					decoration_id, pageno);
			
			
			request.setAttribute("languagelist", languagelist);
			request.setAttribute("decorationList", decorationList);
		}
		request.getRequestDispatcher("Admin/language/index.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
