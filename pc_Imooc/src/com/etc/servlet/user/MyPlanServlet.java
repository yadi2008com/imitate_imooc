package com.etc.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONSerializer;

import com.etc.bean.PlanBean;
import com.etc.bean.UsersBean;
import com.etc.biz.MyCourseBiz;
import com.etc.biz.MyCourseBizImpl;

@WebServlet("/MyPlanServlet")
public class MyPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MyPlanServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String type = request.getParameter("type");

		// //加载左边的

		MyCourseBiz myCourseBiz = new MyCourseBizImpl();

		if ("json".equals(type)) {
			
			String userid = request.getParameter("userid");
			System.out.println(userid);
			int userid2 = 0;
			if (userid != null) {
				try {
					userid2 = Integer.parseInt(userid);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			PrintWriter writer = response.getWriter();
			Map<String, List<PlanBean>> map = new HashMap<String, List<PlanBean>>();
			List<PlanBean> planBeanList = new ArrayList<PlanBean>();
			planBeanList = myCourseBiz.fetchMyPlan(userid2);
			map.put("planBeanList", planBeanList);
			String myPlanString = JSONSerializer.toJSON(map).toString();

			writer.println(myPlanString);
			writer.flush();
			writer.close();
		} else {
			int user_id1=(int) request.getSession().getAttribute("user_id");
			System.out.println(user_id1);
			List<UsersBean> usersBeanList = new ArrayList<UsersBean>();
			usersBeanList = myCourseBiz.fetchUserByuser_id(user_id1);

			if (usersBeanList != null && !usersBeanList.isEmpty()) {
				request.setAttribute("usersBeanList", usersBeanList);
				request.getRequestDispatcher(request.getContextPath()
						+ "/Home/user_course_left.jsp");
			}
			List<PlanBean> planBeanList = new ArrayList<PlanBean>();
			planBeanList = myCourseBiz.fetchMyPlan(user_id1);
			if (planBeanList != null && !planBeanList.isEmpty()) {
				request.setAttribute("planBeanList", planBeanList);
			} else {
				System.out.println("没有计划的课程");
			}

			request.getRequestDispatcher("Home/user_plan.jsp").forward(request,
					response);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
