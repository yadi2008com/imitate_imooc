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
import com.etc.bean.PlanBean;
import com.etc.biz.DecorationBiz;
import com.etc.biz.DecorationBizImpl;
import com.etc.biz.PlanBiz;
import com.etc.biz.PlanBizImpl;

@WebServlet("/PlanEditServlet")
public class PlanEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String idString = request.getParameter("id");
		PlanBean planBean = null;
		String message = "";
		String url = "";
		try {
			int plan_id = Integer.parseInt(idString);
			planBean = new PlanBean();
			PlanBiz planBiz = new PlanBizImpl();
			planBean = planBiz.planEdit(plan_id);
			url = "Admin/Plan/edit.jsp";
		} catch (Exception e) {
			message = "id不合法！";
			url = "./PlanFecthAllServlet";
		}
		List<DecorationBean> decorationBeans = new ArrayList<DecorationBean>();
		DecorationBiz decorationBiz = new DecorationBizImpl();
		decorationBeans = decorationBiz.decorationShow();
		request.setAttribute("decorationBeans", decorationBeans);
		request.setAttribute("message", message);
		request.setAttribute("planBean", planBean);
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
