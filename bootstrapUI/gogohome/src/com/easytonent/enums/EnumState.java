package com.easytonent.enums;


public enum EnumState {
	// 司機狀態
	CANCEL("0", "已取消"),
	QP("1", "已報價"),
	TAKED("2", "已接單"),
	BEGIN("3","開始行程"),
	END("4","結束行程"),
	CLOSED("5","完成訂單");
	
	public static String findByCode(String code){
		EnumState[] vals = EnumState.values();
		for (EnumState val : vals) {
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
	EnumState(String code, String name){
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
