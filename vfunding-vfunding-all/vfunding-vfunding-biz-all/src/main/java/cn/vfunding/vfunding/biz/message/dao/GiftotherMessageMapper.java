package cn.vfunding.vfunding.biz.message.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.message.model.GiftotherMessage;

public interface GiftotherMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GiftotherMessage record);

    int insertSelective(GiftotherMessage record);

    GiftotherMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GiftotherMessage record);

    int updateByPrimaryKey(GiftotherMessage record);
    
    List<GiftotherMessage> selectUnLookByUserId(Integer userId);
    
    List<GiftotherMessage> selectGiftboxMessageListPage(PageSearch pageSearch);
    
    int selectJiuXianTopTenCountDay(@Param("start") String start,@Param("end") String end);
    
    int selectJiuXianTopTenCountDayByUserId(@Param("userId") Integer userId,@Param("start") String start,@Param("end") String end);
}