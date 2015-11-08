package com.etc.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONSerializer;

import com.etc.bean.UsersBean;
import com.etc.biz.UsersBiz;
import com.etc.biz.UsersBizImpl;

/**
 * Servlet implementation class AndroidSetOccupationServlet
 */
@WebServlet("/AndroidSetOccupationServlet")
public class AndroidSetOccupationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AndroidSetOccupationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		UsersBiz usersBiz = new UsersBizImpl();
		String Stringid = request.getParameter("id");
		String Stringoccupation = request.getParameter("Occupation");

		int intid = Integer.parseInt(Stringid);
		int intoccupation = Integer.parseInt(Stringoccupation);
		switch (intoccupation) {
		case 1:Stringoccupation="页面重构设计师";
               
			break;
		case 2:Stringoccupation="web前端设计师";
  
			break;
		case 3:Stringoccupation="Js工程师";

			break;
		case 4:Stringoccupation="PHP开发工程师";

			break;
		case 5:Stringoccupation="JAVA开发工程师";

			break;
		case 6:Stringoccupation="移动开发工程师";

			break;
		case 7:Stringoccupation="软件测试工程师";

			break;

		default:Stringoccupation="页面重构设计师";
			break;
		}

		 System.out.println(intid);
		 try {
	       usersBiz.UpdateUserOccupationById(intid, Stringoccupation);
		 } catch (Exception e) {
		 // TODO Auto-generated catch block
		    e.printStackTrace();
		 }	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
