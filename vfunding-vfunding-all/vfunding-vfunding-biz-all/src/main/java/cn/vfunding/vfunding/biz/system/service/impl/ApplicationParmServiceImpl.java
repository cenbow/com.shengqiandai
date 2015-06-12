package cn.vfunding.vfunding.biz.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.vfunding.vfunding.biz.system.dao.ApplicationParmMapper;
import cn.vfunding.vfunding.biz.system.model.ApplicationParm;
import cn.vfunding.vfunding.biz.system.model.DataGridVO;
import cn.vfunding.vfunding.biz.system.service.ApplicationParmService;

@Service
public class ApplicationParmServiceImpl implements ApplicationParmService {

	@Autowired
	ApplicationParmMapper applicationParmMapper;

	@Override
	public int deleteByPrimaryKey(String key) {

		return applicationParmMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(ApplicationParm record) {

		return applicationParmMapper.insert(record);
	}

	@Override
	public int insertSelective(ApplicationParm record) {

		return applicationParmMapper.insertSelective(record);
	}

	@Override
	public ApplicationParm selectByPrimaryKey(String key) {

		return applicationParmMapper.selectByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKeySelective(ApplicationParm record) {

		return applicationParmMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKey(ApplicationParm record) {

		return applicationParmMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<ApplicationParm> queryAll() {

		return applicationParmMapper.selectAll();
	}

	@Override
	public int saveDataGrid(DataGridVO<JSONObject> dgo) {
		int resutlt = 0;
		if (dgo == null) {
			return resutlt;
		} else {
			List<JSONObject> list = null;
			ApplicationParm appParm = null;
			if (dgo.getInserted() != null) {
				list = dgo.getInserted();
				for (int i = 0; i < list.size(); i++) {
					appParm = JSONObject.parseObject(list
							.get(i).toString(), ApplicationParm.class);
					resutlt += applicationParmMapper.insert(appParm);
				}
			}
			if (dgo.getUpdated() != null) {
				list = dgo.getUpdated();
				for (int i = 0; i < list.size(); i++) {
					appParm = JSONObject.parseObject(list
							.get(i).toString(), ApplicationParm.class);
					resutlt += applicationParmMapper
							.updateByPrimaryKey(appParm);
				}
			}
			if (dgo.getDeleted() != null) {
				list = dgo.getDeleted();
				for (int i = 0; i < list.size(); i++) {
					appParm = JSONObject.parseObject(list
							.get(i).toString(), ApplicationParm.class);
					resutlt += applicationParmMapper
							.deleteByPrimaryKey(appParm.getKey());
				}
			}
			return resutlt;
		}
	}

}
