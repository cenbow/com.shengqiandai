package cn.p2p.p2p.biz.current.dao;

import java.util.List;

import cn.p2p.p2p.biz.current.model.Current;
import cn.vfunding.common.framework.utils.page.PageSearch;

public interface CurrentMapper {
    int deleteByPrimaryKey(Integer currentId);

    int insert(Current record);

    int insertSelective(Current record);

    Current selectByPrimaryKey(Integer currentId);

    int updateByPrimaryKeySelective(Current record);

    int updateByPrimaryKey(Current record);
    
    List<Current> selectCurrentListPage(PageSearch pageSearch);
    
    List<Current> selectCurrentListBmsPage(PageSearch pageSearch);
}