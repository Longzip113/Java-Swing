package com.longnguyen.util;

public class PagingUtils {

	private Integer limit;
	private Integer page;
	
	public PagingUtils(Integer page, Integer limit) {
		this.limit = limit;
		this.page = page;
	}
	
	public Integer getLimit() {
		return limit;
	}
	

	public Integer getPage() {
		return page;
	}

	public Integer getOfset() {
			if(this.page != null && this.limit != null)
				return (this.page - 1) * this.limit;
			return null;
	}

	
}
