package com.etc.servlet.decoration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.biz.DecorationBiz;
import com.etc.biz.DecorationBizImpl;

@WebServlet("/DecorationDeleteServlet")
public class DecorationDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String idString = request.getParameter("id");
		String message = "";
		String url = "";
		try {
			int decoration_id = Integer.parseInt(idString);
			DecorationBiz decorationBiz = new DecorationBizImpl();
			int result = decorationBiz.decorationDelete(decoration_id);
			if (result == 1) {
				message = "课程计划id为" + decoration_id + "的信息删除成功！";
			} else {
				message = "课程计划id为" + decoration_id + "的信息删除失败！！";
			}
		} catch (Exception e) {
			message = "id不合法！";
		}
		url="/DecorationFetchAllServlet";
		request.setAttribute("message", message);
		request.getRequestDispatcher(url).forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
