package com.test.spring.model;

public class Trees {
	private String id;
	private String icon;
	private String text;
	private String url;
	
	public Trees(String id, String icon, String text, String url) {
		super();
		this.id = id;
		this.icon = icon;
		this.text = text;
		this.url = url;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
