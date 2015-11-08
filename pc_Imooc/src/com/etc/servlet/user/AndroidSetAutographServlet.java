package com.etc.servlet.user;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
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
 * Servlet implementation class AndroidSetAutographServlet
 */
@WebServlet("/AndroidSetAutographServlet")
public class AndroidSetAutographServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AndroidSetAutographServlet() {
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
		String Stringid = request.getParameter("id");
		String StringautographString = request.getParameter("autographString");

		
		Boolean flag=false;
		String StringFLAG; 
		int intid = Integer.parseInt(Stringid);
		// System.out.println(intid);
		try {
			DataOutputStream output=new DataOutputStream(response.getOutputStream());
			flag = usersBiz.UpdateUserAutographById(intid,StringautographString);
			if(flag){
				StringFLAG="更新成功";
				
			}else{
				StringFLAG="更新失败";
			}
			output.writeUTF(StringFLAG);
			output.close();
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
