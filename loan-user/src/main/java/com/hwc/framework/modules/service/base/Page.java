package com.hwc.framework.modules.service.base;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int total = 0;
	private int pageNumber = 0;
	private int pageIndex = 0;
	private List<T> elements = new ArrayList<T>();
	private int first = 0;
	private int pageSize = 0;
	private String sysTime = "";
	private String table = "";

	/**
	 * <p>
	 * The default constrctor ,after new Page() You should use 'setXXX()'methods
	 * to give the value of this object
	 * </p>
	 */
	public Page() {
	}

	public Page(int pageIndex, int total, int pageSize) {
		this.total = total;
		this.pageSize = pageSize;
		if (total == 0)
			return;
		pageNumber = total / pageSize;
		if (total % pageSize > 0)
			pageNumber++;
		if (pageIndex < 1)
			pageIndex = 1;
		if (pageIndex > pageNumber) {
			pageIndex = pageNumber;
		}
		this.pageIndex = pageIndex;
		this.first = (pageIndex - 1) * pageSize;
	}

	public int getTotal() {
		return total;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public List<T> getElements() {
		return elements;
	}

	public void setElements(List<T> elements) {
		this.elements = elements;
	}

	public int getFirst() {
		return first;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public String getSysTime() {
		return sysTime;
	}

	public void setSysTime(String sysTime) {
		this.sysTime = sysTime;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}
}
