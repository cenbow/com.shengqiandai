package cn.vfunding.vfunding.biz.session.utils;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author liuyijun
 *
 */
public class UserSession implements Serializable {

	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	/**
	 * 数据的交互枢纽
	 */
	protected static ThreadLocal<UserSession> threadLocalUserSession = new ThreadLocal<UserSession>();

	public static void setUserSession(UserSession BaseUserSession) {
		threadLocalUserSession.set(BaseUserSession);
	}

	public static UserSession getUserSession() {
		UserSession BaseUserSession = threadLocalUserSession.get();
		return BaseUserSession;
	}

	public static Integer getLoginUserId() {
		UserSession userSession = (UserSession) threadLocalUserSession
				.get();
		if (userSession == null) {
			return null;
		} else {
			return userSession.getUserId();
		}
	}

	protected Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	private String userName;

	private String password;

	private String payPassword;

	private String phone;

	private String email;

	private String emailStatus;
	
	private String phoneStatus;

	private Integer videoStatus;

	private Integer sceneStatus;
	
	private String realStatus;
	
	private String cardId;
	
	private String question;

	private String answer;
	
	private Integer typeId;
	
	private String typeName;
	
	private Integer vipStatus;
	
	private String realName;
	
	private String sex;
	
	private String address;
	
	//private BigDecimal hongbao;
	
	private String userIcon;
	
	private Integer isThirdUser;
	
	
	public String getPhoneStatus() {
		return phoneStatus;
	}

	public void setPhoneStatus(String phoneStatus) {
		this.phoneStatus = phoneStatus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailStatus() {
		return emailStatus;
	}

	public void setEmailStatus(String emailStatus) {
		this.emailStatus = emailStatus;
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

	public String getRealStatus() {
		return realStatus;
	}

	public void setRealStatus(String realStatus) {
		this.realStatus = realStatus;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getVipStatus() {
		return vipStatus;
	}

	public void setVipStatus(Integer vipStatus) {
		this.vipStatus = vipStatus;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

//	public BigDecimal getHongbao() {
//		return hongbao;
//	}
//
//	public void setHongbao(BigDecimal hongbao) {
//		this.hongbao = hongbao;
//	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	public Integer getIsThirdUser() {
		return isThirdUser;
	}

	public void setIsThirdUser(Integer isThirdUser) {
		this.isThirdUser = isThirdUser;
	}

}
