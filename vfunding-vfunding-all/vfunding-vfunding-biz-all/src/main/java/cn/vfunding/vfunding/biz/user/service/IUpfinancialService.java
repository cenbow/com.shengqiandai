package cn.vfunding.vfunding.biz.user.service;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.user.model.Upfinancial;


public interface IUpfinancialService {

	Upfinancial selectByPrimaryKey(Integer id);

	List<Upfinancial> selectByListPage(PageSearch pageSearch);

	int updateApplyFinancial(String remark, Integer status, Upfinancial f);
	
}
