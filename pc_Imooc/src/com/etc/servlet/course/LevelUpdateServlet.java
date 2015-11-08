package com.etc.servlet.course;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.LevelBean;
import com.etc.biz.LevelBiz;
import com.etc.biz.LevelBizImpl;

/**
 * 修改等级
 * Servlet implementation class LevelUpdateServlet
 */
@WebServlet("/LevelUpdateServlet")
public class LevelUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LevelUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String idString = request.getParameter("level_id");
		int level_id = Integer.parseInt(idString);

		String leve_name = request.getParameter("leve_name");
		LevelBiz levelBiz = new LevelBizImpl();
		int rows=levelBiz.updatelevel(level_id,leve_name);
		if(rows==1){
			
		}
		request.getRequestDispatcher("./LevelFetchAllServlet").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
