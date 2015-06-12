package cn.p2p.p2p.biz.rechargeandtender.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.p2p.p2p.biz.current.service.ICurrentTenderService;
import cn.p2p.p2p.biz.current.vo.UserAccountActionResultVO;
import cn.p2p.p2p.biz.current.vo.UserCurrentAccountVO;
import cn.p2p.p2p.biz.mobile.base.model.MobileBaseResponse;
import cn.p2p.p2p.biz.rechargeandtender.dao.RechargeAndTenderMapper;
import cn.p2p.p2p.biz.rechargeandtender.model.RechargeAndTender;
import cn.p2p.p2p.biz.rechargeandtender.service.IRechargeAndTenderService;
import cn.vfunding.vfunding.biz.account.dao.AccountRechargeMapper;
import cn.vfunding.vfunding.biz.account.model.AccountRecharge;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowTenderService;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionResultVO;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionVO;

@Service
public class RechargeAndTenderServiceImpl implements IRechargeAndTenderService {

	@Autowired
	private RechargeAndTenderMapper rechargeAndTenderMapper;
	@Autowired
	private IBorrowTenderService tenderService;

	@Autowired
	private ICurrentTenderService currentTenderService;

	@Autowired
	private AccountRechargeMapper accountRechargeMapper;

	@Override
	public int deleteByPrimaryKey(String tradeNo) {
		// TODO Auto-generated method stub
		return this.rechargeAndTenderMapper.deleteByPrimaryKey(tradeNo);
	}

	@Override
	public int insertSelective(RechargeAndTender record) {
		// TODO Auto-generated method stub
		return this.rechargeAndTenderMapper.insertSelective(record);
	}

	@Override
	public RechargeAndTender selectByPrimaryKey(String tradeNo) {
		// TODO Auto-generated method stub
		return this.rechargeAndTenderMapper.selectByPrimaryKey(tradeNo);
	}

	@Override
	public int updateByPrimaryKeySelective(RechargeAndTender record) {
		// TODO Auto-generated method stub
		return this.rechargeAndTenderMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void rechargeTenderAction(String tradeNo, String str) {
		// TODO Auto-generated method stub
		RechargeAndTender rat = this.rechargeAndTenderMapper.selectByPrimaryKey(tradeNo);
		AccountRecharge ar = this.accountRechargeMapper.selectByTradeNo(tradeNo);
		if (rat != null && str.equals("SUCCESS") && ar.getStatus() == 1) {
			if (rat.getType() == 0 && rat.getStatus() == 4) {
				UserTenderActionVO userVO = new UserTenderActionVO();
				userVO.setBorrowId(rat.getBorrowId());
				userVO.setIsUseHikes(rat.getIsUseHikes());
				userVO.setPayMoney(rat.getMoney());
				userVO.setUserId(rat.getUserId());
				userVO.setUserip("recharge");
				UserTenderActionResultVO resultVO = this.tenderService.userTenderAction(userVO);
				if (resultVO.getStatus() == 1) {
					rat.setStatus(11);
					rat.setRemark("投资成功");
					rat.setAccount(resultVO.getRealPayMoney());
					this.rechargeAndTenderMapper.updateByPrimaryKeySelective(rat);
				} else if (resultVO.getStatus() == 2) {
					rat.setStatus(12);
					rat.setRemark("标的已被抢空,成功投资" + resultVO.getRealPayMoney() + "元,多余金额退还至账户中");
					rat.setAccount(resultVO.getRealPayMoney());
					this.rechargeAndTenderMapper.updateByPrimaryKeySelective(rat);
				} else if (resultVO.getStatus() == 3) {
					rat.setStatus(13);
					rat.setRemark("标的已被抢空,你所充值的金额退还至账户中");
					rat.setAccount(resultVO.getRealPayMoney());
					this.rechargeAndTenderMapper.updateByPrimaryKeySelective(rat);
				}
			} else if (rat.getType() == 1 && rat.getStatus() == 4) {
				UserCurrentAccountVO vo = new UserCurrentAccountVO();
				vo.setMoney(rat.getMoney());
				vo.setUserId(rat.getUserId());
				vo.setCurrentId(rat.getBorrowId());
				vo.setUserip("recharge");
				MobileBaseResponse mbr = this.currentTenderService.UserCurrentTender(vo);
				if (mbr.getResponseCode().equals("success")) {
					UserAccountActionResultVO resultVO = (UserAccountActionResultVO) mbr.getResponseObject();
					if (resultVO.getStatus() == 1) {
						rat.setStatus(11);
						rat.setRemark("投资成功");
						rat.setAccount(resultVO.getRealPayMoney());
						this.rechargeAndTenderMapper.updateByPrimaryKeySelective(rat);
					} else if (resultVO.getStatus() == 2) {
						rat.setStatus(12);
						rat.setRemark("标的已被抢空,成功投资" + resultVO.getRealPayMoney() + "元,多余金额退还至账户中");
						rat.setAccount(resultVO.getRealPayMoney());
						this.rechargeAndTenderMapper.updateByPrimaryKeySelective(rat);
					} else if (resultVO.getStatus() == 3) {
						rat.setStatus(13);
						rat.setRemark("标的已被抢空,你所充值的金额退还至账户中");
						rat.setAccount(resultVO.getRealPayMoney());
						this.rechargeAndTenderMapper.updateByPrimaryKeySelective(rat);
					}
				} else {
					rat.setStatus(1);
					rat.setAccount(new BigDecimal(0));
					rat.setRemark(mbr.getResponseMessage());
					this.rechargeAndTenderMapper.updateByPrimaryKeySelective(rat);
				}

			}
		} else if (rat != null && rat.getStatus() == 4) {
			rat.setStatus(2);
			rat.setRemark("充值失败");
			this.rechargeAndTenderMapper.updateByPrimaryKeySelective(rat);
		}
	}

}