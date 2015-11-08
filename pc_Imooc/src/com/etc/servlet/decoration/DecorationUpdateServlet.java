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

@WebServlet("/DecorationUpdateServlet")
public class DecorationUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String idString = request.getParameter("decoration_id");
		String deco_name = request.getParameter("deco_name");
		String message = "";
		String message_name = "";
		String url = "";
		try {
			int decoration_id = Integer.parseInt(idString);
			if (deco_name == null || "".equals(deco_name)) {
				message_name = "*节标题不能为空！";
				url = "Admin/Decoration/edit.jsp";
			} else {
				DecorationBean decorationBean = new DecorationBean();
				decorationBean.setDecoration_id(decoration_id);
				decorationBean.setDeco_name(deco_name);
				DecorationBiz decorationBiz = new DecorationBizImpl();
				int result = decorationBiz.decorationUpdate(decorationBean);
				if (result == 1) {
					message = "方向id为" + decoration_id + "的信息修改成功！";
					url = "./DecorationFecthAllServlet";
				} else {
					message = "方向id为" + decoration_id + "的信息修改失败！！";
					url = "Admin/Decoration/edit.jsp";
				}
			}
		} catch (Exception e) {
			message = "id不合法！";
			url = "Admin/Decoration/edit.jsp";
		}
		request.setAttribute("message_name", message_name);
		request.setAttribute("message", message);
		request.getRequestDispatcher(url).forward(request, response);
	}

}
