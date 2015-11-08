package com.etc.servlet.course;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.CourseBean;
import com.etc.biz.CourseBiz;
import com.etc.biz.CourseBizImpl;
import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * Servlet implementation class CourseAddServlet
 */
@WebServlet("/CourseAddServlet")
public class CourseAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			SmartUpload upload = new SmartUpload();
			upload.initialize(this.getServletConfig(), request, response);
			upload.upload();
			Files files = upload.getFiles();
			File file_image = files.getFile(0);
			Request req = upload.getRequest();
			String cour_image = file_image.getFileName();
			file_image.saveAs("C:/Users/Administrator/Desktop/imooc/Imooc/WebContent/CourseImages/"
					+ cour_image);
			File file_source = files.getFile(1);
			String cour_source = file_source.getFileName();
			file_source.saveAs("C:/Users/Administrator/Desktop/imooc/Imooc/WebContent/CourseSource/"
					+ cour_source);
			//获取时间信息
			String cour_dateString=req.getParameter("cour_date");
			DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
			Date cour_date = fmt.parse(cour_dateString);
			
			//信息实例化
			String cour_title=req.getParameter("cour_title");
			String cour_url=req.getParameter("cour_url");
			String cour_duration=req.getParameter("cour_duration");
			String cour_hotString=req.getParameter("cour_hot");
			String cour_content=req.getParameter("cour_content");
			String cour_language=req.getParameter("cour_language");
			String cour_teacher=req.getParameter("cour_teacher");
			String language_idString=req.getParameter("language_id");
			String decoration_idString=req.getParameter("decoration_id");
			String level_name=req.getParameter("level_name");
			
			int language_id=Integer.parseInt(language_idString);
			int decoration_id=Integer.parseInt(decoration_idString);
			int cour_hot=Integer.parseInt(cour_hotString);
			CourseBean courseBean=new CourseBean();
			courseBean.setCour_content(cour_content);
			courseBean.setCour_date(cour_date);
			courseBean.setCour_image(cour_image);
			courseBean.setCour_teacher(cour_teacher);
			courseBean.setCour_duration(cour_duration);
			courseBean.setCour_title(cour_title);
			courseBean.setCour_url(cour_url);
			courseBean.setCour_hot(cour_hot);
			courseBean.setDecoration_id(decoration_id);
			courseBean.setLanguage_id(language_id);
			courseBean.setLevel_name(level_name);
			courseBean.setCour_language(cour_language);
			courseBean.setCour_source(cour_source);
			
			CourseBiz courseBiz=new CourseBizImpl();
			int result=courseBiz.addCourseinfo(courseBean);
			if(result==1){
				request.setAttribute("courseBean", courseBean);
				request.setAttribute("message", "插入成功！");
				request.getRequestDispatcher("./Admin/Course/show.jsp").forward(request, response);
			}else{
				request.setAttribute("message", "插入失败！");
				request.getRequestDispatcher("./Admin/Course/add.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("message", "插入有误！");
			request.getRequestDispatcher("./Admin/Course/add.jsp").forward(request, response);
		}
		
	}

}
