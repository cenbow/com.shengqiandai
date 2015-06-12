package cn.vfunding.vfunding.biz.system.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.system.model.AttestationType;

public interface AttestationTypeMapper {
    int deleteByPrimaryKey(Integer typeId);

    int insert(AttestationType record);

    int insertSelective(AttestationType record);

    AttestationType selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(AttestationType record);

    int updateByPrimaryKey(AttestationType record);
    /**
     * 加载所有
     * @return
     * @author liuyijun
     */
    List<AttestationType> selectAll();
}