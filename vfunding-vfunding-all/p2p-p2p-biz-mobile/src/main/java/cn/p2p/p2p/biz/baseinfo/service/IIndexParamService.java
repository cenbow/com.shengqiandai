package cn.p2p.p2p.biz.baseinfo.service;

import cn.p2p.p2p.biz.baseinfo.model.IndexParam;

public interface IIndexParamService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(IndexParam record);

    IndexParam selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IndexParam record);

}