package com.etc.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.UsersBean;
import com.etc.biz.UsersBiz;
import com.etc.biz.UsersBizImpl;

/**
 * 删除用户
 * Servlet implementation class SelectUsersServlet
 */
@WebServlet("/DelectUsersServlet")
public class DelectUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelectUsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String idString = request.getParameter("user_id");
		int user_id=Integer.parseInt(idString);
		UsersBiz usersBiz = new UsersBizImpl();
		int result=usersBiz.delectUserById(user_id);
		if(result==1){
			
			request.getRequestDispatcher("./UsersFetchALLServlet").forward(request, response);
		}else{
			request.getRequestDispatcher("./UsersFetchALLServlet").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
