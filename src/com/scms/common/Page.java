package com.scms.common;

import java.util.List;
//分页对象返回至页面

public class Page<T> {

	//总数据。
	private int total;

	//需要几页。
	private int page;

	//每一页显示多少条。
	private int size;

	//映射的结果集。
    private List<T> rows;
    
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
    
	
    
}
