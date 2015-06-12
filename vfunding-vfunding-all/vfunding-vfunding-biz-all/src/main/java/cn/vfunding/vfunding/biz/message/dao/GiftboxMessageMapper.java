package cn.vfunding.vfunding.biz.message.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.message.model.GiftboxMessage;
import cn.vfunding.vfunding.biz.message.model.SystemMessage;

public interface GiftboxMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GiftboxMessage record);

    int insertSelective(GiftboxMessage record);

    GiftboxMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GiftboxMessage record);

    int updateByPrimaryKey(GiftboxMessage record);
    
    List<GiftboxMessage> selectGiftboxMessageListPage(PageSearch pageSearch);
    
    List<GiftboxMessage> selectUnUseByUserId(Integer userId);
    
    List<GiftboxMessage> selectUnLookByUserId(Integer userId);
    
    List<GiftboxMessage> selectAllListPage(PageSearch pageSearch);
    
    String selectUseMoney(Map<String, Object> map);
    
    String selectNoUseMoney(Map<String, Object> map);
    
    List<GiftboxMessage> selectUseMoneyAny(Map<String, Object> map);
    
	/**
	 * 已用红包
	 * @param userId
	 * @return
	 */
	String selectHongbaoSumUsed(Integer userId);
	
	/**
	 * 过期红包
	 * @param userId
	 * @return
	 */
	String selectHongbaoSumExpire(Integer userId);
	
	List<GiftboxMessage> selectHongbaoExpireByStatus(Integer status);
	
	/**
     * 查询未使用返利红包总金额
     * @param userId
     * @return
     *
     * jianwei
     */
    BigDecimal selectInviteFeesByGiftboxSumMoney(Integer userId);
    /**
     * 查询将要过期的红包信息
     * @return
     */
    List<GiftboxMessage> selectByLose();
    
    /**
     * 按userId和title和status查询红包信息
     */
    List<GiftboxMessage> selectByUserIdAndTitleAndStatus(@Param("userId") Integer userId,@Param("title") String title,@Param("status") Integer status);
}