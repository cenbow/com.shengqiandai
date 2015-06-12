package cn.vfunding.vfunding.biz.activity.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.activity.dao.ActivityLotteryMapper;
import cn.vfunding.vfunding.biz.activity.model.ActivityLottery;
import cn.vfunding.vfunding.biz.activity.service.IActivityLotteryService;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowTenderMapper;
import cn.vfunding.vfunding.biz.message.dao.GiftotherMessageMapper;
import cn.vfunding.vfunding.biz.message.model.GiftotherMessage;

@Service("activityLotteryService")
public class ActivityLotteryServiceImpl implements IActivityLotteryService {
		
	@Autowired
	private ActivityLotteryMapper activityLotteryMapper;
	
	@Autowired
	private BorrowTenderMapper tenderMapper;
	
	@Autowired
	private GiftotherMessageMapper giftotherMessageMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return activityLotteryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ActivityLottery record) {
		return activityLotteryMapper.insert(record);
	}

	@Override
	public int insertSelective(ActivityLottery record) {
		return activityLotteryMapper.insertSelective(record);
	}

	@Override
	public ActivityLottery selectByPrimaryKey(Integer id) {
		return activityLotteryMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ActivityLottery record) {
		return activityLotteryMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ActivityLottery record) {
		return activityLotteryMapper.updateByPrimaryKey(record);
	}

	@Override
	public ActivityLottery selectByActivityAndUserId(String activity,
			Integer userId) {
		return activityLotteryMapper.selectByActivityAndUserId(activity, userId);
	}

	@Override
	public int hasAprilLottery(Integer userId) {
		ActivityLottery record = selectByActivityAndUserId("fifteen_april",userId);
		if(EmptyUtil.isNotEmpty(record)&&record.getId()>0){
			return 1;
		}else{
			return 0;
		}		
	}

	@Override
	public Boolean canPickAprilLottery(Integer userId) {
		BigDecimal AprilTenderTotal = this.tenderMapper.myTotalTenderAccountForApril(userId);
		if(AprilTenderTotal.compareTo(new BigDecimal(0)) > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public synchronized String loadAprilLottery(Integer userId) {
		String result="failed";
		if(canPickAprilLottery(userId)){
			if(hasAprilLottery(userId)==0){
				ActivityLottery al = activityLotteryMapper.selectEmpty();
				if(al!=null){
					al.setActivity("fifteen_april");
					al.setUserId(userId);
					updateByPrimaryKeySelective(al);
					result="success";
				}else{
					result = "彩票已领完";
				}				
			}else{
				result = "已领取";
			}
		}else{
			result = "没有达到领取条件";
		}
		return result;
	}

	@Override
	public synchronized String sendGiftOhterMessage(Integer userId) {
		String result="failed";
		ActivityLottery record = selectByActivityAndUserId("fifteen_april",userId);
		if(record!=null&&record.getTakeTime()==null){
			GiftotherMessage gm = new GiftotherMessage();
			gm.setSendUser(0);
			gm.setReceiveUser(userId);
			gm.setIsLook(0);
			gm.setType(0);
			gm.setTitle("小主，恭喜您获得千万财富梦想一个！");
			gm.setContent("您在微积金四月活动期间投资了理财产品，获得平安彩票一注！您的兑换码是："+record.getSn()+"。<br/><a href='http://caipiao.wanlitong.com/index.php?act=management&st=voucher_one&track=ljs' target='_blank' style='color:#00a0e9'>兑换网址</a>,您需要注册/登录之后，在线兑换抵扣券，在购买彩票支付页面选择消费抵用券抵扣。");
			gm.setAddtime(new Date());			
			int i = giftotherMessageMapper.insertSelective(gm);
			if(i>0){
				record.setTakeTime(new Date());
				updateByPrimaryKeySelective(record);
				result = "success";
			}
		}else{
			result = "已领取";
		}
		return result;
	}

}
