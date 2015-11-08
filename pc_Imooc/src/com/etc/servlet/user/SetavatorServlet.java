package com.etc.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.biz.UsersBiz;
import com.etc.biz.UsersBizImpl;
import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * Servlet implementation class SetavatorServlet
 */
@WebServlet("/SetavatorServlet")
public class SetavatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SetavatorServlet() {
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
		int id=2;//(int) request.getSession().getAttribute("user_id");
		String msg;
		String fileField="ic_launcher-web.png";
		SmartUpload upload = new SmartUpload();
		Request req = upload.getRequest();
		try {
			upload.initialize(this.getServletConfig(), request, response);           
			upload.upload();//开始上传，放到内存中
			Files files = upload.getFiles();	//获取上传地址		
			File file_image = files.getFile(0);	//获取上传地址					
			fileField = file_image.getFileName();
			//System.out.println(fileField);			
			file_image.saveAs("/AvatorImages/"+fileField,File.SAVEAS_VIRTUAL);			
			UsersBiz userBiz=new UsersBizImpl();
			userBiz.setAvatorById(fileField, id);
		    msg="上传成功!";
		} catch (Exception e) {
			// TODO: handle exception
			msg="上传失败!";
		}
		request.setAttribute("msg",msg);
		request.setAttribute("fileField", fileField);
		request.getRequestDispatcher("Home/setavator.jsp").forward(request, response);
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
