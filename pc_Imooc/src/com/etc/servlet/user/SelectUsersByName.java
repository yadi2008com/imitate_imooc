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
 * 按名称查询
 * Servlet implementation class SelectUsersByName
 */
@WebServlet("/SelectUsersByName")
public class SelectUsersByName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectUsersByName() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		PageUtil pageUtil = new PageUtil();
		UsersBiz usersBiz=new UsersBizImpl();
		String pagenoString = request.getParameter("pageno");
		int rows = usersBiz.selectUserByUsersNameRows(username);//只有行数不一样
		PageBean pageBean=pageUtil.getPageBean(pagenoString, rows);
		request.setAttribute("rows", rows);
		request.setAttribute("i", 2);
		request.setAttribute("pageBean", pageBean);
		
		try {
			
			List<UsersBean> userslist=null;
			userslist=usersBiz.selectUserByUsersName(username,pageBean.getPageno());
			if (userslist != null && !userslist.isEmpty()) {
				request.setAttribute("userslist", userslist);
				
			}
			
			request.getRequestDispatcher("Admin/Users/index.jsp").forward(
					request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
