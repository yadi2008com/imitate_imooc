package com.etc.servlet.plan;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.CourseBean;
import com.etc.bean.DecorationBean;
import com.etc.bean.PageBean;
import com.etc.bean.PlanBean;
import com.etc.biz.CourseBiz;
import com.etc.biz.CourseBizImpl;
import com.etc.biz.PlanBiz;
import com.etc.biz.PlanBizImpl;

@WebServlet("/AllPlanServlet")
public class AllPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AllPlanServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int pageno = 1;
		CourseBiz courseBiz = new CourseBizImpl();
		PlanBiz planBiz = new PlanBizImpl();
		if (request.getParameter("pageno") != null) {
			String pagenoString = request.getParameter("pageno");
			pageno = Integer.parseInt(pagenoString);
		}

		List<DecorationBean> decorationInfoList = courseBiz
				.fetchAllDecorationInfoList();
		
		
		String idString = request.getParameter("id");
		
		if (idString == null||idString.equals("0")) {
			int rows = planBiz.fetchAllPlanRows();
			// 最大页
			int maxpage = rows % 8== 0 ?
					rows /8:
				(rows / 8 + 1);
			if (pageno < 1) {
				pageno = 1;
			} else if (pageno > maxpage) {
				pageno = maxpage;
			}
			PageBean pageBean = new PageBean();
			pageBean.setMaxpage(maxpage);
			pageBean.setPageno(pageno);
			List<PlanBean> allPlanList = planBiz.fetchAllPlanByPageno(pageno);// 获取用户信息列表
			
			if (allPlanList != null && !allPlanList.isEmpty()) {// 用户信息列表非空时，转向userinfo_list.jsp
				request.setAttribute("pageBean", pageBean);
				request.setAttribute("decorationInfoList", decorationInfoList);
				request.setAttribute("decoPlanList", allPlanList);
				request.getRequestDispatcher("Home/decoPlanList.jsp").forward(request, response);;
				

			}} else {
				int id1 = Integer.parseInt(idString);
			
					int getDecorationPlanRows = planBiz
							.getDecorationPlanRows(id1);
					int maxpage = getDecorationPlanRows % 8 == 0 ? getDecorationPlanRows
							/ 8
							: (getDecorationPlanRows /8 + 1);
					if (pageno < 1) {
						pageno = 1;
					} else if (pageno > maxpage) {
						pageno = maxpage;
					}
					
					List<PlanBean> planBeanInfoList = planBiz
							.fetchDecPlanInfoList(pageno, id1);
					PageBean pageBean = new PageBean();
					pageBean.setMaxpage(maxpage);
					pageBean.setPageno(pageno);
					//request.setAttribute("id", id1);
					
					request.setAttribute("decorationInfoList", decorationInfoList);
					request.setAttribute("decoPlanList", planBeanInfoList);
					request.setAttribute("pageBean", pageBean);
					
					request.getRequestDispatcher("Home/decoPlanList.jsp").forward(
							request, response);
	
				}


	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response, int id) throws ServletException,
			IOException {
		doGet(request, response);
		
	
	
	}
}
