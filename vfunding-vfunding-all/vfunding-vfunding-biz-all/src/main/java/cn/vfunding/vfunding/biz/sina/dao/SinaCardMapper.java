package cn.vfunding.vfunding.biz.sina.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.sina.model.SinaCard;

public interface SinaCardMapper {
    int deleteByPrimaryKey(String sinaCard);

    int insert(SinaCard record);

    int insertSelective(SinaCard record);

    SinaCard selectByPrimaryKey(Integer scId);

    int updateByPrimaryKeySelective(SinaCard record);

    int updateByPrimaryKey(SinaCard record);
    
    List<SinaCard> selectSelective(SinaCard sc);
    
    List<SinaCard> selectByUserId(String userId);
    
    List<SinaCard> selectQuickPayByUserId(String userId);
    
    int updateQuickPayCardWeight(SinaCard record);
}