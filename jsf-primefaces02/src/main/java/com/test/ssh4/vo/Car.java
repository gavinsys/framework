package com.test.ssh4.vo;

public class Car {
	private String id;
	private String brand;
	private int year;
	private String color;
	private int pay;
	private boolean flag;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getPay() {
		return pay;
	}
	public void setPay(int pay) {
		this.pay = pay;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public Car(String id, String brand, int year, String color, int pay, boolean flag) {
		super();
		this.id = id;
		this.brand = brand;
		this.year = year;
		this.color = color;
		this.pay = pay;
		this.flag = flag;
	}
	
}
