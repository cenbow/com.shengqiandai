package cn.vfunding.vfunding.biz.message.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.message.model.HikesMessage;

public interface HikesMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HikesMessage record);

    int insertSelective(HikesMessage record);

    HikesMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HikesMessage record);

    int updateByPrimaryKey(HikesMessage record);
    
    List<HikesMessage> selectHikesMessageListPage(PageSearch pageSearch);
    
    HikesMessage selectJiuXianHikesFristTenderByUserId(Integer userId);
    
    List<HikesMessage> selectUnLookByUserId(Integer userId);
    
    List<HikesMessage> selectByUserIdAndTitle(@Param("userId") Integer userId,@Param("title") String title);
}