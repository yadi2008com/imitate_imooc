package com.etc.servlet.plan;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.biz.PlanBiz;
import com.etc.biz.PlanBizImpl;

@WebServlet("/PlanDeleteServlet")
public class PlanDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String idString = request.getParameter("id");
		String message = "";
		String url = "";
		try {
			int plan_id = Integer.parseInt(idString);
			PlanBiz planBiz = new PlanBizImpl();
			int result = planBiz.planDelete(plan_id);
			if (result == 1) {
				message = "课程计划id为" + plan_id + "的信息删除成功！";
			} else {
				message = "课程计划id为" + plan_id + "的信息删除失败！！";
			}
		} catch (Exception e) {
			message = "id不合法！";
		}
		url="./PlanFecthAllServlet";
		request.setAttribute("message", message);
		request.getRequestDispatcher(url).forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
