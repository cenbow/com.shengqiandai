package cn.vfunding.vfunding.biz.trial.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.trial.model.GiftboxBatch;

public interface IGiftboxBatchService {
	int deleteByPrimaryKey(Integer id);

    int insertSelective(GiftboxBatch record);

    GiftboxBatch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GiftboxBatch record);
    
    Json importExcelAndBatch(GiftboxBatch giftboxBatch,ArrayList<ArrayList<String>> is,String fileName);
    
    List<GiftboxBatch> selectTiralGiftboxListPage(PageSearch pageSearch);
    
    void tiralGiftbox(GiftboxBatch record);
}
