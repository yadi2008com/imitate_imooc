package com.etc.servlet.decoration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.DecorationBean;
import com.etc.biz.DecorationBiz;
import com.etc.biz.DecorationBizImpl;

@WebServlet("/DecorationInsertServlet")
public class DecorationInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String deco_name = request.getParameter("deco_name");
		String url = "";
		String message = "";
		String message_name = "";
		if ("".equals(deco_name) || deco_name == null) {
			message_name = "*方向名称不能为空！";
			url = "Admin/Decoration/add.jsp";
		} else {
			DecorationBiz decorationBiz = new DecorationBizImpl();
			DecorationBean decorationBean = new DecorationBean();
			decorationBean.setDeco_name(deco_name);
			int result = decorationBiz.decorationInsert(decorationBean);
			if (result == 1) {
				message = "添加成功";
				url = "./DecorationFetchAllServlet";
			} else {
				message = "添加失败";
				url = "Admin/Decoration/add.jsp";
			}
		}
		request.setAttribute("message_name", message_name);
		request.setAttribute("message", message);

		request.getRequestDispatcher(url).forward(request, response);
	}

}
