package com.etc.servlet.section;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.biz.SectionBiz;
import com.etc.biz.SectionBizImpl;


@WebServlet("/SectionDeleteServlet")
public class SectionDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String idString = request.getParameter("id");
		String message = "";
		String url = "";
		try {
			int section_id = Integer.parseInt(idString);
			SectionBiz sectionBiz = new SectionBizImpl();
			int result = sectionBiz.sectionDelete(section_id);
			if (result == 1) {
				message = "节id为" + section_id + "的信息删除成功！";
			} else {
				message = "节id为" + section_id + "的信息删除失败！！";
			}
		} catch (Exception e) {
			message = "id不合法！";
		}
		url="./SectionFecthAllServlet";
		request.setAttribute("message", message);
		request.getRequestDispatcher(url).forward(request, response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
