package com.etc.bean;

import java.sql.Date;

public class CourseBean {
	private int course_id;		//课程id
	private String cour_title;	//课程标题
	private String cour_image;	//课程图片
	private String cour_url;	//课程链接
	private String cour_duration;	//课程时长
	private int cour_hot;	//点击量
	private Date cour_date;		//发布时间
	private String cour_source;		//资源
	private String cour_content;	//简介
	private String cour_language;	//语言
	private String cour_teacher;	//老师
	private int language_id;		//语言id
	private int decoration_id;		//方向id
	private String level_name;
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public String getCour_title() {
		return cour_title;
	}
	public void setCour_title(String cour_title) {
		this.cour_title = cour_title;
	}
	public String getCour_image() {
		return cour_image;
	}
	public void setCour_image(String cour_image) {
		this.cour_image = cour_image;
	}
	public String getCour_url() {
		return cour_url;
	}
	public void setCour_url(String cour_url) {
		this.cour_url = cour_url;
	}
	public String getCour_duration() {
		return cour_duration;
	}
	public void setCour_duration(String cour_duration) {
		this.cour_duration = cour_duration;
	}
	public int getCour_hot() {
		return cour_hot;
	}
	public void setCour_hot(int cour_hot) {
		this.cour_hot = cour_hot;
	}
	public Date getCour_date() {
		return cour_date;
	}
	public void setCour_date(Date cour_date) {
		this.cour_date = cour_date;
	}
	public String getCour_source() {
		return cour_source;
	}
	public void setCour_source(String cour_source) {
		this.cour_source = cour_source;
	}
	public String getCour_content() {
		return cour_content;
	}
	public void setCour_content(String cour_content) {
		this.cour_content = cour_content;
	}
	public String getCour_language() {
		return cour_language;
	}
	public void setCour_language(String cour_language) {
		this.cour_language = cour_language;
	}
	public String getCour_teacher() {
		return cour_teacher;
	}
	public void setCour_teacher(String cour_teacher) {
		this.cour_teacher = cour_teacher;
	}
	public int getLanguage_id() {
		return language_id;
	}
	public void setLanguage_id(int language_id) {
		this.language_id = language_id;
	}
	public int getDecoration_id() {
		return decoration_id;
	}
	public void setDecoration_id(int decoration_id) {
		this.decoration_id = decoration_id;
	}
	public String getLevel_name() {
		return level_name;
	}
	public void setLevel_name(String level_name) {
		this.level_name = level_name;
	}	
	
	
}
