package com.smartwork.enums;

public enum EnumJobs {
	// 职位：管理员，主管，成员，
	AD(0, "管理员"),
	MANAGER(1, "主管"),
	MEMBER(2, "成员");
	
	private int code;
	private String name;
	EnumJobs(int code, String name){
		this.code = code;
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static String findByCode(int code){
		for(EnumJobs ej:EnumJobs.values()){
			if(ej.getCode()==code){
				return ej.getName();
			}
		}
		return "";
	}
}
