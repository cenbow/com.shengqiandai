package cn.vfunding.vfunding.biz.system.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.database.redis.RedisManager;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.system.dao.ThirdSynRecordMapper;
import cn.vfunding.vfunding.biz.system.model.ThirdSynRecord;
import cn.vfunding.vfunding.biz.system.service.IThirdSynRecordService;

import com.alibaba.fastjson.JSON;

@Service("thirdSynRecordService")
public class ThirdSynRecordServiceImpl implements IThirdSynRecordService {

	@Autowired
	private ThirdSynRecordMapper thirdSynRecordMapper;

	@Autowired(required = false)
	private RedisManager redisManager;


	@Override
	public int insert(ThirdSynRecord record) {
		if (record.getStatus().intValue() == 1) {
			Set<String> ids = new HashSet<String>();
			ids.add(record.getId());
			try {
				this.redisManager.saveSet("thirdSyns", ids);
				this.redisManager.saveString(record.getId(),
						JSON.toJSONString(record));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this.thirdSynRecordMapper.insert(record);
	}

	

	@Override
	public void updateByPrimaryKey(ThirdSynRecord record) {
		try {
			if (record.getStatus().intValue() != 1) {
				if (this.redisManager.isSetValue("thirdSyns", record.getId())) {
					this.redisManager.deleteFromSetByStoreKeyAndValue(
							"thirdSyns", record.getId());
					this.redisManager.delete(record.getId());
					this.thirdSynRecordMapper.updateByPrimaryKey(record);
				}
			} else {
              this.redisManager.saveString("thirdSyns", JSON.toJSONString(record));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ThirdSynRecord> selectNeedSyn() {
		List<ThirdSynRecord> result = new ArrayList<ThirdSynRecord>();
		try {
			Set<String> ids = new HashSet<String>();
			ids.add(redisManager.getStringValueByKey("thirdSyns"));
			for (String string : ids) {
				String str = this.redisManager.getStringValueByKey(string);
				if (EmptyUtil.isNotEmpty(str)) {
					ThirdSynRecord r = JSON.parseObject(str,
							ThirdSynRecord.class);
					if (EmptyUtil.isNotEmpty(r)) {
						result.add(r);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
