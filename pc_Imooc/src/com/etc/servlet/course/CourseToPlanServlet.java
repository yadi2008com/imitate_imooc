package com.etc.servlet.course;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.etc.bean.PlanBean;
import com.etc.biz.ChapterBiz;
import com.etc.biz.ChapterBizImpl;
import com.etc.biz.CourseBiz;
import com.etc.biz.CourseBizImpl;
import com.etc.biz.PlanBiz;
import com.etc.biz.PlanBizImpl;

@WebServlet("/CourseToPlanServlet")
public class CourseToPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int course_id=Integer.parseInt(request.getParameter("id"));
		CourseBiz courseBiz = new CourseBizImpl();
		int plan_id = courseBiz.courseToPlan(course_id);
		ChapterBiz chapterBiz = new ChapterBizImpl();
		Map<String, List> map = chapterBiz.fetchChapterListString(plan_id);
		if (map != null && !map.isEmpty()) {
			PrintWriter writer = response.getWriter();
			Map<String, Map<String, List>> map1 = new HashMap<String, Map<String, List>>();
			map1.put("plandetail", map);
			String plandetail = JSONSerializer.toJSON(map1).toString();
			System.out.println(plandetail);
			writer.println(plandetail);
			writer.flush();
			writer.close();
		}
	}

}
