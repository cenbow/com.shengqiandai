package cn.vfunding.vfunding.biz.thirdlogin.model;

import java.util.Date;

import cn.vfunding.vfunding.common.model.BaseModel;


@SuppressWarnings("serial")
public class ThirdLogin extends BaseModel  {

	
	public ThirdLogin(){
		super();
	}
	
	public ThirdLogin(String loginAccount,String category){
		this.loginAccount=loginAccount;
		this.category=category;
	}
    private Integer id;

    private Integer userId;

    private String loginAccount;

    private String category;

    private Date lastLogin;

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

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount == null ? null : loginAccount.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}