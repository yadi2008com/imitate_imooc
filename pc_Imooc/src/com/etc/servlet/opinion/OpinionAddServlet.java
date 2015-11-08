package com.etc.servlet.opinion;

import java.io.BufferedReader;
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

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.etc.bean.MyOpinionBean;
import com.etc.bean.UsersBean;
import com.etc.biz.MyOpinionBiz;
import com.etc.biz.MyOpinionBizImpl;

/**
 * Servlet implementation class OpinionAddServlet
 */
@WebServlet("/OpinionAddServlet")
public class OpinionAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OpinionAddServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BufferedReader rd = request.getReader();
		StringBuffer jsonBuffer = new StringBuffer();
		String str = "";
		while ((str = rd.readLine()) != null) {
			jsonBuffer.append(str);
		}
		String jsonString=new String(jsonBuffer.toString().getBytes("iso-8859-1"),"UTF-8");
		MyOpinionBean myOpinionBean = new MyOpinionBean();
		List<MyOpinionBean> myOpinionBeans = JSON.parseArray(
				jsonString, MyOpinionBean.class);
		myOpinionBean = myOpinionBeans.get(0);
		MyOpinionBiz myOpinionBiz = new MyOpinionBizImpl();
		int result = myOpinionBiz.addOpinion(myOpinionBean);
		PrintWriter printWriter = response.getWriter();
		String resultString = "";
		if (result == 1) {

			resultString = "true";

		} else {
			resultString = "false";
		}
		printWriter.print(resultString);

		printWriter.flush();
		printWriter.close();
		// 测试fastjson代码
		// System.out.println(myOpinionBean.getOpin_content()+"===="+myOpinionBean.getOpin_useraddress());
		// com.alibaba.fastjson.JSON.parseObject(str1.toString(),
		// UsersBean.class);
		// com.alibaba.fastjson.JSON.parseArray(str1.toString(), String.class);
		// com.alibaba.fastjson.JSON.parseObject(str1.toString(),new
		// TypeReference<Map<String,List<String>>>(){});
	}

}
