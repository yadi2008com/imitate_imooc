package com.etc.servlet.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.UsersBean;
import com.etc.biz.MyCourseBiz;
import com.etc.biz.MyCourseBizImpl;

/**
 * Servlet implementation class MyCourseLeftServlet
 */
@WebServlet("/MyCourseLeftServlet")
public class MyCourseLeftServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyCourseLeftServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MyCourseBiz myCourseBiz=new MyCourseBizImpl();
		int user_id=(int) request.getSession().getAttribute("user_id");
		
		List<UsersBean> usersBeanList=myCourseBiz.fetchUserByuser_id(user_id);
		
		request.setAttribute("usersBeanList",usersBeanList);
		request.getRequestDispatcher("Home/user_course_left.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
