package cn.vfunding.vfunding.biz.icbcCard.model;

import java.util.Date;

public class IcbcCard {
    private Integer id;

    private Integer userId;

    private String userName;

    private String userIdcard;

    private String userPhone;

    private String province;

    private String city;

    private String postCode;

    private String address;

    private String icbcCard;

    private String idcardPicFace;

    private String idcardPicBack;

    private Integer noFeeCount;

    private Date addtime;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserIdcard() {
        return userIdcard;
    }

    public void setUserIdcard(String userIdcard) {
        this.userIdcard = userIdcard == null ? null : userIdcard.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
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

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getIcbcCard() {
        return icbcCard;
    }

    public void setIcbcCard(String icbcCard) {
        this.icbcCard = icbcCard == null ? null : icbcCard.trim();
    }

    public String getIdcardPicFace() {
        return idcardPicFace;
    }

    public void setIdcardPicFace(String idcardPicFace) {
        this.idcardPicFace = idcardPicFace == null ? null : idcardPicFace.trim();
    }

    public String getIdcardPicBack() {
        return idcardPicBack;
    }

    public void setIdcardPicBack(String idcardPicBack) {
        this.idcardPicBack = idcardPicBack == null ? null : idcardPicBack.trim();
    }

    public Integer getNoFeeCount() {
        return noFeeCount;
    }

    public void setNoFeeCount(Integer noFeeCount) {
        this.noFeeCount = noFeeCount;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}