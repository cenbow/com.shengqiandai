package cn.vfunding.union.plat.mq.service;

import cn.vfunding.union.plat.mq.model.ProfitRecordInfo;
import cn.vfunding.union.plat.mq.model.ProfitStatisticsVO;

public interface IProfitRecordInfoService {

	int deleteByPrimaryKey(String id);

    int insert(ProfitRecordInfo record);

    int insertSelective(ProfitRecordInfo record);

    ProfitRecordInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ProfitRecordInfo record);

    int updateByPrimaryKey(ProfitRecordInfo record);   
    /**
     * 从投资详细表创建月报表数据并保存
     * @param vo
     * @return
     */
    void selectAndSaveMouthData(ProfitStatisticsVO vo);
}
