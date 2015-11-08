package com.etc.servlet.chapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.ChapterBean;
import com.etc.bean.PlanBean;
import com.etc.biz.ChapterBiz;
import com.etc.biz.ChapterBizImpl;
import com.etc.biz.PlanBiz;
import com.etc.biz.PlanBizImpl;

@WebServlet("/ChapterEditServlet")
public class ChapterEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String idString = request.getParameter("id");
		ChapterBean chapterBean = null;
		List<PlanBean> planBeans=null;
		String message = "";
		String url = "";
		try {
			int chapter_id = Integer.parseInt(idString);
			chapterBean = new ChapterBean();
			ChapterBiz chapterBiz = new ChapterBizImpl();
			chapterBean = chapterBiz.chapterEdit(chapter_id);
			PlanBiz planBiz = new PlanBizImpl();
			planBeans=new ArrayList<PlanBean>();
			planBeans=planBiz.planShow();
			url = "Admin/Chapter/edit.jsp";
		} catch (Exception e) {
			message = "id不合法！";
			url = "./ChapterFecthAllServlet";
		}
		request.setAttribute("message", message);
		request.setAttribute("planBeans", planBeans);
		request.setAttribute("chapterBean", chapterBean);
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
