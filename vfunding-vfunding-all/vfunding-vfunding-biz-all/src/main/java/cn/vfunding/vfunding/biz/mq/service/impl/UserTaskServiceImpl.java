package cn.vfunding.vfunding.biz.mq.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowTenderMapper;
import cn.vfunding.vfunding.biz.message.dao.CashMessageMapper;
import cn.vfunding.vfunding.biz.message.model.CashMessage;
import cn.vfunding.vfunding.biz.message.service.IGiftboxMessageService;
import cn.vfunding.vfunding.biz.mq.service.IUserTaskService;
import cn.vfunding.vfunding.biz.system.dao.TaskMapper;
import cn.vfunding.vfunding.biz.system.dao.UserTaskMapper;
import cn.vfunding.vfunding.biz.system.model.Task;
import cn.vfunding.vfunding.biz.system.model.UserTask;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;

@Service
public class UserTaskServiceImpl implements IUserTaskService {

	@Autowired
	private UserTaskMapper userTaskMapper;

	@Autowired
	private BorrowTenderMapper borrowTenderMapper;

	@Autowired
	private TaskMapper taskMapper;

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private IGiftboxMessageService giftboxMessageService;
	
	@Autowired
	private CashMessageMapper cashMessageMapper;
	
	/**
	 * 完成任务
	 */
	@Override
	public void insertUserTaskInfo(Integer userId, Integer taskId) {
		UserTask search = new UserTask();
		search.setUserId(userId);
		search.setTaskId(taskId);
		UserTask ut = userTaskMapper.selectByUserIdAndTaskId(search);
		//用户没有完成过任务
		if (EmptyUtil.isEmpty(ut)) {
			Task task = taskMapper.selectByPrimaryKey(taskId); //vf_task 实体
			if (EmptyUtil.isNotEmpty(task)) {
				if (DateUtil.checkLess(new Date(), task.getEndDate())) { // 有效期检测
					Integer hongbao = task.getHongbao();
					if (taskId.equals(2)) { // 推荐得红包
						this.addTaskInfo(userId, taskId);
						this.updateUserHongbao(userId, task, 1);
					} else if(taskId.equals(4)){ //第一次投资
						this.addTaskInfo(userId, taskId);
						this.addGiftboxMessage(userId, task);
					} else if(taskId.equals(6)){ //手机第一次投资>2000元
						this.addTaskInfo(userId, taskId);
						this.addGiftboxMessage(userId, task);
					} else { //其他活动
						
						this.addTaskInfo(userId, taskId);
						this.updateUserHongbao(userId, task, 0);
					}
				}
			}
		}
	}
	
	/**
	 * 用户领礼品盒现金红包
	 * @param userId
	 * @param task
	 */
	private void addGiftboxMessage(Integer userId, Task task){
		String title="";
		String content="";;
		if(task.getId().equals(4)){
			title="微积金开门红包";
			content="您完成了首次投资任务，微积金送您5元现金红包。生效后点击“使用”即可充值到您的现金账户。";
		}else if(task.getId().equals(6)){
			title="APP首投红包";
			content="您完成了APP首次投资金额超过2000元的任务。微积金送您5元现金红包。生效后点击“使用”即可充值到您的现金账户。";
		}
		this.giftboxMessageService.insertGiftboxMessage(title,content
				,EmptyUtil.isEmpty(task.getHongbao())?new BigDecimal(0):new BigDecimal(task.getHongbao())
				,userId,new Date(),null, 0);
	}
	
	/**
	 * 用户完成任务,数据库添加记录
	 * @param userId
	 * @param taskId
	 */
	private void addTaskInfo(Integer userId, Integer taskId) {
		UserTask userTask = new UserTask();
		userTask.setUserId(userId);
		userTask.setTaskId(taskId);
		userTask.setStatus(1);
		userTask.setCompleteDate(new Date());
		this.userTaskMapper.insertSelective(userTask);
	}
	
	/**
	 * 用户领提现抵扣券,更新rd_user表的hongbao字段
	 * @param userId
	 * @param task
	 * @param type
	 */
	private void updateUserHongbao(Integer userId, Task task, Integer type) {
		if (type == 0) {
			// 给user添加红包
			User oldUser = this.userMapper.selectByPrimaryKey(userId);
			UserWithBLOBs user = new UserWithBLOBs();
			user.setUserId(userId);
			BigDecimal hb = oldUser.getHongbao();
			if (EmptyUtil.isEmpty(hb)) {
				hb = new BigDecimal(0);
			}
			user.setHongbao(hb.add(new BigDecimal(task.getHongbao())));
			this.userMapper.updateByPrimaryKeySelective(user);
			this.addCashMessage(user.getUserId(), task);
		} else if (type == 1) {
			User userInfo = this.userMapper.selectByPrimaryKey(userId);
			// 获取user的介绍人信息
			if (EmptyUtil.isNotEmpty(userInfo) && EmptyUtil.isNotEmpty(userInfo.getInviteUserid())) {
				Integer inviteUserid = Integer.parseInt(userInfo.getInviteUserid());
				User inviteUserInfo = this.userMapper.selectByPrimaryKey(inviteUserid);
				// 更新介绍人的红包数
				UserWithBLOBs user = new UserWithBLOBs();
				user.setUserId(inviteUserid);
				BigDecimal hb = inviteUserInfo.getHongbao();
				if (EmptyUtil.isEmpty(hb)) {
					hb = new BigDecimal(0);
				}
				user.setHongbao(hb.add(new BigDecimal(task.getHongbao())));
				this.userMapper.updateByPrimaryKeySelective(user);
				this.addCashMessage(user.getUserId(), task);
			}
		}
		
		
	}
	
	/**
	 * 用户领提现抵扣券,插入vf_cash_message表
	 * @param userId
	 * @param task
	 */
	private void addCashMessage(Integer userId, Task task) {
		CashMessage cm = new CashMessage();
		cm.setIsLook(0);
		cm.setTitle(task.getName());
		cm.setContent(task.getRemark());
		cm.setMoney(new BigDecimal(task.getHongbao()));
		cm.setSendUser(0);
		cm.setReceiveUser(userId);
		cm.setAddtime(new Date());
		cm.setAddip("127.0.0.1");
		this.cashMessageMapper.insertSelective(cm);
	}
	
	@Override
	public UserTask selectTaskByIdAndTaskId(Integer userId, Integer taskId) {
		UserTask search = new UserTask();
		search.setUserId(userId);
		search.setTaskId(taskId);
		UserTask ut = userTaskMapper.selectByUserIdAndTaskId(search);
		return ut;
	}
}
