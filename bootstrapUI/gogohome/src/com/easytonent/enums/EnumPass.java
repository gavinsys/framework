package com.easytonent.enums;

public enum EnumPass {
	// 狀態
	NEW("0", "未審核"),
	ISPASS("1", "通過"),
	NOPASS("2", "不通過");
	
	public static String findByCode(String code){
		EnumPass[] vals = EnumPass.values();
		for (EnumPass val : vals) {
			if(code.equals(val.getCode())){
				return val.getName();
			}
		}
		return null;
	}
	EnumPass(String code, String name){
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
