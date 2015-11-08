package com.etc.servlet.chapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.PageBean;
import com.etc.bean.ChapterBean;
import com.etc.biz.ChapterBiz;
import com.etc.biz.ChapterBizImpl;

@WebServlet("/ChapterFetchAllServlet")
public class ChapterFetchAllServlet extends HttpServlet {
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
		ChapterBiz chapterBiz = new ChapterBizImpl();
		int chapterRows = 0;
		//获取总记录数
		chapterRows=chapterBiz.chapterRows();
		int maxpage = chapterRows % PageBean.ROWS_PRE_PAGE == 0 ? chapterRows
				/ PageBean.ROWS_PRE_PAGE
				: (chapterRows / PageBean.ROWS_PRE_PAGE + 1);
		if (pageno < 1) {
			pageno = 1;
		}
		if (pageno > maxpage) {
			pageno = maxpage;
		}
		PageBean pageBean = new PageBean();
		pageBean.setMaxpage(maxpage);
		pageBean.setPageno(pageno);
		List<ChapterBean> chapterBeans = new ArrayList<ChapterBean>();
		chapterBeans = chapterBiz.chapterPageShow(pageno);
		request.setAttribute("message", message);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("chapterBeans", chapterBeans);
		request.getRequestDispatcher("Admin/Chapter/index.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
