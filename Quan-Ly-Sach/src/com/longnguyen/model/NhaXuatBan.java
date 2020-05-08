package com.longnguyen.model;

public class NhaXuatBan {
	private String maNhaSanXuat;
	private String tenNhaSanXuat;
	private String diaChi;
	private String soDienThoi;
	
	private Integer page;
	private Integer limit;
	private Integer totalPage;
	private Integer totalItem;
	
	
	public String getMaNhaSanXuat() {
		return maNhaSanXuat;
	}
	public void setMaNhaSanXuat(String maNhaSanXuat) {
		this.maNhaSanXuat = maNhaSanXuat;
	}
	public String getTenNhaSanXuat() {
		return tenNhaSanXuat;
	}
	public void setTenNhaSanXuat(String tenNhaSanXuat) {
		this.tenNhaSanXuat = tenNhaSanXuat;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getSoDienThoi() {
		return soDienThoi;
	}
	public void setSoDienThoi(String soDienThoi) {
		this.soDienThoi = soDienThoi;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getTotalItem() {
		return totalItem;
	}
	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}
	
	
	
	
}
