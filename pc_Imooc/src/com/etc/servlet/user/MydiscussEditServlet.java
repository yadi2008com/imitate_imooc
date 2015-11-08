package com.etc.servlet.user;

/**
 * @功能 编辑评论
 * @作者 郝宝亮
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

@WebServlet("/MydiscussEditServlet")
public class MydiscussEditServlet extends HttpServlet {
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
		Mydiscuss mydiscuss = mydiscussBiz
				.fetchMydiscussByMydiscussId(mydiscuss_id);
		if (mydiscuss != null) {
			request.setAttribute("mydiscuss", mydiscuss);
		}
		request.getRequestDispatcher("Admin/Commite/edit.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
