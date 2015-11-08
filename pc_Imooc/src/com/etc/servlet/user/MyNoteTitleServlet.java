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
import com.etc.biz.CourseBiz;
import com.etc.biz.CourseBizImpl;
import com.etc.biz.MyCourseBiz;
import com.etc.biz.MyCourseBizImpl;

/**
 * Servlet implementation class MyNoteTitleServlet
 */
@WebServlet("/MyNoteTitleServlet")
public class MyNoteTitleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyNoteTitleServlet() {
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
		String an_user_id = request.getParameter("user_id");
		int an_user_idInt = Integer.parseInt(an_user_id);
		MyCourseBiz myCourseBiz = new MyCourseBizImpl();
		List<Map<String, Object>> myNoteList = myCourseBiz
				.androidFetchMyNoteListByUser_id(an_user_idInt);
		
		PrintWriter writer = response.getWriter();
		Map<String, List<Map<String, Object>>> map = new HashMap<String, List<Map<String,Object>>>();
		map.put("myNoteList", myNoteList);
		String noteString = JSONSerializer.toJSON(map).toString();
		writer.println(noteString);
		writer.flush();
		writer.close();

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
