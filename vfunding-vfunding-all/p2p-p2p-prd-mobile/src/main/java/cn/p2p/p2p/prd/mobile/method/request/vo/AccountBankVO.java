package cn.p2p.p2p.prd.mobile.method.request.vo;

import cn.p2p.p2p.prd.mobile.method.request.vo.GeneralRequestVO;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.StringUtil;
import cn.vfunding.vfunding.biz.system.model.Linkage;
import cn.vfunding.vfunding.common.model.BaseModel;
@SuppressWarnings("serial")
public class AccountBankVO extends GeneralRequestVO{
	private Integer id;

	private Integer userId;

	private String account;

	private String bank;

	private String branch;

	private Integer bind;

	private Short province;

	private Short city;

	private Short area;

	private Integer addtime;

	private String addip;

	private Linkage linkage;
	
	private String bankName;
	
	private String provinceName; //开户省
	private String cityName; //市
	private String ktkjfs; //是否快捷
	private String supportQuick = "yes";
	
	
	
	public String getSupportQuick() {
		return supportQuick;
	}

	public void setSupportQuick(String supportQuick) {
		this.supportQuick = supportQuick;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getKtkjfs() {
		return ktkjfs;
	}

	public void setKtkjfs(String ktkjfs) {
		this.ktkjfs = ktkjfs;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account == null ? null : account.trim();
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank == null ? null : bank.trim();
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch == null ? null : branch.trim();
	}

	public Short getProvince() {
		return province;
	}

	public void setProvince(Short province) {
		this.province = province;
	}

	public Short getCity() {
		return city;
	}

	public void setCity(Short city) {
		this.city = city;
	}

	public Short getArea() {
		return area;
	}

	public void setArea(Short area) {
		this.area = area;
	}

	public Integer getAddtime() {
		return addtime;
	}

	public void setAddtime(Integer addtime) {
		this.addtime = addtime;
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip == null ? null : addip.trim();
	}

	public Integer getBind() {
		return bind;
	}

	public void setBind(Integer bind) {
		this.bind = bind;
	}

	public Linkage getLinkage() {
		return linkage;
	}

	public void setLinkage(Linkage linkage) {
		this.linkage = linkage;
	}

	public String getBankNameLetter() {
		if (EmptyUtil.isNotEmpty(bankName)
				&& EmptyUtil.isNotEmpty(bankName)) {
			if (bankName.endsWith("银行")) {
				String re = StringUtil.converterToFirstSpell(bankName)
						.toLowerCase();
				String pre = re.substring(0, re.length() - 1);
				return pre + "h";
			} else {
				return StringUtil.converterToFirstSpell(bankName)
						.toLowerCase();
			}

		}
		return null;
	}

	public String getAccountInfo() {
		if (EmptyUtil.isNotEmpty(account)) {
			return StringUtil.getBankAccount(this.account);
		}
		return null;
	}
	
	public String getAccountLastFour() {
		if (EmptyUtil.isNotEmpty(account)) {
			String last=account.substring(account.length()-4);
			return "*****"+last;
		}
		return null;
	}
}