package cn.vfunding.vfunding.biz.redislog.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.database.redis.RedisManager;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.ObjectId;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.redislog.model.AgainLog;
import cn.vfunding.vfunding.biz.redislog.service.IAgainLogService;

import com.alibaba.fastjson.JSON;

@Service("againLogService")
public class AgainLogServiceImpl implements IAgainLogService {

	@Autowired
	private RedisManager redisManager;
	
	

	public RedisManager getRedisManager() {
		return redisManager;
	}

	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}

	@Override
	public Long deleteByPrimaryKey(String id) throws Exception {
		if (this.redisManager.isSetValue("againLogs", id)) {
			this.redisManager.deleteFromSetByStoreKeyAndValue("againLogs", id);
		}
		if (this.redisManager.isSetValue("againLogsByHand", id)) {
			this.redisManager.deleteFromSetByStoreKeyAndValue("againLogsByHand",
					id);
		}
		return this.redisManager.delete(id);
	}

	@Override
	public void insertNewLog(AgainLog record) throws Exception {
		record.setId(ObjectId.get());
		record.setNextTime(new Date());
		Set<String> ids = new HashSet<String>();
		ids.add(record.getId());
		this.redisManager.saveSet("againLogs", ids);
		this.redisManager.saveString(record.getId(), JSON.toJSONString(record));
	}

	@Override
	public AgainLog selectByPrimaryKey(String id) throws Exception {
		String str = this.redisManager.getStringValueByKey(id);
		return JSON.parseObject(str, AgainLog.class);
	}

	@Override
	public void updateByPrimaryKey(AgainLog record) throws Exception {
		this.redisManager.saveString(record.getId(), JSON.toJSONString(record));
		if (record.getStuts().intValue() == 1) {
			Set<String> ids = new HashSet<String>();
			ids.add(record.getId());
			// 添加到手动执行集合中
			this.redisManager.saveSet("againLogsByHand", ids);
			// 从自动执行集合中删除
			this.redisManager.deleteFromSetByStoreKeyAndValue("againLogs",
					record.getId());
		}
	}

	@Override
	public List<AgainLog> selectByAuto() throws Exception {
		List<AgainLog> result = new ArrayList<AgainLog>();
		Set<String> ids = this.redisManager
				.getAllValuesFromSetByStoreKey("againLogs");
		if (EmptyUtil.isNotEmpty(ids)) {
			Iterator<String> iter = ids.iterator();
			while (iter.hasNext()) {
				AgainLog log = this.selectByPrimaryKey(iter.next());
				result.add(log);
			}
		} else {
			result = null;
		}

		return result;
	}

	@Override
	public List<AgainLog> selectByHandleListPage(PageSearch search)
			throws Exception {
		List<AgainLog> result = new ArrayList<AgainLog>();
		Set<String> ids = this.redisManager
				.getAllValuesFromSetByStoreKey("againLogsByHand");
		if (EmptyUtil.isNotEmpty(ids)) {
			Iterator<String> iter = ids.iterator();
			while (iter.hasNext()) {
				AgainLog log = this.selectByPrimaryKey(iter.next());
				result.add(log);
			}
		} else {
			result = null;
		}

		return result;
	}

}
