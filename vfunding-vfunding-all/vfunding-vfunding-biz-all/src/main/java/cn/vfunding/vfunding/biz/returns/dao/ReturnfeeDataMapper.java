package cn.vfunding.vfunding.biz.returns.dao;

import java.math.BigDecimal;
import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.returns.model.ReturnFeeData;

public interface ReturnfeeDataMapper {

	BigDecimal selectＭonthTenderMoney(Integer userId);

	BigDecimal selectSonＭonthTenderMoney(Integer userId);

	List<ReturnFeeData> selectFirstReturnFee(Integer userId);

	List<ReturnFeeData> selectSecondReturnFee(Integer userId);

	List<ReturnFeeData> selectThirdReturnFee(Integer userId);

	List<ReturnFeeData> selectReturnFeeDatailListPage(PageSearch pageSearch);
	
	List<ReturnFeeData> selectRebateDatailListPage(PageSearch pageSearch);
}