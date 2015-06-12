package cn.vfunding.vfunding.biz.user.model;

import java.io.Serializable;
import java.math.BigDecimal;

import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer userId;

	private Integer typeId;

	private Integer order;

	private String purview;

	private String username;

	private String password;

	private String paypassword;

	private Integer islock;

	private String inviteUserid;

	private String inviteMoney;

	private String realStatus;

	private String cardType;

	private String cardId;

	private String cardPic1;

	private String cardPic2;

	private String nation;

	private String realname;

	private String integral;

	private Integer status;

	private Integer avatarStatus;

	private String emailStatus;

	private String phoneStatus;

	private Integer videoStatus;

	private Integer sceneStatus;

	private String email;

	private String sex;

	private String litpic;

	private String tel;

	private String phone;

	private String qq;

	private String wangwang;

	private String question;

	private String answer;

	private String birthday;

	private String province;

	private String city;

	private String area;

	private String address;

	private Integer logintime;

	private String addtime;

	private String addip;

	private String uptime;

	private String upip;

	private String lasttime;

	private String lastip;

	private Integer isPhone;

	private Integer memberlevel;

	private String serialId;

	private String serialStatus;

	private BigDecimal hongbao;

	private String connectOpenid;

	private String forumKey;

	private Boolean forumAccredit;

	private String encryption;

	private String userStatus;

	private UserType userType;

	private String addtimeStr;

	/**
	 * 用户来源
	 */
	private String fromType;

	public String getFromType() {
		return fromType;
	}

	public void setFromType(String fromType) {
		this.fromType = fromType;
	}

	/**
	 * 用户头像
	 */
	private String userIcon;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getPurview() {
		return purview;
	}

	public void setPurview(String purview) {
		this.purview = purview == null ? null : purview.trim();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getPaypassword() {
		return paypassword;
	}

	public void setPaypassword(String paypassword) {
		this.paypassword = paypassword == null ? null : paypassword.trim();
	}

	public Integer getIslock() {
		return islock;
	}

	public void setIslock(Integer islock) {
		this.islock = islock;
	}

	public String getInviteUserid() {
		return inviteUserid;
	}

	public void setInviteUserid(String inviteUserid) {
		this.inviteUserid = inviteUserid == null ? null : inviteUserid.trim();
	}

	public String getInviteMoney() {
		return inviteMoney;
	}

	public void setInviteMoney(String inviteMoney) {
		this.inviteMoney = inviteMoney == null ? null : inviteMoney.trim();
	}

	public String getRealStatus() {
		return realStatus;
	}

	public void setRealStatus(String realStatus) {
		this.realStatus = realStatus == null ? null : realStatus.trim();
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType == null ? null : cardType.trim();
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId == null ? null : cardId.trim();
	}

	public String getCardPic1() {
		return cardPic1;
	}

	public void setCardPic1(String cardPic1) {
		this.cardPic1 = cardPic1 == null ? null : cardPic1.trim();
	}

	public String getCardPic2() {
		return cardPic2;
	}

	public void setCardPic2(String cardPic2) {
		this.cardPic2 = cardPic2 == null ? null : cardPic2.trim();
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation == null ? null : nation.trim();
	}

	public String getRealname() {
		return this.realname;
	}

	private String realnameStr;

	public String getRealnameStr() {
		return EmptyUtil.isEmpty(this.realname) ? "暂无信息" : this.realname;
	}

	public void setRealnameStr(String realname) {
		this.realnameStr =realname;
	}

	public void setRealname(String realname) {
		this.realname = realname == null ? null : realname.trim();
	}

	public String getIntegral() {
		return integral;
	}

	public void setIntegral(String integral) {
		this.integral = integral == null ? null : integral.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAvatarStatus() {
		return avatarStatus;
	}

	public void setAvatarStatus(Integer avatarStatus) {
		this.avatarStatus = avatarStatus;
	}

	public String getEmailStatus() {
		return emailStatus;
	}

	public void setEmailStatus(String emailStatus) {
		this.emailStatus = emailStatus == null ? null : emailStatus.trim();
	}

	public String getPhoneStatus() {
		return phoneStatus;
	}

	public void setPhoneStatus(String phoneStatus) {
		this.phoneStatus = phoneStatus == null ? null : phoneStatus.trim();
	}

	public Integer getVideoStatus() {
		return videoStatus;
	}

	public void setVideoStatus(Integer videoStatus) {
		this.videoStatus = videoStatus;
	}

	public Integer getSceneStatus() {
		return sceneStatus;
	}

	public void setSceneStatus(Integer sceneStatus) {
		this.sceneStatus = sceneStatus;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public String getLitpic() {
		return litpic;
	}

	public void setLitpic(String litpic) {
		this.litpic = litpic == null ? null : litpic.trim();
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel == null ? null : tel.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq == null ? null : qq.trim();
	}

	public String getWangwang() {
		return wangwang;
	}

	public void setWangwang(String wangwang) {
		this.wangwang = wangwang == null ? null : wangwang.trim();
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question == null ? null : question.trim();
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer == null ? null : answer.trim();
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday == null ? null : birthday.trim();
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province == null ? null : province.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area == null ? null : area.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public Integer getLogintime() {
		return logintime;
	}

	public void setLogintime(Integer logintime) {
		this.logintime = logintime;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime == null ? null : addtime.trim();
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip == null ? null : addip.trim();
	}

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime == null ? null : uptime.trim();
	}

	public String getUpip() {
		return upip;
	}

	public void setUpip(String upip) {
		this.upip = upip == null ? null : upip.trim();
	}

	public String getLasttime() {
		return lasttime;
	}

	public void setLasttime(String lasttime) {
		this.lasttime = lasttime == null ? null : lasttime.trim();
	}

	public String getLastip() {
		return lastip;
	}

	public void setLastip(String lastip) {
		this.lastip = lastip == null ? null : lastip.trim();
	}

	public Integer getIsPhone() {
		return isPhone;
	}

	public void setIsPhone(Integer isPhone) {
		this.isPhone = isPhone;
	}

	public Integer getMemberlevel() {
		return memberlevel;
	}

	public void setMemberlevel(Integer memberlevel) {
		this.memberlevel = memberlevel;
	}

	public String getSerialId() {
		return serialId;
	}

	public void setSerialId(String serialId) {
		this.serialId = serialId == null ? null : serialId.trim();
	}

	public String getSerialStatus() {
		return serialStatus;
	}

	public void setSerialStatus(String serialStatus) {
		this.serialStatus = serialStatus == null ? null : serialStatus.trim();
	}

	public BigDecimal getHongbao() {
		return hongbao;
	}

	public void setHongbao(BigDecimal hongbao) {
		this.hongbao = hongbao;
	}

	public String getConnectOpenid() {
		return connectOpenid;
	}

	public void setConnectOpenid(String connectOpenid) {
		this.connectOpenid = connectOpenid == null ? null : connectOpenid
				.trim();
	}

	public String getForumKey() {
		return forumKey;
	}

	public void setForumKey(String forumKey) {
		this.forumKey = forumKey == null ? null : forumKey.trim();
	}

	public Boolean getForumAccredit() {
		return forumAccredit;
	}

	public void setForumAccredit(Boolean forumAccredit) {
		this.forumAccredit = forumAccredit;
	}

	public String getEncryption() {
		return encryption;
	}

	public void setEncryption(String encryption) {
		this.encryption = encryption == null ? null : encryption.trim();
	}

	public String getUserStatus() {
		return userStatus;
	}

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus == null ? null : userStatus.trim();
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getAddtimeStr() {
		if (EmptyUtil.isNotEmpty(addtime)) {
			return DateUtil.getStringDateByLongDate(new Long(addtime),
					DateUtil.DATESHOWFORMAT);
		} else {
			return "暂无日期信息";
		}
	}

	public void setAddtimeStr(String addtimeStr) {
		this.addtimeStr = addtimeStr;
	}

	public String getTypeName() {
		return EmptyUtil.isNotEmpty(this.userType) ? this.userType.getName()
				: null;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [userId=");
		builder.append(userId);
		builder.append(", typeId=");
		builder.append(typeId);
		builder.append(", order=");
		builder.append(order);
		builder.append(", purview=");
		builder.append(purview);
		builder.append(", username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", paypassword=");
		builder.append(paypassword);
		builder.append(", islock=");
		builder.append(islock);
		builder.append(", inviteUserid=");
		builder.append(inviteUserid);
		builder.append(", inviteMoney=");
		builder.append(inviteMoney);
		builder.append(", realStatus=");
		builder.append(realStatus);
		builder.append(", cardType=");
		builder.append(cardType);
		builder.append(", cardId=");
		builder.append(cardId);
		builder.append(", cardPic1=");
		builder.append(cardPic1);
		builder.append(", cardPic2=");
		builder.append(cardPic2);
		builder.append(", nation=");
		builder.append(nation);
		builder.append(", realname=");
		builder.append(realname);
		builder.append(", integral=");
		builder.append(integral);
		builder.append(", status=");
		builder.append(status);
		builder.append(", avatarStatus=");
		builder.append(avatarStatus);
		builder.append(", emailStatus=");
		builder.append(emailStatus);
		builder.append(", phoneStatus=");
		builder.append(phoneStatus);
		builder.append(", videoStatus=");
		builder.append(videoStatus);
		builder.append(", sceneStatus=");
		builder.append(sceneStatus);
		builder.append(", email=");
		builder.append(email);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", litpic=");
		builder.append(litpic);
		builder.append(", tel=");
		builder.append(tel);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", qq=");
		builder.append(qq);
		builder.append(", wangwang=");
		builder.append(wangwang);
		builder.append(", question=");
		builder.append(question);
		builder.append(", answer=");
		builder.append(answer);
		builder.append(", birthday=");
		builder.append(birthday);
		builder.append(", province=");
		builder.append(province);
		builder.append(", city=");
		builder.append(city);
		builder.append(", area=");
		builder.append(area);
		builder.append(", address=");
		builder.append(address);
		builder.append(", logintime=");
		builder.append(logintime);
		builder.append(", addtime=");
		builder.append(addtime);
		builder.append(", addip=");
		builder.append(addip);
		builder.append(", uptime=");
		builder.append(uptime);
		builder.append(", upip=");
		builder.append(upip);
		builder.append(", lasttime=");
		builder.append(lasttime);
		builder.append(", lastip=");
		builder.append(lastip);
		builder.append(", isPhone=");
		builder.append(isPhone);
		builder.append(", memberlevel=");
		builder.append(memberlevel);
		builder.append(", serialId=");
		builder.append(serialId);
		builder.append(", serialStatus=");
		builder.append(serialStatus);
		builder.append(", hongbao=");
		builder.append(hongbao);
		builder.append(", connectOpenid=");
		builder.append(connectOpenid);
		builder.append(", forumKey=");
		builder.append(forumKey);
		builder.append(", forumAccredit=");
		builder.append(forumAccredit);
		builder.append(", encryption=");
		builder.append(encryption);
		builder.append(", userStatus=");
		builder.append(userStatus);
		builder.append(", userType=");
		builder.append(userType);
		builder.append(", fromType=");
		builder.append(fromType);
		builder.append(", userIcon=");
		builder.append(userIcon);
		builder.append("]");
		return builder.toString();
	}

}