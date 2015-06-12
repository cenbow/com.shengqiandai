package cn.vfunding.vfunding.biz.message.service;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.message.model.GiftboxMessage;
import cn.vfunding.vfunding.biz.message.model.GiftotherMessage;

public interface IGiftotherMessageService {
    int deleteByPrimaryKey(Integer id);

    int insert(GiftotherMessage record);

    int insertSelective(GiftotherMessage record);

    GiftotherMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GiftotherMessage record);

    int updateByPrimaryKey(GiftotherMessage record);
    
    Integer selectUnLookCountByUserId(Integer userId);
    
    List<GiftotherMessage> selectGiftboxMessageListPage(PageSearch pageSearch);
    
    int updateGiftIsLook(GiftotherMessage record);
}