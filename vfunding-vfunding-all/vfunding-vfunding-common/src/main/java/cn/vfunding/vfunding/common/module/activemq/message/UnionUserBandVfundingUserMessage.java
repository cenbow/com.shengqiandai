package cn.vfunding.vfunding.common.module.activemq.message;
/**
 * 网站联盟用户绑定微积金用户消息传输对象
 * @author liuyijun
 *
 */
@SuppressWarnings("serial")
public class UnionUserBandVfundingUserMessage extends BaseMessage {

	public UnionUserBandVfundingUserMessage() {
		super();
	}
	/**
	 * 网站联盟用户ID
	 */
	private String unionUserId;
	
	/**
	 * 微积金账号的用户名
	 */
	private String vfundingUserName;
	public String getUnionUserId() {
		return unionUserId;
	}
	public void setUnionUserId(String unionUserId) {
		this.unionUserId = unionUserId;
	}
	public String getVfundingUserName() {
		return vfundingUserName;
	}
	public void setVfundingUserName(String vfundingUserName) {
		this.vfundingUserName = vfundingUserName;
	}
	
	
}
