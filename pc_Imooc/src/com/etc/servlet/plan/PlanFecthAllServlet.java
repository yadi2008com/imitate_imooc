package com.etc.servlet.plan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.PageBean;
import com.etc.bean.PlanBean;
import com.etc.biz.PlanBiz;
import com.etc.biz.PlanBizImpl;

@WebServlet("/PlanFecthAllServlet")
public class PlanFecthAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
		PlanBiz planBiz = new PlanBizImpl();
		int planRows = 0;
		//获取总记录数
		planRows=planBiz.planRows();
		int maxpage = planRows % PageBean.ROWS_PRE_PAGE == 0 ? planRows
				/ PageBean.ROWS_PRE_PAGE
				: (planRows / PageBean.ROWS_PRE_PAGE + 1);
		if (pageno < 1) {
			pageno = 1;
		}
		if (pageno > maxpage) {
			pageno = maxpage;
		}
		PageBean pageBean = new PageBean();
		pageBean.setMaxpage(maxpage);
		pageBean.setPageno(pageno);
		List<PlanBean> planBeans = new ArrayList<PlanBean>();
		planBeans = planBiz.planPageShow(pageno);
		request.setAttribute("message", message);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("planBeans", planBeans);
		request.getRequestDispatcher("Admin/Plan/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
