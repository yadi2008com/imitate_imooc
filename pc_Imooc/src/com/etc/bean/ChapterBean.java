package com.etc.bean;

/**
 * @���ܣ�Chapter��javaBean����Ҫ���������ݵĴ��ݺͷ�װ
 * @����ʱ�� 2015-09-24
 * @���� ����
 * @�汾�� v1.0
 */
public class ChapterBean {
	private int chapter_id;
	private String chap_name;
	private int plan_id;
	private String chap_content;
	private String plan_name;

	public String getPlan_name() {
		return plan_name;
	}

	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}

	public String getChap_content() {
		return chap_content;
	}

	public void setChap_content(String chap_content) {
		this.chap_content = chap_content;
	}

	public int getChapter_id() {
		return chapter_id;
	}

	public void setChapter_id(int chapter_id) {
		this.chapter_id = chapter_id;
	}

	public String getChap_name() {
		return chap_name;
	}

	public void setChap_name(String chap_name) {
		this.chap_name = chap_name;
	}

	public int getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}

}
