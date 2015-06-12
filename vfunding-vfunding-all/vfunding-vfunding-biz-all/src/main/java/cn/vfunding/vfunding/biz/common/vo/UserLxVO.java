package cn.vfunding.vfunding.biz.common.vo;

import cn.vfunding.common.framework.utils.beans.BaseVO;
import cn.vfunding.common.framework.utils.beans.DateUtil;

/**
 * 用户管理列表
 * 
 * @author lx
 * 
 */
public class UserLxVO extends BaseVO{
	// UID
	private String userId;
	// 用户名
	private String userName;
	// 真实姓名
	private String realName;
	// 性别
	private String sex;
	// 邮箱
	private String email;
	// QQ
	private String qq;
	// 手机
	private String phone;
	// 所在地
	private String address;
	// 身份证
	private String cardId;
	// 注册时间
	private String addtime;
	// 状态
	private String status;
	// 用户类型
	private String name;
	// 是否VIP
	private String VIPstatus;
	// 手机认证
	private String phoneStatus;
	// 视频认证
	private Integer videoStatus;
	// 资料认证
	private Integer avatarStatus;

	public String getPhoneStatus() {
		return phoneStatus;
	}

	public void setPhoneStatus(String phoneStatus) {
		this.phoneStatus = phoneStatus;
	}

	public Integer getVideoStatus() {
		return videoStatus;
	}

	public void setVideoStatus(Integer videoStatus) {
		this.videoStatus = videoStatus;
	}

	public Integer getAvatarStatus() {
		return avatarStatus;
	}

	public void setAvatarStatus(Integer avatarStatus) {
		this.avatarStatus = avatarStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getAddtime() {
		if (addtime !=null && addtime!= "" ) {
			DateUtil util=new DateUtil();
			long date = Long.parseLong(addtime);
			String addtime=	util.getStringDateByLongDate(date);
		System.out.println(addtime);
			return addtime;
		}else{
			return addtime;
		}
		
	}
	public void setAddtime(String addtime) {
		this.addtime  = addtime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVIPstatus() {
		return VIPstatus;
	}

	public void setVIPstatus(String vIPstatus) {
		VIPstatus = vIPstatus;
	}

}
