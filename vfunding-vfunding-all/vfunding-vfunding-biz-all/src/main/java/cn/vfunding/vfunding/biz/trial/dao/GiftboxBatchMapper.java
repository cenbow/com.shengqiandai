package cn.vfunding.vfunding.biz.trial.dao;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.trial.model.GiftboxBatch;

public interface GiftboxBatchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GiftboxBatch record);

    int insertSelective(GiftboxBatch record);

    GiftboxBatch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GiftboxBatch record);

    int updateByPrimaryKey(GiftboxBatch record);
    
    List<GiftboxBatch> selectTiralGiftboxListPage(PageSearch pageSearch);
}