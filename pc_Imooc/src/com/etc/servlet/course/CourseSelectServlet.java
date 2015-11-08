package com.etc.servlet.course;

/**
 * @作者 郝宝亮
 * @功能 全局搜索
 */
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
import com.etc.bean.DecorationBean;
import com.etc.bean.LanguageBean;
import com.etc.bean.LevelBean;
import com.etc.bean.PageBean;
import com.etc.biz.CourseBiz;
import com.etc.biz.CourseBizImpl;
import com.etc.biz.MydiscussBiz;
import com.etc.biz.MydiscussBizImpl;
import com.etc.util.PageUtil;

@WebServlet("/CourseSelectServlet")
public class CourseSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String cour_title = request.getParameter("cour_title");
		CourseBiz courseBiz = new CourseBizImpl();
		List<DecorationBean> decorationInfoList = courseBiz
				.fetchAllDecorationInfoList();// 方向列表
		List<LevelBean> levelBeanInfoList = courseBiz
				.fetchAllLevelBeanInfoList();// 难度列表
		List<LanguageBean> languageInfoList = courseBiz
				.fetchAllLanguageInfoList();// 分类列表
		request.setAttribute("decorationInfoList", decorationInfoList);
		request.setAttribute("levelBeanInfoList", levelBeanInfoList);
		request.setAttribute("languageInfoList", languageInfoList);

		MydiscussBiz mydiscussBiz = new MydiscussBizImpl();

		PageUtil pageUtil = new PageUtil();
		String pagenoString = request.getParameter("pageno");
		int rows = mydiscussBiz.fetchSelectCourseListRows(cour_title);// 只有行数不一样
		PageBean pageBean = pageUtil.getPageBean(pagenoString, rows);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("result", rows);
		request.setAttribute("select", "select");
		int pageno = pageBean.getPageno();

		String type = request.getParameter("type");
		if ("json".equals(type)) {
			String cour_title1 = request.getParameter("cour_title");
			List<CourseBean> courseBeanInfoList = mydiscussBiz
					.json_selectCourseList(cour_title);
			System.out.println("courseBeanInfoList" + courseBeanInfoList);
			PrintWriter writer = response.getWriter();
			Map<String, List<CourseBean>> map = new HashMap<String, List<CourseBean>>();
			map.put("courseBeans", courseBeanInfoList);
			String courseString = JSONSerializer.toJSON(map).toString();
			writer.println(courseString);
			writer.flush();
			writer.close();
		}

		// 查找列表
		List<CourseBean> courseBeanInfoList = mydiscussBiz.selectCourseList(
				cour_title, pageno);
		request.setAttribute("courseBeanInfoList", courseBeanInfoList);
		request.setAttribute("cour_title", cour_title);
		request.setAttribute("pageBean", pageBean);
		request.getRequestDispatcher("/Home/courseList.jsp").forward(request,
				response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
