package com.etc.servlet.course;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.LevelBean;
import com.etc.bean.PageBean;
import com.etc.biz.LevelBiz;
import com.etc.biz.LevelBizImpl;
import com.etc.util.PageUtil;

/**
 * 查询等级
 * Servlet implementation class LevelSelectByName
 */
@WebServlet("/LevelSelectByName")
public class LevelSelectByName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LevelSelectByName() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String leve_name = request.getParameter("leve_name");
		PageUtil pageUtil = new PageUtil();
		LevelBiz levelBiz=new LevelBizImpl();
		String pagenoString = request.getParameter("pageno");
		System.out.println(pagenoString);
		int rows = levelBiz.selectLevelByUsersNameRows(leve_name);//只有行数不一样
		PageBean pageBean=pageUtil.getPageBean(pagenoString, rows);
		request.setAttribute("rows", rows);
		request.setAttribute("i", 2);
		request.setAttribute("pageBean", pageBean);
		
		try {
			
			List<LevelBean> levellist=null;
			levellist=levelBiz.selectLevelByUsersName(leve_name,pageBean.getPageno());
			if (levellist != null && !levellist.isEmpty()) {
				request.setAttribute("levellist", levellist);
				
			}
			
			request.getRequestDispatcher("Admin/level/index.jsp").forward(
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
