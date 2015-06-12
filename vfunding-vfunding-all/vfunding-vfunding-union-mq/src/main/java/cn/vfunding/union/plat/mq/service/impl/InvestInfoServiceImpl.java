package cn.vfunding.union.plat.mq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.ObjectId;
import cn.vfunding.union.plat.mq.dao.InvestInfoMapper;
import cn.vfunding.union.plat.mq.model.InvestInfo;
import cn.vfunding.union.plat.mq.service.IInvestInfoService;
import cn.vfunding.vfunding.common.module.activemq.message.InvestMessage;

@Service("investInfoService")
public class InvestInfoServiceImpl implements IInvestInfoService {

	@Autowired
	private InvestInfoMapper investInfoMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		return this.investInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(InvestInfo record) {
		return this.investInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(InvestInfo record) {
		return this.investInfoMapper.insertSelective(record);
	}

	@Override
	public InvestInfo selectByPrimaryKey(String id) {
		return this.investInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(InvestInfo record) {
		return this.investInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(InvestInfo record) {
		return this.investInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public int insertByInvestMessage(InvestMessage message) {
	    if(EmptyUtil.isNotEmpty(message)){
	    	InvestInfo info=this.createInfoByMessage(message);
	    	if(EmptyUtil.isNotEmpty(info)){
	    		return this.investInfoMapper.insert(info);
	    	}
	    }
		return 0;
	}
	
	
	private InvestInfo createInfoByMessage(InvestMessage message){
		InvestInfo info=new InvestInfo();
		info.setId(ObjectId.get());
		info.setInvestId(message.getUserId());
		info.setInvestMoney(message.getMoney());
		info.setInvestName(message.getUserName());		
		info.setInvestTime(message.getInvestTime());       					
		info.setPeriods(message.getPeriods());
		info.setProductId(message.getProductId());
		info.setUnionUserId(message.getUnionUserId());
		float lv=0.0f;
		if(message.getPeriods()==1){
			lv=(float) (0.0106/12);
		}else if(message.getPeriods()==2){
			lv=(float) (0.0081/12);
		}else if(message.getPeriods()>=3){
			lv=(float) (0.0072/12);
		}
		info.setInterests(message.getMoney()*lv);
		return info;
	}

}
