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

import net.sf.json.JSONSerializer;

import com.etc.bean.CourseBean;
import com.etc.bean.PageBean;
import com.etc.bean.UsersBean;
import com.etc.biz.MyCourseBiz;
import com.etc.biz.MyCourseBizImpl;
import com.etc.util.PageUtil;

@WebServlet("/MyCourseYxServlet")
public class MyCourseYxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MyCourseYxServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String userid = request.getParameter("userid");
		int userid2 = 0;
		if (userid != null) {
			try {
				userid2 = Integer.parseInt(userid);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String type = request.getParameter("type");
		System.out.println(type + "       " + userid2);

		// 判断是否为android的请求
		if (userid2 != 0) {
			System.out.println("我是笨蛋");
			if ("json".equals(type)) {
				List<CourseBean> courseBeanList = new ArrayList<CourseBean>();
				MyCourseBiz myCourseBiz = new MyCourseBizImpl();
				courseBeanList = myCourseBiz.fetchMyCourseBystateYx(userid2, 1);

				System.out.println("---" + courseBeanList.size());
				PrintWriter writer = response.getWriter();
				Map<String, List<CourseBean>> map = new HashMap<String, List<CourseBean>>();
				map.put("courseBeanList", courseBeanList);
				String courseString = JSONSerializer.toJSON(map).toString();
				writer.println(courseString);
				writer.flush();
				writer.close();
			}

		} else {

			// 加载左边的
			MyCourseBiz myCourseBiz1 = new MyCourseBizImpl();
			int user_id1 = (int) request.getSession().getAttribute("user_id");

			List<UsersBean> usersBeanList = myCourseBiz1
					.fetchUserByuser_id(user_id1);

			if (usersBeanList != null && !usersBeanList.isEmpty()) {
				request.setAttribute("usersBeanList", usersBeanList);
				request.getRequestDispatcher("Home/user_course_left.jsp");
			}

			MyCourseBiz myCourseBiz = new MyCourseBizImpl();

			int user_id = (int) request.getSession().getAttribute("user_id");

			// 分页
			PageUtil pageUtil = new PageUtil();
			String pagenoString = request.getParameter("pageno");
			int rows = myCourseBiz.fetchMyCourseBystateYxRows(user_id);
			PageBean pageBean = pageUtil.getPageBean(pagenoString, rows);
			request.setAttribute("pageBean", pageBean);
			request.setAttribute("rows", rows);
			int pageno = pageBean.getPageno();

			List<CourseBean> courseBeanList = new ArrayList<CourseBean>();
			courseBeanList = myCourseBiz
					.fetchMyCourseBystateYx(user_id, pageno);
			if (courseBeanList != null && !courseBeanList.isEmpty()) {
				request.setAttribute("courseBeanList", courseBeanList);

			} else {
				System.out.println("没有学习的课程");
			}

			request.getRequestDispatcher("Home/user_mycourse_yx.jsp").forward(
					request, response);

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
