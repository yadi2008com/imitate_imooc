package com.etc.servlet.course;

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
import com.etc.biz.CourseBiz;
import com.etc.biz.CourseBizImpl;
import com.etc.biz.CourseBizIndex;
import com.etc.biz.CourseBizIndexImpl;

@WebServlet("/CourseShowNewAddServlet")
public class CourseShowNewAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		List<CourseBean> courseBeans = new ArrayList<CourseBean>();
		CourseBizIndex courseBizIndex = new CourseBizIndexImpl();
		String type = request.getParameter("type");
		if ("json1".equals(type)) {
			String rowS = request.getParameter("row");
			int row = Integer.parseInt(rowS);
			CourseBiz courseBiz = new CourseBizImpl();
			int rowAll = courseBiz.getCourseInfoRows();
			PrintWriter writer = response.getWriter();
			courseBeans = courseBizIndex.androidFetchCourseLimit(row, rowAll);
			Map<String, List<CourseBean>> map = new HashMap<String, List<CourseBean>>();
			map.put("courseBeans", courseBeans);
			String courseString = JSONSerializer.toJSON(map).toString();
			writer.println(courseString);
			writer.flush();
			writer.close();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
