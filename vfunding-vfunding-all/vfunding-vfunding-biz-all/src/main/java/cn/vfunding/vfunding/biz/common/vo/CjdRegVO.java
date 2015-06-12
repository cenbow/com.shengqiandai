package cn.vfunding.vfunding.biz.common.vo;

import org.apache.commons.codec.digest.DigestUtils;

import cn.vfunding.common.framework.utils.beans.BaseVO;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;

/**
 * 财经道注册VO
 */
public class CjdRegVO extends BaseVO{

	private String uaccount; // 财经道用户名

	private String phone;

	private String companyid;

	private Integer thirdproductid;

	private Integer productid;

	private Double money;

	private Integer usertarget;

	private String md5_value;

	private String username;// 微积金 用户名

	private String password;

	private Integer type; // 1--快速注册；2--注册投资

	private String vcode;

	private String email;

	private String source; // 注册来源

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUaccount() {
		return uaccount;
	}

	public void setUaccount(String uaccount) {
		this.uaccount = uaccount;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	public Integer getThirdproductid() {
		return thirdproductid;
	}

	public void setThirdproductid(Integer thirdproductid) {
		this.thirdproductid = thirdproductid;
	}

	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getUsertarget() {
		return usertarget;
	}

	public void setUsertarget(Integer usertarget) {
		this.usertarget = usertarget;
	}

	public String getMd5_value() {
		return md5_value;
	}

	public void setMd5_value(String md5_value) {
		this.md5_value = md5_value;
	}

	/**
	 * 全部参数加密接口
	 */
	public String getMd5All(String key) {
		return DigestUtils.md5Hex(this.uaccount + this.phone + this.companyid + this.thirdproductid + this.productid + this.money + this.usertarget
				+ key);
	}

	/**
	 * 快速注册参数加密接口
	 */
	public String getMd5Reg(String key) {
		return DigestUtils.md5Hex(this.uaccount + this.phone + this.companyid + key);
	}

	/**
	 * 注册送红包参数加密
	 */
	public String getMd5Cash(String key) {
		String str = null;
		if (EmptyUtil.isNotEmpty(this.email)) {
			str = DigestUtils.md5Hex(this.uaccount + this.phone + this.email + this.password + this.source + key);
		} else {
			str = DigestUtils.md5Hex(this.uaccount + this.phone + this.password + this.source + key);
		}
		return str;
	}
}
