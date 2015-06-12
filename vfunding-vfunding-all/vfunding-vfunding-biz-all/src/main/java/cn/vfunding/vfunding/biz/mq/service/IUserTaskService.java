package cn.vfunding.vfunding.biz.mq.service;


import cn.vfunding.vfunding.biz.system.model.UserTask;

public interface IUserTaskService {
	
	/**
	 * 完成任务
	 * @param userId 用户编号
	 * @param taskId 活动编号
	 */
	void insertUserTaskInfo(Integer userId, Integer taskId);

	UserTask selectTaskByIdAndTaskId(Integer userId, Integer taskId);
}
