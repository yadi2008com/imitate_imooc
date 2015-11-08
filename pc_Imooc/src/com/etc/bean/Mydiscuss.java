package com.etc.bean;

import java.util.Date;

/**
 * @功能：我的评论类
 * @作者：郝宝亮
 */
public class Mydiscuss {
	private int mydiscuss_id;		// 评论ID
	private int user_id;			// 用户ID
	private int course_id;			// 课程ID
	private String disc_content;	// 评论内容
	private Date disc_date;			// 发表时间
	private int disc_praise;		// 点赞数量

	public int getMydiscuss_id() {
		return mydiscuss_id;
	}

	public void setMydiscuss_id(int mydiscuss_id) {
		this.mydiscuss_id = mydiscuss_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public String getDisc_content() {
		return disc_content;
	}

	public void setDisc_content(String disc_content) {
		this.disc_content = disc_content;
	}

	public Date getDisc_date() {
		return disc_date;
	}

	public void setDisc_date(Date disc_date) {
		this.disc_date = disc_date;
	}

	public int getDisc_praise() {
		return disc_praise;
	}

	public void setDisc_praise(int disc_praise) {
		this.disc_praise = disc_praise;
	}

}
