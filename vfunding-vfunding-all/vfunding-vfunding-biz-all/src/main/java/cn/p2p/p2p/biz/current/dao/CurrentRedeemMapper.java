package cn.p2p.p2p.biz.current.dao;

import java.math.BigDecimal;
import java.util.List;

import cn.p2p.p2p.biz.current.model.CurrentRedeem;
import cn.vfunding.common.framework.utils.page.PageSearch;

public interface CurrentRedeemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CurrentRedeem record);

    int insertSelective(CurrentRedeem record);

    CurrentRedeem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CurrentRedeem record);

    int updateByPrimaryKey(CurrentRedeem record);
    
    BigDecimal selectByUserIdAndDate(Integer userId);
    
    List<CurrentRedeem> selectCurrentRedeemListPage(PageSearch pageSearch);
}