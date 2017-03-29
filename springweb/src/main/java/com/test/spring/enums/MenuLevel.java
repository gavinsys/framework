package com.test.spring.enums;


public enum MenuLevel {
	L1("一级菜单", "1"),L2("二级菜单", "2"),L3("三级菜单", "3");
	private String name;
	private String type;
	MenuLevel(String name, String type){
		this.name = name;
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public static MenuLevel getName(String type){
		for(MenuLevel m : values()){
			if(m.type.equals(type)){
				return m;
			}
		}
		return null;
	}
}
