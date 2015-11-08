package com.etc.bean;

/**
 * @功能：Plan的javaBean，主要用与计划数据的传递和封装
 * @创建时间 2015-09-24
 * @作者 赵燕
 * @版本号 v1.0
 */
public class PlanBean {
private int plan_id;
private String plan_name;
private String plan_content;
private String plan_img;
private int decoration_id;


public String getPlan_img() {
	return plan_img;
}
public void setPlan_img(String plan_img) {
	this.plan_img = plan_img;
}
public int getDecoration_id() {
	return decoration_id;
}
public void setDecoration_id(int decoration_id) {
	this.decoration_id = decoration_id;
}
public int getPlan_id() {
	return plan_id;
}
public void setPlan_id(int plan_id) {
	this.plan_id = plan_id;
}
public String getPlan_name() {
	return plan_name;
}
public void setPlan_name(String plan_name) {
	this.plan_name = plan_name;
}
public String getPlan_content() {
	return plan_content;
}
public void setPlan_content(String plan_content) {
	this.plan_content = plan_content;
}

}
