package cn.vfunding.vfunding.biz.inviteCode.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;

import cn.vfunding.vfunding.biz.inviteCode.model.InviteCode;

public interface IInviteCodeService {
	int deleteByPrimaryKey(Integer id);


    int insertSelective(InviteCode record);

    InviteCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InviteCode record);
    
    InviteCode selectByUserId(Integer userId);
    
    InviteCode selectByCode(String inviteCode);
    
    /**
     * 
     * <p>查询 第几位索引状态为  status(0,1)的所有</p>
     *
     * wang.zeyan 2015年4月24日
     * @param dialogStatusIndex
     * @param status
     * @return
     */
    List<InviteCode> selectByStatus(Integer dialogStatusIndex, boolean status);
    /**
     * 
     * 获取getDialogStatus，index 根据 DialogStatusIndex.java
     * 
     * wang.zeyan 2015年3月4日
     * @param dialogStatusIndex
     * @return
     */
    int getDialogStatus(Integer userId , Integer dialogStatusIndex);
    
    /**
     * 根据UserId 更新dialogStatus的值,index 根据 DialogStatusIndex.java
     * 
     * wang.zeyan 2015年3月4日
     * @param userId
     * @param dialogStatusIndex
     * @return
     */
    int updateDialogStatusByPrimaryKey(Integer userId , Integer dialogStatusIndex);
    
    /**
     * 
     * wang.zeyan 2015年3月4日
     * @param userId
     * @param dialogStatusIndex
     * @return
     */
    Map<String ,Object> getDialogStatusMap(Integer userId ,Integer dialogStatusIndex);
    
    /**
     * 是否已经弹出过对话框
     * wang.zeyan 2015年3月4日
     * @param userId
     * @param dialogStatusIndex
     * @return
     */
    boolean isLeapDialog(Integer userId ,Integer dialogStatusIndex);
}
