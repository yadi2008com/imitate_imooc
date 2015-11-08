package com.etc.bean;

public class PageBean {
	public static int ROWS_PRE_PAGE = 4; // 每页显示行数
	private int pageno; // 当前页数
	private int maxpage; // 最大页

	public int getPageno() {
		return pageno;
	}

	public void setPageno(int pageno) {
		this.pageno = pageno;
	}

	public int getMaxpage() {
		return maxpage;
	}

	public void setMaxpage(int maxpage) {
		this.maxpage = maxpage;
	}
}
