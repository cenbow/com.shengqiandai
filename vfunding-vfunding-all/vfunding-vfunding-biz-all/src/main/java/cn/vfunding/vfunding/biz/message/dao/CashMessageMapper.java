package cn.vfunding.vfunding.biz.message.dao;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.message.model.CashMessage;

public interface CashMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CashMessage record);

    int insertSelective(CashMessage record);

    CashMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CashMessage record);

    int updateByPrimaryKey(CashMessage record);
    
    List<CashMessage> selectCashMessageListPage(PageSearch pageSearch);
    
    List<CashMessage> selectUnLookByUserId(Integer userId);
}