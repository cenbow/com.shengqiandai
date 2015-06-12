package cn.p2p.p2p.biz.baseinfo.dao;

import cn.p2p.p2p.biz.baseinfo.model.IndexParam;

public interface IndexParamMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IndexParam record);

    int insertSelective(IndexParam record);

    IndexParam selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IndexParam record);

    int updateByPrimaryKey(IndexParam record);
}