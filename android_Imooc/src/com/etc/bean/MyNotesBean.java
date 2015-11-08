package com.etc.bean;

public class MyNotesBean {
	private int mynotes_id;
	private int user_id;
	private int course_id;
	private String mynote_content;
	public int getMynotes_id() {
		return mynotes_id;
	}
	public void setMynotes_id(int mynotes_id) {
		this.mynotes_id = mynotes_id;
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
	public String getMynote_content() {
		return mynote_content;
	}
	public void setMynote_content(String mynote_content) {
		this.mynote_content = mynote_content;
	}
	
}
