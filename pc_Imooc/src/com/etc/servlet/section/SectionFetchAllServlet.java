package com.etc.servlet.section;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.SectionBean;
import com.etc.bean.PageBean;
import com.etc.biz.SectionBiz;
import com.etc.biz.SectionBizImpl;


@WebServlet("/SectionFetchAllServlet")
public class SectionFetchAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String message = (String) request.getAttribute("message");
		String pagenoString = request.getParameter("pageno");
		int pageno = 1;
		if (pagenoString != null) {
			try {
				pageno = Integer.parseInt(pagenoString);
			} catch (Exception e) {
			}
		}
		SectionBiz sectionBiz = new SectionBizImpl();
		int sectionRows = 0;
		//获取总记录数
		sectionRows=sectionBiz.sectionRows();
		int maxpage = sectionRows % PageBean.ROWS_PRE_PAGE == 0 ? sectionRows
				/ PageBean.ROWS_PRE_PAGE
				: (sectionRows / PageBean.ROWS_PRE_PAGE + 1);
		if (pageno < 1) {
			pageno = 1;
		}
		if (pageno > maxpage) {
			pageno = maxpage;
		}
		PageBean pageBean = new PageBean();
		pageBean.setMaxpage(maxpage);
		pageBean.setPageno(pageno);
		List<SectionBean> sectionBeans = new ArrayList<SectionBean>();
		sectionBeans = sectionBiz.sectionPageShow(pageno);
		request.setAttribute("message", message);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("sectionBeans", sectionBeans);
		request.getRequestDispatcher("Admin/Section/index.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
