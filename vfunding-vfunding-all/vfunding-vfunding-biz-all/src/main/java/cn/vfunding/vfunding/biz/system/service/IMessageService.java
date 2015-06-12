package cn.vfunding.vfunding.biz.system.service;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.system.model.Message;

public interface IMessageService {

	int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKeyWithBLOBs(Message record);

    int updateByPrimaryKey(Message record);
    
    /**
     * 查询用户的未读的站内信数量 
     * @param receiveUser
     * @return
     * 2014年4月24日
     * liuyijun
     */
    Integer selectNoReadCount(Integer receiveUser);
    
    /**
     * 查询用户所有站内信总数
     * @param receiveUser
     * @return
     * 2014年8月21日
     * liuyijun
     */
    Integer selectAllCount(Integer receiveUser);
    
    /**
     * 查询用户站内信分页查询
     * @param receiveUser 
     * @return
     * 2014年4月24日
     * liuyijun
     */
    List<Message> selectMessageByUserListPage(PageSearch pageSearch);
    /**
     * 更新消息状态为已读
     * @param record
     * @return
     * 2014年5月7日
     * liuyijun
     */
    int updateReadPrimaryKey(Message record);
    /**
     * 更具IDS删除记录
     * @param ids
     * @return
     * 2014年8月22日
     * liuyijun
     */
    int deleteMessage(String ids);
    
    int deleteMessage(String[] ids);
    
}
