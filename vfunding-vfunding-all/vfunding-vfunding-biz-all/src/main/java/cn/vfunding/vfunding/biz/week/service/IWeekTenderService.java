package cn.vfunding.vfunding.biz.week.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.common.vo.BorrowTenderVO;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionResultVO;
import cn.vfunding.vfunding.biz.week.model.WeekTender;
import cn.vfunding.vfunding.biz.week.vo.HoldingAssetsVO;
import cn.vfunding.vfunding.biz.week.vo.WeekTenderVO;
import cn.vfunding.vfunding.common.module.activemq.message.CjdTenderMessage;

public interface IWeekTenderService {
	int deleteByPrimaryKey(Integer id);

	int insertSelective(WeekTender record);

	WeekTender selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(WeekTender record);

	List<WeekTender> selectWeekTenderListPage(PageSearch pageSearch);
	
	/**
	 * 周盈宝投资辅助业务
	 * @param vo 用户投资结果对象
	 * 2015年1月5日
	 * liuyijun
	 */
	void weekTenderAssist(UserTenderActionResultVO vo);
	
	HoldingAssetsVO selectHoldingAssetsByUserId(Integer userId);
	
	List<WeekTender> selectByUserId(PageSearch pageSearch);
	
	List<WeekTenderVO> selectWeekTenderByWeekIdAndRepaymentTimeListPage(PageSearch pageSearch);
	
	void sendUrlByTenderInfo(CjdTenderMessage ms);
	
	/**
	 * 财经道购买推送接口
	 * @param userId
	 * @param weekId
	 * @param tenderId
	 * @param tenderStatus
	 */
	void updateProductBuy(Integer userId, Integer weekId,
			Integer tenderId, Integer tenderStatus)throws Exception;
	
	
	List<BorrowTenderVO> selectAgreementPage(Integer weekId);
	
	
	
}