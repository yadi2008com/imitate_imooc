package com.etc.servlet.decoration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.DecorationBean;
import com.etc.bean.DecorationBean;
import com.etc.biz.DecorationBiz;
import com.etc.biz.DecorationBizImpl;
import com.etc.biz.DecorationBiz;
import com.etc.biz.DecorationBizImpl;


@WebServlet("/DecorationEditServlet")
public class DecorationEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String idString = request.getParameter("id");
		DecorationBean decorationBean = null;
		String message = "";
		String url = "";
		try {
			int decoration_id = Integer.parseInt(idString);
			decorationBean = new DecorationBean();
			DecorationBiz decorationBiz = new DecorationBizImpl();
			decorationBean = decorationBiz.decorationEdit(decoration_id);
			url = "Admin/Decoration/edit.jsp";
		} catch (Exception e) {
			message = "id不合法！";
			url = "./DecorationFetchAllServlet";
		}
		List<DecorationBean> decorationBeans = new ArrayList<DecorationBean>();
		DecorationBiz decorationBiz = new DecorationBizImpl();
		decorationBeans = decorationBiz.decorationShow();
		request.setAttribute("decorationBeans", decorationBeans);
		request.setAttribute("message", message);
		request.setAttribute("decorationBean", decorationBean);
		request.getRequestDispatcher(url).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
