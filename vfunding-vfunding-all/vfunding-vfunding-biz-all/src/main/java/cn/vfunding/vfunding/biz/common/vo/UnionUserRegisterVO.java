package cn.vfunding.vfunding.biz.common.vo;

import cn.vfunding.common.framework.utils.beans.BaseVO;
/**
 * 网站联盟用户注册VO对象
 * @author liuyijun
 *
 */
public class UnionUserRegisterVO extends BaseVO {
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUnionEmail() {
		return unionEmail;
	}
	public void setUnionEmail(String unionEmail) {
		this.unionEmail = unionEmail;
	}
}
