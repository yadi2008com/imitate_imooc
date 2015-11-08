package com.etc.servlet.course;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.CourseBean;
import com.etc.bean.DecorationBean;
import com.etc.bean.LanguageBean;
import com.etc.bean.LevelBean;
import com.etc.bean.PageBean;
import com.etc.biz.CourseBiz;
import com.etc.biz.CourseBizImpl;

/**
 * Servlet implementation class CourseListServlet
 */
@WebServlet("/CourseListServlet")
public class CourseListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseListServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		CourseBiz  courseBiz= new CourseBizImpl();
		int pageno=1;
		if(request.getParameter("pageno")!=null){
		String	pagenoString=request.getParameter("pageno");
		pageno=Integer.parseInt(pagenoString);
		}
		//方向列表
		List<DecorationBean> decorationInfoList = courseBiz.fetchAllDecorationInfoList();
		//难度列表
		List<LevelBean> levelBeanInfoList = courseBiz.fetchAllLevelBeanInfoList();
		//分类列表
		List<LanguageBean> languageInfoList = courseBiz.fetchAllLanguageInfoList();
		try{
			String idString = request.getParameter("id");
			String sort = request.getParameter("sort");
			if(idString == null){
				//得到课程信息表的信息行数
				int getCourseInfoRows=courseBiz.getCourseInfoRows();
				int maxpage=getCourseInfoRows%PageBean.ROWS_PRE_PAGE==0?getCourseInfoRows/PageBean.ROWS_PRE_PAGE:(getCourseInfoRows/PageBean.ROWS_PRE_PAGE+1);
				if(pageno<1){
									pageno=1;
				}else if(pageno>maxpage){
									pageno=maxpage;
				}
				List<CourseBean> courseBeanInfoList = courseBiz.fetchAllCourseInfoList(pageno);
				PageBean pageBean=new PageBean();
				pageBean.setMaxpage(maxpage);
				pageBean.setPageno(pageno);
				request.setAttribute("result", getCourseInfoRows);
				request.setAttribute("courseBeanInfoList", courseBeanInfoList);
				request.setAttribute("pageBean", pageBean);
			}else{
				
			int id = Integer.parseInt(idString);
			if(sort != null && sort.equals("decoration")){
				//得到课程信息表的信息行数
				int getDecorationCourseRows=courseBiz.getDecorationCourseRows(id);
				int maxpage=getDecorationCourseRows%PageBean.ROWS_PRE_PAGE==0?getDecorationCourseRows/PageBean.ROWS_PRE_PAGE:(getDecorationCourseRows/PageBean.ROWS_PRE_PAGE+1);
				if(pageno<1){
									pageno=1;
				}else if(pageno>maxpage){
									pageno=maxpage;
				}
				List<CourseBean> courseBeanInfoList = courseBiz.fetchAllDecCourseInfoList(pageno,id);
				PageBean pageBean=new PageBean();
				pageBean.setMaxpage(maxpage);
				pageBean.setPageno(pageno);
				request.setAttribute("result", getDecorationCourseRows);
				request.setAttribute("courseBeanInfoList", courseBeanInfoList);
				request.setAttribute("pageBean", pageBean);
				
			}else if(sort !=null && sort.equals("language")){
				//得到课程信息表的信息行数
				int getLanguageCourseRows=courseBiz.getLanguageCourseRows(id);
				int maxpage=getLanguageCourseRows%PageBean.ROWS_PRE_PAGE==0?(getLanguageCourseRows/PageBean.ROWS_PRE_PAGE):(getLanguageCourseRows/PageBean.ROWS_PRE_PAGE+1);
				
				if(pageno<1){
									pageno=1;
				}else if(pageno>maxpage){
									pageno=maxpage;
				}
				List<CourseBean> courseBeanInfoList = courseBiz.fetchAllLanCourseInfoList(pageno,id);
				PageBean pageBean=new PageBean();
				pageBean.setMaxpage(maxpage);
				pageBean.setPageno(pageno);
				request.setAttribute("result", getLanguageCourseRows);
				request.setAttribute("courseBeanInfoList", courseBeanInfoList);
				request.setAttribute("pageBean", pageBean);
			}else if(sort !=null && sort.equals("level")){
				//得到课程信息表的信息行数
				int getLevelCourseRows=courseBiz.getLevelCourseRows(id);
				int maxpage=getLevelCourseRows%PageBean.ROWS_PRE_PAGE==0?getLevelCourseRows/PageBean.ROWS_PRE_PAGE:(getLevelCourseRows/PageBean.ROWS_PRE_PAGE+1);
				if(pageno<1){
									pageno=1;
				}else if(pageno>maxpage){
									pageno=maxpage;
				}
				List<CourseBean> courseBeanInfoList = courseBiz.fetchAllLevelCourseInfoList(pageno,id);
				PageBean pageBean=new PageBean();
				pageBean.setMaxpage(maxpage);
				pageBean.setPageno(pageno);
				request.setAttribute("result", getLevelCourseRows);
				request.setAttribute("courseBeanInfoList", courseBeanInfoList);
				request.setAttribute("pageBean", pageBean);
			}else if(sort !=null && sort.equals("time")){
				//得到课程信息表的信息行数
				int getDateCourseRows=courseBiz.getDateCourseRows();
				int maxpage=getDateCourseRows%PageBean.ROWS_PRE_PAGE==0?getDateCourseRows/PageBean.ROWS_PRE_PAGE:(getDateCourseRows/PageBean.ROWS_PRE_PAGE+1);
				if(pageno<1){
									pageno=1;
				}else if(pageno>maxpage){
									pageno=maxpage;
				}
				List<CourseBean> courseBeanInfoList = courseBiz.fetchAllDateCourseInfoList(pageno);
				PageBean pageBean=new PageBean();
				pageBean.setMaxpage(maxpage);
				pageBean.setPageno(pageno);
				request.setAttribute("result", getDateCourseRows);
				request.setAttribute("courseBeanInfoList", courseBeanInfoList);
				request.setAttribute("pageBean", pageBean);
			}else if(sort !=null && sort.equals("hot")){
				//得到课程信息表的信息行数
				int getHotCourseRows=courseBiz.getHotCourseRows();
				int maxpage=getHotCourseRows%PageBean.ROWS_PRE_PAGE==0?getHotCourseRows/PageBean.ROWS_PRE_PAGE:(getHotCourseRows/PageBean.ROWS_PRE_PAGE+1);
				if(pageno<1){
									pageno=1;
				}else if(pageno>maxpage){
									pageno=maxpage;
				}
				List<CourseBean> courseBeanInfoList = courseBiz.fetchAllHotCourseInfoList(pageno);
				PageBean pageBean=new PageBean();
				pageBean.setMaxpage(maxpage);
				pageBean.setPageno(pageno);
				request.setAttribute("result", getHotCourseRows);
				request.setAttribute("courseBeanInfoList", courseBeanInfoList);
				request.setAttribute("pageBean", pageBean);
			}
			}
			request.setAttribute("decorationInfoList", decorationInfoList);
			request.setAttribute("levelBeanInfoList",levelBeanInfoList);
			request.setAttribute("languageInfoList",languageInfoList);
			request.getRequestDispatcher("/Home/courseList.jsp").forward(request, response);
		}catch(Exception e){
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
