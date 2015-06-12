package cn.vfunding.vfunding.biz.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.report.dao.OperateReportdaysMapper;
import cn.vfunding.vfunding.biz.report.dao.OperateReportdaysTwoMapper;
import cn.vfunding.vfunding.biz.report.model.OperateReportdays;
import cn.vfunding.vfunding.biz.report.model.OperateReportdaysTwo;
import cn.vfunding.vfunding.biz.report.service.IOperateReportdaysService;

@Service("operateReportdaysService")
public class OperateReportdaysServiceImpl implements IOperateReportdaysService {

	@Autowired
	private OperateReportdaysMapper orMapper;

	@Autowired
	private OperateReportdaysTwoMapper orTwoMapper;

	public List<OperateReportdays> selectListPage(PageSearch pageSearch) {
		// TODO Auto-generated method stub
		return this.orMapper.selectListPage(pageSearch);
	}

	public List<OperateReportdays> selectOperateReportdays(OperateReportdays or) {
		// TODO Auto-generated method stub
		return this.orMapper.selectOperateReportdays(or);
	}

	public void callProcedure() {
		this.orMapper.callProcedure();

	}

	@Override
	public List<OperateReportdaysTwo> selectListPageTwo(PageSearch pageSearch) {
		// TODO Auto-generated method stub
		return this.orTwoMapper.selectListPage(pageSearch);
	}

	@Override
	public List<OperateReportdaysTwo> selectOperateReportdaysTwo(
			OperateReportdaysTwo or) {
		// TODO Auto-generated method stub
		return this.orTwoMapper.selectOperateReportdays(or);
	}

	@Override
	public void callProcedureTwo() {
		this.orTwoMapper.callProcedureTwo();
	}

}