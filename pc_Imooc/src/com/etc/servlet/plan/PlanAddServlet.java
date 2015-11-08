package com.etc.servlet.plan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.DecorationBean;
import com.etc.biz.DecorationBiz;
import com.etc.biz.DecorationBizImpl;

@WebServlet("/PlanAddServlet")
public class PlanAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<DecorationBean> decorationBeans = new ArrayList<DecorationBean>();
		DecorationBiz decorationBiz = new DecorationBizImpl();
		decorationBeans = decorationBiz.decorationShow();
		request.setAttribute("decorationBeans", decorationBeans);
		request.getRequestDispatcher("Admin/Plan/add.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
