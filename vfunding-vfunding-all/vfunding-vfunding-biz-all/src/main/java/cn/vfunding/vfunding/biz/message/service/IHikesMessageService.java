package cn.vfunding.vfunding.biz.message.service;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.message.model.HikesMessage;
/**
 * 加息全消息
 * @author jianwei
 *
 */
public interface IHikesMessageService {
	int deleteByPrimaryKey(Integer id);

    int insertSelective(HikesMessage record);

    HikesMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HikesMessage record);

    List<HikesMessage> selectHikesMessageListPage(PageSearch pageSearch);
    
    Integer selectUnLookCountByUserId(Integer userId);
}
