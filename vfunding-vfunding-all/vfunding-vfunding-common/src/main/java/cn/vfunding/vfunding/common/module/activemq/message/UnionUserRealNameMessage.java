package cn.vfunding.vfunding.common.module.activemq.message;

/**
 * 网站联盟用户实名认证消息，，主要用于同步微积金用户实名认证
 * @author liuyijun
 *
 */
@SuppressWarnings("serial")
public class UnionUserRealNameMessage extends BaseMessage {

	public UnionUserRealNameMessage() {
		super();
	}

	/**
	 * 网站联盟用户的ID
	 */
	private String unionUserId;
	
	/**
	 * 真实姓名
	 */
	private String realName;
	
	/**
	 * 身份证号码
	 */
	private String cardId;

	public String getUnionUserId() {
		return unionUserId;
	}

	public void setUnionUserId(String unionUserId) {
		this.unionUserId = unionUserId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
}
