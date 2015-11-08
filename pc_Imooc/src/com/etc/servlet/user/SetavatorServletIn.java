package com.etc.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.biz.UsersBiz;
import com.etc.biz.UsersBizImpl;

/**
 * Servlet implementation class SetavatorServletIn
 */
@WebServlet("/SetavatorServletIn")
public class SetavatorServletIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetavatorServletIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id=2;//(int) request.getSession().getAttribute("user_id");
		String fileField="";
		UsersBiz usersBiz=new UsersBizImpl();
		fileField=usersBiz.fetchUseravatorById(id);
		request.setAttribute("fileField",fileField);
		request.getRequestDispatcher("Home/setavator.jsp").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
