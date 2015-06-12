package cn.vfunding.vfunding.biz.report.service;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.report.model.OperateReportdays;
import cn.vfunding.vfunding.biz.report.model.OperateReportdaysTwo;

public interface IOperateReportdaysService {

	List<OperateReportdays> selectListPage(PageSearch pageSearch);

	List<OperateReportdays> selectOperateReportdays(OperateReportdays or);

	void callProcedure();

	List<OperateReportdaysTwo> selectListPageTwo(PageSearch pageSearch);

	List<OperateReportdaysTwo> selectOperateReportdaysTwo(
			OperateReportdaysTwo or);

	void callProcedureTwo();

}