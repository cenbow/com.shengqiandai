package cn.vfunding.vfunding.biz.cjdao.vo;

import cn.vfunding.common.framework.utils.beans.BaseVO;

@SuppressWarnings("serial")
public class CjdaoUserTenderVO extends BaseVO{

	private Integer userId;

	private Integer borrowId;

	private Integer tenderId;


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public Integer getTenderId() {
		return tenderId;
	}

	public void setTenderId(Integer tenderId) {
		this.tenderId = tenderId;
	}

}
