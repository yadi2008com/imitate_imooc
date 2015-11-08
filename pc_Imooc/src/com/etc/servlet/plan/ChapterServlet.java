package com.etc.servlet.plan;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.ChapterBean;
import com.etc.bean.PlanBean;
import com.etc.bean.SectionBean;
import com.etc.biz.ChapterBiz;
import com.etc.biz.ChapterBizImpl;
import com.etc.biz.PlanBiz;
import com.etc.biz.PlanBizImpl;
 
 
@WebServlet("/ChapterServlet")
public class ChapterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message=(String) request.getAttribute("message");
		
		String  plan_id=request.getParameter("plan_id");
		  
		 int planid=0;
		 if (plan_id != null) {
				try {
					planid = Integer.parseInt(plan_id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		 
		  
		 ChapterBiz chapterBiz=new ChapterBizImpl();
		 Map<Integer,List> map= chapterBiz.fetchChapterList(planid);
		 if(map !=null && !map.isEmpty()){
			 request.setAttribute("map",map);
			 request.getRequestDispatcher("/Home/chapter.jsp") ;
		 } else{
			 request.getRequestDispatcher("/Home/chapter.jsp") ;
		 }
		 PlanBiz planBiz=new PlanBizImpl();
		 List<PlanBean> planBeanList=planBiz.fetchPlanList(planid);
		 if(planBeanList !=null && !planBeanList.isEmpty()){
			 request.setAttribute("message", message);
			 request.setAttribute("planBeanList",planBeanList);
			 request.getRequestDispatcher("/Home/chapter.jsp").forward(request, response);;
		 } 
		  
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
