package com.etc.servlet.section;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.ChapterBean;

import com.etc.biz.ChapterBiz;
import com.etc.biz.ChapterBizImpl;

@WebServlet("/SectionAddServlet")
public class SectionAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<ChapterBean> chapterBeans = new ArrayList<ChapterBean>();
		ChapterBiz chapterBiz = new ChapterBizImpl();
		chapterBeans = chapterBiz.chapterShow();
		request.setAttribute("chapterBeans", chapterBeans);
		request.getRequestDispatcher("Admin/Section/add.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
