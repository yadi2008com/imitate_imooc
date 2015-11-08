package com.etc.servlet.course;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.CourseBean;
import com.etc.bean.PageBean;
import com.etc.biz.CourseBiz;
import com.etc.biz.CourseBizImpl;

/**
 * Servlet implementation class CourseShowServlet
 */
@WebServlet("/CourseShowServlet")
public class CourseShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		request.setCharacterEncoding("UTF-8");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//要返回的课程列表信息
		List<CourseBean> courseBeans=new ArrayList<CourseBean>();
		int pageno=1;
		
		if(request.getParameter("pageno")!=null){
		String	pagenoString=request.getParameter("pageno");
		try {
			pageno=Integer.parseInt(pagenoString);
		} catch (Exception e) {
			request.setAttribute("message", "页码号不合法");
			request.getRequestDispatcher("./Admin/Course/index.jsp").forward(request, response);
		}
		}
		CourseBiz courseBiz=new CourseBizImpl();
		int rows=courseBiz.fetchCourseInfoRows();
		int maxpage=rows%PageBean.ROWS_PRE_PAGE==0?rows/PageBean.ROWS_PRE_PAGE:(rows/PageBean.ROWS_PRE_PAGE+1);
		if(pageno<1){
			pageno=1;
		}else if(pageno>maxpage){
			pageno=maxpage;
		}
		courseBeans=courseBiz.fetchCourseInfoByPageno(pageno);
		PageBean pageBean=new PageBean();
		pageBean.setMaxpage(maxpage);
		pageBean.setPageno(pageno);
		if(!courseBeans.isEmpty()){
			request.setAttribute("courseBeans", courseBeans);
			request.setAttribute("pageBean", pageBean);
			request.getRequestDispatcher("./Admin/Course/index.jsp").forward(request, response);
		}else {
			request.setAttribute("pageBean", pageBean);
			request.setAttribute("message", "没有查到课程信息");
			request.setAttribute("courseBeans", courseBeans);
			request.getRequestDispatcher("./Admin/Course/index.jsp").forward(request, response);
		}
	
	}

}
