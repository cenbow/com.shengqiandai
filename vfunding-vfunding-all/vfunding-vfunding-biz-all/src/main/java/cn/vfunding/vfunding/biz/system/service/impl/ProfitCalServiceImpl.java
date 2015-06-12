package cn.vfunding.vfunding.biz.system.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.ConverterUtil;
import cn.vfunding.vfunding.biz.common.vo.ProfitCalJsonVO;
import cn.vfunding.vfunding.biz.common.vo.ProfitCalVO;
import cn.vfunding.vfunding.biz.system.service.IProfitCalService;

@Service
public class ProfitCalServiceImpl implements IProfitCalService {

	@Override
	public List<ProfitCalJsonVO> selectProfitCal(ProfitCalVO vo) {
		// TODO Auto-generated method stub
		List<ProfitCalJsonVO> list = new ArrayList<ProfitCalJsonVO>();
		ProfitCalJsonVO jsonVO = null;
		for (int i = 1; i <= vo.getMonth(); i++) {
			jsonVO = new ProfitCalJsonVO();
			BigDecimal monthCapitalInterest = ConverterUtil.monthlyAccount(vo.getMoney(), vo.getApr(), new BigDecimal(
					vo.getMonth()));
			BigDecimal monthInterest = ConverterUtil.monthlyInterest(vo.getMoney(), vo.getApr(),
					new BigDecimal(vo.getMonth()), new BigDecimal(i));
			jsonVO.setMonthCapitalInterest(monthCapitalInterest);
			jsonVO.setMonthInterest(monthInterest);
			list.add(jsonVO);
		}
		return list;
	}
}
