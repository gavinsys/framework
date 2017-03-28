package com.easytonent.enums;

public enum EnumStatus {
	// 客戶狀態
	CANCEL("0", "已取消"),
	ORDERED("1", "已下單"),
	WAITING("2", "等待司機"),
	BEGIN("3","開始行程"),
	END("4","結束行程"),
	CLOSED("5","完成訂單");
	
	public static String findByCode(String code){
		EnumStatus[] vals = EnumStatus.values();
		for (EnumStatus val : vals) {
			if(code.equals(val.getCode())){
				return val.getName();
			}
		}
		return null;
	}
	EnumStatus(String code, String name){
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
