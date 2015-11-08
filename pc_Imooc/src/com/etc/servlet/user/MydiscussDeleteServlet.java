package com.etc.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.biz.MydiscussBiz;
import com.etc.biz.MydiscussBizImpl;

@WebServlet("/MydiscussDeleteServlet")
public class MydiscussDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String mydiscuss_idString = request.getParameter("mydiscuss_id");
		int mydiscuss_id = 0;
		try {
			mydiscuss_id = Integer.parseInt(mydiscuss_idString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		MydiscussBiz mydiscussBiz = new MydiscussBizImpl();
		int row = mydiscussBiz.deleteMydiscussByMydiscuss_id(mydiscuss_id);
		if (row == 1) {
			request.setAttribute("message", "删除成功");
		} else {
			request.setAttribute("message", "删除失败");
		}
		request.getRequestDispatcher("./MydiscussFetchAllServlet").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
