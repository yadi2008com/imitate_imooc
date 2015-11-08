package com.etc.bean;

/**
 * @���ܣ�Section��javaBean����Ҫ���ڽ����ݵĴ��ݺͷ�װ
 * @����ʱ�� 2015-09-24
 * @���� ����
 * @�汾�� v1.0
 */
public class SectionBean {
	private int section_id;
	private String sect_name;
	private int chap_id;
	private String section_content;
	private String section_img;
	private String chap_name;

	public String getChap_name() {
		return chap_name;
	}

	public void setChap_name(String chap_name) {
		this.chap_name = chap_name;
	}

	public int getChap_id() {
		return chap_id;
	}

	public void setChap_id(int chap_id) {
		this.chap_id = chap_id;
	}

	public String getSection_img() {
		return section_img;
	}

	public void setSection_img(String section_img) {
		this.section_img = section_img;
	}

	public int getSection_id() {
		return section_id;
	}

	public String getSection_content() {
		return section_content;
	}

	public void setSection_content(String section_content) {
		this.section_content = section_content;
	}

	public void setSection_id(int section_id) {
		this.section_id = section_id;
	}

	public String getSect_name() {
		return sect_name;
	}

	public void setSect_name(String sect_name) {
		this.sect_name = sect_name;
	}

}