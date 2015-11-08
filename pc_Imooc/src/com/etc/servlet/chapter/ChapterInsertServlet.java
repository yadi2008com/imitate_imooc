package com.etc.servlet.chapter;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.ChapterBean;
import com.etc.biz.ChapterBiz;
import com.etc.biz.ChapterBizImpl;


@WebServlet("/ChapterInsertServlet")
public class ChapterInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8");
		String chap_name = request.getParameter("chap_name");
		String chap_content = request.getParameter("chap_content");
		String plan_idString = request.getParameter("plan_id");
		String url = "";
		String message = "";
		String message_name = "";
		String message_content = "";
		int plan_id = Integer.parseInt(plan_idString);
		if("".equals(chap_name) || chap_name == null) {
			message_name = "*章名称不能为空！";
			url = "Admin/Chapter/add.jsp";
		} else if ("".equals(chap_content) || chap_content == null) {
			message_content = "*章简介不能为空！";
			url = "Admin/Chapter/add.jsp";
		} else {
			ChapterBiz chapterBiz = new ChapterBizImpl();
			ChapterBean chapterBean = new ChapterBean();
			chapterBean.setChap_name(chap_name);
			chapterBean.setChap_content(chap_content);
			chapterBean.setPlan_id(plan_id);
			int result = chapterBiz.chapterInsert(chapterBean);
			if (result == 1) {
				message = "添加成功";
				url = "/ChapterFetchAllServlet";
			} else {
				message = "添加失败";
				url = "Admin/Chapter/add.jsp";
			}
		}
		request.setAttribute("message_name", message_name);
		request.setAttribute("message_content", message_content);
		request.setAttribute("message", message);

		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
