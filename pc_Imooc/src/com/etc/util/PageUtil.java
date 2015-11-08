package com.etc.util;

import com.etc.bean.PageBean;

public class PageUtil {
	public PageBean getPageBean(String pagenoString, int rows) {
		int pageno = 1;
		if (pagenoString != null) {
			try {
				pageno = Integer.parseInt(pagenoString);
			} catch (Exception e) {
			}
		}
		int maxpage = (rows % PageBean.ROWS_PRE_PAGE == 0) ? (rows / PageBean.ROWS_PRE_PAGE)
				: ((rows / PageBean.ROWS_PRE_PAGE) + 1);
		if (pageno < 1) {
			pageno = 1;
		}
		if (pageno > maxpage) {
			pageno = maxpage;
		}

		PageBean pageBean = new PageBean();
		pageBean.setPageno(pageno);
		pageBean.setMaxpage(maxpage);
		return pageBean;
	}
}
