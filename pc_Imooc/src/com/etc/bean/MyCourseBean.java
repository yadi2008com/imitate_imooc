package com.etc.bean;

/**
 * @功能：我的课程类
 * @作者：许建皓
 */
public class MyCourseBean {
	private int  mycourse_id;   //我的课程ID	
	private int user_id;        //用户ID	
	private int course_id;		//课程ID
	private int mycour_state;	//状态（0未学习，1已学，2，已学完）
	private int mycour_focus; 	//关注(0未关注，1 已关注)
	public int getMycourse_id() {
		return mycourse_id;
	}
	public void setMycourse_id(int mycourse_id) {
		this.mycourse_id = mycourse_id;
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
	 
 
	public int getMycour_state() {
		return mycour_state;
	}
	public void setMycour_state(int mycour_state) {
		this.mycour_state = mycour_state;
	}
	public int getMycour_focus() {
		return mycour_focus;
	}
	public void setMycour_focus(int mycour_focus) {
		this.mycour_focus = mycour_focus;
	}
}
