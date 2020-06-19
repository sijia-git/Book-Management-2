package com.oracle.domain;

import java.io.Serializable;
import java.util.List;

public class PageBean<T> implements Serializable{
	
	/**
	 * T：泛型，eg：book
	 * 封装分页的信息
	 */
	private static final long serialVersionUID = 1L;
	private int pageNew;//当前页
    private int counts;//记录数
//  private int pages;//总页数，是算出来的
    private int pageSize;//一页显示多少条记录
    private List<T> beanList;//当前页的记录数据
    private String url;//路径，以保持搜索条件

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPageNew() {
		return pageNew;
	}
	public void setPageNew(int pageNew) {
		this.pageNew = pageNew;
	}
	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	
	public int getPages() {//向上取整
		int pages=this.counts/this.pageSize;
		return (this.counts%this.pageSize==0)?pages:pages+1;
		//余数为0不变，余数不为0则+1
	}

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
	 
    
}
