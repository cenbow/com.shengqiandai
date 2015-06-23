package cn.p2p.p2p.biz.sqdpaylog.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.p2p.p2p.biz.sqdpaylog.model.SqdPayLog;
import cn.vfunding.common.framework.utils.page.PageSearch;


@Repository("sqdPayLogMapper")
public interface SqdPayLogMapper {
    int deleteByPrimaryKey(Integer payLogId);

    int insert(SqdPayLog record);

    int insertSelective(SqdPayLog record);

    SqdPayLog selectByPrimaryKey(Integer payLogId);

    int updateByPrimaryKeySelective(SqdPayLog record);

    int updateByPrimaryKey(SqdPayLog record);

	List<SqdPayLog> selectAllPayLog(PageSearch pageSearch);
}