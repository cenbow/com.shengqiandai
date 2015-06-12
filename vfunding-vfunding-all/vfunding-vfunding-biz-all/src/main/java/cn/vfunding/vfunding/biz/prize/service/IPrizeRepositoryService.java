package cn.vfunding.vfunding.biz.prize.service;

import java.util.List;
import java.util.Map;

import cn.vfunding.vfunding.biz.prize.model.PrizeRepository;
import cn.vfunding.vfunding.biz.prize.vo.PrizeRepositoryVO;

public interface IPrizeRepositoryService {
    int deleteByPrimaryKey(Integer id);

    int insert(PrizeRepository record);

    int insertSelective(PrizeRepository record);

    PrizeRepository selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PrizeRepository record);

    int updateByPrimaryKey(PrizeRepository record);
    
    List<PrizeRepositoryVO> selectMyPrizeChoose(PrizeRepository entity);
    
    List<PrizeRepositoryVO> selectPrizeChoose(Map<String,Integer> map);
    
    List<PrizeRepositoryVO> selectMyPrizeChooseForSeasonTwo(PrizeRepository entity);
    
    List<PrizeRepositoryVO> selectPrizeChooseForSeasonTwo(Map<String,Integer> map);
}
