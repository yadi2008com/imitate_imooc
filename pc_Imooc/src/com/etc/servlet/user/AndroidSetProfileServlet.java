package com.etc.servlet.user;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.etc.bean.UsersBean;
import com.etc.biz.UsersBiz;
import com.etc.biz.UsersBizImpl;

/**
 * Servlet implementation class AndroidSetProfileServlet
 */
@WebServlet("/AndroidSetProfileServlet")
public class AndroidSetProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AndroidSetProfileServlet() {
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
		UsersBean usersBean = new UsersBean();
		PrintWriter writer = response.getWriter();
		String Stringid = request.getParameter("id");
//		DataOutputStream output=new DataOutputStream(response.getOutputStream());
		int intid = Integer.parseInt(Stringid);
		// System.out.println(intid);
		try {
			
			usersBean = usersBiz.fetchUserBeanById(intid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, UsersBean> map = new HashMap<String, UsersBean>();
		map.put("usersBean", usersBean);
		String usersBeanString = JSONSerializer.toJSON(map).toString();
//		output.writeUTF(usersBeanString);
//		output.close();
		writer.print(usersBeanString);
		writer.flush();
		writer.close();
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
