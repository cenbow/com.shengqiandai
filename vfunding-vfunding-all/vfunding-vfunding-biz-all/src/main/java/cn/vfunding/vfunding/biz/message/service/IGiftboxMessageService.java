package cn.vfunding.vfunding.biz.message.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.lock.NeedLock;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.message.model.GiftboxMessage;
import cn.vfunding.vfunding.common.interceptors.annotations.AfterAction;

public interface IGiftboxMessageService {
    int deleteByPrimaryKey(Integer id);

    int insert(GiftboxMessage record);

    int insertSelective(GiftboxMessage record);

    GiftboxMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GiftboxMessage record);

    int updateByPrimaryKey(GiftboxMessage record);
    
    List<GiftboxMessage> selectGiftboxMessageListPage(PageSearch pageSearch);
    
    Integer selectUnUseCountByUserId(Integer userId);
    
    Integer selectUnLookCountByUserId(Integer userId);
    
    int updateGiftIsLook(GiftboxMessage record);
    
    List<GiftboxMessage> selectAll(PageSearch pageSearch);

	void insertGiftboxMessage(String title, String content, BigDecimal money, Integer userId,
			Date taskTime, Integer tenderId,Integer type);
	
	String selectUseMoney(Map<String, Object> map);
	
	String selectNoUseMoney(Map<String, Object> map);
	
	List<GiftboxMessage> selectUseMoneyAny(Map<String, Object> map);
	
	/**
	 * 使用礼品红包
	 * @param gm
	 * @param loginUserId
	 * @return
	 * @throws Exception
	 */
	@NeedLock("/locks/gift")
	@AfterAction(actionName="giftbox")
	Json useGiftTrade(Integer giftId,Integer loginUserId) throws Exception;
		
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
     * 查询将要过期的红包信息
     * @return
     */
	List<GiftboxMessage> selectByLose();
}
