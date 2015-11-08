package com.etc.servlet.plan;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.bean.PlanBean;
import com.etc.biz.PlanBiz;
import com.etc.biz.PlanBizImpl;
import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;

@WebServlet("/PlanUpdateServlet")
public class PlanUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String url = "";
		String message = "";
		String message_name = "";
		String message_content = "";
		String message_image = "";
		String message_decoration = "";

		try {
			SmartUpload upload = new SmartUpload();
			upload.initialize(this.getServletConfig(), request, response);
			upload.upload();
			Files files = upload.getFiles();
			File file_image = files.getFile(0);
			Request req = upload.getRequest();
			request.setCharacterEncoding("UTF-8");
			String plan_image = file_image.getFileName();
			file_image
					.saveAs("C:/Users/Administrator/Desktop/imooc/Imooc/WebContent/PlanImages/"
							+ plan_image);
			// 信息实例化
			String plan_idString = req.getParameter("plan_id");
			String plan_name = req.getParameter("plan_name");
			String plan_content = req.getParameter("plan_content");
			String decoration_idString = req.getParameter("decoration_id");
			int plan_id=Integer.parseInt(plan_idString);
			int decoration_id = Integer.parseInt(decoration_idString);
			if ("".equals(plan_name) || plan_name == null) {
				message_name = "*课程计划名称不能为空！";
				url = "Admin/Plan/add.jsp";
			} else if ("".equals(plan_content) || plan_content == null) {
				message_content = "*课程计划简介不能为空！";
				url = "Admin/Plan/add.jsp";
			} else if ("".equals(plan_image) || plan_image == null) {
				message_image = "*课程计划缩略图不能为空！";
				url = "Admin/Plan/add.jsp";
			}  else {
				PlanBiz planBiz = new PlanBizImpl();
				PlanBean planBean = new PlanBean();
				planBean.setPlan_id(plan_id);
				planBean.setPlan_name(plan_name);
				planBean.setPlan_content(plan_content);
				planBean.setDecoration_id(decoration_id);
				planBean.setPlan_img(plan_image);
				int result = planBiz.planUpdate(planBean);
				if (result == 1) {
					message = "修改成功";
					url = "./PlanFecthAllServlet";
				} else {
					message = "修改失败";
					url ="./PlanEditServlet?id="+plan_id;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			message_image = "图片上传失败！";
		}
		request.setAttribute("message_name", message_name);
		request.setAttribute("message_content", message_content);
		request.setAttribute("message_image", message_image);
		request.setAttribute("message_decoration", message_decoration);
		request.setAttribute("message", message);
		request.getRequestDispatcher(url).forward(request, response);
	}

}
