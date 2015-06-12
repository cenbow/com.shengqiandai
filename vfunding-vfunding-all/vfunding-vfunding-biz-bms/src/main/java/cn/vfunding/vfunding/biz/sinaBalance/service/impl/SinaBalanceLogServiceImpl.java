package cn.vfunding.vfunding.biz.sinaBalance.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.service.IHostingCollectTradeSinaService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendService;
import cn.vfunding.vfunding.biz.sina.service.ISingleHostingPayTradeSinaService;
import cn.vfunding.vfunding.biz.sina.vo.returns.QueryBalanceReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.QueryBalanceSendVO;
import cn.vfunding.vfunding.biz.sinaBalance.dao.SinaBalanceLogMapper;
import cn.vfunding.vfunding.biz.sinaBalance.model.SinaBalanceLog;
import cn.vfunding.vfunding.biz.sinaBalance.service.ISinaBalanceLogService;
import cn.vfunding.vfunding.biz.sinaResend.service.ISinaResendLogService;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.service.IUserService;

@Service("sinaBalanceLogService")
public class SinaBalanceLogServiceImpl implements ISinaBalanceLogService {

	@Autowired
	private SinaBalanceLogMapper sinaBalanceLogMapper;
	
	@Autowired
	private ISinaSendService sinaSendService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IHostingCollectTradeSinaService hostingCollectTradeSinaService;
	@Autowired
	private ISingleHostingPayTradeSinaService singleHostingPayTradeSinaService;
	@Autowired
	private ISinaResendLogService sinaResendLogService;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return sinaBalanceLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(SinaBalanceLog record) {
		// TODO Auto-generated method stub
		return sinaBalanceLogMapper.insertSelective(record);
	}

	@Override
	public SinaBalanceLog selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return sinaBalanceLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SinaBalanceLog record) {
		// TODO Auto-generated method stub
		return sinaBalanceLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<SinaBalanceLog> selectTotelNotZeroId() {
		// TODO Auto-generated method stub
		return sinaBalanceLogMapper.selectTotelNotZeroId();
	}


	@Async
	@Override
	public void querySinaBalanceAsync(List<SinaBalanceLog> sbs){
		for (SinaBalanceLog sb : sbs) {
			User u = userService.selectByPrimaryKey(sb.getUserId());
			QueryBalanceSendVO qbs = new QueryBalanceSendVO();
			qbs.setIdentity_id(sb.getUserId().toString());
			qbs.setIdentity_type(SinaMemberParmUtil.IdenType.UID);
			if (u.getTypeId() == 40) {
				qbs.setAccount_type(SinaMemberParmUtil.AccountType.BASIC);
			} else {
				qbs.setAccount_type(SinaMemberParmUtil.AccountType.SAVING_POT);
			}
			try {
				QueryBalanceReturnVO qbr = sinaSendService.sinaSendMgs(qbs, QueryBalanceReturnVO.class);
				sb.setAvailableBalance(new BigDecimal(qbr.getAvailable_balance()));
				sb.setBalance(new BigDecimal(qbr.getBalance()));
				if(u.getTypeId() != 40){
					String[] bouns = qbr.getBonus().split("\\^");
					if (bouns[0].equals("null")) {
						sb.setBonusYesterday(new BigDecimal(0));
					} else {
						sb.setBonusYesterday(new BigDecimal(bouns[0]));
					}
					if (bouns[1].equals("null")) {
						sb.setBonusMonth(new BigDecimal(0));
					} else {
						sb.setBonusMonth(new BigDecimal(bouns[1]));
					}
					if (bouns[2].equals("null")) {
						sb.setBonusTotal(new BigDecimal(0));
					} else {
						sb.setBonusTotal(new BigDecimal(bouns[2]));
					}
				}
				this.insertSelective(sb);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return this.sinaBalanceLogMapper.deleteAll();
	}


	
}
