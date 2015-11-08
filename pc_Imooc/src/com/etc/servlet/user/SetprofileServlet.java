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
import com.etc.dao.UsersDao;
import com.etc.dao.UsersDaoImpl;

/**
 * Servlet implementation class SetprofileServlet
 */
@WebServlet("/SetprofileServlet")
public class SetprofileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetprofileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	request.setCharacterEncoding("utf-8");
		String nickname = request.getParameter("nickname");
		String job = request.getParameter("job");
		String province =request.getParameter("Province");
		String city =request.getParameter("City");
		String area =request.getParameter("Area");
		String sex =request.getParameter("sex");
		String aboutme =request.getParameter("aboutme");
		String setmsgint;
		int id=2;
				//(int)request.getSession().getAttribute("user_id");
		
		boolean flag=false;
		
		if(nickname==null||nickname.trim().equals("")){
			request.setAttribute("namemsg", "用户名不能为空");
			request.getRequestDispatcher("Home/setprofile.jsp").forward(request, response);
		}
		if(job==null||job.trim().equals("")){
			request.setAttribute("jobmsg", "职业不能为空");
			request.getRequestDispatcher("Home/setprofile.jsp").forward(request, response);
		}

		UsersBiz userBiz=new UsersBizImpl();
		UsersBean usersBean=new UsersBean();
		usersBean.setUser_id(id);
		usersBean.setUsername(nickname);
		usersBean.setUser_job(job);
		usersBean.setUser_city(province+""+city+""+area);
		usersBean.setUser_sex(sex);
		usersBean.setUser_sign(aboutme);
		flag=userBiz.setPersonalFileById(usersBean);
		if(flag){
			setmsgint="保存成功";		
		}else{
			setmsgint="保存失败";
		}
		request.setAttribute("setmsgint", setmsgint);
		request.getRequestDispatcher("Home/setprofile.jsp").forward(request, response);
		
		
		
		
		
		//request.getRequestDispatcher("setprofile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}
