package cn.vfunding.vfunding.biz.trial.dao;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.trial.model.GiftboxTemplate;

public interface GiftboxTemplateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GiftboxTemplate record);

    int insertSelective(GiftboxTemplate record);

    GiftboxTemplate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GiftboxTemplate record);

    int updateByPrimaryKey(GiftboxTemplate record);
    
    List<GiftboxTemplate> selectByStatus(Integer status);
    
    List<GiftboxTemplate> selectAll(PageSearch pageSearch);
    
    
}