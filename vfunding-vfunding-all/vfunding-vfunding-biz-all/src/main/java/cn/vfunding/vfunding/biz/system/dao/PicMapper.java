package cn.vfunding.vfunding.biz.system.dao;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.system.model.Pic;

public interface PicMapper {
    int deleteByPrimaryKey(String pickey);

    int insert(Pic record);

    int insertSelective(Pic record);

    Pic selectByPrimaryKey(String pickey);

    int updateByPrimaryKeySelective(Pic record);

    int updateByPrimaryKey(Pic record);
    
    List<Pic> selectListPage(PageSearch pageSearch);
    
    List<Pic> selectAll();
}