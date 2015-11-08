package com.etc.servlet.user;

/**
 * @作者 郝宝亮
 * @功能 修改评论
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.Mydiscuss;
import com.etc.biz.MydiscussBiz;
import com.etc.biz.MydiscussBizImpl;

@WebServlet("/MydiscussUpdateServlet")
public class MydiscussUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String mydiscuss_idString = request.getParameter("mydiscuss_id");
		String disc_content = request.getParameter("disc_content");
		String disc_praiseString = request.getParameter("disc_praise");
		int mydiscuss_id = 0, disc_praise = 0;
		try {
			mydiscuss_id = Integer.parseInt(mydiscuss_idString);
			disc_praise = Integer.parseInt(disc_praiseString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		MydiscussBiz mydiscussBiz = new MydiscussBizImpl();
		int row = mydiscussBiz.updateMydiscuss(mydiscuss_id, disc_content,
				disc_praise);
		if (row == 1) {
			request.setAttribute("message", "修改成功");
		} else {
			request.setAttribute("message", "修改失败");
		}
		request.getRequestDispatcher("./MydiscussFetchAllServlet").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
