package com.etc.servlet.section;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.SectionBean;
import com.etc.bean.ChapterBean;
import com.etc.biz.SectionBiz;
import com.etc.biz.SectionBizImpl;
import com.etc.biz.ChapterBiz;
import com.etc.biz.ChapterBizImpl;

@WebServlet("/SectionEditServlet")
public class SectionEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String idString = request.getParameter("id");
		SectionBean sectionBean = null;
		List<ChapterBean> chapterBeans=null;
		String message = "";
		String url = "";
		try {
			int section_id = Integer.parseInt(idString);
			sectionBean = new SectionBean();
			SectionBiz sectionBiz = new SectionBizImpl();
			sectionBean = sectionBiz.sectionEdit(section_id);
			ChapterBiz chapterBiz = new ChapterBizImpl();
			chapterBeans=new ArrayList<ChapterBean>();
			chapterBeans=chapterBiz.chapterShow();
			url = "Admin/Section/edit.jsp";
		} catch (Exception e) {
			message = "id不合法！";
			url = "./SectionFecthAllServlet";
		}
		request.setAttribute("message", message);
		request.setAttribute("chapterBeans", chapterBeans);
		request.setAttribute("sectionBean", sectionBean);
		request.getRequestDispatcher(url).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
