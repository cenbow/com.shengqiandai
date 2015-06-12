package cn.vfunding.vfunding.biz.system.dao;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.system.model.Attestation;

public interface AttestationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Attestation record);

    int insertSelective(Attestation record);

    Attestation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Attestation record);

    int updateByPrimaryKeyWithBLOBs(Attestation record);

    int updateByPrimaryKey(Attestation record);
    
    /**
     * 根据用户ID分页查询
     * @param userId
     * @return
     * 2014年4月24日
     * liuyijun
     */
    List<Attestation> selectByUserIdListPage(PageSearch pageSearch);
}