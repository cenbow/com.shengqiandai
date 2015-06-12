package cn.vfunding.vfunding.biz.sina.service;

import java.math.BigDecimal;
import java.util.List;

import cn.vfunding.vfunding.biz.sina.model.SinaSettlement;

public interface ISinaSettlementService {
    int deleteByPrimaryKey(Integer id);

    //int insert(SinaSettlement record);

    int insertSelective(SinaSettlement record);

    SinaSettlement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SinaSettlement record);

    //int updateByPrimaryKey(SinaSettlement record);
    
    /**
     * 需要同步新浪存钱罐收益的userIds
     * @return
     * @author louchen 2015-03-25
     */
    List<Integer> needSyncBonusUserIds();
    
    /**
     * 创建个人结算数据
     * @param userId
     * @param balance_str
     * @param availableBalance_str
     * @param bonus_str
     * @return
     * @author louchen 2015-3-25
     */
    SinaSettlement createBaseData(Integer userId,String balance_str,String availableBalance_str,String bonus_str);
    
    /**
     * 创建个人结算数据(status=1)
     * @param userId
     * @return
     */
    SinaSettlement createErrorBaseData(Integer userId);
    
    /**
     * 需要同步新浪存钱罐收益的实体
     * @param syncBonusStatus
     * @return
     */
    List<SinaSettlement> selectNeedSyncDataBySyncStatus();
    
    /**
     * 用户新浪存钱罐一共收益
     * @return
     */
    BigDecimal selectSinaBonusHistoryTotalByUserId(Integer userId);
    
    /**
     * 保存用户存钱罐收益
     * @param entity
     * @return
     */
    int saveSinaBonus(SinaSettlement entity);
    
}