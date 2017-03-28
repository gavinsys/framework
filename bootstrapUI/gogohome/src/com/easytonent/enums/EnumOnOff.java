package com.easytonent.enums;

public enum EnumOnOff {
	// 狀態
	OFF("0", "離線"),
	ON("1", "在線");
	
	public static String findByCode(String code){
		EnumOnOff[] vals = EnumOnOff.values();
		for (EnumOnOff val : vals) {
			if(code.equals(val.getCode())){
				return val.getName();
			}
		}
		return null;
	}
	EnumOnOff(String code, String name){
		this.code = code;
		this.name = name;
	}
	private String code;
	private String name;
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
}
