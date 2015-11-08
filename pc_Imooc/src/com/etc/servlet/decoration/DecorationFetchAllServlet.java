package com.etc.servlet.decoration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.PageBean;
import com.etc.bean.DecorationBean;
import com.etc.biz.DecorationBiz;
import com.etc.biz.DecorationBizImpl;


@WebServlet("/DecorationFetchAllServlet")
public class DecorationFetchAllServlet extends HttpServlet {
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
		DecorationBiz decorationBiz = new DecorationBizImpl();
		int decorationRows = 0;
		//获取总记录数
		decorationRows=decorationBiz.decorationRows();
		int maxpage = decorationRows % PageBean.ROWS_PRE_PAGE == 0 ? decorationRows
				/ PageBean.ROWS_PRE_PAGE
				: (decorationRows / PageBean.ROWS_PRE_PAGE + 1);
		if (pageno < 1) {
			pageno = 1;
		}
		if (pageno > maxpage) {
			pageno = maxpage;
		}
		PageBean pageBean = new PageBean();
		pageBean.setMaxpage(maxpage);
		pageBean.setPageno(pageno);
		List<DecorationBean> decorationBeans = new ArrayList<DecorationBean>();
		decorationBeans = decorationBiz.decorationPageShow(pageno);
		request.setAttribute("message", message);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("decorationBeans", decorationBeans);
		request.getRequestDispatcher("Admin/Decoration/index.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
