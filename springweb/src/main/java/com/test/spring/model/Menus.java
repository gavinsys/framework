package com.test.spring.model;


public class Menus {
	private String id;
	private String icon;
	private String name;
	private String url;
	
	public Menus(String id, String icon, String name, String url) {
		super();
		this.id = id;
		this.icon = icon;
		this.name = name;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
