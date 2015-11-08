package com.etc.servlet.course;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.CourseBean;
import com.etc.biz.CourseBiz;
import com.etc.biz.CourseBizImpl;

/**
 * Servlet implementation class CourseShowByIdHomeServlet
 */
@WebServlet("/CourseShowByIdHomeServlet")
public class CourseShowByIdHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CourseShowByIdHomeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int course_id=9;
		
		try {
			if(request.getParameter("course_id")!=null){
			String course_idString=request.getParameter("course_id");
			course_id=Integer.parseInt(course_idString);
			}
			CourseBiz courseBiz=new CourseBizImpl();
			CourseBean courseBean=null;
			courseBean= courseBiz.fetchCourseinfoById(course_id);
			if(courseBean!=null){
				request.setAttribute("courseBean", courseBean);
				request.getRequestDispatcher("./Home/courseShow.jsp").forward(request, response);
			}else{
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	
	}

}
