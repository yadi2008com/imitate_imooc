package com.etc.servlet.chapter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.biz.ChapterBiz;
import com.etc.biz.ChapterBizImpl;

@WebServlet("/ChapterDeleteServlet")
public class ChapterDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String idString = request.getParameter("id");
		String message = "";
		String url = "";
		try {
			int chapter_id = Integer.parseInt(idString);
			ChapterBiz chapterBiz = new ChapterBizImpl();
			int result = chapterBiz.chapterDelete(chapter_id);
			if (result == 1) {
				message = "章id为" + chapter_id + "的信息删除成功！";
			} else {
				message = "章id为" + chapter_id + "的信息删除失败！！";
			}
		} catch (Exception e) {
			message = "id不合法！";
		}
		url="./ChapterFecthAllServlet";
		request.setAttribute("message", message);
		request.getRequestDispatcher(url).forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
