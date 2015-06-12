package cn.vfunding.vfunding.biz.hikes.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.hikes.dao.HikesCardMapper;
import cn.vfunding.vfunding.biz.hikes.model.HikesCard;
import cn.vfunding.vfunding.biz.hikes.service.IHikesCardService;
import cn.vfunding.vfunding.biz.message.beanutil.MessageUtil;
import cn.vfunding.vfunding.biz.message.dao.HikesMessageMapper;
import cn.vfunding.vfunding.biz.message.model.HikesMessage;

@Service("hikesCardService")
public class HikesCardServiceImpl implements IHikesCardService{

	@Autowired
	private HikesCardMapper hikesCardMapper;
	
	@Autowired
	private HikesMessageMapper hikesMessageMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer userId) {
		return hikesCardMapper.deleteByPrimaryKey(userId);
	}

	@Override
	public int insertSelective(HikesCard record) {
		return hikesCardMapper.insertSelective(record);
	}

	@Override
	public HikesCard selectByPrimaryKey(Integer userId) {
		return hikesCardMapper.selectByPrimaryKey(userId);
	}

	@Override
	public int updateByPrimaryKeySelective(HikesCard record) {
		return hikesCardMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int insertHikesCardForActivityTwoMillons(Integer userId) {
		int i = 0;
		//加息卡
		HikesCard hc = this.hikesCardMapper.selectByPrimaryKey(userId);
		BigDecimal rate = MessageUtil.Rate_Activity_TwoMillons;
		rate.setScale(2, BigDecimal.ROUND_HALF_UP);
		if(hc==null){
			hc = new HikesCard();
			hc.setUserId(userId);
			hc.setUseRate(rate);
			hc.setNoUseRate(new BigDecimal(0));
			i = this.insertSelective(hc);
		}else{
			if(hc.getUseRate()==null){
				hc.setUseRate(new BigDecimal(0));
			}
			hc.setUseRate(hc.getUseRate().add(rate));
			i = this.hikesCardMapper.updateByPrimaryKeySelective(hc);
		}		
		if(i==0){
			throw new RuntimeException("加息卡保存失败");
		}
		//加息卡消息
		HikesMessage hm = MessageUtil.getMessageFactory().createHikesMessageForActivityTwoMillons(userId);
		i = this.hikesMessageMapper.insertSelective(hm);
		if(i==0){
			throw new RuntimeException("加息卡消息保存失败");
		}
		return i;
	}

	@Override
	public int selectUnLookCount(Integer userId) {
		// TODO Auto-generated method stub
		return this.hikesCardMapper.selectUnLookCount(userId);
	}

}
