package cn.p2p.p2p.biz.current.dao;

import java.util.List;

import cn.p2p.p2p.biz.current.model.CurrentTender;
import cn.vfunding.common.framework.utils.page.PageSearch;

public interface CurrentTenderMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(CurrentTender record);

	int insertSelective(CurrentTender record);

	CurrentTender selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(CurrentTender record);

	int updateByPrimaryKey(CurrentTender record);

	List<CurrentTender> selectCurrentTenderListPage(PageSearch pageSearch);
}