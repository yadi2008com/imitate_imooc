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
import com.etc.bean.MyNotesBean;
import com.etc.bean.UsersBean;
import com.etc.biz.CourseBiz;
import com.etc.biz.CourseBizImpl;
import com.etc.biz.MyCourseBiz;
import com.etc.biz.MyCourseBizImpl;

/**
 * Servlet implementation class MyNoteServlet
 */
@WebServlet("/MyNoteServlet")
public class MyNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyNoteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		MyCourseBiz myCourseBiz = new MyCourseBizImpl();

		String type = request.getParameter("type");
		String user_idString = request.getParameter("user_id");
		String course_id = request.getParameter("course_id");

		if ("json".equals(type)) {
			List<MyNotesBean> myNotesBeanListByMycourse_id = new ArrayList<MyNotesBean>();
			int course_idInt = Integer.parseInt(course_id);
			int user_idInt = Integer.parseInt(user_idString);
			try {

				myNotesBeanListByMycourse_id = myCourseBiz
						.androidFetchMynoteBymycourse_idAndUser_id(
								course_idInt, user_idInt);
			} catch (Exception e) {
				e.printStackTrace();
			}

			myNotesBeanListByMycourse_id = myCourseBiz
					.fetchMynoteBymycourse_id(course_idInt);

			PrintWriter writer = response.getWriter();
			Map<String, List<MyNotesBean>> map = new HashMap<String, List<MyNotesBean>>();
			map.put("notebeanlist", myNotesBeanListByMycourse_id);
			String notesString = JSONSerializer.toJSON(map).toString();
			writer.println(notesString);
			writer.flush();
			writer.close();
		} else {

			// 加载左边的
			MyCourseBiz myCourseBiz1 = new MyCourseBizImpl();
			int user_id1 = (int) request.getSession().getAttribute("user_id");

			List<UsersBean> usersBeanList = myCourseBiz1
					.fetchUserByuser_id(user_id1);

			if (usersBeanList != null && !usersBeanList.isEmpty()) {
				request.setAttribute("usersBeanList", usersBeanList);
				request.getRequestDispatcher(request.getContextPath()
						+ "/Home/user_course_left.jsp");
			}

			int user_id = (int) request.getSession().getAttribute("user_id");

			List<MyNotesBean> myNotesBeanList = null;
			myNotesBeanList = myCourseBiz
					.fetchMynote_contentByUser_idAndCourse_id(user_id);
			request.setAttribute("myNotesBeanList", myNotesBeanList);
			request.getRequestDispatcher("Home/user_note.jsp").forward(request,
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
