package cn.vfunding.vfunding.biz.report.dao;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.report.model.OperateReportdays;

public interface OperateReportdaysMapper {
	int insert(OperateReportdays record);

	int insertSelective(OperateReportdays record);

	List<OperateReportdays> selectListPage(PageSearch pageSearch);

	List<OperateReportdays> selectOperateReportdays(OperateReportdays or);

	void callProcedure();

}