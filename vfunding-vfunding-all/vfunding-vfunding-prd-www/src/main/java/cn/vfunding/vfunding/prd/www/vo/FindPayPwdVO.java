package cn.vfunding.vfunding.prd.www.vo;

import cn.vfunding.common.framework.utils.beans.BaseVO;

public class FindPayPwdVO extends BaseVO {

	private String question;
	
	private String answer;
	
	private String newPassword;
	
	private String rePassword;

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

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	
	
	
}
