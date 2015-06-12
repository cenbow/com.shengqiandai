package cn.vfunding.vfunding.biz.prize.dao;

import java.util.List;
import java.util.Map;

import cn.vfunding.vfunding.biz.prize.model.PrizeRepository;
import cn.vfunding.vfunding.biz.prize.vo.PrizeRepositoryVO;

public interface PrizeRepositoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PrizeRepository record);

    int insertSelective(PrizeRepository record);

    PrizeRepository selectByPrimaryKey(Integer id);
    
    List<PrizeRepository> selectByEntity(PrizeRepository entity);
    
    //我的中奖记录
    List<PrizeRepositoryVO> selectMyPrizeChoose(PrizeRepository entity);
    
    //我的中奖记录_第二季
    List<PrizeRepositoryVO> selectMyPrizeChooseForSeasonTwo(PrizeRepository entity);

    int updateByPrimaryKeySelective(PrizeRepository record);

    int updateByPrimaryKey(PrizeRepository record);
    
    //内部用户抽中某奖品的总数
    Integer selectInternalUserPrizeCount(Integer prizeId);
    
    //外部用户抽中某奖品的总数
    Integer selectExternalUserPrizeCount(Integer prizeId);
    
    //抽中某奖品的总数
    Integer selectPrizeCount(Integer prizeId);
    
    //获奖名单
    List<PrizeRepositoryVO> selectPrizeChoose(Map<String,Integer> map);
    
    //获奖名单_第二季
    List<PrizeRepositoryVO> selectPrizeChooseForSeasonTwo(Map<String,Integer> map);
    
}