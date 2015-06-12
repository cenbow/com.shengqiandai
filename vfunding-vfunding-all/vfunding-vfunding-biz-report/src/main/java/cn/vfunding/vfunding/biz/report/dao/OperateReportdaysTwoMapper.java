package cn.vfunding.vfunding.biz.report.dao;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.report.model.OperateReportdaysTwo;

public interface OperateReportdaysTwoMapper {
	List<OperateReportdaysTwo> selectListPage(PageSearch pageSearch);

	List<OperateReportdaysTwo> selectOperateReportdays(OperateReportdaysTwo or);

	void callProcedureTwo();
}