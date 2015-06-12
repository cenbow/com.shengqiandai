package cn.vfunding.vfunding.biz.message.service;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.message.model.CashMessage;
/**
 * 提现抵扣券消息
 * @author jianwei
 *
 */
public interface ICashMessageService {
	int deleteByPrimaryKey(Integer id);

    int insertSelective(CashMessage record);

    CashMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CashMessage record);

    List<CashMessage> selectCashMessageListPage(PageSearch pageSearch);
    
    Integer selectUnLookCountByUserId(Integer userId);
}
