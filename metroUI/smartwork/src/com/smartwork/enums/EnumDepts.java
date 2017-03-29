package com.smartwork.enums;

public enum EnumDepts {
	// 部门：老板，市场部，项目部，测试部，设计部，研发部
	BOSS("0", "老板"),
	MARKET("1", "市场部"),
	PM("2", "项目部"),
	TEST("3", "测试部"),
	UED("4", "设计部"),
	RD("5", "研发部");
	
	private String code;
	private String name;
	EnumDepts(String code, String name){
		this.code = code;
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static String findByCode(String code){
		for(EnumDepts ed:EnumDepts.values()){
			if(ed.getCode().equals(code)){
				return ed.getName();
			}
		}
		return "";
	}
}
