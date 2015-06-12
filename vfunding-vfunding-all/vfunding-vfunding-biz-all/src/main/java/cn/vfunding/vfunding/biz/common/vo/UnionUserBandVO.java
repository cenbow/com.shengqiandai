package cn.vfunding.vfunding.biz.common.vo;

import cn.vfunding.common.framework.utils.beans.BaseVO;
/**
 * 网站联盟用户绑定微积金用户VO对象
 * @author liuyijun
 *
 */
public class UnionUserBandVO extends BaseVO {

	/**
	 * 网站联盟用户ID
	 */
	private String unionUserId;
	
	/**
	 * 绑定的身份证号码
	 */
	private String bindCardId;
	public String getUnionUserId() {
		return unionUserId;
	}
	public void setUnionUserId(String unionUserId) {
		this.unionUserId = unionUserId;
	}
	public String getBindCardId() {
		return bindCardId;
	}
	public void setBindCardId(String bindCardId) {
		this.bindCardId = bindCardId;
	}
	
	
}
