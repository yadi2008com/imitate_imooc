package com.etc.servlet.opinion;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.etc.bean.MyOpinionBean;
import com.etc.bean.PageBean;
import com.etc.biz.MyOpinionBiz;
import com.etc.biz.MyOpinionBizImpl;

import net.sf.json.JSONSerializer;

/**
 * Servlet implementation class OtherOpinionGetServlet
 */
@WebServlet("/OtherOpinionGetServlet")
public class OtherOpinionGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OtherOpinionGetServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter printWriter=response.getWriter();
		String pagenoString=request.getParameter("pageno");
		int pageno=Integer.parseInt(pagenoString);
		MyOpinionBiz myOpinionBiz=new MyOpinionBizImpl();
		int otherOpinionNumber=myOpinionBiz.getOtherOpinionNumber();
		int maxPageno=otherOpinionNumber%PageBean.ROWS_PRE_PAGE==0?otherOpinionNumber/PageBean.ROWS_PRE_PAGE:(otherOpinionNumber/PageBean.ROWS_PRE_PAGE+1);
		if(pageno>maxPageno){
			printWriter.print("false");
			printWriter.flush();
			printWriter.close();
		}else{
			List<MyOpinionBean> OtherOpinionBeans=myOpinionBiz.getOtherOpinionInfoByPageno(pageno);
			String otherOpinionJsonString=JSON.toJSONString(OtherOpinionBeans); 
			
			printWriter.print(otherOpinionJsonString);
			printWriter.flush();
			printWriter.close();
		}
		
	}

}
