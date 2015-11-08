package com.etc.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.biz.UsersBiz;
import com.etc.biz.UsersBizImpl;

/**
 * Servlet implementation class AndroidSetRegionServlet
 */
@WebServlet("/AndroidSetRegionServlet")
public class AndroidSetRegionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AndroidSetRegionServlet() {
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
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		UsersBiz usersBiz = new UsersBizImpl();
		String Stringid = request.getParameter("id");
		String StringRegion = request.getParameter("Occupation");

		int intid = Integer.parseInt(Stringid);
		int intRegion = Integer.parseInt(StringRegion);
		switch (intRegion) {
		case 1:
			StringRegion = "北京";

			break;
		case 2:
			StringRegion = "天津";
//region21
			break;
		case 31:
			StringRegion = "山西 朔州";

			break;
		case 32:
			StringRegion = "山西  忻州";

			break;
		case 33:
			StringRegion = "山西 太原";

			break;
		case 34:
			StringRegion = "山西 大同";
//
			break;
		case 41:
			StringRegion = "日本 北海道";

			break;
		case 42:
			StringRegion = "日本 本州";

			break;
		case 43:
			StringRegion = "日本 四国";

			break;
		case 44:
			StringRegion = "日本 九州";

			break;
		}

		System.out.println(intid);
		try {
			usersBiz.UpdateUserRegionById(intid, StringRegion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
