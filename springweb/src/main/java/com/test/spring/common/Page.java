package com.test.spring.common;

import java.util.List;

public class Page {
	
	private int pageSize=7;
	private int totalCount;
	private int pageNum=1;
	private int pageCount;
	private List<?> data;
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		if(totalCount<0)
			this.totalCount=0;
		else{
			this.totalCount = totalCount;
			if(this.totalCount%pageSize==0)
				setPageCount(this.totalCount/pageSize);
			else
				setPageCount(this.totalCount/pageSize+1);
		}		
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		if(pageCount<0)
			this.pageCount=0;
		else
			this.pageCount = pageCount;
	}
	public int getBeginIndex() {
		if(pageNum<1)
			return 0;
		else if(pageNum>pageCount)
			return (pageCount-1)*pageSize;
		else
			return (pageNum-1)*pageSize;
	}
	public int getEndIndex() {
		return (getBeginIndex()+pageSize);
	}
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}	
}
