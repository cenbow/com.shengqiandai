package cn.vfunding.vfunding.biz.user.model;

import cn.vfunding.vfunding.biz.financialPlanner.vo.LeaderboardVO;

/**
 * 
 * @author wang.zeyan
 * @date 2015年3月2日
 * @version 1.0
 */
public class NumberInvitationVO extends LeaderboardVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8913475769790763176L;

	private Integer number;

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
}
