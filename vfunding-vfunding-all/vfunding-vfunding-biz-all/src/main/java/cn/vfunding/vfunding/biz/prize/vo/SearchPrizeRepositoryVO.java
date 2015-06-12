package cn.vfunding.vfunding.biz.prize.vo;

import cn.vfunding.common.framework.utils.beans.BaseVO;

@SuppressWarnings("serial")
public class SearchPrizeRepositoryVO extends BaseVO {
	private Integer chooseUser;
	
	private Integer prizeId;

	public Integer getChooseUser() {
		return chooseUser;
	}

	public void setChooseUser(Integer chooseUser) {
		this.chooseUser = chooseUser;
	}

	public Integer getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(Integer prizeId) {
		this.prizeId = prizeId;
	}
	
}
