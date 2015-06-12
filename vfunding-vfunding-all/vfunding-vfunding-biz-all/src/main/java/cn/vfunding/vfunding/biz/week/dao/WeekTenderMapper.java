package cn.vfunding.vfunding.biz.week.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.common.vo.BorrowTenderVO;
import cn.vfunding.vfunding.biz.week.model.WeekTender;
import cn.vfunding.vfunding.biz.week.vo.HoldingAssetsVO;
import cn.vfunding.vfunding.biz.week.vo.WeekTenderVO;

public interface WeekTenderMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(WeekTender record);

	int insertSelective(WeekTender record);

	WeekTender selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(WeekTender record);

	int updateByPrimaryKey(WeekTender record);

	List<WeekTender> selectWeekTenderListPage(PageSearch pageSearch);

	int updateStatusByDate(String date);

	List<WeekTender> selectByDateGroupWeekId(String date);

	HoldingAssetsVO selectHoldingAssetsByUserId(Integer userId);

	List<WeekTender> selectByUserId(PageSearch pageSearch);

	List<WeekTenderVO> selectWeekTenderByWeekIdAndRepaymentTimeListPage(
			PageSearch pageSearch);

	List<WeekTender> selectByParameters(WeekTender wt);

	List<WeekTender> selectByAddTime(String date);
	
	List<BorrowTenderVO> selectAgreementPage(@Param("weekId") Integer weekId);
}