package cn.p2p.p2p.biz.current.service;

import java.util.List;

import cn.p2p.p2p.biz.current.model.Current;
import cn.p2p.p2p.biz.current.model.CurrentRule;
import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.utils.page.PageSearch;


public interface ICurrentService {
    int deleteByPrimaryKey(Integer currentId);

    int insertSelective(Current record);

    Current selectByPrimaryKey(Integer currentId);

    int updateByPrimaryKeySelective(Current record);

    /**
     * 添加活期标的
     * @param current
     * @param currentRule
     * @return
     */
    Json  insertCurrent(Current current,CurrentRule currentRule);
    
    /**
     * 查询活期标的列表
     * @param pageSearch
     * @return
     */
	 List<Current> selectCurrentPageList(PageSearch pageSearch);
	 
	 /**
	  * 查询活期标的列表后台
	  * @param pageSearch
	  * @return
	  *
	  * 2015年6月5日
	  * lijianwei
	  */
	 List<Current> selectCurrentListBmsPage(PageSearch pageSearch);
	 /**
	  * 审核标的
	  * @param current
	  * @param currentRule
	  * @return
	  */
	 boolean trialCurrent(Current current,CurrentRule currentRule);
}