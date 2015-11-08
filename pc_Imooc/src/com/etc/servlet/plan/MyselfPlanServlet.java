package com.etc.servlet.plan;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.biz.MyPlanBiz;
import com.etc.biz.MyPlanBizImpl;

@WebServlet("/MyselfPlanServlet")
public class MyselfPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MyselfPlanServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int user_id=(int) request.getSession().getAttribute("user_id");
		
		String idString = request.getParameter("id");

		int plan_id1 = Integer.parseInt(idString);

		String message = request.getParameter("sort");
		MyPlanBiz myPlanBiz = new MyPlanBizImpl();
		if (message != null && message.equals("参加该计划")) {

			int result = myPlanBiz.join(plan_id1,user_id);
			if (result == 1) {// 参加计划成功

				request.setAttribute("message", "退出该计划");

				request.getRequestDispatcher(
						"./ChapterServlet?plan_id=" + plan_id1).forward(
						request, response);

			} else {// 参加计划失败
				request.setAttribute("message", "参加该计划");

				request.getRequestDispatcher(
						"./ChapterServlet?plan_id=" + plan_id1).forward(
						request, response);
			}
		} else if (message != null && message.equals("退出该计划")) {
			int result = myPlanBiz.delete(plan_id1,user_id);
			if (result == 1) {// 计划退出成功

				request.setAttribute("message", "参加该计划");

				request.getRequestDispatcher(
						"./ChapterServlet?plan_id=" + plan_id1).forward(
						request, response);

			} else {// 计划退出失败
				request.setAttribute("message", "退出该计划");

				request.getRequestDispatcher(
						"./ChapterServlet?plan_id=" + plan_id1).forward(
						request, response);
			}

		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

}
