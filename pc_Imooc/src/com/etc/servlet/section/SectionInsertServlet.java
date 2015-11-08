package com.etc.servlet.section;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.SectionBean;
import com.etc.biz.SectionBiz;
import com.etc.biz.SectionBizImpl;
@WebServlet("/SectionInsertServlet")
public class SectionInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.setCharacterEncoding("UTF-8");
		String sect_name = request.getParameter("sect_name");
		String chap_idString = request.getParameter("chap_id");
		String url = "";
		String message = "";
		String message_name = "";
		String message_content = "";
		int chap_id = Integer.parseInt(chap_idString);
		if("".equals(sect_name) || sect_name == null) {
			message_name = "*节名称不能为空！";
			url = "Admin/Section/add.jsp";
		} else {
			SectionBiz sectionBiz = new SectionBizImpl();
			SectionBean sectionBean = new SectionBean();
			sectionBean.setSect_name(sect_name);
			sectionBean.setChap_id(chap_id);
			int result = sectionBiz.sectionInsert(sectionBean);
			if (result == 1) {
				message = "添加成功";
				url = "./SectionFetchAllServlet";
			} else {
				message = "添加失败";
				url = "Admin/Section/add.jsp";
			}
		}
		request.setAttribute("message_name", message_name);
		request.setAttribute("message_content", message_content);
		request.setAttribute("message", message);

		request.getRequestDispatcher(url).forward(request, response);
	}

}
