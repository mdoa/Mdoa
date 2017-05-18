package com.mdoa.util;

import java.util.List;

import com.github.pagehelper.Page;

public class PageModel<T> {
	/**
	 * 总行数
	 */
	private long total;
	
	/**
	 * 分页中的rows行信息
	 */
	private List<T> rows;
	
	/**
	 * 汇总数据对象
	 */
	private T sum;
	
	public PageModel(){
		
	}
	
	public PageModel(Page<T> page){
		this.total = page.getTotal();
		this.rows = page.getResult();
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public T getSum() {
		return sum;
	}

	public void setSum(T sum) {
		this.sum = sum;
	}
	
}
