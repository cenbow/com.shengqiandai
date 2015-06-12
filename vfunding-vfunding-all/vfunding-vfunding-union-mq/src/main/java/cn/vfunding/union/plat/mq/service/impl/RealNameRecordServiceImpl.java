package cn.vfunding.union.plat.mq.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.union.plat.mq.dao.RealNameRecordMapper;
import cn.vfunding.union.plat.mq.model.RealNameRecord;
import cn.vfunding.union.plat.mq.service.IRealNameRecordService;
import cn.vfunding.vfunding.common.module.activemq.message.UserRealNameMessage;

@Service("realNameRecordService")
public class RealNameRecordServiceImpl implements IRealNameRecordService {

	@Autowired
	private RealNameRecordMapper mapper;

	@Override
	public int insert(RealNameRecord record) {
		return this.mapper.insert(record);
	}

	@Override
	public int insertSelective(RealNameRecord record) {
		return this.mapper.insertSelective(record);
	}

	@Override
	public RealNameRecord selectByPrimaryKey(Integer id) {
		return this.mapper.selectByPrimaryKey(id);
	}

	@Override
	public RealNameRecord selectByUserId(String userId) {
		return this.mapper.selectByUserId(userId);
	}

	@Override
	public int updateByPrimaryKeySelective(RealNameRecord record) {
		return this.mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RealNameRecord record) {
		return this.mapper.updateByPrimaryKey(record);
	}

	@Override
	public int vfundingUserRealName(UserRealNameMessage message) {
		if(EmptyUtil.isNotEmpty(message)){
			RealNameRecord record=this.mapper.selectByUserId(message.getUnionUserId());
			if(EmptyUtil.isNotEmpty(record)){
				if(EmptyUtil.isEmpty(record.getStatus()) || !record.getStatus().equals("1")){
					record.setStatus("1");
					this.mapper.updateByPrimaryKey(record);
				}
			}else{
				record=new RealNameRecord();
				record.setCheckTime(new Date());
				record.setIdNumber(message.getCardId());
				record.setRealName(message.getRealName());
				record.setUserId(new Long(message.getUnionUserId()));
				this.mapper.insert(record);
			}		
		}
			
		return 0;
	}

}
