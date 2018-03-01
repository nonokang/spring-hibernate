package com.spring.hibernate.utils;

import java.util.List;

public class Page<T> {

	private int pageIndex;
	private int pageNum;
	private int pageSize;
	private int totalRow;
	private List<T> list;
	
	public Page(int pageIndex, int pageSize){
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}
	
	public Page(int pageIndex, int pageSize, int pageNum, int totalRow, List<T> list){
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.totalRow = totalRow;
		this.list = list;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	public int getPageNum() {
		this.pageNum = (this.pageIndex * this.pageSize)-this.pageSize;
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
}
