package cn.vfunding.vfunding.biz.user.model;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class UserInfo extends BaseModel  {
    private Integer id;

    private Integer siteId;

    private Integer userId;

    private String name;

    private Integer status;

    private Integer order;

    private Integer hits;

    private String litpic;

    private String flag;

    private String source;

    private String publish;

    private String marry;

    private String child;

    private String education;

    private String income;

    private String shebao;

    private String shebaoid;

    private String housing;

    private String car;

    private String late;

    private String houseAddress;

    private String houseArea;

    private String houseYear;

    private String houseStatus;

    private String houseHolder1;

    private String houseHolder2;

    private String houseRight1;

    private String houseRight2;

    private String houseLoanyear;

    private String houseLoanprice;

    private String houseBalance;

    private String houseBank;

    private String companyName;

    private String companyType;

    private String companyIndustry;

    private String companyOffice;

    private String companyJibie;

    private String companyWorktime1;

    private String companyWorktime2;

    private String companyWorkyear;

    private String companyTel;

    private String companyAddress;

    private String companyWeburl;

    private String companyReamrk;

    private String privateType;

    private String privateDate;

    private String privatePlace;

    private String privateRent;

    private String privateTerm;

    private String privateTaxid;

    private String privateCommerceid;

    private String privateIncome;

    private String privateEmployee;

    private String financeRepayment;

    private String financeProperty;

    private String financeAmount;

    private String financeCar;

    private String financeCaramount;

    private String financeCreditcard;

    private String mateName;

    private String mateSalary;

    private String matePhone;

    private String mateTel;

    private String mateType;

    private String mateOffice;

    private String mateAddress;

    private String mateIncome;

    private String educationRecord;

    private String educationSchool;

    private String educationStudy;

    private String educationTime1;

    private String educationTime2;

    private String tel;

    private String phone;

    private String post;

    private String address;

    private String province;

    private String city;

    private String area;

    private String linkman1;

    private String relation1;

    private String tel1;

    private String phone1;

    private String linkman2;

    private String relation2;

    private String tel2;

    private String phone2;

    private String linkman3;

    private String relation3;

    private String tel3;

    private String phone3;

    private String msn;

    private String qq;

    private String wangwang;

    private String ability;

    private String interest;

    private String others;

    private String addtime;

    private String addip;

    private String updatetime;

    private String updateip;

    private String experience;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public String getLitpic() {
        return litpic;
    }

    public void setLitpic(String litpic) {
        this.litpic = litpic == null ? null : litpic.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish == null ? null : publish.trim();
    }

    public String getMarry() {
        return marry;
    }

    public void setMarry(String marry) {
        this.marry = marry == null ? null : marry.trim();
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child == null ? null : child.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income == null ? null : income.trim();
    }

    public String getShebao() {
        return shebao;
    }

    public void setShebao(String shebao) {
        this.shebao = shebao == null ? null : shebao.trim();
    }

    public String getShebaoid() {
        return shebaoid;
    }

    public void setShebaoid(String shebaoid) {
        this.shebaoid = shebaoid == null ? null : shebaoid.trim();
    }

    public String getHousing() {
        return housing;
    }

    public void setHousing(String housing) {
        this.housing = housing == null ? null : housing.trim();
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car == null ? null : car.trim();
    }

    public String getLate() {
        return late;
    }

    public void setLate(String late) {
        this.late = late == null ? null : late.trim();
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress == null ? null : houseAddress.trim();
    }

    public String getHouseArea() {
        return houseArea;
    }

    public void setHouseArea(String houseArea) {
        this.houseArea = houseArea == null ? null : houseArea.trim();
    }

    public String getHouseYear() {
        return houseYear;
    }

    public void setHouseYear(String houseYear) {
        this.houseYear = houseYear == null ? null : houseYear.trim();
    }

    public String getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(String houseStatus) {
        this.houseStatus = houseStatus == null ? null : houseStatus.trim();
    }

    public String getHouseHolder1() {
        return houseHolder1;
    }

    public void setHouseHolder1(String houseHolder1) {
        this.houseHolder1 = houseHolder1 == null ? null : houseHolder1.trim();
    }

    public String getHouseHolder2() {
        return houseHolder2;
    }

    public void setHouseHolder2(String houseHolder2) {
        this.houseHolder2 = houseHolder2 == null ? null : houseHolder2.trim();
    }

    public String getHouseRight1() {
        return houseRight1;
    }

    public void setHouseRight1(String houseRight1) {
        this.houseRight1 = houseRight1 == null ? null : houseRight1.trim();
    }

    public String getHouseRight2() {
        return houseRight2;
    }

    public void setHouseRight2(String houseRight2) {
        this.houseRight2 = houseRight2 == null ? null : houseRight2.trim();
    }

    public String getHouseLoanyear() {
        return houseLoanyear;
    }

    public void setHouseLoanyear(String houseLoanyear) {
        this.houseLoanyear = houseLoanyear == null ? null : houseLoanyear.trim();
    }

    public String getHouseLoanprice() {
        return houseLoanprice;
    }

    public void setHouseLoanprice(String houseLoanprice) {
        this.houseLoanprice = houseLoanprice == null ? null : houseLoanprice.trim();
    }

    public String getHouseBalance() {
        return houseBalance;
    }

    public void setHouseBalance(String houseBalance) {
        this.houseBalance = houseBalance == null ? null : houseBalance.trim();
    }

    public String getHouseBank() {
        return houseBank;
    }

    public void setHouseBank(String houseBank) {
        this.houseBank = houseBank == null ? null : houseBank.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType == null ? null : companyType.trim();
    }

    public String getCompanyIndustry() {
        return companyIndustry;
    }

    public void setCompanyIndustry(String companyIndustry) {
        this.companyIndustry = companyIndustry == null ? null : companyIndustry.trim();
    }

    public String getCompanyOffice() {
        return companyOffice;
    }

    public void setCompanyOffice(String companyOffice) {
        this.companyOffice = companyOffice == null ? null : companyOffice.trim();
    }

    public String getCompanyJibie() {
        return companyJibie;
    }

    public void setCompanyJibie(String companyJibie) {
        this.companyJibie = companyJibie == null ? null : companyJibie.trim();
    }

    public String getCompanyWorktime1() {
        return companyWorktime1;
    }

    public void setCompanyWorktime1(String companyWorktime1) {
        this.companyWorktime1 = companyWorktime1 == null ? null : companyWorktime1.trim();
    }

    public String getCompanyWorktime2() {
        return companyWorktime2;
    }

    public void setCompanyWorktime2(String companyWorktime2) {
        this.companyWorktime2 = companyWorktime2 == null ? null : companyWorktime2.trim();
    }

    public String getCompanyWorkyear() {
        return companyWorkyear;
    }

    public void setCompanyWorkyear(String companyWorkyear) {
        this.companyWorkyear = companyWorkyear == null ? null : companyWorkyear.trim();
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel == null ? null : companyTel.trim();
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    public String getCompanyWeburl() {
        return companyWeburl;
    }

    public void setCompanyWeburl(String companyWeburl) {
        this.companyWeburl = companyWeburl == null ? null : companyWeburl.trim();
    }

    public String getCompanyReamrk() {
        return companyReamrk;
    }

    public void setCompanyReamrk(String companyReamrk) {
        this.companyReamrk = companyReamrk == null ? null : companyReamrk.trim();
    }

    public String getPrivateType() {
        return privateType;
    }

    public void setPrivateType(String privateType) {
        this.privateType = privateType == null ? null : privateType.trim();
    }

    public String getPrivateDate() {
        return privateDate;
    }

    public void setPrivateDate(String privateDate) {
        this.privateDate = privateDate == null ? null : privateDate.trim();
    }

    public String getPrivatePlace() {
        return privatePlace;
    }

    public void setPrivatePlace(String privatePlace) {
        this.privatePlace = privatePlace == null ? null : privatePlace.trim();
    }

    public String getPrivateRent() {
        return privateRent;
    }

    public void setPrivateRent(String privateRent) {
        this.privateRent = privateRent == null ? null : privateRent.trim();
    }

    public String getPrivateTerm() {
        return privateTerm;
    }

    public void setPrivateTerm(String privateTerm) {
        this.privateTerm = privateTerm == null ? null : privateTerm.trim();
    }

    public String getPrivateTaxid() {
        return privateTaxid;
    }

    public void setPrivateTaxid(String privateTaxid) {
        this.privateTaxid = privateTaxid == null ? null : privateTaxid.trim();
    }

    public String getPrivateCommerceid() {
        return privateCommerceid;
    }

    public void setPrivateCommerceid(String privateCommerceid) {
        this.privateCommerceid = privateCommerceid == null ? null : privateCommerceid.trim();
    }

    public String getPrivateIncome() {
        return privateIncome;
    }

    public void setPrivateIncome(String privateIncome) {
        this.privateIncome = privateIncome == null ? null : privateIncome.trim();
    }

    public String getPrivateEmployee() {
        return privateEmployee;
    }

    public void setPrivateEmployee(String privateEmployee) {
        this.privateEmployee = privateEmployee == null ? null : privateEmployee.trim();
    }

    public String getFinanceRepayment() {
        return financeRepayment;
    }

    public void setFinanceRepayment(String financeRepayment) {
        this.financeRepayment = financeRepayment == null ? null : financeRepayment.trim();
    }

    public String getFinanceProperty() {
        return financeProperty;
    }

    public void setFinanceProperty(String financeProperty) {
        this.financeProperty = financeProperty == null ? null : financeProperty.trim();
    }

    public String getFinanceAmount() {
        return financeAmount;
    }

    public void setFinanceAmount(String financeAmount) {
        this.financeAmount = financeAmount == null ? null : financeAmount.trim();
    }

    public String getFinanceCar() {
        return financeCar;
    }

    public void setFinanceCar(String financeCar) {
        this.financeCar = financeCar == null ? null : financeCar.trim();
    }

    public String getFinanceCaramount() {
        return financeCaramount;
    }

    public void setFinanceCaramount(String financeCaramount) {
        this.financeCaramount = financeCaramount == null ? null : financeCaramount.trim();
    }

    public String getFinanceCreditcard() {
        return financeCreditcard;
    }

    public void setFinanceCreditcard(String financeCreditcard) {
        this.financeCreditcard = financeCreditcard == null ? null : financeCreditcard.trim();
    }

    public String getMateName() {
        return mateName;
    }

    public void setMateName(String mateName) {
        this.mateName = mateName == null ? null : mateName.trim();
    }

    public String getMateSalary() {
        return mateSalary;
    }

    public void setMateSalary(String mateSalary) {
        this.mateSalary = mateSalary == null ? null : mateSalary.trim();
    }

    public String getMatePhone() {
        return matePhone;
    }

    public void setMatePhone(String matePhone) {
        this.matePhone = matePhone == null ? null : matePhone.trim();
    }

    public String getMateTel() {
        return mateTel;
    }

    public void setMateTel(String mateTel) {
        this.mateTel = mateTel == null ? null : mateTel.trim();
    }

    public String getMateType() {
        return mateType;
    }

    public void setMateType(String mateType) {
        this.mateType = mateType == null ? null : mateType.trim();
    }

    public String getMateOffice() {
        return mateOffice;
    }

    public void setMateOffice(String mateOffice) {
        this.mateOffice = mateOffice == null ? null : mateOffice.trim();
    }

    public String getMateAddress() {
        return mateAddress;
    }

    public void setMateAddress(String mateAddress) {
        this.mateAddress = mateAddress == null ? null : mateAddress.trim();
    }

    public String getMateIncome() {
        return mateIncome;
    }

    public void setMateIncome(String mateIncome) {
        this.mateIncome = mateIncome == null ? null : mateIncome.trim();
    }

    public String getEducationRecord() {
        return educationRecord;
    }

    public void setEducationRecord(String educationRecord) {
        this.educationRecord = educationRecord == null ? null : educationRecord.trim();
    }

    public String getEducationSchool() {
        return educationSchool;
    }

    public void setEducationSchool(String educationSchool) {
        this.educationSchool = educationSchool == null ? null : educationSchool.trim();
    }

    public String getEducationStudy() {
        return educationStudy;
    }

    public void setEducationStudy(String educationStudy) {
        this.educationStudy = educationStudy == null ? null : educationStudy.trim();
    }

    public String getEducationTime1() {
        return educationTime1;
    }

    public void setEducationTime1(String educationTime1) {
        this.educationTime1 = educationTime1 == null ? null : educationTime1.trim();
    }

    public String getEducationTime2() {
        return educationTime2;
    }

    public void setEducationTime2(String educationTime2) {
        this.educationTime2 = educationTime2 == null ? null : educationTime2.trim();
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

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post == null ? null : post.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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

    public String getLinkman1() {
        return linkman1;
    }

    public void setLinkman1(String linkman1) {
        this.linkman1 = linkman1 == null ? null : linkman1.trim();
    }

    public String getRelation1() {
        return relation1;
    }

    public void setRelation1(String relation1) {
        this.relation1 = relation1 == null ? null : relation1.trim();
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1 == null ? null : tel1.trim();
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1 == null ? null : phone1.trim();
    }

    public String getLinkman2() {
        return linkman2;
    }

    public void setLinkman2(String linkman2) {
        this.linkman2 = linkman2 == null ? null : linkman2.trim();
    }

    public String getRelation2() {
        return relation2;
    }

    public void setRelation2(String relation2) {
        this.relation2 = relation2 == null ? null : relation2.trim();
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2 == null ? null : tel2.trim();
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2 == null ? null : phone2.trim();
    }

    public String getLinkman3() {
        return linkman3;
    }

    public void setLinkman3(String linkman3) {
        this.linkman3 = linkman3 == null ? null : linkman3.trim();
    }

    public String getRelation3() {
        return relation3;
    }

    public void setRelation3(String relation3) {
        this.relation3 = relation3 == null ? null : relation3.trim();
    }

    public String getTel3() {
        return tel3;
    }

    public void setTel3(String tel3) {
        this.tel3 = tel3 == null ? null : tel3.trim();
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3 == null ? null : phone3.trim();
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn == null ? null : msn.trim();
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

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability == null ? null : ability.trim();
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest == null ? null : interest.trim();
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others == null ? null : others.trim();
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

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime == null ? null : updatetime.trim();
    }

    public String getUpdateip() {
        return updateip;
    }

    public void setUpdateip(String updateip) {
        this.updateip = updateip == null ? null : updateip.trim();
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience == null ? null : experience.trim();
    }
}