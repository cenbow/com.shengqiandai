package cn.vfunding.vfunding.biz.thirdplat.dao;

import cn.vfunding.vfunding.biz.thirdplat.model.ThirdTendercheck;

public interface ThirdTendercheckMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ThirdTendercheck record);

    int insertSelective(ThirdTendercheck record);

    ThirdTendercheck selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ThirdTendercheck record);

    int updateByPrimaryKey(ThirdTendercheck record);
}