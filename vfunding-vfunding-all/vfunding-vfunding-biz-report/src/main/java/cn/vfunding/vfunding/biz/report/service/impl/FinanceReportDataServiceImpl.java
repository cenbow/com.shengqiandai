package cn.vfunding.vfunding.biz.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.report.dao.FinanceReportDataMapper;
import cn.vfunding.vfunding.biz.report.model.FinanceReportData;
import cn.vfunding.vfunding.biz.report.service.IFinanceReportDataService;

@Service
public class FinanceReportDataServiceImpl implements IFinanceReportDataService {

	@Autowired
	private FinanceReportDataMapper frdMapper;

	public List<FinanceReportData> selectCashListPage(PageSearch pageSearch) {
		// TODO Auto-generated method stub
		return this.frdMapper.selectCashListPage(pageSearch);
	}

	public List<FinanceReportData> selectTenderListPage(PageSearch pageSearch) {
		// TODO Auto-generated method stub
		return this.frdMapper.selectTenderListPage(pageSearch);
	}

	public List<FinanceReportData> selectCash(FinanceReportData frd) {
		// TODO Auto-generated method stub
		return this.frdMapper.selectCash(frd);
	}

	public List<FinanceReportData> selectTender(FinanceReportData frd) {
		// TODO Auto-generated method stub
		return this.frdMapper.selectTender(frd);
	}

	public List<FinanceReportData> selectUserVipListPage(PageSearch pageSearch) {
		// TODO Auto-generated method stub
		return this.frdMapper.selectUserVipListPage(pageSearch);
	}

	public List<FinanceReportData> selectUserVip(FinanceReportData frd) {
		// TODO Auto-generated method stub
		return this.frdMapper.selectUserVip(frd);
	}

	@Override
	public FinanceReportData selectSumCashFee(FinanceReportData frd) {
		// TODO Auto-generated method stub
		return this.frdMapper.selectSumCashFee(frd);
	}

	@Override
	public FinanceReportData selectSumTenderFee(FinanceReportData frd) {
		// TODO Auto-generated method stub
		return this.frdMapper.selectSumTenderFee(frd);
	}

	@Override
	public FinanceReportData selectSumVipMoney(FinanceReportData frd) {
		// TODO Auto-generated method stub
		return this.frdMapper.selectSumVipMoney(frd);
	}

	@Override
	public List<FinanceReportData> selectUserHongbaoListPage(PageSearch pageSearch) {
		// TODO Auto-generated method stub
		return this.frdMapper.selectUserHongbaoListPage(pageSearch);
	}

	@Override
	public List<FinanceReportData> selectUserHongbao() {
		// TODO Auto-generated method stub
		return this.frdMapper.selectUserHongbao();
	}

	@Override
	public FinanceReportData selectSumUserHongbao() {
		// TODO Auto-generated method stub
		return this.frdMapper.selectSumUserHongbao();
	}

	@Override
	public List<FinanceReportData> selectCashRedEnvelopeListPage(
			PageSearch pageSearch) {

		return this.frdMapper.selectCashRedEnvelopeListPage(pageSearch);
	}

	@Override
	public FinanceReportData selectSumCashRedEnvelope(FinanceReportData frd) {

		return this.frdMapper.selectSumCashRedEnvelope(frd);
	}

	@Override
	public FinanceReportData selectSumUsedRedEnvelope(FinanceReportData frd) {

		return this.frdMapper.selectSumUsedRedEnvelope(frd);
	}

	@Override
	public List<FinanceReportData> selectCashRedEnvelope(
			FinanceReportData frd) {

		return this.frdMapper.selectCashRedEnvelope(frd);
	}

}
