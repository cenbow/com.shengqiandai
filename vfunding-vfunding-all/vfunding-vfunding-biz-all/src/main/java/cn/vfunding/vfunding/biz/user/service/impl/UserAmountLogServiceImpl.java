package cn.vfunding.vfunding.biz.user.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.vfunding.biz.common.vo.AmountVO;
import cn.vfunding.vfunding.biz.common.vo.ApplyAmountVO;
import cn.vfunding.vfunding.biz.user.dao.UserAmountMapper;
import cn.vfunding.vfunding.biz.user.dao.UserAmountapplyMapper;
import cn.vfunding.vfunding.biz.user.dao.UserAmountlogMapper;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.model.UserAmount;
import cn.vfunding.vfunding.biz.user.model.UserAmountapply;
import cn.vfunding.vfunding.biz.user.model.UserAmountapplyWithBLOBs;
import cn.vfunding.vfunding.biz.user.model.UserAmountlog;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
import cn.vfunding.vfunding.biz.user.service.IUserAmountLogService;
@Service("userAmountLogService")
public class UserAmountLogServiceImpl implements IUserAmountLogService {
	@Autowired
	private UserAmountlogMapper userAmountlogMapper;
	@Autowired
	private UserAmountapplyMapper userAmountapplyMapper;
	@Autowired
	private UserAmountMapper userAmountMapper;
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return userAmountlogMapper.deleteByPrimaryKey(id);
	}
	@Override
	public int insert(UserAmountlog record) {
		return userAmountlogMapper.insert(record);
	}

	@Override
	public int insertSelective(UserAmountlog record) {
		return userAmountlogMapper.insertSelective(record);
	}

	@Override
	public UserAmountlog selectByPrimaryKey(Integer id) {
		return userAmountlogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserAmountlog record) {
		return userAmountlogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(UserAmountlog record) {
		return userAmountlogMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(UserAmountlog record) {
		return userAmountlogMapper.updateByPrimaryKey(record);
	}
	
	@Override
	public Json applyOnline(ApplyAmountVO applyAmountvo) {
		Json j = new Json();
		UserAmountapply record = new UserAmountapply();
		record.setUserId(applyAmountvo.getUserId());
		record.setType(applyAmountvo.getType());
//		List<UserAmountapply> applys = userAmountapplyMapper.selectByParam(record);//历史申请记录
//		if(!applys.isEmpty()){ // 一月内只能申请一次
//			if(Integer.parseInt(DateUtil.getTime()) - Integer.parseInt(applys.get(0).getVerifyTime()) < 2592000){
//				j.setMsg("一个月内只能申请一次.");
//				return j;
//			}
//			if(applys.get(0).getStatus() == 2){
//				j.setMsg("您有一笔申请正在审核,不能再次申请.");
//				return j;
//			}
//		}
		UserAmountapplyWithBLOBs applyWithBLOBs = new UserAmountapplyWithBLOBs();
		applyWithBLOBs.setAccount(applyAmountvo.getAccount());
		applyWithBLOBs.setType(applyAmountvo.getType());
		applyWithBLOBs.setMortgagetypeId(applyAmountvo.getMortgagetypeId());
		applyWithBLOBs.setLimit(applyAmountvo.getLimit());
		applyWithBLOBs.setContent(applyAmountvo.getContent());
		applyWithBLOBs.setUserId(applyAmountvo.getUserId());
		applyWithBLOBs.setAddip(applyAmountvo.getIp());
		applyWithBLOBs.setAddtime(DateUtil.getTime());
		applyWithBLOBs.setStatus(2);//待审核
		applyWithBLOBs.setRemark("");
		userAmountapplyMapper.insertSelective(applyWithBLOBs);
		
		j.setSuccess(true);
		j.setMsg("申请已提交，请敬候信贷专员的联络！");
		return j;
	}
	/**
	 * @Description:额度审批
	 * @param amountVO
	 * @return
	 * @author liuhuan
	 */
	@Override
	public synchronized int updateApplyAmount(AmountVO amountVO) {
		UserAmountapply record = new UserAmountapply();
		record.setId(amountVO.getAmountId());
		UserAmountapply amountApply = userAmountapplyMapper.selectByParam(record).get(0);
		if(amountApply.getStatus() == 1){
			return -1;
		}
		
		amountApply.setStatus(amountVO.getStatus());
		amountApply.setVerifyRemark(amountVO.getRemark());
		amountApply.setVerifyTime(DateUtil.getTime());
		amountApply.setVerifyUser(amountVO.getAdminUserId());
		
		UserAmountapplyWithBLOBs wb = new UserAmountapplyWithBLOBs();
		wb.setId(amountApply.getId());
		wb.setStatus(amountApply.getStatus());
		wb.setVerifyRemark(amountApply.getVerifyRemark());
		wb.setVerifyTime(amountApply.getVerifyTime());
		wb.setVerifyUser(amountApply.getVerifyUser());
		userAmountapplyMapper.updateByPrimaryKeySelective(wb);//更新amountApply
		
		UserAmountlog log = new UserAmountlog();
		log.setUserId(amountApply.getUserId());
		log.setType("apply_add");
		log.setAmountType(amountApply.getType().equals("credit")?"credit":
			amountApply.getType().equals("tender_vouch")?"tender_vouch":"borrow_vouch"); 
		log.setAccount(amountVO.getAccount());
		log.setAccountAll(amountVO.getAccount());
		log.setAccountUse(amountVO.getAccount());
		log.setAccountNouse(new BigDecimal("0"));
		log.setRemark(amountVO.getRemark());
		log.setAddtime(DateUtil.getTime());
		log.setAddip(amountVO.getIp());
		userAmountlogMapper.insertSelective(log);//写入日志
		
		if(amountVO.getStatus()==1){
			UserAmount userAmount = new UserAmount();
			userAmount.setUserId(amountApply.getUserId());
			userAmount = userAmountMapper.selectByParam(userAmount).get(0);
			if(amountApply.getType().equals("credit")){ //信用额度
				userAmount.setCredit(amountVO.getAccount());
				userAmount.setCreditUse(amountVO.getAccount());
			} else if(amountApply.getType().equals("tender_vouch")){//投资担保额度
				userAmount.setTenderVouch(amountVO.getAccount());
				userAmount.setTenderVouchUse(amountVO.getAccount());
			} else if(amountApply.getType().equals("borrow_vouch")){//借款担保额度
				userAmount.setBorrowVouch(amountVO.getAccount());
				userAmount.setBorrowVouchUse(amountVO.getAccount());
			} else {}
			userAmount.setId(userAmount.getId());
			userAmountMapper.updateByPrimaryKeySelective(userAmount);//更新额度表
		}
		if(amountVO.getStatus()==1){
			//修改user为借款用户
			User user = userMapper.selectByPrimaryKey(amountApply.getUserId());
			UserWithBLOBs uwb = new UserWithBLOBs();
			uwb.setTypeId(40);
			uwb.setUserId(user.getUserId());
			userMapper.updateByPrimaryKeySelective(uwb);
		}
		
		return amountVO.getStatus()==1?1:0;
	}

}
