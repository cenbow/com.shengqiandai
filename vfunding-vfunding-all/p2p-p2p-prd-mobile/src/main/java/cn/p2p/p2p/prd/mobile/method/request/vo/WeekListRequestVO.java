package cn.p2p.p2p.prd.mobile.method.request.vo;

import cn.p2p.p2p.biz.mobile.base.model.MobileBaseRequest;

public class WeekListRequestVO extends MobileBaseRequest {
	public WeekListRequestVO() {
		super();
	}
	public WeekListRequestVO(Integer page, Integer rows) {
		super();
		this.page = page;
		this.rows = rows;
	}
	
	private Integer page = 0;
	private Integer rows = 10;
	/**
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
	}
	/**
	 * @return the rows
	 */
	public Integer getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
	
}
