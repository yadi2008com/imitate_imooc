package com.etc.servlet.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.PageBean;
import com.etc.bean.UsersBean;
import com.etc.biz.UsersBiz;
import com.etc.biz.UsersBizImpl;
import com.etc.util.PageUtil;

/**
 * 查看所有用户信息
 * Servlet implementation class UpdateUsersServlet
 */
@WebServlet("/UsersFetchALLServlet")
public class UsersFetchALLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersFetchALLServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		PageUtil pageUtil = new PageUtil();
		UsersBiz usersBiz=new UsersBizImpl();
		String pagenoString = request.getParameter("pageno");
		int rows = usersBiz.usersFetchAllListRows();//只有行数不一样
		PageBean pageBean=pageUtil.getPageBean(pagenoString, rows);
		request.setAttribute("rows", rows);
		request.setAttribute("i", 1);
		request.setAttribute("pageBean", pageBean);
		

		
		List<UsersBean> userslist=usersBiz.usersFetchAllList(pageBean.getPageno());
		if(userslist!= null && !userslist.isEmpty()){
			
			
			request.setAttribute("userslist", userslist);
		}
		
		request.getRequestDispatcher("Admin/Users/index.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
