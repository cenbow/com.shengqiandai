package cn.vfunding.vfunding.biz.system.service;

import java.util.List;

import cn.vfunding.vfunding.biz.system.model.AttestationType;

public interface IAttestationTypeServicve {
    int deleteByPrimaryKey(Integer typeId);

    int insert(AttestationType record);

    int insertSelective(AttestationType record);

    AttestationType selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(AttestationType record);

    int updateByPrimaryKey(AttestationType record);
    /**
     * 获取所有数据
     * @return
     * @author liuyijun
     */
    List<AttestationType> selectAll();
}
