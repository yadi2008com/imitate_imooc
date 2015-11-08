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

@WebServlet("/SectionUpdateServlet")
public class SectionUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String idString = request.getParameter("section_id");
		String sect_name = request.getParameter("sect_name");
		String chap_idSting = request.getParameter("chap_id");
		String message = "";
		String message_name = "";
		String message_content = "";
		String url = "";
		try {
			int chap_id = Integer.parseInt(chap_idSting);
			int section_id = Integer.parseInt(idString);
			if (sect_name == null || "".equals(sect_name)) {
				message_name = "*节标题不能为空！";
				url = "Admin/Section/edit.jsp";
			} else {
				SectionBean sectionBean = new SectionBean();
				sectionBean.setSection_id(section_id);
				sectionBean.setSect_name(sect_name);
				sectionBean.setChap_id(chap_id);
				SectionBiz sectionBiz = new SectionBizImpl();
				int result = sectionBiz.sectionUpdate(sectionBean);
				if (result == 1) {
					message = "节id为" + section_id + "的信息修改成功！";
					url = "./SectionFecthAllServlet";
				} else {
					message = "节id为" + section_id + "的信息修改失败！！";
					url = "Admin/Section/edit.jsp";
				}
			}
		} catch (Exception e) {
			message = "id不合法！";
			url = "Admin/Section/edit.jsp";
		}
		request.setAttribute("message_name", message_name);
		request.setAttribute("message_content", message_content);
		request.setAttribute("message", message);
		request.getRequestDispatcher(url).forward(request, response);
	}

}
