package cn.vfunding.vfunding.biz.trial.service;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.trial.model.GiftboxTemplate;

public interface IGiftboxTemplateService {
	int deleteByPrimaryKey(Integer id);


    int insertSelective(GiftboxTemplate record);

    GiftboxTemplate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GiftboxTemplate record);
    
    List<String> selectTemplateParams(Integer templateId);
    
    List<GiftboxTemplate> selectByStatus(Integer status);
    
    List<GiftboxTemplate> selectAll(PageSearch pageSearch);
}
