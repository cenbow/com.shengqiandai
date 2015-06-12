package cn.vfunding.vfunding.biz.user.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.exception.BusinessException;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.common.vo.EarnedAndWaitOfReturnFeeVO;
import cn.vfunding.vfunding.biz.returns.dao.ReturnfeeDataMapper;
import cn.vfunding.vfunding.biz.returns.model.ReturnFeeData;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.system.model.Linkage;
import cn.vfunding.vfunding.biz.system.service.ILinkageService;
import cn.vfunding.vfunding.biz.user.dao.UpfinancialMapper;
import cn.vfunding.vfunding.biz.user.dao.UserInfoMapper;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.Upfinancial;
import cn.vfunding.vfunding.biz.user.model.UserInfo;
import cn.vfunding.vfunding.biz.user.service.IUserInfoService;

@Service("userInfoService")
public class UserInfoServiceImpl implements IUserInfoService {

	@Autowired
	private UserInfoMapper infoMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UpfinancialMapper upfinancialMapper;

	@Autowired
	private ReturnfeeDataMapper returnfeeDataMapper;

	@Autowired
	private ILinkageService linkageService;

	@Override
	public int insertSelective(UserInfo record) {
		return this.infoMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(UserInfo record) {
		return this.infoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public UserInfo selectByUser_Id(Integer userId) {
		UserInfo userInfo = this.infoMapper.selectByUserId(userId);
		return userInfo;
	}

	@Override
	public UserInfo selectByUserId(Integer userId) {
		UserInfo userInfo = this.infoMapper.selectByUserId(userId);
		Linkage linkage = null;
		if (EmptyUtil.isNotEmpty(userInfo)) {
			if (EmptyUtil.isNotEmpty(userInfo.getPrivateIncome())) {
				linkage = linkageService.selectByPrimaryKey((short) Integer.parseInt(userInfo.getPrivateIncome()));
				userInfo.setPrivateIncome(linkage.getName());
			}
			if (EmptyUtil.isNotEmpty(userInfo.getEducation())) {
				linkage = linkageService.selectByPrimaryKey((short) Integer.parseInt(userInfo.getEducation()));
				userInfo.setEducation(linkage.getName());
			}
			if (EmptyUtil.isNotEmpty(userInfo.getHousing())) {
				linkage = linkageService.selectByPrimaryKey((short) Integer.parseInt(userInfo.getHousing()));
				userInfo.setHousing(linkage.getName());
			}
			if (EmptyUtil.isNotEmpty(userInfo.getCar())) {
				linkage = linkageService.selectByPrimaryKey((short) Integer.parseInt(userInfo.getCar()));
				userInfo.setCar(linkage.getName());
			}
			if (EmptyUtil.isNotEmpty(userInfo.getCompanyAddress())) {
				linkage = linkageService.selectByPrimaryKey((short) Integer.parseInt(userInfo.getCompanyAddress()));
				userInfo.setCompanyAddress(linkage.getName());
			}

			if (EmptyUtil.isNotEmpty(userInfo.getCompanyType())) {
				linkage = linkageService.selectByPrimaryKey((short) Integer.parseInt(userInfo.getCompanyType()));
				userInfo.setCompanyType(linkage.getName());
			}
			if (EmptyUtil.isNotEmpty(userInfo.getCompanyOffice())) {
				linkage = linkageService.selectByPrimaryKey((short) Integer.parseInt(userInfo.getCompanyOffice()));
				userInfo.setCompanyOffice(linkage.getName());
			}
			if (EmptyUtil.isNotEmpty(userInfo.getIncome())) {
				linkage = linkageService.selectByPrimaryKey((short) Integer.parseInt(userInfo.getIncome()));
				userInfo.setIncome(linkage.getName());
			}
		}
		return userInfo;
	}

	@Override
	public String insertUpFinancialPlanner(UserSession user) {
		String msg = "出现异常，告知客服，尽快为您处理";
		Integer typeId = user.getTypeId();
		Double tenderMoney = this.returnfeeDataMapper.selectＭonthTenderMoney(user.getUserId()).doubleValue();
		Double sonTenderMoney = this.returnfeeDataMapper.selectSonＭonthTenderMoney(user.getUserId()).doubleValue();
		Upfinancial u = new Upfinancial();
		if (typeId == 30 || typeId == 31) {
			throw new BusinessException("8005011", "您已是最高级别理财师了");
		}
		Upfinancial upfinancial = upfinancialMapper.selectByUserIdAndType(user.getUserId());
		if (EmptyUtil.isNotEmpty(upfinancial)) {
			throw new BusinessException("8005011", "您的申请已提交,客服会快马加鞭的帮您处理");
		}
		if (typeId == 2 || typeId == 32) {
			if (tenderMoney >= 100000) {
				u.setTypeId(28);
				u.setUserId(user.getUserId());
				u.setAddTime(new Date());
				upfinancialMapper.insertSelective(u);
				msg = "您已成功提交升级特约理财师,客服会尽快帮您处理";
			} else {
				msg = "您30天内的投资金额不足10W元";
			}
		} else if (typeId == 28) {
			if (tenderMoney >= 5000) {
				if (sonTenderMoney >= 1000000) {
					u.setTypeId(29);
					u.setUserId(user.getUserId());
					u.setAddTime(new Date());
					upfinancialMapper.insertSelective(u);
					msg = "您已成功提交升级高级理财师,客服会尽快帮您处理";
				} else {
					msg = "您所邀请的好友投资金额不足100W元";
				}
			} else {
				msg = "您30天内的投资金额不足5000元";
			}

		} else if (typeId == 29) {
			if (tenderMoney >= 10000) {
				if (sonTenderMoney >= 5000000) {
					u.setTypeId(30);
					u.setUserId(user.getUserId());
					u.setAddTime(new Date());
					upfinancialMapper.insertSelective(u);
					msg = "您已成功提交升级首席理财师,客服会尽快帮您处理";
				} else {
					msg = "您所邀请的好友投资金额不足500W元";
				}

			} else {
				msg = "您30天内的投资金额不足10000元";
			}

		} else if (typeId == 27) {
			if (tenderMoney >= 5000) {
				u.setTypeId(31);
				u.setUserId(user.getUserId());
				u.setAddTime(new Date());
				upfinancialMapper.insertSelective(u);
				msg = "您已成功提交升级内部理财师,客服会尽快帮您处理";
			} else {
				msg = "您30天内的投资金额不足5000元";
			}
		}
		return msg;
	}

	@Override
	public List<ReturnFeeData> selectReturnFeeDatailListPage(PageSearch pageSearch) {
		// TODO Auto-generated method stub
		List<ReturnFeeData> returnList = this.returnfeeDataMapper.selectReturnFeeDatailListPage(pageSearch);
		return returnList;
	}

	@Override
	public EarnedAndWaitOfReturnFeeVO selectEarnedAndWaitOfReturnFee(Integer userId) {
		// TODO Auto-generated method stub
		List<ReturnFeeData> first = this.returnfeeDataMapper.selectFirstReturnFee(userId);
		List<ReturnFeeData> second = this.returnfeeDataMapper.selectSecondReturnFee(userId);
		List<ReturnFeeData> third = this.returnfeeDataMapper.selectThirdReturnFee(userId);
		BigDecimal zero = new BigDecimal(0);
		EarnedAndWaitOfReturnFeeVO ew = new EarnedAndWaitOfReturnFeeVO();
		ew.setFirstEarned(zero);
		ew.setFirstWait(zero);
		ew.setSecondEarned(zero);
		ew.setSecondWait(zero);
		ew.setThirdEarned(zero);
		ew.setThirdWait(zero);
		for (ReturnFeeData vo : first) {
			if (vo.getStatus() == 0) {
				ew.setFirstWait(vo.getReturnfee());
			} else if (vo.getStatus() == 1) {
				ew.setFirstEarned(vo.getReturnfee());
			}
		}
		for (ReturnFeeData vo : second) {
			if (vo.getStatus() == 0) {
				ew.setSecondWait(vo.getReturnfee());
			} else if (vo.getStatus() == 1) {
				ew.setSecondEarned(vo.getReturnfee());
			}
		}
		for (ReturnFeeData vo : third) {
			if (vo.getStatus() == 0) {
				ew.setThirdWait(vo.getReturnfee());
			} else if (vo.getStatus() == 1) {
				ew.setThirdEarned(vo.getReturnfee());
			}
		}
		return ew;
	}

}
