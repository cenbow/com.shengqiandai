package cn.vfunding.vfunding.biz.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.system.dao.AttestationTypeMapper;
import cn.vfunding.vfunding.biz.system.model.AttestationType;
import cn.vfunding.vfunding.biz.system.service.IAttestationTypeServicve;

@Service("attestationTypeServicve")
public class AttestationTypeServicveImpl implements IAttestationTypeServicve {

	@Autowired
	private AttestationTypeMapper attestationTypeMapper;

	private static List<AttestationType> allData;

	@Override
	public int deleteByPrimaryKey(Integer typeId) {
		allData = null;
		return 0;
	}

	@Override
	public int insert(AttestationType record) {
		allData = null;
		return this.attestationTypeMapper.insert(record);
	}

	@Override
	public int insertSelective(AttestationType record) {
		allData = null;
		return this.attestationTypeMapper.insertSelective(record);
	}

	@Override
	public AttestationType selectByPrimaryKey(Integer typeId) {
		return this.attestationTypeMapper.selectByPrimaryKey(typeId);
	}

	@Override
	public int updateByPrimaryKeySelective(AttestationType record) {
		allData = null;
		return this.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AttestationType record) {
		allData = null;
		return this.updateByPrimaryKey(record);
	}

	private void init() {
		allData = this.attestationTypeMapper.selectAll();
	}

	@Override
	public List<AttestationType> selectAll() {
		if (EmptyUtil.isEmpty(allData)) {
			init();
		}
		return allData;
	}

}
