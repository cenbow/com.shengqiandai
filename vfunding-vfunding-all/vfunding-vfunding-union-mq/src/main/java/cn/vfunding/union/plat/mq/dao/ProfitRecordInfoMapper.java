package cn.vfunding.union.plat.mq.dao;

import java.util.List;

import cn.vfunding.union.plat.mq.model.ProfitRecordInfo;
import cn.vfunding.union.plat.mq.model.ProfitStatisticsVO;

public interface ProfitRecordInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProfitRecordInfo record);

    int insertSelective(ProfitRecordInfo record);

    ProfitRecordInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ProfitRecordInfo record);

    int updateByPrimaryKey(ProfitRecordInfo record);
    /**
     * 从投资详细表创建月报表数据
     * @param vo
     * @return
     */
    List<ProfitRecordInfo> createMouthData(ProfitStatisticsVO vo);
}