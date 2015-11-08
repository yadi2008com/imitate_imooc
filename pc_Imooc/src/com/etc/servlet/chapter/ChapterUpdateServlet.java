package com.etc.servlet.chapter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.ChapterBean;
import com.etc.biz.ChapterBiz;
import com.etc.biz.ChapterBizImpl;


@WebServlet("/ChapterUpdateServlet")
public class ChapterUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String idString = request.getParameter("chapter_id");
		String chap_name = request.getParameter("chap_name");
		String chap_content = request.getParameter("chap_content");
		String plan_idSting=request.getParameter("plan_id");
		String message="";
		String message_name="";
		String message_content="";
		String url="";
		try {
			int plan_id=Integer.parseInt(plan_idSting);
			int chapter_id=Integer.parseInt(idString);
			if(chap_name==null || "".equals(chap_name))
			{
				message_name="章标题不能为空！";
				url="Admin/Chapter/edit.jsp";
			}else if(chap_content==null || "".equals(chap_content)){
				message_content="章简介不能为空！";
				url="Admin/Chapter/edit.jsp";
			}else{
			ChapterBean chapterBean=new ChapterBean();
			chapterBean.setChapter_id(chapter_id);
			chapterBean.setChap_name(chap_name);
			chapterBean.setChap_content(chap_content);
			chapterBean.setPlan_id(plan_id);
			ChapterBiz chapterBiz=new ChapterBizImpl();
			int result=chapterBiz.chapterUpdate(chapterBean);
			if(result==1)
			{
				message="章id为"+chapter_id+"的信息修改成功！";
				url="./ChapterFecthAllServlet";
			}else{
				message="章id为"+chapter_id+"的信息修改失败！！";
				url="Admin/Chapter/edit.jsp";
				}
			}
		} catch (Exception e) {
			message="id不合法！";
			url="Admin/Chapter/edit.jsp";
		}
		request.setAttribute("message_name", message_name);
		request.setAttribute("message_content", message_content);
		request.setAttribute("message", message);
		request.getRequestDispatcher(url).forward(request, response);
	}

}
