package cn.vfunding.vfunding.biz.report.service;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.report.model.FinanceReportData;

public interface IFinanceReportDataService {
	List<FinanceReportData> selectTenderListPage(PageSearch pageSearch);

	List<FinanceReportData> selectCashListPage(PageSearch pageSearch);

	List<FinanceReportData> selectCash(FinanceReportData frd);

	List<FinanceReportData> selectTender(FinanceReportData frd);

	List<FinanceReportData> selectUserVipListPage(PageSearch pageSearch);

	List<FinanceReportData> selectUserVip(FinanceReportData frd);
	
	
	FinanceReportData selectSumCashFee(FinanceReportData frd);

	FinanceReportData selectSumTenderFee(FinanceReportData frd);

	FinanceReportData selectSumVipMoney(FinanceReportData frd);

	List<FinanceReportData> selectUserHongbaoListPage(PageSearch pageSearch);

	List<FinanceReportData> selectUserHongbao();

	FinanceReportData selectSumUserHongbao();

	List<FinanceReportData> selectCashRedEnvelopeListPage(PageSearch pageSearch);

	FinanceReportData selectSumCashRedEnvelope(FinanceReportData frd);

	FinanceReportData selectSumUsedRedEnvelope(FinanceReportData frd);

	List<FinanceReportData> selectCashRedEnvelope(FinanceReportData frd);
}