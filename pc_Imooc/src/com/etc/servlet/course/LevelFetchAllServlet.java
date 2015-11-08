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
 * 等级列表
 * Servlet implementation class LevelFetchAllServlet
 */
@WebServlet("/LevelFetchAllServlet")
public class LevelFetchAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LevelFetchAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		PageUtil pageUtil = new PageUtil();
		LevelBiz levelBiz = new LevelBizImpl();
		String pagenoString = request.getParameter("pageno");
		int rows = levelBiz.levelFetchAllListRows();
		PageBean pageBean=pageUtil.getPageBean(pagenoString, rows);
		request.setAttribute("rows", rows);
		request.setAttribute("i", 1);
		request.setAttribute("pageBean", pageBean);
		

		
		List<LevelBean> levellist=levelBiz.levelFetchAllList(pageBean.getPageno());
		if(levellist!= null && !levellist.isEmpty()){
			
			
			request.setAttribute("levellist", levellist);
		}
		
		request.getRequestDispatcher("Admin/level/index.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
