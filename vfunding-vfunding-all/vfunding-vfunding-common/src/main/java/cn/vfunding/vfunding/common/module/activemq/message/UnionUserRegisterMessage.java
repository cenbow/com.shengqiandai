package cn.vfunding.vfunding.common.module.activemq.message;
/**
 * 网站联盟用户注册时和微积金系统同步用户信息的消息对象
 * @author liuyijun
 *
 */
@SuppressWarnings("serial")
public class UnionUserRegisterMessage extends BaseMessage {

	public UnionUserRegisterMessage() {
		super();
	}
	/**
	 * 网站联盟用户的ID
	 */
	private String unionUserId;
	/**
	 * 网站联盟用户的用户名
	 */
	private String unionUserName;
	/**
	 * 网站联盟用户的密码
	 */
	private String unionPassword;
	/**
	 * 网站联盟用户的手机号码
	 */
	private String unionMobile;
	
	/**
	 * 网站联盟用户的性别
	 */
    private String sex;
	/**
	 * 网站联盟用户的邮箱
	 */
	private String unionEmail;
	public String getUnionUserId() {
		return unionUserId;
	}
	public void setUnionUserId(String unionUserId) {
		this.unionUserId = unionUserId;
	}
	public String getUnionUserName() {
		return unionUserName;
	}
	public void setUnionUserName(String unionUserName) {
		this.unionUserName = unionUserName;
	}
	public String getUnionPassword() {
		return unionPassword;
	}
	public void setUnionPassword(String unionPassword) {
		this.unionPassword = unionPassword;
	}
	public String getUnionMobile() {
		return unionMobile;
	}
	public void setUnionMobile(String unionMobile) {
		this.unionMobile = unionMobile;
	}
	public String getUnionEmail() {
		return unionEmail;
	}
	public void setUnionEmail(String unionEmail) {
		this.unionEmail = unionEmail;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
	
	
}
