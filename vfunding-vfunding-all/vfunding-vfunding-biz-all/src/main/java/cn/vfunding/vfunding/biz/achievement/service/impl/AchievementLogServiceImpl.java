package cn.vfunding.vfunding.biz.achievement.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.achievement.dao.AchievementLogMapper;
import cn.vfunding.vfunding.biz.achievement.dao.AchievementMapper;
import cn.vfunding.vfunding.biz.achievement.model.Achievement;
import cn.vfunding.vfunding.biz.achievement.model.AchievementLog;
import cn.vfunding.vfunding.biz.achievement.service.IAchievementLogService;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowTenderMapper;
import cn.vfunding.vfunding.biz.hikes.dao.HikesCardMapper;
import cn.vfunding.vfunding.biz.hikes.model.HikesCard;
import cn.vfunding.vfunding.biz.message.dao.HikesMessageMapper;
import cn.vfunding.vfunding.biz.message.model.HikesMessage;

@Service("achievementLogService")
public class AchievementLogServiceImpl implements IAchievementLogService {

	@Autowired
	private AchievementMapper achievementMapper;
	
	@Autowired
	private AchievementLogMapper achievementLogMapper;
	
	@Autowired
	private HikesMessageMapper hikesMessageMapper;
	
	@Autowired
	private HikesCardMapper hikesCardMapper;
	
	@Autowired
	private BorrowTenderMapper tenderMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return achievementLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(AchievementLog record) {
		return achievementLogMapper.insert(record);
	}

	@Override
	public int insertSelective(AchievementLog record) {
		return achievementLogMapper.insertSelective(record);
	}

	@Override
	public AchievementLog selectByPrimaryKey(Integer id) {
		return achievementLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(AchievementLog record) {
		return achievementLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AchievementLog record) {
		return achievementLogMapper.updateByPrimaryKey(record);
	}
	
	@Override
	public AchievementLog selectByAchievementIdAndUserId(Integer achievementId,Integer userId) {
		return achievementLogMapper.selectByAchievementIdAndUserId(achievementId, userId); 
	}

	@Override
	public int createAchievementLog(Integer achievementId, Integer userId) {
		AchievementLog al = new AchievementLog();
		al.setAchievementId(achievementId);
		al.setUserId(userId);
		al.setStatus(0);
		al.setAddtime(new Date());
		return insertSelective(al);
	}

	@Override
	public int pickAchievementLog(Integer achievementId, Integer userId) {
		int result = 0 ;
		AchievementLog al = selectByAchievementIdAndUserId(achievementId, userId);
		if(EmptyUtil.isNotEmpty(al) && al.getId()>0 && al.getStatus()!=null && !al.getStatus().equals(1) && al.getUserId().equals(userId)){
			Achievement achievement = achievementMapper.selectByPrimaryKey(achievementId);
			if(achievement.getAward().equals("hikes")){
				HikesCard hc = this.hikesCardMapper.selectByPrimaryKey(userId);
				if(EmptyUtil.isEmpty(hc)){
					hc.setUserId(userId);
					hc.setNoUseRate(new BigDecimal(0));
					hc.setUseRate(achievement.getAwardValue());
					result = hikesCardMapper.insertSelective(hc);
				}else{
					if(hc.getUseRate()==null){
						hc.setUseRate(new BigDecimal(0));
					}
					hc.setUseRate(hc.getUseRate().add(achievement.getAwardValue()));
					result = hikesCardMapper.updateByPrimaryKeySelective(hc);
				}
				if(result==0){
					throw new RuntimeException("更新失败");
				}
				BigDecimal AprilTenderTotal = this.tenderMapper.myTotalTenderAccountForApril(userId);
				HikesMessage hm = new HikesMessage();
				hm.setReceiveUser(userId);
				hm.setTitle("小主，加息连连看加息奖励来啦！");
				hm.setContent("您本次活动完成累计投资金额"+achievement.getCondition().toString()+"元,本次获得加息"+achievement.getAwardValue()+"%"
						+ ",本次活动累计获得加息"+(sumHikesForApril(userId)).add(achievement.getAwardValue())+"%。获得之后可即刻投资使用,投资加息将于满标审核后以现金红包（可再次用于投资或者提现）的形式统一发放到礼品盒。");
				hm.setRate(achievement.getAwardValue());
				hm.setAddtime(new Date());
				result = hikesMessageMapper.insertSelective(hm);
				if(result==0){
					throw new RuntimeException("更新失败");
				}
				al.setStatus(1);
				result = updateByPrimaryKeySelective(al);
				if(result==0){
					throw new RuntimeException("更新失败");
				}
			}						
		}
		return result;
	}

	@Override
	public BigDecimal sumHikesForApril(Integer userId) {
		return achievementLogMapper.sumHikesForApril(userId);
	}

}