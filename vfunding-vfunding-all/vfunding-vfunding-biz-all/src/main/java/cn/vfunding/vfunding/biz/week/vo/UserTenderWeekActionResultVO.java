package cn.vfunding.vfunding.biz.week.vo;

import cn.vfunding.common.framework.utils.beans.BaseVO;
import cn.vfunding.common.framework.utils.rest.annotation.RestAttribute;

/**
 * 用户投资参数VO
 * 
 * @author lilei
 * 
 */
@SuppressWarnings("serial")
public class UserTenderWeekActionResultVO extends BaseVO{

	@RestAttribute(len = 0, name = "投资状态", remark = "1:全额投资,2:部分投资,3:已满标", notnull = false)
	private Integer status;

	private Integer buyShare;

	private Integer tenderId;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getBuyShare() {
		return buyShare;
	}

	public void setBuyShare(Integer buyShare) {
		this.buyShare = buyShare;
	}

	public Integer getTenderId() {
		return tenderId;
	}

	public void setTenderId(Integer tenderId) {
		this.tenderId = tenderId;
	}

}
