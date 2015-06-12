package cn.vfunding.vfunding.biz.report.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.report.model.FinanceReportData;

@Repository
public interface FinanceReportDataMapper {
	List<FinanceReportData> selectTenderListPage(PageSearch pageSearch);

	List<FinanceReportData> selectCashListPage(PageSearch pageSearch);

	List<FinanceReportData> selectUserVipListPage(PageSearch pageSearch);

	List<FinanceReportData> selectCash(FinanceReportData frd);

	List<FinanceReportData> selectTender(FinanceReportData frd);

	List<FinanceReportData> selectUserVip(FinanceReportData frd);

	FinanceReportData selectSumCashFee(FinanceReportData frd);

	FinanceReportData selectSumTenderFee(FinanceReportData frd);

	FinanceReportData selectSumVipMoney(FinanceReportData frd);

	List<FinanceReportData> selectUserHongbaoListPage(PageSearch pageSearch);

	List<FinanceReportData> selectUserHongbao();

	FinanceReportData selectSumUserHongbao();

	/**
	 * 
	 * <p>查询用户现金红包</p>
	 * 
	 * <pre>
	 * 		status=0 未使用红包
	 * 		status=1 已使用红包
	 * 		status=2 已过期红包
	 * </pre>
	 * 
	 * wang.zeyan 2015年4月10日
	 * @param pageSearch
	 * @return
	 */
	List<FinanceReportData> selectCashRedEnvelopeListPage(PageSearch pageSearch);

	/**
	 * 
	 * <p>查询现金红包总和</p>
	 * <pre>
	 * 		status=0 未使用红包
	 * 		status=1 已使用红包
	 * 		status=2 已过期红包
	 * </pre>
	 *
	 * wang.zeyan 2015年4月10日
	 * @return
	 */
	FinanceReportData selectSumCashRedEnvelope(FinanceReportData frd);
	
	/**
	 * 
	 * <p>已使用红包总和</p>
	 *
	 * wang.zeyan 2015年4月10日
	 * @param frd (startTime , endTime)
	 * @return
	 */
	FinanceReportData selectSumUsedRedEnvelope(FinanceReportData frd);

	/**
	 * 
	 * <p>查询用户所有现金红包</p>
	 *
	 * wang.zeyan 2015年4月13日
	 * @param frd
	 * @return
	 */
	List<FinanceReportData> selectCashRedEnvelope(FinanceReportData frd);
}