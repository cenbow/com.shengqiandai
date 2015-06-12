package cn.vfunding.vfunding.biz.thirdplat.service;

import cn.vfunding.vfunding.biz.thirdplat.model.JjCard;

public interface IJjCardService {
	int deleteByPrimaryKey(Integer id);

    int insertSelective(JjCard record);

    JjCard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JjCard record);
    
    /**
     * 
     * <p>gamePeriod info JjCard.java</p>
     *
     * wang.zeyan 2015年4月24日
     * @param gamePeriod
     * @return
     */
    JjCard selectByNoSend(Integer gamePeriod);
}
