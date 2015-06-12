package cn.vfunding.vfunding.biz.user.service;

import java.util.List;

import cn.vfunding.common.framework.lock.NeedLock;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.common.vo.FeelVO;
import cn.vfunding.vfunding.biz.system.model.Feel;

public interface IFeelService {	

	int insert(Feel record);

	Feel selectByPrimaryKey(Integer id);
    
	List<Feel> selectByUserId(Integer userId);
    
	Feel selectByStatus(Integer status);
	
	Feel selectByCode(String code);

    int updateByPrimaryKey(Feel record);
    
    int updateByPrimaryKeySelective(Feel record);
    
    List<Feel> selectByNoStatus(Feel feel);
    
    List<Feel> selectAll();

    @NeedLock
	String updateBatchUserId(Integer startNo,Integer endNo,String username,Integer type);

	List<FeelVO> selectFeelsListPage(PageSearch pageSearch);
}
