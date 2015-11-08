package com.etc.servlet.user;

/**
 * @功能 查询全部评论
 * @作者 郝宝亮
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.Mydiscuss;
import com.etc.bean.PageBean;
import com.etc.biz.MydiscussBiz;
import com.etc.biz.MydiscussBizImpl;
import com.etc.util.PageUtil;

@WebServlet("/MydiscussFetchAllServlet")
public class MydiscussFetchAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		MydiscussBiz mydiscussBiz = new MydiscussBizImpl();

		// 分页
		PageUtil pageUtil = new PageUtil();
		String pagenoString = request.getParameter("pageno");
		int rows = mydiscussBiz.fetchMydiscussListRows();// 只有行数不一样
		PageBean pageBean = pageUtil.getPageBean(pagenoString, rows);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("rows", rows);
		int pageno = pageBean.getPageno();

		// 查找列表
		List<Mydiscuss> mydiscusseList = mydiscussBiz
				.mydiscussFetchAllList(pageno);

		if (mydiscusseList != null && !mydiscusseList.isEmpty()) {

			int[] userID = new int[mydiscusseList.size()];
			int[] courseID = new int[mydiscusseList.size()];
			for (int x = 0; x < mydiscusseList.size(); x++) {
				Mydiscuss mydiscuss = mydiscusseList.get(x);
				userID[x] = mydiscuss.getUser_id();
				courseID[x] = mydiscuss.getCourse_id();
			}
			String[] usernameList = mydiscussBiz.mydiscussFetchAllUsername(
					userID, pageno);// 查询出所有评论里显示的昵称
			String[] cour_titleList = mydiscussBiz.mydiscussFetchAllCourTitle(
					courseID, pageno);// 查询出所有评论里显示的课程名

			request.setAttribute("mydiscusseList", mydiscusseList);
			request.setAttribute("usernameList", usernameList);
			request.setAttribute("cour_titleList", cour_titleList);
		}
		request.getRequestDispatcher("Admin/Commite/index.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
