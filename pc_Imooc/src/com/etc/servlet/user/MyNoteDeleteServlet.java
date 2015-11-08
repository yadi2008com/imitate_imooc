package com.etc.servlet.user;

/**
 * @功能 Android端删除笔记使用的Servlet
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

import com.etc.bean.MyNotesBean;
import com.etc.biz.MyCourseBiz;
import com.etc.biz.MyCourseBizImpl;

@WebServlet("/MyNoteDeleteServlet")
public class MyNoteDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MyNoteDeleteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("1");// -------------------------
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		MyCourseBiz myCourseBiz = new MyCourseBizImpl();

		String mynotes_idString = request.getParameter("mynotes_idInt");
		try {
			int mynotes_id = Integer.parseInt(mynotes_idString);
			int result = myCourseBiz.deleteMyNotesByMyNoteId(mynotes_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
