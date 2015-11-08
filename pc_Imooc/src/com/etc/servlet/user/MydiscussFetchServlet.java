package com.etc.servlet.user;

/**
 * @作者 郝宝亮
 * @功能 通过用户名或者课程名查找我的评论
 */
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.Mydiscuss;
import com.etc.bean.PageBean;
import com.etc.biz.CourseBiz;
import com.etc.biz.CourseBizImpl;
import com.etc.biz.MydiscussBiz;
import com.etc.biz.MydiscussBizImpl;
import com.etc.biz.UsersBiz;
import com.etc.biz.UsersBizImpl;
import com.etc.util.PageUtil;

@WebServlet("/MydiscussFetchServlet")
public class MydiscussFetchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		MydiscussBiz mydiscussBiz = new MydiscussBizImpl();

		String username = request.getParameter("username");
		int user_id = 0;

		String cour_title = request.getParameter("cour_title");
		int course_id = 0;
		// 通过用户名和课程名查询各自的ID
		if (username != null && !username.isEmpty()) {
			user_id = mydiscussBiz.fetchUserIdByUsername(username);
		}
		if (cour_title != null && !cour_title.isEmpty()) {
			course_id = mydiscussBiz.fetchCourseIdByCourTitle(cour_title);
		}

		// 分页
		PageUtil pageUtil = new PageUtil();
		String pagenoString = request.getParameter("pageno");
		int rows = mydiscussBiz.fetchMydiscussRows(user_id, course_id);// 只有行数不一样
		PageBean pageBean = pageUtil.getPageBean(pagenoString, rows);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("rows", rows);
		int pageno = pageBean.getPageno();

		// 执行不同的查询
		List<Mydiscuss> mydiscusseList = mydiscussBiz.mydiscussFetchList(
				user_id, course_id, pageno);

		// 在现实列表的上一页下一页里使用（区分是跳转所有评论还是条件评论）
		request.setAttribute("select", "select");
		request.setAttribute("username", username);
		request.setAttribute("cour_title", cour_title);

		if (mydiscusseList != null && !mydiscusseList.isEmpty()) {
			int[] userID = new int[mydiscusseList.size()];
			int[] courseID = new int[mydiscusseList.size()];
			for (int x = 0; x < mydiscusseList.size(); x++) {
				Mydiscuss mydiscuss = mydiscusseList.get(x);
				userID[x] = mydiscuss.getUser_id();
				courseID[x] = mydiscuss.getCourse_id();
			}
			String[] usernameList = mydiscussBiz.mydiscussFetchAllUsername(
					userID, pageno);// 评论里的昵称
			String[] cour_titleList = mydiscussBiz.mydiscussFetchAllCourTitle(
					courseID, pageno);// 评论里的课程名
			request.setAttribute("mydiscusseList", mydiscusseList);
			request.setAttribute("usernameList", usernameList);
			request.setAttribute("cour_titleList", cour_titleList);
		} else {
			request.setAttribute("message", "查询失败");
		}
		request.getRequestDispatcher("Admin/Commite/index.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
